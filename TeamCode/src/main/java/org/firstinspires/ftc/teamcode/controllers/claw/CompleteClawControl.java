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
            // Call Claw Movement Functions
            mainClawControl();
            clawPointControl();
            mainArmControl();
            supportControl();

            // Call Servo Slews Functions
            slewServo1();
            slewServo2();
            slewServo3();
            slewServo4();

            // Display the current value for each servo
            telemetry.addData("Servo position1", "%5.2f", position1);
            telemetry.addData("Servo position1", "%5.2f", position2);
            telemetry.addData("Servo position1", "%5.2f", position3);
            telemetry.addData("Servo position1", "%5.2f", position4);

            telemetry.addData(">", "Press Stop to end test." );
            telemetry.update();
        }
    }

    /**
     * Start each servo to init position
     */
    protected void initialControl() {
        if (!rampUp && gamepad2.right_bumper) {
            servo.s1.setPosition(position1);
            servo.s2.setPosition(position2);
            servo.s3.setPosition(position3);
            servo.s4.setPosition(position4);
        }
    }
    /**
     * Control each claw component according to gamepad control
     */
    protected void mainClawControl() {
        if (!rampUp1 && gamepad2.y) {
            servo.s1.setPosition(MAX_POS_S1);
            rampUp1 = true;
            servo.s1.setPosition(position1);
        }
    }
    protected void clawPointControl() {
        if (!rampUp2 && gamepad2.y) {
            servo.s2.setPosition(MAX_POS_S2);
            rampUp2 = true;
            servo.s1.setPosition(position1);
        }
    }
    protected void mainArmControl() {
        if (!rampUp3 && gamepad2.y) {
            servo.s3.setPosition(MAX_POS_S3);
            rampUp3 = true;
            servo.s1.setPosition(position1);
        }
    }
    protected void supportControl() {
        if (!rampUp4 && gamepad2.y) {
            servo.s4.setPosition(MAX_POS_S4);
            rampUp4 = true;
            servo.s1.setPosition(position1);
        }
    }

    /**
     * Slew each servo according to MAX_POS and MIN_POS
     */
    protected void slewServo1() {
        if (rampUp1) {
            position1 += INCREMENT_S1;
            if (position1 >= MAX_POS_S1) {
                position1 = MAX_POS_S1;
                rampUp1 = false;
            }
        } else {
            position1 -= INCREMENT_S1;
            if (position1 <= MIN_POS_S1) {
                position1 = MAX_POS_S1;
            }
        }
    }
    protected void slewServo2() {
        if (rampUp2) {
            position2 += INCREMENT_S2;
            if (position2 >= MAX_POS_S2) {
                position2 = MAX_POS_S2;
                rampUp2 = false;
            }
        } else {
            position1 -= INCREMENT_S2;
            if (position2 <= MIN_POS_S2) {
                position2 = MAX_POS_S2;
            }
        }
    }
    protected void slewServo3() {
        if (rampUp3) {
            position1 += INCREMENT_S3;
            if (position3 >= MAX_POS_S3) {
                position3 = MAX_POS_S3;
                rampUp4 = false;
            }
        } else {
            position3 -= INCREMENT_S3;
            if (position3 <= MIN_POS_S3) {
                position3 = MAX_POS_S3;
            }
        }
    }
    protected void slewServo4() {
        if (rampUp4) {
            position4 += INCREMENT_S4;
            if (position4 >= MAX_POS_S4) {
                position4 = MAX_POS_S4;
                rampUp4 = false;
            }
        } else {
            position1 -= INCREMENT_S4;
            if (position4 <= MIN_POS_S4) {
                position4 = MAX_POS_S4;
            }
        }
    }
}