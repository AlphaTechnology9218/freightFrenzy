package org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
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
public class HSVColorFilter extends OpMode {
    OpenCvCamera camera;

    @Override
    public void init() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        camera.setPipeline(new HSVVision());
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
    } // initialize the camera

    @Override
    public void loop() {

    }

    class HSVVision extends OpenCvPipeline {
        Mat mat  = new Mat();
        Mat outPut = new Mat();
        Mat leftMat;
        Mat rightMat;
        Mat centerMat;

        Scalar rectColor = new Scalar(255.0, 0.0, 0.0);

        @Override
        public Mat processFrame(Mat input) {

            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGBA2RGB);
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV);

            // Lower and upper bound for PINK
            Scalar lowerBound = new Scalar(331.0 / 2, 30, 30);
            Scalar upperBound = new Scalar(345.0 / 2, 255, 255);

            // Return Binary Mask
            Core.inRange(mat, lowerBound, upperBound, mat);

            //Rect leftRect = new Rect(1, 60, 80, 120);
            //Rect centerRect = new Rect(80, 60, 80, 120);
            //Rect rightRect = new Rect(160, 60, 80, 120);

            //input.copyTo(outPut);
            //Imgproc.rectangle(outPut, leftRect, rectColor, 2);
            //Imgproc.rectangle(outPut, rightRect, rectColor, 2);
            //Imgproc.rectangle(outPut, centerRect, rectColor, 2);

            //leftMat = mat.submat(leftRect);
            //rightMat = mat.submat(rightRect);
            //centerMat = mat.submat(centerRect);

            // Remove Noise
            Imgproc.morphologyEx(mat, mat, Imgproc.MORPH_OPEN, new Mat());
            Imgproc.morphologyEx(mat, mat, Imgproc.MORPH_CLOSE, new Mat());

            // Find Contours
            List<MatOfPoint> contours = new ArrayList<>();
            Imgproc.findContours(mat, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

            // Draw Contours
            Imgproc.drawContours(input, contours, -1, new Scalar(255, 0, 0));

            //double centerValue = Math.round(Core.mean(mat).val[2] / 255);
            //mat.release();

            return mat;
        }
    }
}
