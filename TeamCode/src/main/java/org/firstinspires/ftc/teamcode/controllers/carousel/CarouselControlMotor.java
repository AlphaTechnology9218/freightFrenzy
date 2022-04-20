package org.firstinspires.ftc.teamcode.controllers.carousel;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.abilities.SleepRobot;

@TeleOp(name = "Motor Carousel Control", group = "Controllers")

public class CarouselControlMotor extends OpMode {
    public DcMotor leftDuck, rightDuck;
    public boolean atv;
    /**************************************************************************
     * leftDuck, rightDuck - motors to take down the ducks                    *
     * atv - activate the servo                                               *
     **************************************************************************/
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftDuck = hardwareMap.get(DcMotor.class, "leftDuck");
        rightDuck = hardwareMap.get(DcMotor.class, "rightDuck");
    }

    @Override
    public void start() { atv = true; }

    @Override
    public void loop() { takeDownDuck(); }

    @Override
    public void stop() { telemetry.addData("Status", "Finished"); }

    private void takeDownDuck() {
        SleepRobot sleep = new SleepRobot();

        if (gamepad2.y && atv) {
            leftDuck.setPower(1);
            rightDuck.setPower(1);
            atv = false;
            sleep.robotSleeping(200);
        } else if (gamepad2.y) {
            leftDuck.setPower(0);
            rightDuck.setPower(0);
            atv = true;
            sleep.robotSleeping(200);
        }
    }
}
