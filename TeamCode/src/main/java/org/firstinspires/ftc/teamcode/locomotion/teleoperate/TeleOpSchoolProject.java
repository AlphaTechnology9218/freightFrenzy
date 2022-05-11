package org.firstinspires.ftc.teamcode.locomotion.teleoperate;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

@TeleOp(name = "TeleOp School Project", group = "Controllers")
public class TeleOpSchoolProject extends OpMode {
    public DcMotor mFL, mBL, mFR, mBR;
    /**************************************************************************
     * mFL - front left motor (Ultra Planetary)                               *
     * mBL - back left motor                                                  *
     * mFR - front right motor (Ultra Planetary)                              *
     * mBR - back right motor                                                 *
     **************************************************************************/
    private final ElapsedTime runtime = new ElapsedTime();
    MotorComponents motors = new MotorComponents();
    TeleOpLocomotion move = new TeleOpLocomotion();

    @Override
    public void init() {
        telemetry.addData("Status", "TeleOp Initialized");
        motors.init(hardwareMap);
    }

    @Override
    public void start() { runtime.reset(); }

    @Override
    public void loop() {
        telemetry.addData("Status", "TeleOp Executing: " + runtime.toString());

        axisXY();
        axisXYAdjusts();
        round();
        roundX();

        telemetry.update();
    }

    public void axisXY() {
        move.motorPower(gamepad1.left_stick_x, gamepad1.left_stick_x, gamepad1.left_stick_x , gamepad1.left_stick_x);
        move.motorPower(-gamepad1.left_stick_y * 0.6f, -gamepad1.left_stick_y, -gamepad1.left_stick_y* 0.7f, -gamepad1.left_stick_y);
    }

    public void axisXYAdjusts() {
        if (gamepad1.dpad_up) {
            move.motorPower(0.75f, 0.75f, 0.75f, 0.75f);
        }
        if (gamepad1.dpad_down) {
            move.motorPower(-0.75f, -0.75f, -0.75f, -0.75f);
        }
        if (gamepad1.dpad_right) {
            move.motorPower(0.75f, -0.75f, -0.75f, 0.75f);
        }
        if (gamepad1.dpad_left) {
            move.motorPower(-0.75f, 0.75f, 0.75f, -0.75f);
        }
    }

    // reverse round
    public void round() {
        if (gamepad1.left_bumper) {
            move.motorPower(-0.6f, -1, 0.7f, 1);
        }
        if (gamepad1.right_bumper) {
            move.motorPower(0.6f, 1, -0.7f, -1);
        }
    }
    public void roundX() {
        if (gamepad1.b) {
            move.motorPower(0.6f,1,0,0);
        }
        if (gamepad1.x) {
            move.motorPower(0,0,0.7f,1);
        }
    }

    @Override
    public void stop() {
        telemetry.addData("Status", "TeleOp Finished");
    }
}