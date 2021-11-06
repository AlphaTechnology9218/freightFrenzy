package org.firstinspires.ftc.teamcode.controles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "ControleGarra_Base", group = "TeleOp_Algorithms")

public class ControleGarra extends OpMode
{
    private Servo servoDireita, servoEsquerda;
    private DcMotor motorBracoDireita, motorBracoEsquerda;
    private DistanceSensor sensorDistancia;

    public boolean ativadorMotor, ativadorServo;
    public int bracoDirPosicao, bracoEsqPosicao;
    public double valorDistancia;

    @Override
    public void init() {
        telemetry.addData("Status", "Iniciado");

        servoDireita = hardwareMap.get(Servo.class, "servoDireita");
        servoEsquerda = hardwareMap.get(Servo.class, "servoEsquerda");
        motorBracoDireita = hardwareMap.get(DcMotor.class, "motorBracoDireita");
        motorBracoEsquerda = hardwareMap.get(DcMotor.class, "motorBracoEsquerda");
        sensorDistancia = hardwareMap.get(DistanceSensor.class, "sensorDistancia");

        motorBracoDireita.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBracoEsquerda.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorBracoDireita.setDirection(DcMotor.Direction.REVERSE);
        motorBracoEsquerda.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void start() {
        ativadorMotor = true;
        ativadorServo = true;
    }

    @Override
    public void loop() {
        CapturarDistancia();

        if (valorDistancia <= 31 && valorDistancia >= 30)
        {
            telemetry.addData("Distância Ideal", valorDistancia);

            ControleGarra();
            ControleBracos();
        } else {
            assert true;
        }
    }

    @Override
    public void stop() {telemetry.addData("Status", "Finalizado");}

    private void ControleBracos() {
        if (ativadorMotor && gamepad2.b) {
            Mov(500, 500, 1);
            ativadorMotor = false;
        } else if (!ativadorMotor && gamepad2.b) {
            Mov(-500, -500, 1);
            ativadorMotor = true;
        } else {
            Mov(-500, -500, 1);
        }
    }

    private void ControleGarra() {
        if (ativadorServo && gamepad2.x) {
            servoDireita.setPosition(1);
            servoEsquerda.setPosition(1);
            ativadorServo = false;
        } else if (!ativadorServo && gamepad2.x) {
            servoDireita.setPosition(-1);
            servoEsquerda.setPosition(-1);
            ativadorServo = true;
        } else {
            servoDireita.setPosition(0);
            servoEsquerda.setPosition(0);
        }
    }

    private void Mov(int bracoDirTarget, int bracoEsqTarget, double vel) {
        bracoDirPosicao += bracoDirTarget;
        bracoEsqPosicao += bracoEsqTarget;

        motorBracoDireita.setTargetPosition(bracoDirPosicao);
        motorBracoEsquerda.setTargetPosition(bracoEsqPosicao);

        motorBracoDireita.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBracoEsquerda.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorBracoDireita.setPower(vel);
        motorBracoEsquerda.setPower(vel);

        while (motorBracoDireita.isBusy() && motorBracoEsquerda.isBusy()) {
            assert true;
        }
    }

    private void CapturarDistancia() {
        valorDistancia = sensorDistancia.getDistance(DistanceUnit.CM);
        telemetry.addData("Distância: ", valorDistancia);
        telemetry.update();
    }
}
