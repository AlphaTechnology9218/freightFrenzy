package org.firstinspires.ftc.teamcode.robot_components;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
@Autonomous(name = "Setup Cellphone Camera", group = "Robot Components")
public class SetupCellphone extends LinearOpMode {
    int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
            ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    public OpenCvCamera camera = OpenCvCameraFactory.getInstance().createInternalCamera
            (OpenCvInternalCamera.CameraDirection.BACK);
    private boolean act = true;
    /*******************************
     * cameraMonitorViewId - live camera preview to display on the Robot Controller screen       *
     * camera - create a supported camera                                                        *
     * act - active and detective the camera                                                     *
     *******************************/
    @Override
    public void runOpMode() {
        waitForStart();
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
            while (opModeIsActive()) stopCellphoneView();
            telemetry.update();
        }
    }
    public void stopCellphoneView() {
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