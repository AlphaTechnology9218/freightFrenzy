package org.firstinspires.ftc.teamcode.initial_tests.tele_operate;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Intake Test", group = "claw tests")
public class ClawIntake extends OpMode {
    private DcMotor intake, claw;
    private boolean full;

    @Override
    public void init() {
        intake = hardwareMap.get(DcMotor.class, "Intake");
        claw = hardwareMap.get(DcMotor.class, "claw");

        intake.setDirection(DcMotor.Direction.FORWARD);
        claw.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop() {
        eat();
        vomit();
        guard();
        attack();
    }

    public void eat(){
        if(gamepad1.y && full){
            intake.setPower(1);
            full = false;
        }
        else if(gamepad1.y && !full){
            intake.setPower(0);
            full = true;
        }
    }

    public void vomit() {
        if (gamepad1.a && full) {
            intake.setPower(-1);
            full = false;
        }
        else if(gamepad1.a && !full){
            intake.setPower(0);
            full = true;
        }
    }

    public void guard(){
        if(gamepad1.x){
            claw.setTargetPosition(200);
            claw.setPower(0.6);
        }
    }

    public void attack(){
        if(gamepad1.b){
            claw.setTargetPosition(0);
            claw.setPower(0.6);
        }
    }

}
