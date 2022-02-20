package org.firstinspires.ftc.teamcode.habilidades;

public class RoboDesligado {

    public void SleepRobo(int tMS) {
        try {
            Thread.sleep(tMS);
        } catch (Exception ignored) {
        }
    }
}

