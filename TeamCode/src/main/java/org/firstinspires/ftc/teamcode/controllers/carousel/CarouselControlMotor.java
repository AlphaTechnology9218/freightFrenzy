package org.firstinspires.ftc.teamcode.controllers.carousel;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.abilities.SleepRobot;

@TeleOp(name = "Motor Carousel Control", group = "Controllers")
public class CarouselControlMotor extends OpMode {
    public DcMotor leftDuck, rightDuck;
    public boolean act;
    /**************************************************************************
     * leftDuck, rightDuck - motors to take down the ducks                    *
     * act - activate the servo                                               *
     **************************************************************************/
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftDuck = hardwareMap.get(DcMotor.class, "leftDuck");
        rightDuck = hardwareMap.get(DcMotor.class, "rightDuck");
    }

    @Override
    public void start() { act = true; }

    @Override
    public void loop() { takeDownDuck(); }

    @Override
    public void stop() { telemetry.addData("Status", "Finished"); }

    private void takeDownDuck() {
        SleepRobot sleep = new SleepRobot();

        if (gamepad2.y && act) {
            leftDuck.setPower(1);
            rightDuck.setPower(1);
            act = false;
            sleep.robotSleeping(200);
        } else if (gamepad2.y) {
            leftDuck.setPower(0);
            rightDuck.setPower(0);
            act = true;
            sleep.robotSleeping(200);
        }
    }
}
