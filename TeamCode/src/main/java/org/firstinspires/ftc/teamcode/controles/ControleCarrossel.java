package org.firstinspires.ftc.teamcode.controles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.habilidades.RoboDesligado;

@TeleOp(name = "Controle: Carrossel", group = "Controle")
public class ControleCarrossel extends OpMode {
    private Servo sPato;
    public boolean atv;

    /**************************************************************************
    * sPato - Variável servo                                                  *
    * atv - Variável ativação do servo                                        *
    ***************************************************************************/

    @Override
    public void init() {
        telemetry.addData("Status", "Iniciado");
        sPato = hardwareMap.get(Servo.class, "servoPato");
    }

    @Override
    public void start() {
        atv = true;
    }

    @Override
    public void loop() {
        derrubarPato();
    }

    @Override
    public void stop() {
        telemetry.addData("Status", "Finalizado");
    }

    private void derrubarPato() {
        RoboDesligado dormir = new RoboDesligado(); // Objeto para sleep do robô

        if (gamepad2.y) {
            dormir.SleepRobo(500);
            atv = false;
            dormir.SleepRobo(500);

            if (atv) {
                sPato.setPosition(1);
            } else {
                sPato.setPosition(0);
            }
        } else {
            assert true;
        }
    }
}
