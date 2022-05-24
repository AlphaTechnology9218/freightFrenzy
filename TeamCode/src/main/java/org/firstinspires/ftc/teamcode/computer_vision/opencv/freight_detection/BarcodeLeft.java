package org.firstinspires.ftc.teamcode.computer_vision.opencv.freight_detection;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element.HSVColorFilter;
import org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element.YCrCbChannel;
import org.firstinspires.ftc.teamcode.odometry.control.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.odometry.traject.TrajectorySequence;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name = "Left Barcode", group = "Barcode Detection")
public class BarcodeLeft extends LinearOpMode {
    OpenCvCamera camera;
    YCrCbChannel vision = new YCrCbChannel();
    boolean found = false;

    @Override
    public void runOpMode() {
        runPipeline(hardwareMap);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-10 / 2, -10 / 2, 0);

        drive.setPoseEstimate(startPose);

        Trajectory traj1 = drive.trajectoryBuilder(startPose)
                .forward(10)
                //.splineTo(new Vector2d(-2, -52), Math.toRadians(46.5))
                //.back(5)
                .build();
        Trajectory traj2 = drive.trajectoryBuilder(startPose)
                .strafeRight(4)
                .build();


        waitForStart();

        while (!found) {
            double left   = vision.getLeftValue();
            double right  = vision.getRightValue();
            double center = vision.getCenterValue();

            telemetry.update();
            if ((left > right) && (left > center)) {
                hubLevel1();
                found = true;
            } else if ((center > left) && (center > right)) {
                hubLevel2();
                found = true;
            } else if ((right > center) && (right > left)) {
                hubLevel3();
                found = true;
            } else {
                hubDefault(); }
                // 2° strategy: duck control
        }
        if(isStopRequested()) return;
        drive.followTrajectory(traj1);
        drive.followTrajectory(traj2);

    }

    public void runPipeline(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        camera.setPipeline(vision);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);
                camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {
                telemetry.addData("Status", "An error occurred with OpenCV!");
            }
        });
    } // camera initialization

    //TODO: Implement claw's movement for each hub level
    public void hubLevel1() { telemetry.addLine("Left"); }
    public void hubLevel2() {
        telemetry.addLine("Center");
    }
    public void hubLevel3() {
        telemetry.addLine("Right");
    }
    public void hubDefault() {
        telemetry.addLine("None");
    }
}
