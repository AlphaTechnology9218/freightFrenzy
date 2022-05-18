package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
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
public class ExtractYCbCrChannel extends LinearOpMode {
    OpenCvCamera camera;

    @Override
    public void runOpMode() throws InterruptedException {
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
    }


    public static class BlueBoxVision extends OpenCvPipeline {

        Mat YCbCr  = new Mat();
        Mat outPut = new Mat();

        Mat leftMat;
        Mat rightMat;
        Mat centerMat;

        double leftValue;
        double rightValue;
        double centerValue;

        Scalar rectColor = new Scalar(255.0, 0.0, 0.0);
        Telemetry telemetry;

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

