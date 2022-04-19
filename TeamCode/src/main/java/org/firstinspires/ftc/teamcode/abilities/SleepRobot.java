package org.firstinspires.ftc.teamcode.abilities;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
public class SleepRobot {

    public void robotSleeping(int tMS) {
        try {
            Thread.sleep(tMS);
        } catch (Exception ignored) {
        }
    }
}

