package org.firstinspires.ftc.teamcode.robot_components;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "OpenCV Setup Webcam", group = "Robot Components")
public class SetupWebcam extends LinearOpMode {
    public WebcamName robotWebcam;
    public int cameraMonitorViewId;
    OpenCvCamera camera;
    public boolean act = true;
    /*********************************************************************************************
     * cameraMonitorViewId - live camera preview to display on the Robot Controller screen       *
     * robotWebcam - robot webcam                                                                *
     * camera - create a supported camera                                                        *
     * act - active and detective the camera                                                     *
     *********************************************************************************************/

    @Override
    public void runOpMode() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Robot Webcam");
        OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                camera.startStreaming(160, 120, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode)
            {
                telemetry.addData("Status", "An error occurred with OpenCV!");
            }
        });

        waitForStart();

        if (opModeIsActive()) {
            telemetry.addData("Status", "Op Mode is Activated");
            while (opModeIsActive()) {
                stopWebcamView();
            }
            telemetry.update();
        }
    }
    public void stopWebcamView() {
        if (gamepad2.y && act) {
            camera.pauseViewport();
            sleep(200);
            act = false;
        } else if (gamepad2.a && !act) {
            camera.resumeViewport();
            sleep(200);
            act = true;
        }
    }
}