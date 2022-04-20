package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Display Box Detection Results", group = "Computer Vision")
public class DisplayResults extends LinearOpMode {
    @Override
    public void runOpMode() {
        BasicBlueBoxDetection parameter = new BasicBlueBoxDetection();

        waitForStart();

        telemetry.addData("Lower Value: ", parameter.lowerValue);
        telemetry.addData("Upper Value: ", parameter.upperValue);
        telemetry.addData("Seeing Box: ", parameter.seeingBox);

        telemetry.update();
    }
}