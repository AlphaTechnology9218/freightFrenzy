package org.firstinspires.ftc.teamcode.testes_iniciais;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.locomocao.EncodersMovimentacao;

@Autonomous(name = "Teste Rodar a Arena", group = "Auto")
public class RodarArena extends LinearOpMode {

    EncodersMovimentacao robo = new EncodersMovimentacao(); // Objeto para movimentação do robô

    @Override
    public void runOpMode() {
        robo.runOpMode();

        telemetry.addData(">", "Aperte Play para iniciar o op mode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                    rodarArena();
            }
            telemetry.update();
        }
    }

    public void rodarArena(){ robo.moverRobo(500, 500,500,500, 1); }
}
