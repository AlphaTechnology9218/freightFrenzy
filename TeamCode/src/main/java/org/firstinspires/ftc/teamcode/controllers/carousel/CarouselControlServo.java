package org.firstinspires.ftc.teamcode.controllers.carousel;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.abilities.SleepRobot;

@TeleOp(name = "Servo Carousel Control", group = "Controllers")
public class CarouselControlServo extends OpMode {
    private Servo servoDuck;
    public boolean act;
    /***************************************************************************
     * servoDuck - servo to take down the ducks                                *
     * act - activate the servo                                                *
     ***************************************************************************/
    SleepRobot sleep = new SleepRobot();

    @Override
    public void init() {
        telemetry.addData("Status: ", "Initialized");
        servoDuck = hardwareMap.get(Servo.class, "servoDuck");
    }

    @Override
    public void start() { act = true; }

    @Override
    public void loop() { takeDownDuck(); }

    @Override
    public void stop() { telemetry.addData("Status", "Finished"); }

    private void takeDownDuck() {
        if (gamepad2.y && act) {
            servoDuck.setPosition(1); // Down Duck
            act = false;
            sleep.robotSleeping(200);
        } else if (gamepad2.y) {
            servoDuck.setPosition(0); // Back servo to min position
        }
    }
}