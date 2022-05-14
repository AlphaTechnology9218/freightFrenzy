package org.firstinspires.ftc.teamcode.initial_tests.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.odometry.control.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.odometry.traject.TrajectorySequence;

@Autonomous(name = "Sequence Test", group = "Odometry Tests")
public class SequenceTrajectory extends LinearOpMode {
    public static double DISTANCE = 10; // in

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-DISTANCE / 2, -DISTANCE / 2, 0);

        drive.setPoseEstimate(startPose);

        waitForStart();

        if (isStopRequested()) return;

        while (!isStopRequested()) {
            TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
                    .turn(Math.toRadians(24.5f))
                    .forward(8)
                    .back(3)
                    .turn(Math.toRadians(25))
                    .forward(48)
                    .turn(Math.toRadians(-25))
                    .strafeLeft(10)
                    .splineToLinearHeading(startPose, 45)
                    .build();
            drive.followTrajectorySequence(trajSeq);
        }
    }
}
