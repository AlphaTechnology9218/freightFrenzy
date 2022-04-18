package org.firstinspires.ftc.teamcode.robot_components;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServoComponents extends OpMode {
    public Servo s1, s2, s3, s4, objectServo;
    public List<Servo> completeServos = new ArrayList<>();

    @Override
    public void init() {
        setupServos();
    }

    @Override
    public void loop() {
    }

    public void setupServos() {
        List<Servo> servoComponents = Arrays.asList(s1, s2, s3, s4);
        for (int i = 0; i < servoComponents.size(); i++) {
            objectServo = servoComponents.get(i);
            objectServo = hardwareMap.get(Servo.class, "servo " + servoComponents.get(i));
            completeServos.add(objectServo);
        }
    }
}
