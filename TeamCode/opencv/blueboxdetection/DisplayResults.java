package org.firstinspires.ftc.robotcontroller.opencv.blueboxdetection;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
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
