package org.firstinspires.ftc.teamcode.robot_components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MotorComponents {
    public DcMotor mFL, mBL, mFR, mBR, mDDR, mDDL, take, cL, cR;
    /**************************************************************************
     * mFL  - front left motor                                                *
     * mBL  - back left motor                                                 *
     * mFR  - front right motor                                               *
     * mBR  - back right motor                                                *
     * mDDR - motors to take the duck down right                              *
     * mDDL - motors to take the duck down left                               *
     * take - intake motor                                                    *
     * cL   - left arm motor                                                  *
     * cR   - right arm motor                                                 *
     **************************************************************************/

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

        /* Motors for duck down control */
        mDDR = hardwareMap.get(DcMotor.class, "motorDDR");
        mDDL = hardwareMap.get(DcMotor.class, "motorDDL");

        mDDR.setDirection(DcMotor.Direction.FORWARD);
        mDDL.setDirection(DcMotor.Direction.FORWARD);

        /* Motors for intake and arm */
        take = hardwareMap.get(DcMotor.class, "Intake");
        cL = hardwareMap.get(DcMotor.class, "clawR");
        cR = hardwareMap.get(DcMotor.class, "clawL");

        take.setDirection(DcMotor.Direction.FORWARD);
        cL.setDirection(DcMotor.Direction.FORWARD);
        cR.setDirection(DcMotor.Direction.FORWARD);

        cL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        cR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        cL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void motorPower(float powFL, float powBL, float powFR, float powBR) {
        mFL.setPower(powFL);
        mBL.setPower(powBL);
        mFR.setPower(powFR);
        mBR.setPower(powBR);
    }
}