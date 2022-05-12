package org.firstinspires.ftc.teamcode.locomotion.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

@Disabled
@Autonomous(name = "Locomotion Encoders", group = "Hardware Configuration")
public class MotorEncodersSetup extends LinearOpMode {
    private int pFL, pBL, pFR, pBR;
    /**************************************************************************
     * pFL - front left motor position                                        *
     * pBL - back left motor position                                         *
     * pFR - front right motor position                                       *
     * pBR - back right motor position                                        *
     **************************************************************************/
    private DcMotor mFL, mBL, mFR, mBR;

    public void runOpMode(HardwareMap hardwareMap) {
        /* Motors for robot's locomotion */
        mFL = hardwareMap.get(DcMotor.class, "motorFL");
        mBL = hardwareMap.get(DcMotor.class, "motorBL");
        mFR = hardwareMap.get(DcMotor.class, "motorFR");
        mBR = hardwareMap.get(DcMotor.class, "motorBR");

        mFL.setDirection(DcMotor.Direction.FORWARD);
        mBL.setDirection(DcMotor.Direction.FORWARD);
        mFR.setDirection(DcMotor.Direction.REVERSE);
        mBR.setDirection(DcMotor.Direction.REVERSE);
    }

    public void setupEncoders(){
        mFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Move robot using encoders while motors aren't busy
     * @param tFL - front left target
     * @param tBL - back left target
     * @param tFR - front right target
     * @param tBR - back right target
     * @param vel - velocity
     */

    public void moveRobot(int tFL, int tBL, int tFR, int tBR, double vel) {

        pBR -= tBR;
        pBL -= tBL;
        pFR += tFR;
        pFL += tFL;

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

    @Override
    public void runOpMode() {

    }
}