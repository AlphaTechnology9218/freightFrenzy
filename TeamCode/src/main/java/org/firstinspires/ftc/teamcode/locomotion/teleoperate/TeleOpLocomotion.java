package org.firstinspires.ftc.teamcode.locomotion.teleoperate;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

public class TeleOpLocomotion extends OpMode {
    MotorComponents motors = new MotorComponents();

    @Override
    public void init() { motors.init(hardwareMap); }

    @Override
    public void loop() {

    }
    /**
     * Set power for each robot motor:
     *
     * @param powLF - front left motor power
     * @param powLB - back left motor power
     * @param powRF - front right motor power
     * @param powRB - back right motor power
     */
    public void motorPower(float powLF, float powLB, float powRF, float powRB) {
        motors.mFL.setPower(powLF);
        motors.mBL.setPower(powLB);
        motors.mFR.setPower(powRF);
        motors.mBR.setPower(powRB);
    }
}
