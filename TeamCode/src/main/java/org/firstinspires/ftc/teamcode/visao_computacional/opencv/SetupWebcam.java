package org.firstinspires.ftc.teamcode.visao_computacional.opencv;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

@Autonomous(name = "OpenCV Setup Webcam", group = "Computer Vision")
public class SetupWebcam extends LinearOpMode {
    @Override
    public void runOpMode() {
        initializeWebcam();
    }

    public void initializeWebcam() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        WebcamName robotWebcam = hardwareMap.get(WebcamName.class, "Robot Webcam");

        // With live preview
        OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(robotWebcam,
                cameraMonitorViewId);

        // Without a live preview
        // OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(robotWebcam);
    }
}
