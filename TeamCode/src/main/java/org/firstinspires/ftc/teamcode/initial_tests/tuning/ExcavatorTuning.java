package org.firstinspires.ftc.teamcode.initial_tests.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot_components.MotorComponentsClaw;

@Autonomous(name = "EscavatorPID", group = "Controllers")
public class ExcavatorTuning extends LinearOpMode {
    MotorComponentsClaw motors = new MotorComponentsClaw();
    ElapsedTime timer = new ElapsedTime();

    double power = 0.0;     // motor strength
    int tP = 12;            // set point
    double feedF = -0.362;  // feed-forward
    double integralSum;    // integral sum
    public static double Kp = 0.1;
    public static double Ki = 0.1;
    public static double Kd = 0.4;
    double lastError;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        motors.init(hardwareMap);

        while(opModeIsActive()){
            telemetry.addData("Pos", motors.cL.getCurrentPosition());
            telemetry.addData("Set", tP);
            telemetry.update();

            power = controlLoop(tP, motors.cL.getCurrentPosition());
            runPower();
        }
    }

    public double controlLoop(double SP, double PV) {
        timer.reset();
        double error = SP - PV;
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) * timer.seconds();
        lastError = error;

        timer.reset();

        return (error * Kp) + (integralSum * Ki) + (derivative * Kd) + (Math.cos(SP) * feedF);
    }

    public void runPower() {
        motors.cL.setPower(power);
        motors.cR.setPower(power);
    }
}
