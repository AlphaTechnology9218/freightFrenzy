package org.firstinspires.ftc.teamcode.robot_components;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Disabled
@Autonomous(name = "Servo Components", group = "Robot Components")
public class ServoComponents extends OpMode {
    public Servo s1, s2, s3, s4, objectServo;
    public List<Servo> completeServos = new ArrayList<>();

    @Override
    public void init() { mapServos(); setupServos(); }

    @Override
    public void loop() { assert true; }

    public void mapServos() {
        List<Servo> servoComponents = Arrays.asList(s1, s2, s3, s4);
        for (int i = 0; i < servoComponents.size(); i++) {
            objectServo = servoComponents.get(i);
            objectServo = hardwareMap.get(Servo.class, "servo " + servoComponents.get(i));
            completeServos.add(objectServo);
        }
    }

    public void setupServos() {
        s1 = completeServos.get(0);
        s2 = completeServos.get(1);
        s3 = completeServos.get(2);
        s4 = completeServos.get(3);
    }
}
