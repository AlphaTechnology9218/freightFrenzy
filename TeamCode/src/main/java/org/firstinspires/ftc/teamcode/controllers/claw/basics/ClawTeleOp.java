package org.firstinspires.ftc.teamcode.controllers.claw.basics;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.abilities.SleepRobot;

public class ClawTeleOp extends OpMode {
    public Servo mCC, cPC, mAC, spC;

    protected boolean act1, act2, act3, act4 = true;

    static final double START_POS_1 = 0.5;
    static final double START_POS_2 = 0.5;
    static final double START_POS_3 = 0.5;
    static final double START_POS_4 = 0.5;

    SleepRobot sleep = new SleepRobot();

    /*****************************************************************
     * START_POS - initial position for each servo                   *
     * mCC - Main Claw Control                                       *
     * cPC - Claw Point Control                                      *
     * mAC - Main Arm Control                                        *
     * spC - Support Control                                         *
     *****************************************************************/

    @Override
    public void init() {
        mCC = hardwareMap.get(Servo.class, "servo mCC");
        cPC = hardwareMap.get(Servo.class, "servo cPC");
        mAC = hardwareMap.get(Servo.class, "servo mAC");
        spC = hardwareMap.get(Servo.class, "servo spC");
    }

    @Override
    public void loop() {
        startPosition();
        mainArmControl();
        supportControl();
        mainClawControl();
        clawPointControl();
    }

    public void startPosition() {
        mCC.setPosition(START_POS_1);
        cPC.setPosition(START_POS_2);
        mAC.setPosition(START_POS_3);
        spC.setPosition(START_POS_4);
    }
    public void mainClawControl() {
        if (act1 && gamepad2.y) {
            mCC.setPosition(1);
            sleep.robotSleeping(200);
            act1 = false;
        } else if (!act1 && gamepad2.y) {
            mCC.setPosition(0);
            sleep.robotSleeping(200);
            act1 = true;
        }
    }
    public void clawPointControl() {
        if (act2 && gamepad2.x) {
            cPC.setPosition(1);
            sleep.robotSleeping(200);
            act2 = false;
        } else if (!act2 && gamepad2.x) {
            cPC.setPosition(0);
            sleep.robotSleeping(200);
            act2 = true;
        }
    }
    public void mainArmControl() {
        if (act3 && gamepad2.a) {
            mAC.setPosition(1);
            sleep.robotSleeping(200);
            act3 = false;
        } else if (!act3 && gamepad2.a) {
            mAC.setPosition(0);
            sleep.robotSleeping(200);
            act3 = true;
        }
    }
    public void supportControl() {
        if (act4 && gamepad2.b) {
            spC.setPosition(1);
            sleep.robotSleeping(200);
            act4 = false;
        } else if (!act4 && gamepad2.b) {
            spC.setPosition(0);
            sleep.robotSleeping(200);
            act4 = true;
        }
    }
}