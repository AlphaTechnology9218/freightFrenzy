package org.firstinspires.ftc.teamcode.initial_tests.tele_operate;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controllers.carousel.CarouselControl;
import org.firstinspires.ftc.teamcode.controllers.claw.complete.CompleteClawTeleOperate;
import org.firstinspires.ftc.teamcode.locomotion.teleoperate.BasicTeleOp;

@TeleOp(name = "Locomotion and Duck Control", group = "TeleOp Tests")
public class LocomotionDuckControl extends OpMode {
    BasicTeleOp locomotion  = new BasicTeleOp();
    CarouselControl carousel     = new CarouselControl();
    CompleteClawTeleOperate claw = new CompleteClawTeleOperate();
    @Override
    public void init() {
        telemetry.addData("Status", "TeleOp Initialized");
        /* start init for each robot function:
         * 1. Locomotion
         * 2. Claw Control
         * 3. Carousel Control
         */
        locomotion.init();
        carousel.init();
        claw.init();
    }
    @Override
    public void loop() {
        /* call locomotion functions */
        locomotion.axisXY();
        locomotion.axisXYAdjusts();
        locomotion.round();
        locomotion.diagonal();
        locomotion.roundX();
        /* call claw functions */
        claw.mainClawControl();
        claw.clawPointControl();
        claw.mainArmControl();
        claw.supportControl();
        /* call carousel functions */
        carousel.takeDownDuck();
    }
    @Override
    public void stop() { telemetry.addData("Status", "finished"); }
}