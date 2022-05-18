package org.firstinspires.ftc.teamcode.initial_tests.tele_operate;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Intake Test", group = "claw tests")
public class ClawIntake extends LinearOpMode {
    private DcMotor intake, claw;
    private double position;

    @Override
    public void runOpMode() throws InterruptedException {
        intake = hardwareMap.get(DcMotor.class, "Intake");
        claw = hardwareMap.get(DcMotor.class, "claw");

        intake.setDirection(DcMotor.Direction.FORWARD);
        claw.setDirection(DcMotor.Direction.REVERSE);

        claw.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        claw.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        while(!isStarted()){
            position = claw.getCurrentPosition();
            telemetry.addData("position", position);
            telemetry.update();
        }

        boolean dir = true;
        boolean full = false;

        while(opModeIsActive()) {
            if(gamepad1.y && !full) {
                intake.setPower(1);
                if(gamepad1.y) {
                    intake.setPower(0);
                    full = true;
                }
            }
            else if(gamepad1.a && full) {
                intake.setPower(-1);
                if (gamepad1.a) {
                    intake.setPower(0);
                    full = false;
                }
            }

            if(gamepad1.x) {
                dir = true;
                claw.setPower(1);
                claw.setTargetPosition(200);
                claw.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            else if(gamepad1.b) {
                dir = false;
                claw.setPower(-0.1);
                claw.setTargetPosition(0);
                claw.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if (!dir && ( claw.getTargetPosition() < 5 || claw.getTargetPosition() < 5)) {
                claw.setPower(0);
                claw.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
            telemetry.addData("position", position);
            telemetry.update();
        }
    }
    //https://www.ctrlaltftc.com/practical-examples/ftc-motor-control
}
