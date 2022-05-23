package org.firstinspires.ftc.teamcode.controllers.claw.basics;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot_components.HexClawComponents;
import org.firstinspires.ftc.teamcode.robot_components.MotorComponentsClaw;

@TeleOp(name = "Claw Hex Teleop", group = "Controllers")
public class ClawHexTeleop extends OpMode {
    HexClawComponents motors = new HexClawComponents();

    @Override
    public void init() {
        motors.init(hardwareMap);
    }

    @Override
    public void loop() {
        jaw();
        telemetry.addData("Position =", motors.sPin.getPosition());
        telemetry.update();
    }

    public void jaw(){
        if(gamepad1.x){
            motors.sPin.setPosition(0);
        }
        if(gamepad1.b){
            motors.sPin.setPosition(60);
        }
    }
}
