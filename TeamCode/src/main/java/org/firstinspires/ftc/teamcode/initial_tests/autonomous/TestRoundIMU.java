package org.firstinspires.ftc.teamcode.initial_tests.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.abilities.SensorIMU;
import org.firstinspires.ftc.teamcode.locomotion.autonomous.MotorEncodersSetup;
import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

@Autonomous(name = "Test IMU", group = "Autonomous Test")
public class TestRoundIMU extends LinearOpMode {
    SensorIMU sense = new SensorIMU();
    MotorEncodersSetup motors = new MotorEncodersSetup();

    @Override
    public void runOpMode() throws InterruptedException {
        motors.runOpMode(hardwareMap);
        sense.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            sense.runOpMode();
            if(sense.degree > 5){
                motors.moveRobot(50,50,50,50,0.5);
            } else if (sense.degree < -5){
                motors.moveRobot(50,50,50,50,0.5);
            }
        }
    }
}
