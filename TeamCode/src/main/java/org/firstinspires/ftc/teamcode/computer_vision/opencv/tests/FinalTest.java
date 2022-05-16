package org.firstinspires.ftc.teamcode.computer_vision.opencv.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.computer_vision.opencv.tests.AnotherTest;
import org.firstinspires.ftc.teamcode.robot_components.SetupCellphone;

@Autonomous
public class FinalTest extends OpMode {
    SetupCellphone camera = new SetupCellphone();
    AnotherTest object = new AnotherTest();

    @Override
    public void init() {
        camera.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (object.test) {
            telemetry.addLine("Left");
        } else {
            telemetry.addLine("Right");
        }

    }
}
