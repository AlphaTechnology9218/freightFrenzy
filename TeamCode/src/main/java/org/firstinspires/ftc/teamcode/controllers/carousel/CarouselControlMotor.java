package org.firstinspires.ftc.teamcode.controllers.carousel;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.abilities.SleepRobot;

@TeleOp(name = "Motor Carousel Control", group = "Controllers")
public class CarouselControlMotor extends OpMode {
    public DcMotor mDD;
    public boolean act;
    /***********************
     * mDD - motor to take down the duck                             *
     * act - activate the motor                                      *
     ***********************/

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        mDD = hardwareMap.get(DcMotor.class, "Duck Motor");
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
            mDD.setPower(1); // Down Duck
            act = false;
            sleep.robotSleeping(200);
        } else if (gamepad2.y) {
            mDD.setPower(0);
            act = true;
            sleep.robotSleeping(200);
        }
    }
}