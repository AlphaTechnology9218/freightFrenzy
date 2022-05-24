package org.firstinspires.ftc.teamcode.controllers.claw.basics;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot_components.HexClawComponents;

@TeleOp(name = "Claw Hex Teleop", group = "Controllers")
public class ClawHexTeleop extends OpMode {
    HexClawComponents motors = new HexClawComponents();
    ElapsedTime timer = new ElapsedTime();

    double iS;
    double lE;
    public static double p;
    public static double i;
    public static double d;
    double power;
    int reference;

    @Override
    public void init() {
        motors.init(hardwareMap);
    }

    @Override
    public void loop() {
        jaw();
        power = pidHex(reference, motors.mCH2.getCurrentPosition());
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

    public double pidHex(double sP, double pV){
        timer.reset();
        double error = sP - pV;
        iS += error * timer.seconds();
        double derivative = (error - lE) * timer.seconds();
        lE = error;

        timer.reset();

        return (error * p) + (iS * i) + (derivative * d);
    }
}
