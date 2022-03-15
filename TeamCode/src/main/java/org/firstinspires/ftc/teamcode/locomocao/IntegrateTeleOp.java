package org.firstinspires.ftc.teamcode.locomocao;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class IntegrateTeleOp extends OpMode {
    public DcMotor mFE, mTE, mFD, mTD;
    /**************************************************************************
     * mFE - Variável motor frente esquerda                                   *
     * mTE - Variável motor tras esquerda                                     *
     * mFD - Variável motor frente direita                                    *
     * mTD - Variável motor tras direita                                      *
     **************************************************************************/
    private final ElapsedTime runtime = new ElapsedTime();
    // HardwareTeleOp motor = new HardwareTeleOp(); // Objeto de acesso dos componentes do OpMode

    @Override
    public void init() {
        telemetry.addData("Status", "TeleOp Iniciado");

        mFE = hardwareMap.get(DcMotor.class, "motorFE");
        mTE = hardwareMap.get(DcMotor.class, "motorTE");
        mFD = hardwareMap.get(DcMotor.class, "motorFD");
        mTD = hardwareMap.get(DcMotor.class, "motorTD");

        mFE.setDirection(DcMotor.Direction.REVERSE);
        mTE.setDirection(DcMotor.Direction.REVERSE);
        mFD.setDirection(DcMotor.Direction.FORWARD);
        mTD.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void start() { runtime.reset(); }

    @Override
    public void loop() {
        telemetry.addData("Status", "TeleOp Executando: " + runtime.toString());

        axisXY();
        axisXYAdjusts();
        round();
        axisZ();
        roundX();

        telemetry.update();
    }


    private void axisXY() {
        motorPower(-gamepad1.left_stick_x, gamepad1.left_stick_x, gamepad1.left_stick_x, -gamepad1.left_stick_x);
        motorPower(-gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y);
    }

    private void axisXYAdjusts() {
        if (gamepad1.dpad_up) {
            motorPower(0.75f, 0.75f, 0.75f, 0.75f);
        }
        if (gamepad1.dpad_down) {
            motorPower(-0.75f, -0.75f, -0.75f, -0.75f);
        }
        if (gamepad1.dpad_right) {
            motorPower(0.75f, -0.75f, -0.75f, 0.75f);
        }
        if (gamepad1.dpad_left) {
            motorPower(-0.75f, 0.75f, 0.75f, -0.75f);
        }
    }

    private void round() {
        if (gamepad1.left_bumper) {
            motorPower(1, 1, -1, -1);
        }
        if (gamepad1.right_bumper) {
            motorPower(-1, -1, 1, 1);
        }
    }

    private void axisZ() {
        motorPower(0, gamepad1.left_trigger,0, gamepad1.right_trigger);
        motorPower(gamepad1.right_trigger, 0, 0, gamepad1.right_trigger);
    }

    private void roundX() {
        if (gamepad1.b) {
            motorPower(1,1,0,0);
        }
        if (gamepad1.x) {
            motorPower(0,0,1,1);
        }
    }

    void motorPower(float powLF, float powLB, float powRF, float powRB) {
        mFE.setPower(powLF);
        mTE.setPower(powLB);
        mFD.setPower(powRF);
        mTD.setPower(powRB);
    }

    @Override
    public void stop() {
        telemetry.addData("Status", "TeleOp Finalizado");
    }
}
