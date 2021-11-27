package org.firstinspires.ftc.teamcode.controles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.habilidades.SensorDistancia;
import org.firstinspires.ftc.teamcode.locomocao.EncodersMovimentacaoGarra;

@TeleOp(name = "ControleGarra_Base", group = "TeleOp_Algorithms")

public class ControleGarra extends OpMode {
    private Servo sD, sE;
    public boolean atvM, atvS;

    SensorDistancia sensorDis = new SensorDistancia();
    EncodersMovimentacaoGarra garra = new EncodersMovimentacaoGarra();

    /*
     **************************************************************************
     * sD - Variável servo direita                                            *
     * sE - Variável servo esquerda                                           *
     * atvM - Variavel ativação servo                                         *
     * atvS - Variavel ativação motor                                         *
     *                                                                        *
     * sensorDis - Objeto de acesso ao código do sensor de distância          *
     * garra - Objeto de acesso ao código do de movimentação da garra         *
     **************************************************************************
     */

    @Override
    public void init() {
        telemetry.addData("Status", "Iniciado");

        sD = hardwareMap.get(Servo.class, "sD");
        sE = hardwareMap.get(Servo.class, "sE");
    }

    @Override
    public void start() {
        atvM = true;
        atvS = true;
    }

    @Override
    public void loop() {
        sensorDis.capturarDistancia();
        if (sensorDis.vDist <= 31 && sensorDis.vDist >= 30) {
            telemetry.addData("Distância Ideal", sensorDis.vDist);

            controleGarra();
            controleBracos();
        } else {
            assert true;
        }
    }

    @Override
    public void stop() { telemetry.addData("Status", "Finalizado"); }

    private void controleBracos() {
        if (atvM && gamepad2.b) {
            garra.moverGarra(500, 500, 1);
            atvM = false;
        } else if (!atvM && gamepad2.b) {
            garra.moverGarra(-500, -500, 1);
            atvM = true;
        } else {
            garra.moverGarra(-500, -500, 1);
        }
    }

    private void controleGarra() {
        if (atvS && gamepad2.x) {
            sD.setPosition(1);
            sE.setPosition(1);
            atvS = false;
        } else if (!atvS && gamepad2.x) {
            sD.setPosition(-1);
            sE.setPosition(-1);
            atvS = true;
        } else {
            sD.setPosition(0);
            sE.setPosition(0);
        }
    }
}
