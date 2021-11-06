package org.firstinspires.ftc.teamcode.habilidades;

public class DormirRobo {

    public void SleepRobo(int tempoMilisegundos) {
        try {
            Thread.sleep(tempoMilisegundos);
        } catch (Exception ignored) {
        }
    }
}

