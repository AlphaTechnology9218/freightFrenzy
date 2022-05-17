package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

@Autonomous
public class BlueBoxYCbCrChannel extends OpMode {
    OpenCvCamera camera;
    public int detectionResult;

    @Override
    public void init() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        camera.setPipeline(new BlueBoxVision());

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);
                camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {
                telemetry.addData("Status", "An error occurred with OpenCV!");
            }
        });
    } // Initialize the cellphone camera

    @Override
    public void loop() {

    }

    class BlueBoxVision extends OpenCvPipeline {
        Mat YCbCr  = new Mat();
        Mat outPut = new Mat();

        Mat leftMat;
        Mat rightMat;
        Mat centerMat;

        double leftValue;
        double rightValue;
        double centerValue;

        private int width = 320;

        Scalar rectColor = new Scalar(255.0, 0.0, 0.0);

        @Override
        public Mat processFrame(Mat input) {

            Imgproc.cvtColor(input, YCbCr, Imgproc.COLOR_RGB2YCrCb);
            telemetry.addLine("pipeline is running");

            Rect leftRect = new Rect(1, 80, 80, 110);
            Rect rightRect = new Rect(160, 80, 80, 110);
            Rect centerRect = new Rect(80, 80, 80, 110);

            input.copyTo(outPut);
            Imgproc.rectangle(outPut, leftRect, rectColor, 2);
            Imgproc.rectangle(outPut, rightRect, rectColor, 2);
            Imgproc.rectangle(outPut, centerRect, rectColor, 2);

            leftMat = YCbCr.submat(leftRect);
            rightMat = YCbCr.submat(rightRect);
            centerMat = YCbCr.submat(centerRect);

            double leftTotal   = Core.sumElems(leftMat).val[2];
            double rightTotal  = Core.sumElems(rightMat).val[2];
            double centerTotal = Core.sumElems(centerMat).val[2];

            Core.extractChannel(leftMat, leftMat, 1);
            Core.extractChannel(rightMat, rightMat, 1);
            Core.extractChannel(centerMat, centerMat, 1);

            Scalar leftValueAvg = Core.mean(leftMat);
            Scalar rightValueAvg = Core.mean(rightMat);
            Scalar centerValueAvg = Core.mean(centerMat);

            leftValue = leftValueAvg.val[0];
            rightValue = rightValueAvg.val[0];
            centerValue = centerValueAvg.val[0];




            Mat thresh = new Mat();
            Mat edges = new Mat();
            Imgproc.Canny(thresh, edges, 100, 300);

            List<MatOfPoint> contours = new ArrayList<>();
            Mat hierarchy = new Mat();
            Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

            MatOfPoint2f[] contoursPoly  = new MatOfPoint2f[contours.size()];
            Rect[] boundRect = new Rect[contours.size()];
            for (int i = 0; i < contours.size(); i++) {
                contoursPoly[i] = new MatOfPoint2f();
                Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), contoursPoly[i], 3, true);
                boundRect[i] = Imgproc.boundingRect(new MatOfPoint(contoursPoly[i].toArray()));
            }

            // Iterate and check whether the bounding boxes
            // cover left and/or right side of the image
            double left_x = 0.25 * width;
            double right_x = 0.75 * width;
            boolean left = false; // true if regular stone found on the left side
            boolean right = false; // "" "" on the right side
            for (int i = 0; i != boundRect.length; i++) {
                if (boundRect[i].x < left_x)
                    left = true;
                if (boundRect[i].x + boundRect[i].width > right_x)
                    right = true;

                // draw red bounding rectangles on mat
                // the mat has been converted to HSV so we need to use HSV as well
                Imgproc.rectangle(YCbCr, boundRect[i], new Scalar(0.5, 76.9, 89.8));
            }







            if ((leftTotal > rightTotal) && (leftTotal > centerTotal)) {
                telemetry.addData("Status", "LEFT");
            }
            if ((rightTotal > leftTotal) && (rightTotal > centerTotal)) {
                telemetry.addData("Status", "RIGHT");
            }
            if ((centerTotal > leftTotal) && (centerTotal > rightTotal)) {
                telemetry.addData("Status", "CENTER");
            }

            //TODO: Extract the light conditions from the equation

            return(outPut);
        }
    }
}

