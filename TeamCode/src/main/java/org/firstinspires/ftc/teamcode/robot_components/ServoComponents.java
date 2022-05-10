package org.firstinspires.ftc.teamcode.robot_components;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoComponents extends OpMode {
    private Servo sOC, sUD;
    /**************************************************************************
     * mFL - front left motor                                                 *
     * mBL - back left motor                                                  *
     * mFR - front right motor                                                *
     * mBR - back right motor                                                 *
     * mDDR - motors to take the duck down right                              *
     * mDDL - motors to take the duck down left                               *
     * ************************************************************************/

    public void init(HardwareMap hardwareMap) {
        sOC = hardwareMap.get(Servo.class, "sOC");
        sUD = hardwareMap.get(Servo.class, "sUD");
    }

    @Override
    public void init() {
        sOC.setDirection(Servo.Direction.FORWARD);
        sUD.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void loop() {
    }
}