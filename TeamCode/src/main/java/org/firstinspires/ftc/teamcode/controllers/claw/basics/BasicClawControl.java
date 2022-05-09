package org.firstinspires.ftc.teamcode.controllers.claw.basics;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.abilities.SleepRobot;
@TeleOp(name = "Basic Claw Control", group = "Controllers")
public class BasicClawControl extends OpMode {
    private Servo servo;
    public boolean act = true;
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        servo = hardwareMap.get(Servo.class, "servo");
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
            servo.setPosition(1);
            act = false;
            sleep.robotSleeping(500);
        } else if (!act && gamepad1.x) {
            servo.setPosition(-1);
            act = true;
            sleep.robotSleeping(500);
        }
    }
}