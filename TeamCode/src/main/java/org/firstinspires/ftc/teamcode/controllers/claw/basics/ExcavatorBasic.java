package org.firstinspires.ftc.teamcode.controllers.claw.basics;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot_components.MotorComponentsClaw;

@Disabled
@TeleOp(name = "Excavator Control", group = "Controllers")
public class ExcavatorBasic extends LinearOpMode {
    MotorComponentsClaw motors = new MotorComponentsClaw();
    ElapsedTime timer = new ElapsedTime();

    double command = 0.0;
    int tP = 0;
    double feedF;
    double integralSum;
    double Kp;
    double Ki;
    double Kd;
    double lastError;

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        while (opModeIsActive()) {
            armUp();
            armDown();
            controLoop(tP, motors.cL.getCurrentPosition());
            runPower();
        }
    }

    public void armUp(){
        if(gamepad1.y){
            tP = 60;
        }
    }

    public void armDown(){
        if(gamepad1.b){
            tP = 5;
        }
    }

    public double controLoop(double SP, double PV){
        timer.reset();
        double error = SP - PV;
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) * timer.seconds();
        lastError = error;

        timer.reset();

        return (error * Kp) + (integralSum * Ki) + (derivative * Kd) + (Math.cos(SP) * feedF);
    }

    public void runPower(){
        motors.cL.setPower(command);
        motors.cR.setPower(command);
    }
}
