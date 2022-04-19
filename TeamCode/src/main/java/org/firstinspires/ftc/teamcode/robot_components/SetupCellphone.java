package org.firstinspires.ftc.teamcode.robot_components;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

public class SetupCellphone extends LinearOpMode {
    int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

    public OpenCvCamera camera = OpenCvCameraFactory.getInstance().createInternalCamera
            (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

    private boolean atv = true;

    @Override
    public void runOpMode() {
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                camera.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);
                /*
                 * Commonly Supported Resolutions:
                 * 320x240
                 * 640x480
                 * 1280x720
                 * 1920x1080
                 * */
                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {
                telemetry.addData("Status: ", "An error occurred with OpenCV!");
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
        if (gamepad2.y && atv) {
            camera.pauseViewport();
            sleep(200);
            atv = false;
        } else if (gamepad2.a && !atv) {
            camera.resumeViewport();
            sleep(200);
            atv = true;
        }
    }
}
