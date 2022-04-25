package org.firstinspires.ftc.teamcode.controllers.claw;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.abilities.SensorTouch;
import org.firstinspires.ftc.teamcode.robot_components.ServoComponents;

/*****************************************************************
 * Autonomous Control step-by-step:                              *
 *                                                               *
 * 1. Down claw                                                  *
 * 2. Open claw                                                  *
 * 3. Close claw                                                 *
 * 4. Up claw                                                    *
 * 5. Down claw                                                  *
 * 6. Open claw                                                  *
 * 7. Close claw                                                 *
 * 8. Up claw                                                    *
 *****************************************************************/

@Autonomous(name = "Autonomous Claw Control", group = "Controllers")
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

    ServoComponents servo = new ServoComponents();
    SensorTouch sensor = new SensorTouch();

    @Override
    public void runOpMode() {
        servo.init();
        startPosition();

        servo.s3.setPosition(MIN_POS_3);     // 1. Down claw
        position3 -= INCREMENT_3;
        if (position3 <= MIN_POS_3) {
            servo.s1.setPosition(MAX_POS_1); // 2. Open claw
            position1 += INCREMENT_1;
        }
        if (position1 >= MAX_POS_1) {
            servo.s1.setPosition(MIN_POS_1); // 3. Close claw
            position1 -= INCREMENT_1;
            servo.s3.setPosition(MAX_POS_3); // 4. Up claw
            position3 += INCREMENT_3;
        }
        sleep(2000); // Wait the robot go to Shipping Hub.

        // Claw will be able to close if the sensor touch is pressed.
        if (position1 <= MIN_POS_1 && position3 >= MAX_POS_3 && sensor.isPressed) {
            servo.s4.setPosition(MIN_POS_4); // 5. Down claw
            position4 -= INCREMENT_4;
        }
        if (position4 <= MIN_POS_4) {
            servo.s1.setPosition(MAX_POS_1); // 6. Open claw
            position1 += INCREMENT_1;
        }
        if (position1 >= MAX_POS_1) {
            servo.s1.setPosition(MIN_POS_1); // 7. Close claw
            servo.s4.setPosition(MIN_POS_4); // 8. Up claw
        }
    }
    void startPosition() {
        servo.s1.setPosition(START_POS_1);
        servo.s2.setPosition(START_POS_2);
        servo.s3.setPosition(START_POS_3);
        servo.s4.setPosition(START_POS_4);
    }
}