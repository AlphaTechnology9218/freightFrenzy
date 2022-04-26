package org.firstinspires.ftc.teamcode.controllers.claw;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.abilities.SensorDistance;
import org.firstinspires.ftc.teamcode.locomotion.autonomous.SetupClawEncoders;

@Disabled
@TeleOp(name = "Basic Claw Control", group = "Controllers")

public class BasicClawControl extends OpMode {
    private Servo rightS;
    public boolean atvM, atvS;

    SensorDistance distance = new SensorDistance();
    // TODO: Review the necessity of Distance Sensors -> we do not have at FabLab

    SetupClawEncoders claw = new SetupClawEncoders();
    /**************************
     * rightS - right servo                                                   *
     * leftS - left servo                                                     *
     * atvM - servo activation                                                *
     * atvS - motor activation                                                *
     *                                                                        *
     * Distsensor - object to access distance sensor                          *
     * claw - object to robot claw object                                     *
     **************************/

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        rightS = hardwareMap.get(Servo.class, "rightS");
        // leftS = hardwareMap.get(Servo.class, "leftS");
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
            // armsControl();
        } else {
            assert true;
        }
    }

    @Override
    public void stop() { telemetry.addData("Status", "Finished"); }
    /*
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
    */
    private void clawControl() {
        if (atvS && gamepad2.x) {
            rightS.setPosition(1);
            // leftS.setPosition(1);
            atvS = false;
        } else if (!atvS && gamepad2.x) {
            rightS.setPosition(-1);
            // leftS.setPosition(-1);
            atvS = true;
        } else {
            rightS.setPosition(0);
            // leftS.setPosition(0);
        }
    }
}