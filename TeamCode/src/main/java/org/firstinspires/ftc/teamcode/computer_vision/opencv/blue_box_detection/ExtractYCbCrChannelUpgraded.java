package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

@Autonomous
public class ExtractYCbCrChannelUpgraded extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        OpenCvCamera camera;
        BlueBoxVision pipeline;

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        pipeline = new BlueBoxVision();
        camera.setPipeline(pipeline);

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

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Status", pipeline.getResult());
            telemetry.addData("Result", pipeline.result);
            telemetry.update();
        }


    } // Start Camera and display results

    public static class BlueBoxVision extends OpenCvPipeline {
        boolean result;

        /* RGB Color constants for boxes*/
        static final Scalar RED = new Scalar(255, 0, 0);
        static final Scalar GREEN = new Scalar(0, 255, 0);

        static final Point ANALISE_BOX_REGION = new Point(40, 40);

        static final int REGION_WIDTH = 20;
        static final int REGION_HEIGHT = 20;

        static final int THRESHOLD = 140;

        Point pointA = new Point(
                ANALISE_BOX_REGION.x,
                ANALISE_BOX_REGION.y
        );

        Point pointB = new Point(
                ANALISE_BOX_REGION.x + REGION_WIDTH,
                ANALISE_BOX_REGION.y + REGION_HEIGHT
        );

        Mat YCbCr = new Mat();
        Mat outPut = new Mat();
        Mat regionCb;

        int average;

        void inputTab(Mat input) {
            Imgproc.cvtColor(input, YCbCr, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCbCr, outPut, 2);
        }

        @Override
        public void init(Mat firstFrame) {
            inputTab(firstFrame);

            regionCb = outPut.submat(new Rect(pointA, pointB));
        }


        @Override
        public Mat processFrame(Mat input) {

            average = (int) Core.mean(regionCb).val[1];

            Imgproc.rectangle(input, pointA, pointB, RED, 2);    // Draw Red
            Imgproc.rectangle(input, pointA, pointB, GREEN, -1); // Full fill Green

            if (average > THRESHOLD) {
                result = true;
            } else {
                result = false;
            }
            return input;
        }

        public int getResult() {
            return average;
        }
    }
}