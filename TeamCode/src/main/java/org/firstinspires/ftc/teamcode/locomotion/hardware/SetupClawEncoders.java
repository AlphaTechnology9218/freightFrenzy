package org.firstinspires.ftc.teamcode.locomotion.hardware;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Claw Encoders", group = "Hardware Configuration")
@Disabled
public class SetupClawEncoders extends LinearOpMode {
    private DcMotor mRA, mLA;
    public int pRA, pLA;
    /**************************************************************************
     * mRA - right arm motor                                                  *
     * mLA - left arm motor                                                   *
     * pRA - right motor position                                             *
     * pLA - left motor position                                              *
     **************************************************************************/

    @Override
    public void runOpMode() {
        mRA = hardwareMap.get(DcMotor.class, "mRA");
        mLA = hardwareMap.get(DcMotor.class, "mLA");
        mRA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mLA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mRA.setDirection(DcMotor.Direction.REVERSE);
        mLA.setDirection(DcMotor.Direction.FORWARD);
    }

    /**
     * @param tRA - target right arm
     * @param tLA - target left arm
     * @param vel - claw's arms velocity
     */
    public void moveClaw(int tRA, int tLA, double vel) {
        pRA += tRA;
        pLA += tLA;
        mRA.setTargetPosition(pRA);
        mLA.setTargetPosition(pLA);
        mRA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mLA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mRA.setPower(vel);
        mLA.setPower(vel);

        while (mRA.isBusy() && mLA.isBusy()) { idle(); }
    }
}
