package org.firstinspires.ftc.teamcode.robot_components;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServoComponentsComplexClaw extends OpMode {
    public Servo s1, s2, s3, s4, objectServo;
    public List<Servo> completeServos = new ArrayList<>();
    /**************************************************************************
     * s1, s2, s3, s4 - robot servo components                                *
     * objectMotor - used in list to store robot components                   *
     * completeServos - complete list of named servos                         *
     * ************************************************************************/

    @Override
    public void init() {
        mapServos();
        setupServos();
    }
    @Override
    public void loop() { assert true; }

    /* Map and name each claw component */
    public void mapServos() {
        List<Servo> servoComponents = Arrays.asList(s1, s2, s3, s4);
        for (int i = 0; i < servoComponents.size(); i++) {
            objectServo = servoComponents.get(i);
            objectServo = hardwareMap.get(Servo.class, "servo " + servoComponents.get(i));
            completeServos.add(objectServo);
        }
    }
    /* Address each mapped servo component to a variable */
    public void setupServos() {
        s1 = completeServos.get(0); // Main Claw Control
        s2 = completeServos.get(1); // Claw Point Control
        s3 = completeServos.get(2); // Main Arm Control
        s4 = completeServos.get(3); // Support Control
    }
}