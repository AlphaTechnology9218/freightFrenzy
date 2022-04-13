package org.firstinspires.ftc.teamcode.initial_tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.locomotion.hardware.RobotEncoders;

@Autonomous(name = "Test Autonomous", group = "Autonomous")
public class InitialAutoTests extends LinearOpMode {
    RobotEncoders robot = new RobotEncoders(); // access robot components configuration

    @Override
    public void runOpMode() {
        robot.runOpMode();

        telemetry.addData(">", "Press Play to Begin");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                moveStorageUnit();
            }
            telemetry.update();
        }
    }

    public void moveStorageUnit(){
        robot.moveRobot(500, 500,500,500, 1);
    }
}
