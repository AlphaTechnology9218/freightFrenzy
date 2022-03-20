package org.firstinspires.ftc.teamcode.habilidades;

public class SleepRobot {

    public void robotSleeping(int tMS) {
        try {
            Thread.sleep(tMS);
        } catch (Exception ignored) {
        }
    }
}

