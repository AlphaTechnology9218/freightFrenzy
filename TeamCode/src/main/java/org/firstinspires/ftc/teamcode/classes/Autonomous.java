package org.firstinspires.ftc.teamcode.classes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Autonomous extends LinearOpMode {
    private DcMotor  mFL, mBL, mFR, mBR;
    private int pFL, pBL, pFR, pBR;

    @Override
    public void runOpMode() {
        mFL = hardwareMap.get(DcMotor.class, "motor mFL");
        mBL = hardwareMap.get(DcMotor.class, "motor mBL");
        mFR = hardwareMap.get(DcMotor.class, "motor mFR");
        mBR = hardwareMap.get(DcMotor.class, "motor mBR");

        resetEncoders();

        waitForStart();

        while (opModeIsActive()) {
            moveRobot(100, 100, 100, 100, 1); // move forward 100 rotations
        }
    }

    public void resetEncoders() {
        mFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Move robot using encoders while motors aren't busy
     * @param tLF - left front target
     * @param tBL - back left target
     * @param tRF - right front target
     * @param tBR - back right target
     * @param vel - velocity
     */
    public void moveRobot(int tLF, int tBL, int tRF, int tBR, double vel) {
        pBR += tRF;
        pBL += tLF;
        pFR += tBR;
        pFL += tBL;

        mFL.setTargetPosition(pFL);
        mBL.setTargetPosition(pBL);
        mFR.setTargetPosition(pFR);
        mBR.setTargetPosition(pBR);

        mFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        mFL.setPower(vel);
        mBL.setPower(vel);
        mFR.setPower(vel);
        mBR.setPower(vel);

        while (opModeIsActive() && mFL.isBusy() && mBL.isBusy() && mFR.isBusy()
                && mBR.isBusy()) { idle(); }
    }
}