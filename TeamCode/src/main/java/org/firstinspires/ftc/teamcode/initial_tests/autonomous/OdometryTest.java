package org.firstinspires.ftc.teamcode.initial_tests.autonomous;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.odometry.control.DriveConstants;
import org.firstinspires.ftc.teamcode.odometry.control.SampleMecanumDrive;

@TeleOp(name = "Odometers Test", group = "Autonomous Tests")
public class OdometryTest extends LinearOpMode {
    DcMotorEx motor;
    double currentVelocity;
    double maxVelocity = 0.0;


    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        while (opModeIsActive()) {
            currentVelocity = motor.getVelocity();

            if (currentVelocity > maxVelocity) {
                maxVelocity = currentVelocity;
            }

            simpleTest();

            telemetry.addData("current velocity", currentVelocity);
            telemetry.addData("maximum velocity", maxVelocity);
            telemetry.update();
        }
    }

    void straightFowardTest(){

    }

    void splineTest(){

    }

    void simpleTest(){
        PIDCoefficients coefficients = new PIDCoefficients(DriveConstants.MOTOR_VELO_PID.p,
                DriveConstants.MOTOR_VELO_PID.i, DriveConstants.MOTOR_VELO_PID.d);
        PIDFController control = new PIDFController(coefficients);
        if(gamepad1.a){
            control.setTargetPosition(150);
        }
    }
}
