package org.firstinspires.ftc.teamcode.robot_components;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.Arrays;
import java.util.List;

@Disabled
@Autonomous(name = "Motor Components", group = "Robot Components")
public class MotorComponents extends LinearOpMode {
    public DcMotor mFL, mBL, mFR, mBR, objectMotor;
    /**************************
     * mFL - front left motor                                                 *
     * mBL - back left motor                                                  *
     * mFR - front right motor                                                *
     * mBR - back right motor                                                 *
     * objectMotor - used in list to store robot components                   *
     * ************************/
    public void runOpMode() throws InterruptedException {
        renameMotor();
        resetEncoders();
    }

    /**
     * @param powLF - front left motor power
     * @param powLB - back left motor power
     * @param powRF - front right motor power
     * @param powRB - back right motor power
     */
    public void motorPower(float powLF, float powLB, float powRF, float powRB) {
        mFL.setPower(powLF);
        mBL.setPower(powLB);
        mFR.setPower(powRF);
        mBR.setPower(powRB);
    }

    public void resetEncoders() {
        mFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void renameMotor() {

        List<DcMotor> motorComponents = Arrays.asList(mFL, mBL, mFR, mBR);
        for (int i = 0; i< motorComponents.size(); i++) {
            objectMotor = motorComponents.get(i);
            objectMotor = hardwareMap.dcMotor.get("motor " + motorComponents.get(i));
        }
        mFL.setDirection(DcMotor.Direction.REVERSE);
        mBL.setDirection(DcMotor.Direction.REVERSE);
        mFR.setDirection(DcMotor.Direction.FORWARD);
        mBR.setDirection(DcMotor.Direction.FORWARD);
    }
}