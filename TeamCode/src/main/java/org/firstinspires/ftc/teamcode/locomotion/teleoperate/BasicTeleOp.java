package org.firstinspires.ftc.teamcode.locomotion.teleoperate;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "TeleOp Base Controllers", group = "Tele Operate")
public class BasicTeleOp extends OpMode {
    private final ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
    }

    @Override
    public void start() { runtime.reset(); }

    @Override
    public void loop() {
        telemetry.addData("Status", "TeleOp Executing: " + runtime.toString());

        axisXY();
        axisXYAdjusts();
        round();
        diagonal();
        roundX();

        telemetry.update();
    }

    public void axisXY() {
        motorPower(-gamepad1.left_stick_x, gamepad1.left_stick_x, gamepad1.left_stick_x, -gamepad1.left_stick_x);
        motorPower(-gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y);
    }

    public void axisXYAdjusts() {
        if (gamepad1.dpad_up) {
            motorPower(0.75f, 0.75f, 0.75f, 0.75f);
        }
        if (gamepad1.dpad_down) {
            motorPower(-0.75f, -0.75f, -0.75f, -0.75f);
        }
        if (gamepad1.dpad_right) {
            motorPower(0.75f, -0.75f, -0.75f, 0.75f);
        }
        if (gamepad1.dpad_left) {
            motorPower(-0.75f, 0.75f, 0.75f, -0.75f);
        }
    }

    public void round() {
        if (gamepad1.left_bumper) {
            motorPower(1, 1, -1, -1);
        }
        if (gamepad1.right_bumper) {
            motorPower(-1, -1, 1, 1);
        }
    }

    public void diagonal() {
        motorPower(0, gamepad1.left_trigger,gamepad1.left_trigger,0 );
        motorPower(gamepad1.right_trigger, 0, 0, gamepad1.right_trigger);
    }

    public void roundX() {
        if (gamepad1.b) {
            motorPower(1,1,0,0);
        }
        if (gamepad1.x) {
            motorPower(0,0,1,1);
        }
    }
}