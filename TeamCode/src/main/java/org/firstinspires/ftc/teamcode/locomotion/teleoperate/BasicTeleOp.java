package org.firstinspires.ftc.teamcode.locomotion.teleoperate;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

@TeleOp(name = "TeleOp Base Controllers", group = "Tele Operate")
public class BasicTeleOp extends OpMode {
    private final ElapsedTime runtime = new ElapsedTime();
    MotorComponents motors = new MotorComponents();

    @Override
    public void init() { motors.init(hardwareMap); }

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
        straight(); // axis X and Y

        telemetry.update();
    }

    public void axisXY() {
        motors.motorPower(gamepad1.left_stick_x, -gamepad1.left_stick_x, -gamepad1.left_stick_x,
                gamepad1.left_stick_x);
        motors.motorPower(-gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y,
                -gamepad1.left_stick_y);
    }

    public void axisXYAdjusts() {
        if (gamepad1.dpad_up) {
            motors.motorPower(0.5f, 0.5f, 0.5f, 0.5f);
        }
        if (gamepad1.dpad_down) {
            motors.motorPower(-0.5f, -0.5f, -0.5f, -0.5f);
        }
        if (gamepad1.dpad_right) {
            motors.motorPower(0.5f, -0.5f, -0.5f, 0.5f);
        }
        if (gamepad1.dpad_left) {
            motors.motorPower(-0.5f, 0.5f, 0.5f, -0.5f);
        }
    }

    public void straight() {
        motors.motorPower(-gamepad1.left_trigger, -gamepad1.left_trigger, -gamepad1.left_trigger,
                -gamepad1.left_trigger); // FRONT
        motors.motorPower(gamepad1.right_trigger, gamepad1.right_trigger, gamepad1.right_trigger,
                gamepad1.right_trigger); // BACK
    }

    public void diagonal() {
        if (gamepad1.a) {
            motors.motorPower(0, 1, 1, 0);
        }
        if (gamepad1.b) {
            motors.motorPower(1, 0, 0, 1);
        }
    }

    public void round() {
        if (gamepad1.left_bumper) {
            motors.motorPower(1, 1, -1, -1);
        }
        if (gamepad1.right_bumper) {
            motors.motorPower(-1, -1, 1, 1);
        }
    }

    public void roundX() {
        if (gamepad1.b) {
            motors.motorPower(1,1,0,0);
        }
        if (gamepad1.x) {
            motors.motorPower(0,0,1,1);
        }
    }
}