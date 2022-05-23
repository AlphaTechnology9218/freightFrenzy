package org.firstinspires.ftc.teamcode.controllers.carousel;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.abilities.SleepRobot;
import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

import java.util.concurrent.TimeUnit;

@TeleOp(name = "Motor Carousel Control", group = "Controllers")
public class TeleOperateCarousel extends OpMode {
    MotorComponents motors = new MotorComponents();
    ElapsedTime timer = new ElapsedTime();

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
        telemetry.addData("Time", timer.time());

        float power = (float) 0.7;

        if (gamepad1.y && act) { // Take Duck Down
            timer.startTime();
            motors.mDDR.setPower(power);
            motors.mDDL.setPower(power);
            if (timer.time(TimeUnit.SECONDS) == 2) {
                int i = 0;
                for (i = 0; i < 3; i++) {
                    power = (float) + 0.1;
                    motors.mDDR.setPower(power);
                    motors.mDDL.setPower(power);
                    i++;
                }
            }
            act = false;
            sleep.robotSleeping(500);
        } else if (gamepad1.y) {
            motors.mDDR.setPower(0);
            motors.mDDL.setPower(0);
            act = true;
            timer.reset();
            sleep.robotSleeping(500);
        }
    }
}