/*************************************************************************************************
 *                                           START                                               *
 *************************************************************************************************/
package org.firstinspires.ftc.teamcode.initial_tests.tele_operate;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.abilities.SleepRobot;
/**************************************************************************
 * This class contemplates the following robot's functionalities:         *
 *                                                                        *
 * 1. Locomotion                                                          *
 * 2. Claw Control                                                        *
 * 3. Carousel Control                                                    *
 **************************************************************************/
@TeleOp(name = "Complete TeleOp", group = "Tests")
public class CompleteTeleOp extends OpMode {
    private DcMotor mFL, mBL, mFR, mBR;
    private DcMotor mDD;
    private Servo   mCC, cPC, mAC, spC;
    final ElapsedTime runtime = new ElapsedTime();
    SleepRobot sleep = new SleepRobot();
    private static final double START_POS_1 = 0.5;
    private static final double START_POS_2 = 0.5;
    private static final double START_POS_3 = 0.5;
    private static final double START_POS_4 = 0.5;
    private boolean act = true;
    private boolean act1, act2, act3, act4 = true;
    /***********************************************************************
     * mFL       - (locomotion) front left motor                           *
     * mBL       - (locomotion) back left motor                            *
     * mFR       - (locomotion) front right motor                          *
     * mBR       - (locomotion) back right motor                           *
     * mCC       - (claw) main claw control                                *
     * cPC       - (claw) claw point control                               *
     * mAC       - (claw) main arm control                                 *
     * spC       - (claw) support control                                  *
     * mDD       - (duck) motor to take down the duck                      *
     * runtime   - op mode timer                                           *
     * START_POS - initial position for each servo                         *
     * act       - activate the motor                                      *
     * sleep     - object to access the class to sleep the robot           *
     * act1, act2, act3, act4 - activate servos                            *
     ***********************************************************************/
    @Override
    public void init() {
        telemetry.addData("Status", "TeleOp Initialized");
        mFL = hardwareMap.get(DcMotor.class, "motor FL");
        mBL = hardwareMap.get(DcMotor.class, "motor BL");
        mFR = hardwareMap.get(DcMotor.class, "motor FR");
        mBR = hardwareMap.get(DcMotor.class, "motor BR");
        mFL.setDirection(DcMotor.Direction.REVERSE);
        mBL.setDirection(DcMotor.Direction.REVERSE);
        mFR.setDirection(DcMotor.Direction.FORWARD);
        mBR.setDirection(DcMotor.Direction.FORWARD);
        mDD = hardwareMap.get(DcMotor.class, "motor DD");
        mDD.setDirection(DcMotor.Direction.REVERSE); // Round the motor in the correct direction
        mCC = hardwareMap.get(Servo.class, "servo mCC");
        cPC = hardwareMap.get(Servo.class, "servo cPC");
        mAC = hardwareMap.get(Servo.class, "servo mAC");
        spC = hardwareMap.get(Servo.class, "servo spC");
    }
    @Override
    public void start() { runtime.reset(); }
    @Override
    public void loop() {
        telemetry.addData("Status", "TeleOp Executing: " + runtime.toString());
        axisXY();
        axisXYAdjusts();
        round();
        diagonal();
        roundX();
        takeDownDuck();
        startPosition();
        mainArmControl();
        supportControl();
        mainClawControl();
        clawPointControl();
        telemetry.update();
    }
    /* Locomotion Methods */
    private void axisXY() {
        motorPower(-gamepad1.left_stick_x, gamepad1.left_stick_x, gamepad1.left_stick_x, -gamepad1.left_stick_x);
        motorPower(-gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y);
    }
    private void axisXYAdjusts() {
        if (gamepad1.dpad_up) {
            motorPower(0.75f, 0.75f, 0.75f, 0.75f);
        }
        if (gamepad1.dpad_down) {
            motorPower(-0.75f, -0.75f, -0.75f, -0.75f);
        }
        if (gamepad1.dpad_right) {
            motorPower(0.75f, -0.75f, -0.75f, 0.75f);
        }
        if (gamepad1.dpad_left) {
            motorPower(-0.75f, 0.75f, 0.75f, -0.75f);
        }
    }
    private void round() {
        if (gamepad1.left_bumper) {
            motorPower(-1, -1, 1, 1);
        }
        if (gamepad1.right_bumper) {
            motorPower(1, 1, -1, -1);
        }
    }
    private void diagonal() {
        motorPower(0, gamepad1.left_trigger,gamepad1.left_trigger,0);
        motorPower(gamepad1.right_trigger, 0, 0, gamepad1.right_trigger);
    }
    private void roundX() {
        if (gamepad1.b) {
            motorPower(1,1,0,0);
        }
        if (gamepad1.x) {
            motorPower(0,0,1,1);
        }
    }
    /**
     * @param powFL - front left motor power
     * @param powBL - back left motor power
     * @param powFR - front right motor power
     * @param powBR - back right motor power
     */
    private void motorPower(float powFL, float powBL, float powFR, float powBR) {
        mFL.setPower(powFL);
        mBL.setPower(powBL);
        mFR.setPower(powFR);
        mBR.setPower(powBR);
    }
    /* Duck Control Methods */
    private void takeDownDuck() {
        SleepRobot sleep = new SleepRobot();
        if (gamepad1.y && act) {
            mDD.setPower(1); // Down Duck
            act = false;
            sleep.robotSleeping(500);
        } else if (gamepad1.y) {
            mDD.setPower(0);
            act = true;
            sleep.robotSleeping(500);
        }
    }
    /* Claw Control Methods */
    private void startPosition() {
        mCC.setPosition(START_POS_1);
        cPC.setPosition(START_POS_2);
        mAC.setPosition(START_POS_3);
        spC.setPosition(START_POS_4);
    }
    private void mainClawControl() {
        if (act1 && gamepad2.y) {
            mCC.setPosition(1);
            sleep.robotSleeping(200);
            act1 = false;
        } else if (!act1 && gamepad2.y) {
            mCC.setPosition(0);
            sleep.robotSleeping(200);
            act1 = true;
        }
    }
    private void clawPointControl() {
        if (act2 && gamepad2.x) {
            cPC.setPosition(1);
            sleep.robotSleeping(200);
            act2 = false;
        } else if (!act2 && gamepad2.x) {
            cPC.setPosition(0);
            sleep.robotSleeping(200);
            act2 = true;
        }
    }
    private void mainArmControl() {
        if (act3 && gamepad2.a) {
            mAC.setPosition(1);
            sleep.robotSleeping(200);
            act3 = false;
        } else if (!act3 && gamepad2.a) {
            mAC.setPosition(0);
            sleep.robotSleeping(200);
            act3 = true;
        }
    }
    private void supportControl() {
        if (act4 && gamepad2.b) {
            spC.setPosition(1);
            sleep.robotSleeping(200);
            act4 = false;
        } else if (!act4 && gamepad2.b) {
            spC.setPosition(0);
            sleep.robotSleeping(200);
            act4 = true;
        }
    }
    @Override
    public void stop() {
        telemetry.addData("Status", "TeleOp Finished");
    }
}
/*************************************************************************************************
 *                                           END                                                 *
 *************************************************************************************************/