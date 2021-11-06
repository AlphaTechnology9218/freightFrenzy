package org.firstinspires.ftc.teamcode.locomocao;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Base_Autonomo", group = "Autonomous_Algorithms")
public class Base_Autonomo extends LinearOpMode {

    @Override
    public void runOpMode() {
        DcMotor motorightFrontrontLeft = hardwareMap.get(DcMotor.class, "front_motor_left");
        DcMotor motorightBackackLeft = hardwareMap.get(DcMotor.class, "back_motor_left");
        DcMotor motorightFrontrontRight = hardwareMap.get(DcMotor.class, "front_motor_right");
        DcMotor motorightBackackRight = hardwareMap.get(DcMotor.class, "back_motor_right");

        motorightFrontrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorightBackackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorightFrontrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorightBackackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorightBackackLeft.setDirection(DcMotor.Direction.REVERSE);
        motorightFrontrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorightBackackRight.setDirection(DcMotor.Direction.FORWARD);
        motorightFrontrontRight.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                while (opModeIsActive()) {
                    RodarArena();
                }
            }
            telemetry.update();
        }
    }
    public void RodarArena(){
        Movimentação robo = new Movimentação();
        robo.Mover(500, 500, 500, 500, 1);
    }
}


