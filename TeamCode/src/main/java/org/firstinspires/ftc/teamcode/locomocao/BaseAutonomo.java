package org.firstinspires.ftc.teamcode.locomocao;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "BaseAutonomo", group = "AlgoritmosAutonomo")
public class BaseAutonomo extends LinearOpMode {

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
