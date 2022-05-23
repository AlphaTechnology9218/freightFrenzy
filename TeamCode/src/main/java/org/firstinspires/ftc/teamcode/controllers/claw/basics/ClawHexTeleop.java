package org.firstinspires.ftc.teamcode.controllers.claw.basics;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot_components.MotorComponentsClaw;

@TeleOp(name = "Claw Hex Teleop", group = "Controllers")
public class ClawHexTeleop extends OpMode {
    MotorComponentsClaw motors = new MotorComponentsClaw();

    @Override
    public void init() {
        motors.init(hardwareMap);
    }

    @Override
    public void loop() {

    }


}
