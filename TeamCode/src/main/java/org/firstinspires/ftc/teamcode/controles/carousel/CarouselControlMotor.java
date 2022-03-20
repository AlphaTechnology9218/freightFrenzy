package org.firstinspires.ftc.teamcode.controles.carousel;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.habilidades.SleepRobot;

@TeleOp(name = "Motor Carousel Control", group = "Controllers")

public class CarouselControlMotor extends OpMode {

    private Servo servoPato;
    public boolean ativador;

    // TODO: Change servo system for Core Hex Motor

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

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
        telemetry.addData("Status", "Finished");
    }

    private void DerrubarPato() {
        SleepRobot dormir = new SleepRobot();

        if (gamepad2.y) {
            dormir.robotSleeping(500);
            ativador = false;
            dormir.robotSleeping(500);

            if (ativador) {
                servoPato.setPosition(1);
            } else {
                servoPato.setPosition(0);
            }
        } else {
        }
    }
}
