package org.firstinspires.ftc.teamcode.controllers.claw;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot_components.ServoComponents;

public class CompleteClawAutonomous extends LinearOpMode {

    static final double START_POS_1 = 0.5;
    static final double INCREMENT_1 = 0.01;
    static final double MAX_POS_1 = 1;
    static final double MIN_POS_1 = 0;

    static final double START_POS_2 = 0.5;
    static final double INCREMENT_2 = 0.01;
    static final double MAX_POS_2 = 1;
    static final double MIN_POS_2 = 0;

    static final double START_POS_3 = 0.5;
    static final double INCREMENT_3 = 0.01;
    static final double MAX_POS_3 = 1;
    static final double MIN_POS_3 = 0;

    static final double START_POS_4 = 0.5;
    static final double INCREMENT_4 = 0.01;
    static final double MAX_POS_4 = 1;
    static final double MIN_POS_4 = 0;

    double position1, position2, position3, position4;

    Servo s1, s2, s3, s4;
    ServoComponents servo = new ServoComponents();

    @Override
    public void runOpMode() {
        /*
         * Autonomous Control step-by-step:
         *
         * 1. Down claw
         * 2. Open claw
         * 3. Close claw
         * 4. Up claw
         * 5. Down claw
         * 6. Open claw
         * 7. Close claw
         * 8. Up claw
         * */

        servo.init();
        setStartPos();
        setupServos();

        s3.setPosition(MIN_POS_3);     // 1. Down claw
        position3 -= INCREMENT_3;
        if (position3 <= MIN_POS_3) {
            s1.setPosition(MAX_POS_1); // 2. Open claw
            position1 += INCREMENT_1;
        }
        if (position1 >= MAX_POS_1) {
            s1.setPosition(MIN_POS_1); // 3. Close claw
            position1 -= INCREMENT_1;
            s3.setPosition(MAX_POS_3); // 4. Up claw
            position3 += INCREMENT_3;
        }
        if (position1 <= MIN_POS_1 && position3 >= MAX_POS_3) {
            s4.setPosition(MIN_POS_4); // 5. Down claw
            position4 -= INCREMENT_4;
        }
        if (position4 <= MIN_POS_4) {
            s1.setPosition(MAX_POS_1); // 6. Open claw
            position1 += INCREMENT_1;
        }
        if (position1 >= MAX_POS_1) {
            s1.setPosition(MIN_POS_1); // 7. Close claw
            s4.setPosition(MIN_POS_4); // 8. Up claw
        }
    }
    void setStartPos() {
        s1.setPosition(START_POS_1);
        s2.setPosition(START_POS_2);
        s3.setPosition(START_POS_3);
        s4.setPosition(START_POS_4);
    }
    void setupServos() {
        s1 = servo.completeServos.get(0); // main claw control
        s2 = servo.completeServos.get(1); // claw point control
        s3 = servo.completeServos.get(2); // main arm control
        s4 = servo.completeServos.get(3); // support control
    }
}