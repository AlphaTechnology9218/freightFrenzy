package org.firstinspires.ftc.teamcode.locomotion.hardware;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Locomotion Encoders", group = "Hardware Configuration")
@Disabled
public class RobotEncoders extends LinearOpMode {
    public DcMotor mFL, mBL, mFR, mBR;
    private int pFL, pBL, pFR, pBR;
    /**************************************************************************
     * mFL - front left motor                                                 *
     * mBL - back left motor                                                  *
     * mFR - front right motor                                                *
     * mBR - back right motor                                                 *
     *                                                                        *
     * pFL - front left motor position                                        *
     * pBL - back left motor position                                         *
     * pFR - front right motor position                                       *
     * pBR - back right motor position                                        *                                                                    *
     **************************************************************************/

    @Override
    public void runOpMode() {
        mFL = hardwareMap.get(DcMotor.class, "motorFE");
        mBL = hardwareMap.get(DcMotor.class, "motorTE");
        mFR = hardwareMap.get(DcMotor.class, "motorFD");
        mBR = hardwareMap.get(DcMotor.class, "motorTD");

        mFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        mFL.setDirection(DcMotor.Direction.REVERSE);
        mBL.setDirection(DcMotor.Direction.REVERSE);
        mBR.setDirection(DcMotor.Direction.FORWARD);
        mFR.setDirection(DcMotor.Direction.FORWARD);
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

        while (opModeIsActive() && mFL.isBusy() && mBL.isBusy() && mFR.isBusy() && mBR.isBusy()) {
            idle();
        }
    }
}
