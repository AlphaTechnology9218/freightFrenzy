package org.firstinspires.ftc.teamcode.controllers.claw;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot_components.ServoComponents;

public class CompleteClawControl extends LinearOpMode {

    static final double INCREMENT   = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final int    CYCLE_MS    =   50;     // period of each cycle
    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.0;     // Minimum rotational position

    double position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    boolean rampUp = false;
    ServoComponents servo = new ServoComponents();

    @Override
    public void runOpMode() {
        servo.setupServos();

        waitForStart();
        while(opModeIsActive()) {
            simpleControl();

            if (rampUp) {
                position += INCREMENT;
                if (position >= MAX_POS) {
                    position = MAX_POS;
                    rampUp = false;
                }
            } else {
                position -= INCREMENT;
                if (position <= MIN_POS) {
                    position = MAX_POS;
                    rampUp = false;
                }
            }
            // Display the current value
            telemetry.addData("Servo Position", "%5.2f", position);
            telemetry.addData(">", "Press Stop to end test." );
            telemetry.update();

            // Set the servo to the new position and pause;
            sleep(CYCLE_MS);
            idle();
        }
    }

    protected void simpleControl () {
        if (!rampUp && gamepad2.x) {
            servo.s1.setPosition(position);
            rampUp = true;
        }
    }
}
