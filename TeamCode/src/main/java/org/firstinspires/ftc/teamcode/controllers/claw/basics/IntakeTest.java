package org.firstinspires.ftc.teamcode.controllers.claw.basics;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot_components.MotorComponentsClaw;

@Disabled
@TeleOp(name = "Intake", group = "Controllers")
public class IntakeTest extends LinearOpMode {
    MotorComponentsClaw motors = new MotorComponentsClaw();

    @Override
    public void runOpMode() {
        motors.init(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            motors.take.setPower(1);
        }
    }
}
