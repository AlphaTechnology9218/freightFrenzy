package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot_components.SetupCellphone;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name = "OpenCV Robot Locomotion", group = "Computer Vision")
public class LocomotionFromResult extends LinearOpMode {
    SetupCellphone camera = new SetupCellphone();
    @Override
    public void runOpMode() {
        camera.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Status", "Autonomous from detection running");
            telemetry.update();
        }
    }
}