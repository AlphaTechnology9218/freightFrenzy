package org.firstinspires.ftc.teamcode.controllers.claw.basics;

import static org.firstinspires.ftc.teamcode.odometry.control.DriveConstants.MOTOR_VELO_PID;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.abilities.SleepRobot;
import org.firstinspires.ftc.teamcode.odometry.control.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.robot_components.MotorComponentsClaw;

import java.util.concurrent.TimeUnit;

@Config
@TeleOp(name = "Excavator Control", group = "Controllers")
public class ExcavatorBasic extends LinearOpMode {
    MotorComponentsClaw motors = new MotorComponentsClaw();
    ElapsedTime timer = new ElapsedTime();
    DcMotor mD;

    public boolean act = true;
    // Excavator weight: 362 g

    double power = 0.0;           // motor strength
    public static int tP = 80;   // set point
    public static double feedF = 0.326; // feed-forward
    double integralSum;        // integral sum
    public static double Kp = 0.1;
    public static double Ki = 0.1;
    public static double Kd = 0.4;
    double lastError;

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        motors.init(hardwareMap);

        mD = hardwareMap.get(DcMotor.class, "motorDuck");
        mD.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        drive.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, MOTOR_VELO_PID);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //angularClock();
        while (opModeIsActive()) {
            telemetry.addData("Pos", motors.cL.getCurrentPosition());
            telemetry.addData("Set", tP);
            telemetry.update();

            takeDownDuck();
            manualControl();
            Frames();
            power = controlLoop(tP, motors.cL.getCurrentPosition());
            runPower();
            intake();

            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y * 0.5,
                            -gamepad1.left_stick_x * 0.5,
                            -gamepad1.right_stick_x * 0.5
                    )
            );
        }
    }

    public void manualControl(){
        if(gamepad2.dpad_down){
            tP+=1;
        }
        else if(gamepad2.dpad_up){
            tP-=1;
        }


        if(tP < 6)
        {
            tP = 3;
        }

        if(tP > 220)
        {
            tP = 220;
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

    public void Frames()
    {
        if(gamepad2.x)// ground
        {
            tP = 216;
        }

        if(gamepad2.a)// 1° level
        {
            tP = 200;
        }

        if(gamepad2.b)// 2° level
        {
            tP = 180;
        }

        if(gamepad2.y)// 3° level
        {
            tP = 160;
        }
    }

    public void intake() { //Intaker Control
        if(gamepad2.left_bumper){ // intake
            motors.take.setPower(1);
        }
        if (gamepad2.right_bumper){ // outtake
            motors.take.setPower(-1);
        }
        if(gamepad2.dpad_left || gamepad2.dpad_right){ // notake
            motors.take.setPower(0);
        }
    }


    public void takeDownDuck() {
        SleepRobot sleep = new SleepRobot();
        telemetry.addData("Time", timer.time());
        float power_blue = 0.4F;
        float power_red = -0.4F;


        if (gamepad1.x && act) { // Take Duck Down
            timer.startTime();
            mD.setPower(power_blue);
            for (power_blue = 0.4F; power_blue <= 0.7; power_blue += 0.1) {
                if (timer.time(TimeUnit.SECONDS) == 0.2) {
                    mD.setPower(power_blue);
                    timer.reset();
                }
            }

            act = false;
            sleep.robotSleeping(500);
        } else if (gamepad1.x) {
            mD.setPower(0);
            act = true;
            timer.reset();
            sleep.robotSleeping(500);
        }

        if (gamepad1.b && act) { // Take Duck Down
            timer.startTime();
            //mD.setPower(-0.7);
            mD.setPower(power_red);
            for (power_red = -0.4F; power_red >= -0.7; power_red -= 0.1) {
                if (timer.time(TimeUnit.SECONDS) == 0.2) {
                    mD.setPower(power_red);
                    timer.reset();
                }
            }

            act = false;
            sleep.robotSleeping(500);
        } else if (gamepad1.b) {
            mD.setPower(0);
            act = true;
            timer.reset();
            sleep.robotSleeping(500);
        }
    }
}
