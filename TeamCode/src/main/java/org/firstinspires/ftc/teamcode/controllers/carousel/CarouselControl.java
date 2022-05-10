package org.firstinspires.ftc.teamcode.controllers.carousel;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.abilities.SleepRobot;
import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

@TeleOp(name = "Motor Carousel Control", group = "Controllers")
public class CarouselControl extends OpMode {
    MotorComponents motors = new MotorComponents();
    public boolean act = true;

    @Override
    public void init() {
        motors.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
    }
    @Override
    public void loop() { takeDownDuck(); }

    @Override
    public void stop() { telemetry.addData("Status", "Finished"); }

    public void takeDownDuck() {
        SleepRobot sleep = new SleepRobot();

        if (gamepad1.y && act) {
            // Down Duck
            motors.mDDR.setPower(1);
            motors.mDDL.setPower(1);
            act = false;
            sleep.robotSleeping(500);
        } else if (gamepad1.y) {
            motors.mDDR.setPower(0);
            motors.mDDL.setPower(0);
            act = true;
            sleep.robotSleeping(500);
        }
    }
}