package org.firstinspires.ftc.teamcode.initial_tests.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.odometry.control.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.odometry.traject.TrajectorySequence;
import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

@Autonomous(name = "Sequence Test", group = "Odometry Tests")
public class SequenceTrajectory extends LinearOpMode {
    public static double DISTANCE = 10; // in
    MotorComponents motors = new MotorComponents();

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        motors.init(hardwareMap);

        Pose2d startPose = new Pose2d(-DISTANCE / 2, -DISTANCE / 2, 0);

        drive.setPoseEstimate(startPose);

        waitForStart();

        if (isStopRequested()) return;

        while (!isStopRequested()) {
            TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
                    .turn(Math.toRadians(44.6f))
                    .forward(8)
                    .build();
            drive.followTrajectorySequence(trajSeq);
            motors.mD.setPower(1);
        }
    }
}
