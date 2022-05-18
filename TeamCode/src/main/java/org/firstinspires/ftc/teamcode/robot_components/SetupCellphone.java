package org.firstinspires.ftc.teamcode.robot_components;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.abilities.SleepRobot;
import org.firstinspires.ftc.teamcode.computer_vision.opencv.basics.HSVColorFilter;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name = "OpenCV Setup Camera", group = "Robot Components")
public class SetupCellphone extends OpMode {
    OpenCvCamera camera;
    HSVColorFilter pipeline;
    private boolean act = true;
    /**********************************************************************************************
     * cameraMonitorViewId - live camera preview to display on the Robot Controller screen        *
     * camera - create a supported camera                                                         *
     * act - active and detective the camera                                                      *
     **********************************************************************************************/

    public void init(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        camera.setPipeline(pipeline);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);
                camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
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
                telemetry.addData("Status", "An error occurred with OpenCV!");
            }
        });
    }

    @Override
    public void init() {
        telemetry.addData("Status", "OpMode is activated");
    }

    @Override
    public void loop() { stopCellphoneView(); }

    public void stopCamera() { camera.closeCameraDeviceAsync(() -> {}); }

    public void stopCellphoneView() {
        SleepRobot sleep = new SleepRobot();
        if (gamepad1.y && act) {
            camera.pauseViewport();
            sleep.robotSleeping(500);
            act = false;
        } else if (gamepad1.y) {
            camera.resumeViewport();
            sleep.robotSleeping(500);
            act = true;
        }
    }
}
