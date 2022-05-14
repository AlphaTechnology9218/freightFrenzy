package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot_components.SetupCellphone;

@Autonomous(name = "OpenCV Robot Locomotion", group = "Computer Vision")
public class LocomotionFromResult extends LinearOpMode {
    BasicBlueBoxDetection boxDetection = new BasicBlueBoxDetection();
    SetupCellphone camera = new SetupCellphone();

    @Override
    public void runOpMode() {
        waitForStart();
        TargetPosition targetPosition = boxDetection.getTargetPosition();
        camera.stopCamera();

        switch(targetPosition) {
            case LEFT:
                assert true;
            case RIGHT:
                assert true;
            case CENTER:
                assert true;
        }

        while (opModeIsActive()) {
            telemetry.addData("Status", "Autonomous from detection running");
            telemetry.update();
        }
    }
}