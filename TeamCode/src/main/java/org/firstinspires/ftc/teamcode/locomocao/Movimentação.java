package org.firstinspires.ftc.teamcode.locomocao;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class Movimentação extends LinearOpMode {
    private DcMotor motorightFrontrontLeft, motorightBackackLeft, motorightFrontrontRight,
            motorightBackackRight;
    private int rightBackPosicao, rightFrontPosicao, leftFrontPosicao, leftBackPosicao;
    @Override
    public void runOpMode() throws InterruptedException {
        motorightFrontrontLeft = hardwareMap.get(DcMotor.class, "front_motor_left");
        motorightBackackLeft = hardwareMap.get(DcMotor.class, "back_motor_left");
        motorightFrontrontRight = hardwareMap.get(DcMotor.class, "front_motor_right");
        motorightBackackRight = hardwareMap.get(DcMotor.class, "back_motor_right");

    }
    public void Mover(int leftBackTarget, int leftFrontTarget, int rightBackTarget,
                       int rightFrontTarget, double vel) {
        rightBackPosicao += rightBackTarget;
        leftBackPosicao += leftBackTarget;
        rightFrontPosicao += rightFrontTarget;
        leftFrontPosicao += leftFrontTarget;

        motorightFrontrontLeft.setTargetPosition(leftFrontPosicao);
        motorightBackackLeft.setTargetPosition(leftBackPosicao);
        motorightFrontrontRight.setTargetPosition(rightFrontPosicao);
        motorightBackackRight.setTargetPosition(rightBackPosicao);

        motorightFrontrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorightBackackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorightFrontrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorightBackackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorightFrontrontLeft.setPower(vel);
        motorightBackackLeft.setPower(vel);
        motorightFrontrontRight.setPower(vel);
        motorightBackackRight.setPower(vel);

        while (opModeIsActive() && motorightFrontrontLeft.isBusy() && motorightBackackLeft.isBusy()
                && motorightFrontrontRight.isBusy() && motorightBackackRight.isBusy()) {
            idle();
        }
    }
}
