package org.firstinspires.ftc.teamcode.controllers.claw.complete;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.abilities.SleepRobot;
import org.firstinspires.ftc.teamcode.robot_components.ServoComponentsComplexClaw;

@TeleOp(name = "TeleOperate Claw Control", group = "Controllers")
public class CompleteClawTeleOperate extends OpMode {
    protected boolean act1, act2, act3, act4 = true;

    static final double START_POS_1 = 0.5;
    static final double START_POS_2 = 0.5;
    static final double START_POS_3 = 0.5;
    static final double START_POS_4 = 0.5;

    ServoComponentsComplexClaw servo = new ServoComponentsComplexClaw();
    SleepRobot sleep = new SleepRobot();
    /*****************************************************************
     * START_POS - initial position for each servo                   *
     * servo     - robot servo components                            *
     * sensor    - robot sensor component                            *
     *****************************************************************/

    @Override
    public void init() {
        startPosition();
        servo.init();
    }

    @Override
    public void loop() {
        mainClawControl();
        clawPointControl();
        mainArmControl();
        supportControl();
    }
    public void startPosition() {
        servo.s1.setPosition(START_POS_1);
        servo.s2.setPosition(START_POS_2);
        servo.s3.setPosition(START_POS_3);
        servo.s4.setPosition(START_POS_4);
    }
    public void mainClawControl() {
        if (act1 && gamepad2.y) {
            servo.s1.setPosition(1);
            sleep.robotSleeping(200);
            act1 = false;
        } else if (!act1 && gamepad2.y) {
            servo.s1.setPosition(0);
            sleep.robotSleeping(200);
            act1 = true;
        }
    }
    public void clawPointControl() {
        if (act2 && gamepad2.x) {
            servo.s2.setPosition(1);
            sleep.robotSleeping(200);
            act2 = false;
        } else if (!act2 && gamepad2.x) {
            servo.s2.setPosition(0);
            sleep.robotSleeping(200);
            act2 = true;
        }
    }
    public void mainArmControl() {
        if (act3 && gamepad2.a) {
            servo.s3.setPosition(1);
            sleep.robotSleeping(200);
            act3 = false;
        } else if (!act3 && gamepad2.a) {
            servo.s3.setPosition(0);
            sleep.robotSleeping(200);
            act3 = true;
        }
    }
    public void supportControl() {
        if (act4 && gamepad2.b) {
            servo.s4.setPosition(1);
            sleep.robotSleeping(200);
            act4 = false;
        } else if (!act4 && gamepad2.b) {
            servo.s4.setPosition(0);
            sleep.robotSleeping(200);
            act4 = true;
        }
    }
}