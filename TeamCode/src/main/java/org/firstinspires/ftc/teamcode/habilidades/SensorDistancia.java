package org.firstinspires.ftc.teamcode.habilidades;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class SensorDistancia extends OpMode{
    private DistanceSensor sDist;
    public double vDist;

    /*
    **************************************************************************
    * sDist - Variável sensor de distância                                   *
    * vDist - Variável valor de distância                                    *
    * ************************************************************************
    */

    @Override
    public void init() { sDist = hardwareMap.get(DistanceSensor.class, "sensorDist"); }

    @Override
    public void loop() { capturarDistancia(); }

    public void capturarDistancia() {
        vDist = sDist.getDistance(DistanceUnit.CM);
        telemetry.addData("Distância: ", vDist);
        telemetry.update();
    }
}
