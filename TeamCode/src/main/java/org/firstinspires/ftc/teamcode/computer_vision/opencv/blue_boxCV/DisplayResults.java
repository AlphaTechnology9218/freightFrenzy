package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_boxCV;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class DisplayResults extends LinearOpMode {
    @Override
    public void runOpMode() {
        BasicBlueBoxDetection parameter = new BasicBlueBoxDetection();
        telemetry("Lower Result: ", parameter.lowerValue);
        telemetry("Upper Result: ", parameter.upperValue);

        telemetry.update();
    }

    private void telemetry(String s, double lowerValue) {
    }

}