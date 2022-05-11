package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot_components.SetupWebcam;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "Display Box Detection Results", group = "Computer Vision")
public class LocomotionFromResult extends LinearOpMode {
    BasicBlueBoxDetection boxDetection = new BasicBlueBoxDetection();
    public boolean result = boxDetection.seeing;

    @Override
    public void runOpMode() {

        waitForStart();
        while (opModeIsActive()) {

            if (result) assert true;
            else telemetry.addData("Status", "No blue box detected");

            telemetry.update();
        }
    }
}