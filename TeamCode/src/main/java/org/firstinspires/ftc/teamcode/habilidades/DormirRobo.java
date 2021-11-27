package org.firstinspires.ftc.teamcode.habilidades;

public class DormirRobo {

    public void SleepRobo(int tMS) {
        try {
            Thread.sleep(tMS);
        } catch (Exception ignored) {
        }
    }
}

