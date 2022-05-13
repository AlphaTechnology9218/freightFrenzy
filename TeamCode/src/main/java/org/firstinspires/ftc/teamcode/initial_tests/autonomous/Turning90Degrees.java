package org.firstinspires.ftc.teamcode.initial_tests.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.odometry.control.SampleMecanumDrive;

public class Turning90Degrees extends LinearOpMode {
        public static double ANGLE = 90;

        @Override
        public void runOpMode() throws InterruptedException {
            SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

            waitForStart();

            if (isStopRequested()) return;

            drive.turn(Math.toRadians(ANGLE));
        }
    }
