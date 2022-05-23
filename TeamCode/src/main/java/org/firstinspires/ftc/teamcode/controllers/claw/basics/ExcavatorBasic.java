package org.firstinspires.ftc.teamcode.controllers.claw.basics;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

@TeleOp(name = "Excavator Control", group = "Controllers")
public class ExcavatorBasic extends LinearOpMode {
    MotorComponents motor = new MotorComponents();

    double command = 0.0;
    double tP = 0.0;
    double feedF;

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        while (opModeIsActive()) {
            runPower();
            controLoop();
            armUp();
            armDown();
        }
    }

    public void runPower(){
        motor.cL.setPower(command);
        motor.cR.setPower(command);
    }

    public void controLoop(){
        command = tP - motor.cL.getCurrentPosition();
    }

    public void armUp(){
        if(gamepad1.y){
            tP = 60;
        }
    }

    public void armDown(){
        if(gamepad1.b){
            tP = 5;
        }
    }
}
