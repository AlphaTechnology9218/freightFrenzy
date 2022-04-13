package org.firstinspires.ftc.teamcode.controllers.gamepad;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class ControlButtons extends OpMode {
    public boolean buttonA;
    public boolean buttonB;
    public boolean buttonX;
    public boolean buttonY;

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        setGamepad2Buttons();
    }

    public void setGamepad2Buttons() {
        buttonA = gamepad2.a;
        buttonB = gamepad2.b;
        buttonX = gamepad2.x;
        buttonY = gamepad2.y;
    }
}
