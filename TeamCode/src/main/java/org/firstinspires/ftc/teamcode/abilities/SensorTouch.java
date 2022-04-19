package org.firstinspires.ftc.teamcode.abilities;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@Disabled
public class SensorTouch extends LinearOpMode {
    public boolean isPressed;
    DigitalChannel digitalTouch;

    @Override
    public void runOpMode() throws InterruptedException {
        digitalTouch = hardwareMap.get(DigitalChannel.class, "Digital Sensor");
        digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        waitForStart();

        while(opModeIsActive()) {
            if (digitalTouch.getState()) {
                telemetry.addData("Digital Touch: ", "Is not pressed");
                isPressed = false;
            } else {
                telemetry.addData("Digital Touch: ", "Is pressed");
                isPressed = true;
            }
            telemetry.update();
        }
    }
}
