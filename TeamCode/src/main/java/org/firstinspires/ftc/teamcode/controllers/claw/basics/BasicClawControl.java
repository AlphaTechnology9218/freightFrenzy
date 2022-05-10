package org.firstinspires.ftc.teamcode.controllers.claw.basics;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.abilities.SleepRobot;
@TeleOp(name = "Basic Claw Control", group = "Controllers")
public class BasicClawControl extends OpMode {
    private Servo sOC, sUD;
    public boolean act = true;
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        sOC = hardwareMap.get(Servo.class, "sOC");
        sUD = hardwareMap.get(Servo.class, "sUD");

        sOC.setPosition(0.5);
        sUD.setPosition(0);
    }
    @Override
    public void loop() {
        clawControl();
    }
    @Override
    public void stop() { telemetry.addData("Status", "Finished"); }
    private void clawControl() {
        SleepRobot sleep = new SleepRobot();

        if (act && gamepad1.x) {
            sUD.setPosition(0);
            // sOC.setPosition(0.7);
            sOC.setPosition(Range.clip(0, 0.7, sOC.getPosition() + .001)); // slow servo position amount increasing
            act = false;
            sleep.robotSleeping(500);
        } else if (!act && gamepad1.x) {
            sUD.setPosition(0);
            sOC.setPosition(0);
            act = true;
            sleep.robotSleeping(500);
        }
    }
}