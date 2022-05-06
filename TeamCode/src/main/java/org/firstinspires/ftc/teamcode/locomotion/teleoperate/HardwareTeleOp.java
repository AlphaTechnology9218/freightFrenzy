package org.firstinspires.ftc.teamcode.locomotion.teleoperate;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "TeleOp Robot Components", group = "Tele Operate")
public class HardwareTeleOp extends OpMode {
    public DcMotor mFL;
    public DcMotor mBL;
    public DcMotor mFR;
    public DcMotor mBR;
    /**************************************************************************
     * mFL - front left motor                                                 *
     * mBL - back left motor                                                  *
     * mFR - front right motor                                                *
     * mBR - back right motor                                                 *
     **************************************************************************/
public HardwareTeleOp(HardwareMap hardwareMap){

    mFL = hardwareMap.get(DcMotor.class, "motorFL");
    mBL = hardwareMap.get(DcMotor.class, "motorBL");
    mFR = hardwareMap.get(DcMotor.class, "motorFR");
    mBR = hardwareMap.get(DcMotor.class, "motorBR");

    mFL.setDirection(DcMotor.Direction.REVERSE);
    mBL.setDirection(DcMotor.Direction.REVERSE);
    mFR.setDirection(DcMotor.Direction.FORWARD);
    mBR.setDirection(DcMotor.Direction.FORWARD);
}

    /**
     * Set power for each robot motor
     *
     * @param powLF - front left motor power
     * @param powLB - back left motor power
     * @param powRF - front right motor power
     * @param powRB - back right motor power
     */
    public void motorPower(float powLF, float powLB, float powRF, float powRB) {
        mFL.setPower(powLF);
        mBL.setPower(powLB);
        mFR.setPower(powRF);
        mBR.setPower(powRB);
    }

    @Override
    public void init() {
    }

    @Override
    public void loop() {

    }
}