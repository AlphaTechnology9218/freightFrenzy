package org.firstinspires.ftc.teamcode.initial_tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.odometry.control.DriveConstants;

@TeleOp(name = "Odometry Test", group = "Tests")
public class OdometryTest extends LinearOpMode {
    static DriveConstants con = new DriveConstants();
    DcMotorEx motor;
    double currentVelocity;
    double maxVelocity = 0.0;



    static final double P = con.F * 0.1;
    static final double I = 0.1 * P;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "CoreHex");
        waitForStart();
        while (opModeIsActive()) {
            currentVelocity = motor.getVelocity();

            if (currentVelocity > maxVelocity) {
                maxVelocity = currentVelocity;
            }

            telemetry.addData("current velocity", currentVelocity);
            telemetry.addData("maximum velocity", maxVelocity);
            telemetry.update();
        }
    }
}
