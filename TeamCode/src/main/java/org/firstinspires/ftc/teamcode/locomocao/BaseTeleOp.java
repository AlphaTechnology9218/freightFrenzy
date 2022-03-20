package org.firstinspires.ftc.teamcode.locomocao;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Base TeleOp", group = "Tele Operate")
public class BaseTeleOp extends OpMode {
    private final ElapsedTime runtime = new ElapsedTime();
    HardwareTeleOp motors = new HardwareTeleOp(); // access robot components

    @Override
    public void init() {
        telemetry.addData("Status", "TeleOp Initialized");
        motors.init();
    }

    @Override
    public void start() { runtime.reset(); }

    @Override
    public void loop() {
        telemetry.addData("Status", "TeleOp Executing: " + runtime.toString());

        axisXY();
        axisXYAdjusts();
        round();
        axisZ();
        roundX();

        telemetry.update();
    }

    private void axisXY() {
        motors.motorPower(-gamepad1.left_stick_x, gamepad1.left_stick_x, gamepad1.left_stick_x, -gamepad1.left_stick_x);
        motors.motorPower(-gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y);
    }

    private void axisXYAdjusts() {
        if (gamepad1.dpad_up) {
            motors.motorPower(0.75f, 0.75f, 0.75f, 0.75f);
        }
        if (gamepad1.dpad_down) {
            motors.motorPower(-0.75f, -0.75f, -0.75f, -0.75f);
        }
        if (gamepad1.dpad_right) {
            motors.motorPower(0.75f, -0.75f, -0.75f, 0.75f);
        }
        if (gamepad1.dpad_left) {
            motors.motorPower(-0.75f, 0.75f, 0.75f, -0.75f);
        }
    }

    private void round() {
        if (gamepad1.left_bumper) {
            motors.motorPower(1, 1, -1, -1);
        }
        if (gamepad1.right_bumper) {
            motors.motorPower(-1, -1, 1, 1);
        }
    }

    private void axisZ() {
        motors.motorPower(0, gamepad1.left_trigger,0, gamepad1.right_trigger);
        motors.motorPower(gamepad1.right_trigger, 0, 0, gamepad1.right_trigger);
    }

    private void roundX() {
        if (gamepad1.b) {
            motors.motorPower(1,1,0,0);
        }
        if (gamepad1.x) {
            motors.motorPower(0,0,1,1);
        }
    }

    @Override
    public void stop() {
        telemetry.addData("Status", "TeleOp Finished");
    }
}
