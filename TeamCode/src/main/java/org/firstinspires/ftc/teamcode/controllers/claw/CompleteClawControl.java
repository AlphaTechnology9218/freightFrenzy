package org.firstinspires.ftc.teamcode.controllers.claw;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.controllers.gamepad.ControlButtons;

public class CompleteClawControl extends OpMode {
    ClawControlComponents servo = new ClawControlComponents();
    ControlButtons gamepad = new ControlButtons();

    static final double INCREMENT   = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final int    CYCLE_MS    =   50;     // period of each cycle
    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.0;     // Minimum rotational position

    double  position = (MAX_POS - MIN_POS) / 2; // Start at halfway position

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        mainClawControl();
        mainArmControl();
        supportControl();
        clawPointControl();
    }

    protected void mainArmControl() {

    }

    protected void supportControl() {

    }

    protected void clawPointControl() {

    }

    protected void mainClawControl() {

    }
}
