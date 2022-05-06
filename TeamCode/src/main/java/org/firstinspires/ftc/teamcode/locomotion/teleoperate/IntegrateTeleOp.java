package org.firstinspires.ftc.teamcode.locomotion.teleoperate;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.controllers.carousel.CarouselControl;

@TeleOp(name = "Integrated TeleOp", group = "Controllers")
public class IntegrateTeleOp extends BasicTeleOp {
    public DcMotor mFL, mBL, mFR, mBR;
    /**************************************************************************
     * mFL - front left motor                                                 *
     * mBL - back left motor                                                  *
     * mFR - front right motor                                                *
     * mBR - back right motor                                                 *
     **************************************************************************/
    private final ElapsedTime runtime = new ElapsedTime();
    CarouselControl carousel = new CarouselControl();
    HardwareTeleOp motors = new HardwareTeleOp(); // access robot components

    @Override
    public void init() {
        carousel.init();
        telemetry.addData("Status", "TeleOp Initialized");

        // change robot's components names
        mFL = hardwareMap.get(DcMotor.class, "mFL");
        mBL = hardwareMap.get(DcMotor.class, "mBL");
        mFR = hardwareMap.get(DcMotor.class, "mFR");
        mBR = hardwareMap.get(DcMotor.class, "mBR");

        mFL.setDirection(DcMotor.Direction.REVERSE);
        mBL.setDirection(DcMotor.Direction.REVERSE);
        mFR.setDirection(DcMotor.Direction.FORWARD);
        mBR.setDirection(DcMotor.Direction.FORWARD);
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

        carousel.loop();

        telemetry.update();
    }

    public void axisXY() {
        motors.motorPower(-gamepad1.left_stick_x, gamepad1.left_stick_x, gamepad1.left_stick_x, -gamepad1.left_stick_x);
        motors.motorPower(-gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y);
    }

    public void axisXYAdjusts() {
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

    // reverse round
    public void round() {
        if (gamepad1.left_bumper) {
            motors.motorPower(-1, -1, 1, 1);
        }
        if (gamepad1.right_bumper) {
            motors.motorPower(1, 1, -1, -1);
        }
    }

    public void diagonal() {
        motors.motorPower(0, gamepad1.left_trigger,gamepad1.left_trigger,0);
        motors.motorPower(gamepad1.right_trigger, 0, 0, gamepad1.right_trigger);
    }

    public void roundX() {
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