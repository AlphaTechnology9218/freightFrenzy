package org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box.TargetLocation;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

@Autonomous(name = "YCrCb Channel", group = "Computer Vision")
public class YCrCbChannel extends OpMode {
    public int targetPos;
    OpenCvCamera camera;
    TargetLocation target;

    @Override
    public void init() {
        init(hardwareMap);
    }

    @Override
    public void loop() {

    }

    public void init(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        camera.setPipeline(new YCrCbVision());
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
    } // Initialize the camera

    class YCrCbVision extends OpenCvPipeline {
        Mat YCrCb  = new Mat();
        Mat outPut = new Mat();
        Mat leftMat;
        Mat rightMat;
        Mat centerMat;

        double leftValue;
        double rightValue;
        double centerValue;

        Scalar rectColor = new Scalar(255.0, 0.0, 0.0);

        @Override
        public Mat processFrame(Mat input) {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            telemetry.addLine("Pipeline is running");

            Rect leftRect = new Rect(1, 60, 80, 120);
            Rect centerRect = new Rect(80, 60, 80, 120);
            Rect rightRect = new Rect(160, 60, 80, 120);

            input.copyTo(outPut);
            Imgproc.rectangle(outPut, leftRect, rectColor, 2);
            Imgproc.rectangle(outPut, rightRect, rectColor, 2);
            Imgproc.rectangle(outPut, centerRect, rectColor, 2);

            leftMat = YCrCb.submat(leftRect);
            rightMat = YCrCb.submat(rightRect);
            centerMat = YCrCb.submat(centerRect);

            Core.extractChannel(leftMat, leftMat, 1);
            Core.extractChannel(rightMat, rightMat, 1);
            Core.extractChannel(centerMat, centerMat, 1);

            Scalar leftAvg = Core.mean(leftMat);
            Scalar rightAvg = Core.mean(rightMat);
            Scalar centerAvg = Core.mean(centerMat);

            leftValue = Math.round(leftAvg.val[0]);
            rightValue = Math.round(rightAvg.val[0]);
            centerValue = Math.round(centerAvg.val[0]);

            telemetry.addData("Left", leftValue);
            telemetry.addData("Right", rightValue);
            telemetry.addData("Center", centerValue);

            if ((leftValue > rightValue) && (leftValue > centerValue)) {
                telemetry.addLine("The Object is on the Left");
                targetPos = 0;
            } else if ((rightValue > leftValue) && (rightValue > centerValue)) {
                telemetry.addLine("The Object ios on the Right");
                targetPos = 1;
            } else if ((centerValue > leftValue) && (centerValue > rightValue)) {
                telemetry.addLine("The object is on the center");
                targetPos = 2;
            } else {
                telemetry.addLine("There are no objects");
            }
            return (outPut);
        }
    }

    public TargetLocation getTarget() {
        return target;
    }
}
