package org.firstinspires.ftc.teamcode.controllers.claw;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.abilities.SleepRobot;
import org.firstinspires.ftc.teamcode.robot_components.ServoComponents;

@TeleOp(name = "Complete Claw Control", group = "Controllers")
public class CompleteClawTeleOperate extends OpMode {
    protected boolean act1, act2, act3, act4 = true;

    static final double POS1 = 0.5;
    static final double POS2 = 0.5;
    static final double POS3 = 0.5;
    static final double POS4 = 0.5;

    ServoComponents servo = new ServoComponents();
    SleepRobot sleep = new SleepRobot();

    @Override
    public void init() { startPosition(); }

    @Override
    public void loop() {
        mainClawControl();
        clawPointControl();
        mainArmControl();
        supportControl();
    }
    protected void startPosition() {
        servo.completeServos.get(0).setPosition(POS1);
        servo.completeServos.get(1).setPosition(POS2);
        servo.completeServos.get(2).setPosition(POS3);
        servo.completeServos.get(3).setPosition(POS4);
    }

    protected void mainClawControl() {
        if (act1 && gamepad2.y) {
            servo.completeServos.get(0).setPosition(1);
            sleep.robotSleeping(200);
            act1 = false;
        } else if (!act1 && gamepad2.y) {
            servo.completeServos.get(0).setPosition(0);
            sleep.robotSleeping(200);
            act1 = true;
        }
    }
    protected void clawPointControl() {
        if (act2 && gamepad2.x) {
            servo.completeServos.get(1).setPosition(1);
            sleep.robotSleeping(200);
            act2 = false;
        } else if (!act2 && gamepad2.x) {
            servo.completeServos.get(1).setPosition(0);
            sleep.robotSleeping(200);
            act2 = true;
        }
    }
    protected void mainArmControl() {
        if (act3 && gamepad2.a) {
            servo.completeServos.get(2).setPosition(1);
            sleep.robotSleeping(200);
            act3 = false;
        } else if (!act3 && gamepad2.a) {
            servo.completeServos.get(2).setPosition(0);
            sleep.robotSleeping(200);
            act3 = true;
        }
    }
    protected void supportControl() {
        if (act4 && gamepad2.b) {
            servo.completeServos.get(3).setPosition(1);
            sleep.robotSleeping(200);
            act4 = false;
        } else if (!act4 && gamepad2.b) {
            servo.completeServos.get(3).setPosition(0);
            sleep.robotSleeping(200);
            act4 = true;
        }
    }
}
