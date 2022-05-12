package org.firstinspires.ftc.teamcode.robot_components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MotorComponents {
    public DcMotor mFL, mBL, mFR, mBR, mDDR, mDDL;
    /**************************************************************************
     * mFL - front left motor                                                 *
     * mBL - back left motor                                                  *
     * mFR - front right motor                                                *
     * mBR - back right motor                                                 *
     * mDDR - motors to take the duck down right                              *
     * mDDL - motors to take the duck down left                               *
     * ************************************************************************/

    public void init(HardwareMap hardwareMap) {
        /* Motors for robot's locomotion */
        mFL = hardwareMap.get(DcMotor.class, "motorFL");
        mBL = hardwareMap.get(DcMotor.class, "motorBL");
        mFR = hardwareMap.get(DcMotor.class, "motorFR");
        mBR = hardwareMap.get(DcMotor.class, "motorBR");

        mFL.setDirection(DcMotor.Direction.FORWARD);
        mBL.setDirection(DcMotor.Direction.FORWARD);
        mFR.setDirection(DcMotor.Direction.REVERSE);
        mBR.setDirection(DcMotor.Direction.REVERSE);

        /* Motors for duck down control*/
        mDDR = hardwareMap.get(DcMotor.class, "motorDDR");
        mDDL = hardwareMap.get(DcMotor.class, "motorDDL");

        mDDR.setDirection(DcMotor.Direction.REVERSE);
        mDDL.setDirection(DcMotor.Direction.REVERSE);
    }

    public void motorPower(float powFL, float powBL, float powFR, float powBR) {
        mFL.setPower(powFL);
        mBL.setPower(powBL);
        mFR.setPower(powFR);
        mBR.setPower(powBR);
    }
}