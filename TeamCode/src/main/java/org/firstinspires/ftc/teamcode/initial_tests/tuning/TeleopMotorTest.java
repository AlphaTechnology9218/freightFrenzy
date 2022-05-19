package org.firstinspires.ftc.teamcode.initial_tests.tuning;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

public class TeleopMotorTest extends OpMode {
    MotorComponents motor = new MotorComponents();

    @Override
    public void init(){
        motor.init(hardwareMap);

        telemetry.addLine("Y = mFR");
        telemetry.addLine("X = mFL");
        telemetry.addLine("A = mBL");
        telemetry.addLine("B = mBR");

        telemetry.update()
;    }

    @Override
    public void loop(){
        if(gamepad1.y){
            motor.motorPower(0,0,1,0);
        }
        if(gamepad1.x){
            motor.motorPower(1,0,0,0);
        }
        if(gamepad1.a){
            motor.motorPower(0,1,0,0);
        }
        if(gamepad1.b){
            motor.motorPower(0,0,0,1);
        }
    }
}
