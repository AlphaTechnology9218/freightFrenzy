package org.firstinspires.ftc.teamcode.robot_components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MotorComponentsClaw {
    public DcMotor take, cL, cR;
    /**************************************************************************
     * take - intake motor                                                    *
     * cL   - left arm motor                                                  *
     * cR   - right arm motor                                                 *
     **************************************************************************/

    public void init(HardwareMap hardwareMap){
        /* Motors for intake and arm */
        take = hardwareMap.get(DcMotor.class, "intake");
        cL = hardwareMap.get(DcMotor.class, "clawL");
        cR = hardwareMap.get(DcMotor.class, "clawR");

        take.setDirection(DcMotor.Direction.FORWARD);
        cL.setDirection(DcMotor.Direction.FORWARD);
        cR.setDirection(DcMotor.Direction.FORWARD);

        cL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        cR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        cL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
