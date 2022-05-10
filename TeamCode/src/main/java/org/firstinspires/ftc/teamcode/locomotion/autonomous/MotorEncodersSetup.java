package org.firstinspires.ftc.teamcode.locomotion.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

@Disabled
@Autonomous(name = "Locomotion Encoders", group = "Hardware Configuration")
public class MotorEncodersSetup extends LinearOpMode {
    MotorComponents motors = new MotorComponents();
    private int pFL, pBL, pFR, pBR;
    /**************************************************************************
     * pFL - front left motor position                                        *
     * pBL - back left motor position                                         *
     * pFR - front right motor position                                       *
     * pBR - back right motor position                                        *
     **************************************************************************/

    @Override
    public void runOpMode() { motors.init(hardwareMap); }

    /**
     * Move robot using encoders while motors aren't busy
     * @param tLF - left front target
     * @param tBL - back left target
     * @param tRF - right front target
     * @param tBR - back right target
     * @param vel - velocity
     */
    public void moveRobot(int tLF, int tBL, int tRF, int tBR, double vel) {
        motors.mFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motors.mBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motors.mFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motors.mBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        pBR += tRF;
        pBL += tLF;
        pFR += tBR;
        pFL += tBL;

        motors.mFL.setTargetPosition(pFL);
        motors.mBL.setTargetPosition(pBL);
        motors.mFR.setTargetPosition(pFR);
        motors.mBR.setTargetPosition(pBR);

        motors.mFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors.mBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors.mFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors.mBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motors.mFL.setPower(vel);
        motors.mBL.setPower(vel);
        motors.mFR.setPower(vel);
        motors.mBR.setPower(vel);

        while (opModeIsActive() && motors.mFL.isBusy() && motors.mBL.isBusy() && motors.mFR.isBusy()
                && motors.mBR.isBusy()) { idle(); }
    }
}