package org.firstinspires.ftc.teamcode.controllers.carousel;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.abilities.SleepRobot;
import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HandshakeCompletedEvent;

@TeleOp(name = "Advanced Carousel Control", group = "Controllers")
public class TeleOperateCarousel extends OpMode {
    MotorComponents motors = new MotorComponents();
    ElapsedTime timer = new ElapsedTime();
    DcMotor mD;

    public boolean act = true;

    public TeleOperateCarousel(HardwareMap hardwareMap){
        mD = hardwareMap.get(DcMotor.class, "motorDuck");
        mD.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
    }
    @Override
    public void loop() { takeDownDuck(); }

    public void takeDownDuck() {
        SleepRobot sleep = new SleepRobot();
        telemetry.addData("Time", timer.time());
        float power = 0.4F;

        if (gamepad1.y && act) { // Take Duck Down
            timer.startTime();
            motors.mD.setPower(power);
            for (power = 0.4F; power <= 0.7; power += 0.1) {
                if (timer.time(TimeUnit.SECONDS) == 0.2) {
                    motors.mD.setPower(power);
                    timer.reset();
                }
            }

//            while(power <= 0.7) {
//                if (timer.time(TimeUnit.SECONDS) == 0.2) {
//                    power += 0.1F;
//                    motors.mD.setPower(power);
//                    timer.reset();
//                }
//            }

            act = false;
            sleep.robotSleeping(500);
        } else if (gamepad1.y) {
            motors.mD.setPower(0);
            act = true;
            timer.reset();
            sleep.robotSleeping(500);
        }
    }
}