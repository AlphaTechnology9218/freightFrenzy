package org.firstinspires.ftc.teamcode.controllers.claw;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Arrays;
import java.util.List;

public class ClawControlComponents extends OpMode {
    protected Servo s1, s2, s3, s4, s5, s6, objectServo;

    @Override
    public void init() {
        List<Servo> servoComponents = Arrays.asList(s1, s2, s3, s4, s5, s6);
        for (int i = 0; i < servoComponents.size(); i++) {
            objectServo = servoComponents.get(i);
            objectServo = hardwareMap.get(Servo.class, "servo " + servoComponents.get(i));
        }
    }

    @Override
    public void loop() {
    }

    protected void servoControl() {

    }
}
