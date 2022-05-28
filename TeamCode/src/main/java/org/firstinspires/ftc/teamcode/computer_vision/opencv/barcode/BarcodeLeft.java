package org.firstinspires.ftc.teamcode.computer_vision.opencv.barcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element.HSVColorFilter;
import org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element.YCrCbChannel;
import org.firstinspires.ftc.teamcode.odometry.control.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.odometry.traject.TrajectorySequence;
import org.firstinspires.ftc.teamcode.robot_components.MotorComponentsClaw;
import org.firstinspires.ftc.teamcode.robot_components.SetupWebcam;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name = "Left Barcode", group = "Barcode Detection")
public class BarcodeLeft extends LinearOpMode {
    OpenCvCamera camera;
    HSVColorFilter vision = new HSVColorFilter();
    boolean found = false;
    int level = 0;
    MotorComponentsClaw motors = new MotorComponentsClaw();
    ElapsedTime timer = new ElapsedTime();

    double power = 0.0;           // motor strength
    public static double feedF = 0.326; // feed-forward
    double integralSum;        // integral sum
    public static double Kp = 0.1;
    public static double Ki = 0.1;
    public static double Kd = 0.4;
    double lastError;

    @Override
    public void runOpMode() {
        runPipeline(hardwareMap);
        motors.init(hardwareMap);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(0, 0, 0);

        drive.setPoseEstimate(startPose);

        Trajectory traj1 = drive.trajectoryBuilder(startPose)
                .strafeTo(new Vector2d(18, 15))
                .build();

        double left   = vision.getLeftValue();
        double right  = vision.getRightValue();
        double center = vision.getCenterValue();

        telemetry.update();
        if ((left > right) && (left > center)) {
            hubLevel1();
            found = true;
            level = 3;
        } else if ((center > left) && (center > right)) {
            hubLevel2();
            found = true;
            level = 2;
        } else if ((right > center) && (right > left)) {
            hubLevel3();
            found = true;
            level = 1;
        } else {
            hubDefault(); }

        waitForStart();

        if(isStopRequested()) return;
        if(level > 0){
            power = controlLoop(80, motors.cL.getCurrentPosition());
            runPower();
            sleep(2000);
            power = controlLoop(160, motors.cL.getCurrentPosition());
            runPower();
            sleep(2000);
            if(level > 1){
                power = controlLoop(180, motors.cL.getCurrentPosition());
                runPower();
                sleep(2000);
                if(level > 2){
                    power = controlLoop(200, motors.cL.getCurrentPosition());
                    runPower();
                    sleep(2000);
                }
            }
        }
        drive.followTrajectory(traj1);
        motors.take.setPower(-1);
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

    public double controlLoop(double SP, double PV) {
        timer.reset();
        double error = SP - PV;
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) * timer.seconds();
        lastError = error;

        timer.reset();

        return (error * Kp) + (integralSum * Ki) + (derivative * Kd) + (Math.cos(SP) * feedF);
    }

    public void runPower() {
        motors.cL.setPower(power);
        motors.cR.setPower(power);
    }

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
