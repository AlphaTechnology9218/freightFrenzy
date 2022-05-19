package org.firstinspires.ftc.teamcode.initial_tests.tele_operate;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Intake Test", group = "claw tests")
public class ClawIntake extends LinearOpMode {
    private DcMotor intake, clawL, clawR;

    @Override
    public void runOpMode() throws InterruptedException {
        intake = hardwareMap.get(DcMotor.class, "Intake");
        clawL = hardwareMap.get(DcMotor.class, "clawR");
        clawR = hardwareMap.get(DcMotor.class, "clawL");

        intake.setDirection(DcMotor.Direction.FORWARD);
        clawL.setDirection(DcMotor.Direction.REVERSE);
        clawR.setDirection(DcMotor.Direction.FORWARD);

        clawL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        clawL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

       waitForStart();

        boolean full = false;
        boolean dir = true;

        while(opModeIsActive()) {
            // INTAKE
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

            // GO
            if(gamepad1.x && dir) {
                clawL.setTargetPosition(1);
                clawR.setTargetPosition(1);

                clawL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                clawR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                clawL.setPower(0.7);
                clawR.setPower(0.7);

                sleep(500);
                dir = false;
            }

            // BACK
            if(gamepad1.y && !dir) {
                clawL.setTargetPosition(0);
                clawR.setTargetPosition(0);

                clawL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                clawR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                clawR.setPower(-0.1);
                clawL.setPower(-0.1);

                dir = true;
            }
            if (!dir && ( clawL.getTargetPosition() < 5 || clawR.getTargetPosition() < 5)) {
                clawL.setPower(0);
                clawR.setPower(0);
                clawL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                clawR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
        }
    }
    //https://www.ctrlaltftc.com/practical-examples/ftc-motor-control
}
