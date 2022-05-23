package org.firstinspires.ftc.teamcode.initial_tests.tele_operate;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp(name = "Intake Test", group = "claw tests")
public class ClawIntake extends LinearOpMode {
    private DcMotor intake, clawL, clawR;

    @Override
    public void runOpMode() throws InterruptedException {
        intake = hardwareMap.get(DcMotor.class, "Intake");
        clawL = hardwareMap.get(DcMotor.class, "clawR");
        clawR = hardwareMap.get(DcMotor.class, "clawL");

        intake.setDirection(DcMotor.Direction.FORWARD);
        clawL.setDirection(DcMotor.Direction.FORWARD);
        clawR.setDirection(DcMotor.Direction.FORWARD);

        clawL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        clawL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

       waitForStart();

        boolean full = false;
        boolean dir  = false;

        while(opModeIsActive()) {

            telemetry.addData("LPos", clawL.getCurrentPosition());
            telemetry.addData("RPos", clawR.getCurrentPosition());

            telemetry.update();

            // INTAKE
            if(gamepad1.y && !full) {
                intake.setPower(1);
                if(gamepad1.y) {
                    intake.setPower(0);
                    full = true;
                }
            }
            else if(gamepad1.y && full) {
                intake.setPower(-1);
                if (gamepad1.y) {
                    intake.setPower(0);
                    full = false;
                }
            }

            // GO
            if(gamepad1.x) {
                clawL.setTargetPosition(40);
                clawR.setTargetPosition(40);

                clawL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                clawR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                clawL.setPower(0.8);
                clawR.setPower(0.8);
                dir = true;
            }

            // BACK
            else if(gamepad1.b) {
                clawL.setTargetPosition(0);
                clawR.setTargetPosition(0);

                clawL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                clawR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                clawR.setPower(-0.3);
                clawL.setPower(-0.3);
                dir = false;

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
