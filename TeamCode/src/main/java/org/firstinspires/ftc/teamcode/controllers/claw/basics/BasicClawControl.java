package org.firstinspires.ftc.teamcode.controllers.claw.basics;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.abilities.SleepRobot;

import java.util.concurrent.TimeUnit;

@TeleOp(name = "Basic Claw Control", group = "Controllers")
public class BasicClawControl extends OpMode {
    private Servo sOC, sUD;
    private boolean act = true;

    private final ElapsedTime runtime = new ElapsedTime();
    private double servoPosition;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        sOC = hardwareMap.get(Servo.class, "sOC");
        sUD = hardwareMap.get(Servo.class, "sUD");

        /* set start position for each servo */
        sOC.setPosition(0.5);
        sUD.setPosition(0);
    }

    @Override
    public void start() { runtime.reset(); }
    @Override
    public void loop() {
        clawControl();
        slowClawControl();
    }
    @Override
    public void stop() { telemetry.addData("Status", "Finished"); }

    private void clawControl() {
        SleepRobot sleep = new SleepRobot();

        if (act && gamepad1.x) {
            sUD.setPosition(0);
            sOC.setPosition(0.7);
            act = false;
            sleep.robotSleeping(500);
        } else if (!act && gamepad1.x) {
            sUD.setPosition(0);
            sOC.setPosition(0);
            act = true;
            sleep.robotSleeping(500);
        }
    }

    private void slowClawControl() {
        if(gamepad1.b) {
            double servoDelayTime = 5;
            if(runtime.time() > servoDelayTime) {
                double servoDelta = 0.01;
                servoPosition += servoDelta;
                // clip the position values so that they never exceed 0..1
                servoPosition = Range.clip(servoPosition, 0, 1);
                sOC.setPosition(servoPosition);
                runtime.reset();
            }
        }
    }
}