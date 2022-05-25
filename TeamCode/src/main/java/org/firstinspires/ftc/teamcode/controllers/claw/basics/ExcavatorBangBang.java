package org.firstinspires.ftc.teamcode.controllers.claw.basics;

import static org.firstinspires.ftc.teamcode.odometry.control.DriveConstants.MOTOR_VELO_PID;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.odometry.control.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.robot_components.MotorComponentsClaw;

@Disabled
@TeleOp(name = "Bang Bang Arm", group = "controllers")
public class ExcavatorBangBang extends LinearOpMode {
    MotorComponentsClaw motors = new MotorComponentsClaw();

    double sP = 5;
    double pV;
    double error = 0;

    @Override
    public void runOpMode() {
        motors.init(hardwareMap);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);


        waitForStart();
        motors.cL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drive.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, MOTOR_VELO_PID);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (opModeIsActive()){
            telemetry.addData("Pos", motors.cL.getCurrentPosition());
            telemetry.update();
            motorBang();
            positionControl();
            intake();

            drive.setWeightedDrivePower(
                    new Pose2d(
                            gamepad1.left_stick_y,
                            gamepad1.left_stick_x,
                            gamepad1.right_stick_x
                    )
            );
        }
    }

    public void motorBang(){
        motors.cL.setPower(error);
        motors.cR.setPower(error);

        if(2 < Math.abs(error)){
            motors.cL.setPower(0.2);
        };
    }

    public void positionControl(){
        pV = motors.cL.getCurrentPosition();
        sP = 5;
        if(gamepad1.x){
            sP = 120;
        }
        if(gamepad1.b){
            sP = 2;
        }
        error = sP - pV;
    }

    public void intake(){
        if(gamepad1.left_bumper){
            motors.take.setPower(1);
        }
        if (gamepad1.right_bumper){
            motors.take.setPower(-1);
        }
        if(gamepad1.y){
            motors.take.setPower(0);
        }
    }
}
