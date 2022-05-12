package org.firstinspires.ftc.teamcode.initial_tests.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name = "Autonomous complete Test", group = "Autonomous Tests")
public class AutonomousComplete extends LinearOpMode {

    private int pFL, pBL, pFR, pBR;
    /**************************************************************************
     * pFL - front left motor position                                        *
     * pBL - back left motor position                                         *
     * pFR - front right motor position                                       *
     * pBR - back right motor position                                        *
     **************************************************************************/
    private DcMotor mFL, mBL, mFR, mBR;

    @Override
    public void runOpMode() {
        /* Motors for robot's locomotion */
        mFL = hardwareMap.get(DcMotor.class, "motorFL");
        mBL = hardwareMap.get(DcMotor.class, "motorBL");
        mFR = hardwareMap.get(DcMotor.class, "motorFR");
        mBR = hardwareMap.get(DcMotor.class, "motorBR");

        mFL.setDirection(DcMotor.Direction.REVERSE);
        mBL.setDirection(DcMotor.Direction.REVERSE);
        mFR.setDirection(DcMotor.Direction.FORWARD);
        mBR.setDirection(DcMotor.Direction.FORWARD);

        while(opModeIsActive()){
            moveRobot(500, 500,500,500, 1);
        }

    }

    public void moveRobot(int tLF, int tBL, int tRF, int tBR, double vel) {
        mFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        pBR += tBR;
        pBL += tBL;
        pFR += tRF;
        pFL += tLF;

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
