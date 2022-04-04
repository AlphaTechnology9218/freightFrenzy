package org.firstinspires.ftc.teamcode.computer_vision.opencv.blueboxdetection;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class DisplayResults extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        BasicBlueBoxDetection parameter = new BasicBlueBoxDetection();
        telemetry("Lower Result: ", parameter.lowerValue);
        telemetry("Upper Result: ", parameter.upperValue);
    }

    private void telemetry(String s, double upperValue) {
    }
}
