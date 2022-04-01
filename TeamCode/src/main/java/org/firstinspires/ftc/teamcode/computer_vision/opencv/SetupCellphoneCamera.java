package org.firstinspires.ftc.teamcode.computer_vision.opencv;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@TeleOp(name = "Setup Cellphone Camera", group = "Computer Vision")
public class SetupCellphoneCamera extends LinearOpMode {
    int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
            ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

    OpenCvCamera camera = OpenCvCameraFactory.getInstance().createInternalCamera
            (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

    @Override
    public void runOpMode() {
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                /*
                 * Commonly Supported Resolutions:
                 * 320x240
                 * 640x480
                 * 1280x720
                 * 1920x1080
                 * */
                camera.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode)
            {
                telemetry.addData("Status:", "An error occurred with OpenCV!");
            }
        });
        if (opModeIsActive()) {
            telemetry.addData("Status: ", "Op Mode is Activated");
            while (opModeIsActive()) {
                stopCellphoneView();
            }
            telemetry.update();
        }
    }
    public void stopCellphoneView() {
        if (gamepad2.y) {
            camera.pauseViewport();
        } else if (gamepad2.a) {
            camera.resumeViewport();
        }
    }
}
