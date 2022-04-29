package org.firstinspires.ftc.teamcode.initial_tests.tele_operate;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.controllers.carousel.CarouselControl;
import org.firstinspires.ftc.teamcode.locomotion.teleoperate.BasicTeleOp;

@TeleOp(name = "Locomotion and Duck Control", group = "TeleOp Tests")
public class LocomotionDuckControl extends OpMode {
    BasicTeleOp locomotion = new BasicTeleOp();
    CarouselControl carrousel = new CarouselControl();

    @Override
    public void init() {
        telemetry.addData("Status", "TeleOp Initialized");
        locomotion.init();
        carrousel.init();
    }

    @Override
    public void loop() {
        locomotion.loop();        // call locomotion functions
        carrousel.loop(); // call carrousel control
    }

    @Override
    public void stop() { telemetry.addData("Status", "finished"); }
}
