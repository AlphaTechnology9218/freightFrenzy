package org.firstinspires.ftc.teamcode.main.tele_operate;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

public class TeleOperate extends OpMode {
    ElapsedTime timer = new ElapsedTime();

    @Override
    public void init() {
        timer.startTime();
    }

    @Override
    public void loop() {
        if (timer.time(TimeUnit.SECONDS) == 120) {
            // End Game
        }
    }
}
