package org.firstinspires.ftc.teamcode.computer_vision.opencv.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

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

@Autonomous
public class OpenCVTest extends OpMode {
    OpenCvCamera camera;

    @Override
    public void init() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        camera.setPipeline(new TestPipeline());
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

    @Override
    public void loop() {

    }

    class TestPipeline extends OpenCvPipeline {
        Mat YCbCr    = new Mat();
        Mat outPut   = new Mat();
        Mat leftMat;
        Mat rightMat;

        double leftValue;
        double rightValue;

        Scalar rectColor = new Scalar(255.0, 0.0, 0.0);

        @Override
        public Mat processFrame(Mat input) {

            Imgproc.cvtColor(input, YCbCr, Imgproc.COLOR_RGB2YCrCb);
            telemetry.addLine("pipeline is running");

            Rect leftRect   = new Rect(1, 1, 106, 240);
            Rect rightRect  = new Rect(106, 1, 106, 240);
            Rect centerRect = new Rect(212, 1, 106, 240);

            input.copyTo(outPut);
            Imgproc.rectangle(outPut, leftRect, rectColor, 2);
            Imgproc.rectangle(outPut, rightRect, rectColor, 2);
            Imgproc.rectangle(outPut, centerRect, rectColor, 2);

            leftMat = YCbCr.submat(leftRect);
            rightMat = YCbCr.submat(rightRect);

            Core.extractChannel(leftMat, leftMat, 1);
            Core.extractChannel(rightMat, rightMat, 1);

            Scalar leftValueAvg = Core.mean(leftMat);
            Scalar rightValueAvg = Core.mean(rightMat);

            leftValue = leftValueAvg.val[0];
            rightValue = rightValueAvg.val[0];

            if(leftValue > rightValue) {
                telemetry.addLine("The object is on the left");
            } else if (rightValue > leftValue) {
                telemetry.addLine("The object is on the right");
            } else {
                telemetry.addLine("There are no objects");
            }

            return(outPut);
        }
    }
}