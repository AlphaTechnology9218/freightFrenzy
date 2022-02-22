package org.firstinspires.ftc.teamcode.controles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.habilidades.DormirRobo;

@TeleOp(name = "ControlePato_Base", group = "TeleOp_Algorithms")

public class ControlePato extends OpMode {

    private Servo servoPato;
    public boolean ativador;

    @Override
    public void init() {
        telemetry.addData("Status", "Iniciado");

        servoPato = hardwareMap.get(Servo.class, "servoPato");
    }

    @Override
    public void start() {
        ativador = true;
    }

    @Override
    public void loop() {
        DerrubarPato();
    }

    @Override
    public void stop() {
        telemetry.addData("Status", "Finalizado");
    }

    private void DerrubarPato() {
        DormirRobo dormir = new DormirRobo();

        if (gamepad2.y) {
            dormir.SleepRobo(500);
            ativador = false;
            dormir.SleepRobo(500);

            if (ativador) {
                servoPato.setPosition(1);
            } else {
                servoPato.setPosition(0);
            }
        } else {
        }
    }
}
