package org.firstinspires.ftc.teamcode.controles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.habilidades.RobotDistanceSensor;
import org.firstinspires.ftc.teamcode.locomocao.hardware.ClawEncoders;

@TeleOp(name = "Controle da Garra", group = "Controles")

public class ClawControl extends OpMode {
    private Servo rightS, leftS;
    public boolean atvM, atvS;

    RobotDistanceSensor distance = new RobotDistanceSensor();
    // TODO: Review the necessity of Distance Sensors -> we do not have at FabLab

    ClawEncoders claw = new ClawEncoders();
    /**************************************************************************
     * rightS - right servo                                                   *
     * leftS - left servo                                                     *
     * atvM - servo activation                                                *
     * atvS - motor activation                                                *
     *                                                                        *
     * Distsensor - object to access distance sensor                          *
     * claw - object to robot claw object                                     *
     **************************************************************************/

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        rightS = hardwareMap.get(Servo.class, "rightS");
        leftS = hardwareMap.get(Servo.class, "leftS");
    }

    @Override
    public void start() {
        atvM = true;
        atvS = true;
    }

    @Override
    public void loop() {
        distance.captureDistance();
        if (distance.valueDist <= 31 && distance.valueDist >= 30) {
            telemetry.addData("Ideal Distance: ", distance.valueDist);

            clawControl();
            armsControl();
        } else {
            assert true;
        }
    }

    @Override
    public void stop() { telemetry.addData("Status", "Finished"); }

    private void armsControl() {
        if (atvM && gamepad2.b) {
            claw.moveClaw(500, 500, 1);
            atvM = false;
        } else if (!atvM && gamepad2.b) {
            claw.moveClaw(-500, -500, 1);
            atvM = true;
        } else {
            claw.moveClaw(-500, -500, 1);
        }
    }

    private void clawControl() {
        if (atvS && gamepad2.x) {
            rightS.setPosition(1);
            leftS.setPosition(1);
            atvS = false;
        } else if (!atvS && gamepad2.x) {
            rightS.setPosition(-1);
            leftS.setPosition(-1);
            atvS = true;
        } else {
            rightS.setPosition(0);
            leftS.setPosition(0);
        }
    }
}
