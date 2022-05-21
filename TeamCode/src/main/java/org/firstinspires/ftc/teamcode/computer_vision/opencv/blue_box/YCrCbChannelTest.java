package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element.YCrCbChannel;

@Autonomous
public class YCrCbChannelTest extends OpMode {
    YCrCbChannel vision = new YCrCbChannel();

    @Override
    public void init() {
        vision.init(hardwareMap);

        if (vision.targetPos == 1) {
            telemetry.addLine("The Object is on the Left");
        } else if (vision.targetPos == 2) {
            telemetry.addLine("The Object ios on the Right");
        } else if (vision.targetPos == 3) {
            telemetry.addLine("The object is on the center");
        } else {
            telemetry.addLine("There are no objects");
        }
    }

    @Override
    public void loop() {

    }
}
