package org.firstinspires.ftc.teamcode.controllers.claw;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot_components.ServoComponents;

public class CompleteClawControl extends LinearOpMode {

    static final double INCREMENT_S1 = 0.01; // amount to slew servo each CYCLE_MS cycle
    static final double MAX_POS_S1   =  1.0; // Maximum rotational position
    static final double MIN_POS_S1   =  0.0; // Minimum rotational position

    static final double INCREMENT_S2 = 0.01;
    static final double MAX_POS_S2   =  1.0;
    static final double MIN_POS_S2   =  0.0;

    static final double INCREMENT_S3 = 0.01;
    static final double MAX_POS_S3   =  1.0;
    static final double MIN_POS_S3   =  0.0;

    static final double INCREMENT_S4 = 0.01;
    static final double MAX_POS_S4   =  1.0;
    static final double MIN_POS_S4   =  0.0;

    double position1 = (MAX_POS_S1 - MIN_POS_S1) / 2; // Start at halfway position
    double position2 = (MAX_POS_S2 - MIN_POS_S2) / 2; // Start at halfway position
    double position3 = (MAX_POS_S3 - MIN_POS_S3) / 2; // Start at halfway position
    double position4 = (MAX_POS_S4 - MIN_POS_S4) / 2; // Start at halfway position

    boolean rampUp  = false;
    boolean rampUp1 = false;
    boolean rampUp2 = false;
    boolean rampUp3 = false;
    boolean rampUp4 = false;

    ServoComponents servo = new ServoComponents();

    @Override
    public void runOpMode() {
        servo.setupServos();
        initialControl();

        waitForStart();
        while(opModeIsActive()) {
            clawPointControl();
            mainClawControl();
            mainArmControl();
            supportControl();

            // Display the current value for each servo
            telemetry.addData("Servo position 1", "%5.2f", position1);
            telemetry.addData("Servo position 2", "%5.2f", position2);
            telemetry.addData("Servo position 3", "%5.2f", position3);
            telemetry.addData("Servo position 4", "%5.2f", position4);

            telemetry.addData(">", "Press Stop to end test." );
            telemetry.update();

            if (rampUp1) {
                controlSlew(position1, INCREMENT_S1, MAX_POS_S1, MIN_POS_S1);
            } else if (rampUp2) {
                controlSlew(position2, INCREMENT_S2, MAX_POS_S2, MIN_POS_S2);
            } else if (rampUp3) {
                controlSlew(position3, INCREMENT_S3, MAX_POS_S3, MIN_POS_S3);
            } else if (rampUp4) {
                controlSlew(position4, INCREMENT_S4, MAX_POS_S4, MIN_POS_S4);
            }
        }
    }
    // Start each servo to init position
    protected void initialControl() {
        if (!rampUp && gamepad2.right_bumper) {
            servo.completeServos.get(0).setPosition(position1);
            servo.completeServos.get(1).setPosition(position2);
            servo.completeServos.get(2).setPosition(position3);
            servo.completeServos.get(3).setPosition(position4);
        }
    }
    // Control each claw component according to gamepad control
    protected void mainClawControl() {
        if (!rampUp1 && gamepad2.y) {
            servo.s1.setPosition(MAX_POS_S1);
            rampUp1 = true;
            servo.s1.setPosition(position1);
        }
    }
    protected void clawPointControl() {
        if (!rampUp2 && gamepad2.x) {
            servo.s2.setPosition(MAX_POS_S2);
            rampUp2 = true;
            servo.s1.setPosition(position1);
        }
    }
    protected void mainArmControl() {
        if (!rampUp3 && gamepad2.b) {
            servo.s3.setPosition(MAX_POS_S3);
            rampUp3 = true;
            servo.s1.setPosition(position1);
        }
    }
    protected void supportControl() {
        if (!rampUp4 && gamepad2.a) {
            servo.s4.setPosition(MAX_POS_S4);
            rampUp4 = true;
            servo.s1.setPosition(position1);
        }
    }
    /**
     * @param position  - current servo position
     * @param INCREMENT - increment for each movement
     * @param MIN_POS   - minimum servo position
     * @param MAX_POS   - maximum servo position
     */
    protected void controlSlew(double position, double INCREMENT, double MAX_POS,
                               double MIN_POS) {
        position += INCREMENT;
        if (position <= MAX_POS) {
            position = MAX_POS;
            rampUp = false;
        }
        if (!rampUp) {
            position -= INCREMENT;
            if (position <= MIN_POS) {
                position = MIN_POS;
            }
        }
    }
}