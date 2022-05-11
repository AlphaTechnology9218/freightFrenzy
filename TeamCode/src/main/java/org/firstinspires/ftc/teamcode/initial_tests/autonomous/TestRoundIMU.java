package org.firstinspires.ftc.teamcode.initial_tests.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.abilities.SensorIMU;
import org.firstinspires.ftc.teamcode.locomotion.teleoperate.TeleOpLocomotion;
import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

@Autonomous(name = "Test IMU", group = "Autonomous Test")
public class TestRoundIMU extends LinearOpMode {
    SensorIMU sense = new SensorIMU();
    MotorComponents motors = new MotorComponents();
    TeleOpLocomotion move = new TeleOpLocomotion();

    @Override
    public void runOpMode() throws InterruptedException {
        motors.init(hardwareMap);
        sense.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            sense.runOpMode();
            if(sense.degree > 5){
                move.motorPower(-0.07f, -0.1f, 0.06f, 0.1f);
            } else if (sense.degree < -5){
                move.motorPower(0.07f, 0.1f, -0.06f, -0.1f);
            }
        }
    }
}
