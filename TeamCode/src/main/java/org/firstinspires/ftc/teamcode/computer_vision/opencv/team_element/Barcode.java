package org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name = "Barcode", group = "Computer Vision")
public class Barcode extends LinearOpMode {
    OpenCvCamera camera;
    YCbCrChannelTest vision = new YCbCrChannelTest();

    public int robot = vision.position;

    @Override
    public void runOpMode() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        camera.setPipeline(vision);

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


        if (robot == 1) {
            telemetry.addData("Status", "Left");
        } else if (robot == 2) {
            telemetry.addData("Status", "Right");
        } else if (robot == 3) {
            telemetry.addData("Status", "Center");
        } else {
            telemetry.addData("Status", "None");
        }

        waitForStart();

        while (opModeIsActive()) {
            telemetry.update();

            if ((vision.getLeftValue() > vision.getRightValue()) && (vision.getLeftValue() > vision.getCenterValue())) {
                telemetry.addData("Status", "Left");
            } else if ((vision.getRightValue() > vision.getLeftValue()) && (vision.getRightValue() > vision.getCenterValue())) {
                telemetry.addData("Status", "Right");
            } else if ((vision.getCenterValue() > vision.getRightValue()) && (vision.getCenterValue() > vision.getLeftValue())) {
                telemetry.addData("Status", "Center");
            } else {
                telemetry.addData("Status", "None");
            }
         }
    }
}
