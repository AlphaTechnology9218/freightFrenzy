package org.firstinspires.ftc.teamcode.classes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Locomotion extends OpMode {
    private DcMotor motor;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class, "motor");
    }

    @Override
    public void loop() {
        motor.setPower(1);
        move(1);
    }

    public void move(double pow) {
        motor.setPower(pow);
    }
}
