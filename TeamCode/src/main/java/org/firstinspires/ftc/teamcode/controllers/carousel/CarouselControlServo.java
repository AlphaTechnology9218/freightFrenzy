package org.firstinspires.ftc.teamcode.controllers.carousel;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.abilities.SleepRobot;

@TeleOp(name = "Servo Carousel Control", group = "Controllers")
public class CarouselControlServo extends OpMode {
    private Servo servoDuck;
    public boolean atv;
    /**************************************************************************
    * servoDuck - servo to take down the ducks                                *
    * atv - activate the servo                                                *
    ***************************************************************************/
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        servoDuck = hardwareMap.get(Servo.class, "servoDuck");
    }

    @Override
    public void start() {
        atv = true;
    }

    @Override
    public void loop() {
        takeDownDuck();
    }

    @Override
    public void stop() {
        telemetry.addData("Status", "Finished");
    }

    private void takeDownDuck() {
        SleepRobot sleep = new SleepRobot();

        if (gamepad2.y && atv) {
            servoDuck.setPosition(1);
            atv = false;
            sleep.robotSleeping(200);
         } else if (gamepad2.y) {
            servoDuck.setPosition(0);
        }
    }
}
