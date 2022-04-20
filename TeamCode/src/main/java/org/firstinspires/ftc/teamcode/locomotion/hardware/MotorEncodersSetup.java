package org.firstinspires.ftc.teamcode.locomotion.hardware;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

@Disabled
@Autonomous(name = "Locomotion Encoders", group = "Hardware Configuration")
public class MotorEncodersSetup extends LinearOpMode {
    MotorComponents motor = new MotorComponents();
    private int pFL, pBL, pFR, pBR;
     /*************************************************************************
     * pFL - front left motor position                                        *
     * pBL - back left motor position                                         *
     * pFR - front right motor position                                       *
     * pBR - back right motor position                                        *
     **************************************************************************/

    @Override
    public void runOpMode() {
        motor.renameMotor();
        motor.resetEncoders();
    }

    /**
     * Move robot using encoders while motors aren't busy
     * @param tLF - left front target
     * @param tBL - back left target
     * @param tRF - right front target
     * @param tBR - back right target
     * @param vel - velocity
     */
    public void moveRobot(int tLF, int tBL, int tRF, int tBR, double vel) {
        pBR += tRF;
        pBL += tLF;
        pFR += tBR;
        pFL += tBL;

        motor.mFL.setTargetPosition(pFL);
        motor.mBL.setTargetPosition(pBL);
        motor.mFR.setTargetPosition(pFR);
        motor.mBR.setTargetPosition(pBR);

        motor.mFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.mBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.mFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.mBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor.mFL.setPower(vel);
        motor.mBL.setPower(vel);
        motor.mFR.setPower(vel);
        motor.mBR.setPower(vel);

        while (opModeIsActive() && motor.mFL.isBusy() && motor.mBL.isBusy() && motor.mFR.isBusy()
                && motor.mBR.isBusy()) { idle(); }
    }
}