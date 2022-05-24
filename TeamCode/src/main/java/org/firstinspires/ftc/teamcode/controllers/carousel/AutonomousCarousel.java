package org.firstinspires.ftc.teamcode.controllers.carousel;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

@Autonomous(name = "Autonomous Carousel Control", group = "Controllers")
public class AutonomousCarousel extends LinearOpMode {
    MotorComponents motors = new MotorComponents();
    public double position;

    @Override
    public void runOpMode() {
        motors.init(hardwareMap);
        float power = 0.5F;
        int i;
        for (i = 0; i < 5; i++) {
            motors.mDDR.setPower(power);
            motors.mDDL.setPower(power);
            power += 0.1;
            i++;
        }
    }
}