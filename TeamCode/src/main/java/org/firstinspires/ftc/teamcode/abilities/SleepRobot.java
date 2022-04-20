package org.firstinspires.ftc.teamcode.abilities;

public class SleepRobot {

    public void robotSleeping(int tMS) {
        try {
            Thread.sleep(tMS);
        } catch (Exception ignored) {
        }
    }
}

