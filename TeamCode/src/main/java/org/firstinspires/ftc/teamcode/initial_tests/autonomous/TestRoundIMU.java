package org.firstinspires.ftc.teamcode.initial_tests.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.abilities.SensorIMU;
import org.firstinspires.ftc.teamcode.locomotion.teleoperate.TeleOpLocomotion;

@Autonomous(name = "Test IMU", group = "Autonomous Test")
public class TestRoundIMU extends LinearOpMode {
    SensorIMU sense = new SensorIMU();
    TeleOpLocomotion robot = new TeleOpLocomotion();

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        while(opModeIsActive()){
            if(sense.degree > 0){
                robot.motorPower(-0.07f, -0.1f, 0.06f, 0.1f);
            } else if (sense.degree < 0){
                robot.motorPower(0.07f, 0.1f, -0.06f, -0.1f);
            }
            telemetry.addData("Angle", sense.degree);
            telemetry.update();
        }
    }
}
