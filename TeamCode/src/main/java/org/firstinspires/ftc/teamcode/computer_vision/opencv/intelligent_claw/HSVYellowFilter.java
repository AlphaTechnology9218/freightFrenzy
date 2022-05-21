package org.firstinspires.ftc.teamcode.computer_vision.opencv.intelligent_claw;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element.YCbCrChannel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
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

@Autonomous(name = "HSV Yellow Filter", group = "Computer Vision")
public class HSVYellowFilter extends OpMode {
    OpenCvCamera camera;

    @Override
    public void init() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        camera.setPipeline(new YellowVision());

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
    } // camera initialization

    @Override
    public void loop() {

    }

    class YellowVision extends OpenCvPipeline {
        Mat mat  = new Mat();
        Mat mask = new Mat();

        Scalar HOT_PINK = new Scalar(196, 23, 112);

        @Override
        public Mat processFrame(Mat input) {
            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGBA2RGB);
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV);

            // Lower and upper bound for YELLOW
            Scalar lowerBound = new Scalar(51.0 / 2, 20, 20);
            Scalar upperBound = new Scalar(60.0 / 2, 255, 255);

            // Return Binary Mask
            Core.inRange(mat, lowerBound, upperBound, mat);

            // Remove Noise
            Imgproc.morphologyEx(mat, mat, Imgproc.MORPH_OPEN, new Mat());
            Imgproc.morphologyEx(mat, mat, Imgproc.MORPH_CLOSE, new Mat());

            // Find Contours
            List<MatOfPoint> contours = new ArrayList<>();
            Imgproc.findContours(mask, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

            // Draw Contours
            Imgproc.drawContours(input, contours, -1, new Scalar(255, 0, 0));

            return mat;
        }
    }
}