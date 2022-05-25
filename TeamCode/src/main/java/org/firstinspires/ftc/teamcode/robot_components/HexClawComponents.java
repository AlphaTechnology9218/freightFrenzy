package org.firstinspires.ftc.teamcode.robot_components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HexClawComponents {
    public DcMotor mCH1, mCH2;
    public Servo sHan, sPin;
    /**************************************************************************
     * mCH1 - Core Hex motor N° 1                                             *
     * mCH2 - Core Hex motor N° 2                                             *
     * sHan - Handle Servo                                                    *
     * sPin - Pincer Servo                                                    *
     **************************************************************************/

    public void init(HardwareMap hardwareMap) {
        mCH1 = hardwareMap.get(DcMotorEx.class, "Core Hex 1");
        mCH2 = hardwareMap.get(DcMotorEx.class, "Core Hex 2");
        sHan = hardwareMap.get(Servo.class, "Handle Servo");
        sPin = hardwareMap.get(Servo.class, "Pincer Servo");

        mCH1.setDirection(DcMotorEx.Direction.FORWARD);
        mCH2.setDirection(DcMotorEx.Direction.REVERSE);
        sHan.setDirection(Servo.Direction.FORWARD);
        sPin.setDirection(Servo.Direction.FORWARD);

        mCH1.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        mCH2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        mCH1.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        mCH2.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }
}
