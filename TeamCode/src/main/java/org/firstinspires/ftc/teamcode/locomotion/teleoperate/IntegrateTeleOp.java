package org.firstinspires.ftc.teamcode.locomotion.teleoperate;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
@TeleOp(name = "Integrated TeleOp", group = "Tele Operate")
public class IntegrateTeleOp extends OpMode {
    public DcMotor mFL, mBL, mFR, mBR;
    /**************************************************************************
     * mFL - front left motor                                                 *
     * mBL - back left motor                                                  *
     * mFR - front right motor                                                *
     * mBR - back right motor                                                 *
     **************************************************************************/
    private final ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
        telemetry.addData("Status", "TeleOp Iniciado");

        // change robot's components names
        mFL = hardwareMap.get(DcMotor.class, "mFL");
        mBL = hardwareMap.get(DcMotor.class, "mBL");
        mFR = hardwareMap.get(DcMotor.class, "mFR");
        mBR = hardwareMap.get(DcMotor.class, "mBR");

        mFL.setDirection(DcMotor.Direction.REVERSE);
        mBL.setDirection(DcMotor.Direction.REVERSE);
        mFR.setDirection(DcMotor.Direction.FORWARD);
        mBR.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void start() { runtime.reset(); }

    @Override
    public void loop() {
        telemetry.addData("Status", "TeleOp Executing: " + runtime.toString());

        axisXY();
        axisXYAdjusts();
        round();
        axisZ();
        roundX();

        telemetry.update();
    }


    private void axisXY() {
        motorPower(-gamepad1.left_stick_x, gamepad1.left_stick_x, gamepad1.left_stick_x, -gamepad1.left_stick_x);
        motorPower(-gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y);
    }

    private void axisXYAdjusts() {
        if (gamepad1.dpad_up) {
            motorPower(0.75f, 0.75f, 0.75f, 0.75f);
        }
        if (gamepad1.dpad_down) {
            motorPower(-0.75f, -0.75f, -0.75f, -0.75f);
        }
        if (gamepad1.dpad_right) {
            motorPower(0.75f, -0.75f, -0.75f, 0.75f);
        }
        if (gamepad1.dpad_left) {
            motorPower(-0.75f, 0.75f, 0.75f, -0.75f);
        }
    }

    // reverse round
    private void round() {
        if (gamepad1.left_bumper) {
            motorPower(-1, -1, -1, -1);
        }
        if (gamepad1.right_bumper) {
            motorPower(1, 1, -1, -1);
        }
    }

    private void axisZ() {
        motorPower(0, gamepad1.left_trigger, gamepad1.right_trigger, 0);
        motorPower(gamepad1.right_trigger, 0, 0, gamepad1.right_trigger);
    }

    private void roundX() {
        if (gamepad1.b) {
            motorPower(1,1,0,0);
        }
        if (gamepad1.x) {
            motorPower(0,0,1,1);
        }
    }

    /**
     * @param powFL - front left motor power
     * @param powBL - back left motor power
     * @param powFR - front right motor power
     * @param powBR - back right motor power
     */
    void motorPower(float powFL, float powBL, float powFR, float powBR) {
        mFL.setPower(powFL);
        mBL.setPower(powBL);
        mFR.setPower(powFR);
        mBR.setPower(powBR);
    }

    @Override
    public void stop() {
        telemetry.addData("Status", "TeleOp Finished");
    }
}
