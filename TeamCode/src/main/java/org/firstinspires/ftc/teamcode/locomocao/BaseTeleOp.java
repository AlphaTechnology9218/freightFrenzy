package org.firstinspires.ftc.teamcode.locomocao;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Base_TeleOp", group = "TeleOp")
public class BaseTeleOp extends OpMode {
    private final ElapsedTime runtime = new ElapsedTime();
    HardwareTeleOp motor = new HardwareTeleOp(); // Objeto de acesso dos componentes do OpMode

    @Override
    public void init() {
        telemetry.addData("Status", "TeleOp Iniciado");
        motor.init();
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
        motor.motorPower(-gamepad1.left_stick_x, gamepad1.left_stick_x, gamepad1.left_stick_x, -gamepad1.left_stick_x);
        motor.motorPower(-gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y);
    }

    private void axisXYAdjusts() {
        if (gamepad1.dpad_up) {
            motor.motorPower(0.75f, 0.75f, 0.75f, 0.75f);
        }
        if (gamepad1.dpad_down) {
            motor.motorPower(-0.75f, -0.75f, -0.75f, -0.75f);
        }
        if (gamepad1.dpad_right) {
            motor.motorPower(0.75f, -0.75f, -0.75f, 0.75f);
        }
        if (gamepad1.dpad_left) {
            motor.motorPower(-0.75f, 0.75f, 0.75f, -0.75f);
        }
    }

    private void round() {
        if (gamepad1.left_bumper) {
            motor.motorPower(1, 1, -1, -1);
        }
        if (gamepad1.right_bumper) {
            motor.motorPower(-1, -1, 1, 1);
        }
    }

    private void axisZ() {
        motor.motorPower(0, gamepad1.left_trigger,0, gamepad1.right_trigger);
        motor.motorPower(gamepad1.right_trigger, 0, 0, gamepad1.right_trigger);
    }

    private void roundX() {
        if (gamepad1.b) {
            motor.motorPower(1,1,0,0);
        }
        if (gamepad1.x) {
            motor.motorPower(0,0,1,1);
        }
    }

    @Override
    public void stop() {
        telemetry.addData("Status", "TeleOp Finalizado");
    }
}
