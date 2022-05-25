package org.firstinspires.ftc.teamcode.robot_components;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element.HSVColorFilter;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "OpenCV Setup Webcam", group = "Robot Components")
public class SetupWebcam extends LinearOpMode {
    HSVColorFilter colorFilter = new HSVColorFilter();
    public int cameraMonitorViewId;
    public WebcamName robotWebcam;
    public OpenCvCamera camera;
    /*********************************************************************************************
     * colorFilter - object to access the pipeline for team element detection                    *
     * cameraMonitorViewId - live camera preview to display on the Robot Controller screen       *
     * robotWebcam - robot webcam                                                                *
     * camera - create a supported camera                                                        *
     *********************************************************************************************/

    @Override
    public void runOpMode() {
        startWebcam(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            double left = colorFilter.getLeftValue();
            double right = colorFilter.getRightValue();
            double center = colorFilter.getCenterValue();

            if ((left > right) && (left > center)) { telemetry.addLine("Left");
            } else if ((center > left) && (center > right)) { telemetry.addLine("Right");
            } else if ((right > center) && (right > left)) { telemetry.addLine("Center");
            } else { telemetry.addLine("None");
            }
        }
    }

    public void startWebcam(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        robotWebcam = hardwareMap.get(WebcamName.class, "Robot Webcam");
        camera = OpenCvCameraFactory.getInstance().createWebcam(robotWebcam, cameraMonitorViewId);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened() {
                camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                camera.startStreaming(160, 120, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {
                telemetry.addData("Status", "An error occurred with OpenCV!");
            }
        });

        camera.setPipeline(colorFilter);
    }
}