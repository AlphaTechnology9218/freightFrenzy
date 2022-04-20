package org.firstinspires.ftc.teamcode.abilities;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@Disabled
@Autonomous(name = "Touch Sensor", group = "Abilities")
public class SensorTouch extends LinearOpMode {
    public boolean isPressed;
    DigitalChannel digitalTouch;

    @Override
    public void runOpMode() {
        digitalTouch = hardwareMap.get(DigitalChannel.class, "Digital Sensor");
        digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        waitForStart();

        while(opModeIsActive()) {
            controlSensorTouch();
            telemetry.update();
        }
    }
    public void controlSensorTouch() {
        if (digitalTouch.getState()) {
            telemetry.addData("Digital Touch: ", "Is not pressed");
            isPressed = false;
        } else {
            telemetry.addData("Digital Touch: ", "Is pressed");
            isPressed = true;
        }
    }
}
