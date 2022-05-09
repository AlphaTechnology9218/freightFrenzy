package org.firstinspires.ftc.teamcode.controllers.carousel;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

/***********************
 * Autonomous Control step-by-step:                              *
 *                                                               *
 * 1. Start duck                                                 *
 * 2. Down duck                                                  *
 * 3. Back duck                                                  *
 * *********************/

@Autonomous(name = "Autonomous Claw Control", group = "Controllers")
public class AutonomousCarouselControl extends LinearOpMode {
    private Servo servoDuck;

    static final double INCREMENT = 0.01;
    static final double MAX_POS = 1;
    static final double MIN_POS = 0;

    double position;

    @Override
    public void runOpMode() {
        servoDuck.setPosition(MIN_POS); // Start duck
        sleep(200);
        servoDuck.setPosition(MAX_POS); // Down duck
        position += INCREMENT;
        if (position >= MAX_POS) {
            servoDuck.setPosition(MIN_POS); // Back duck
            position -= INCREMENT;
        }
        if (position <= MIN_POS) telemetry.addData("Duck Status: ", "Down");
    }
}