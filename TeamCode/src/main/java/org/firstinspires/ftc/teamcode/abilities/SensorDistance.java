package org.firstinspires.ftc.teamcode.abilities;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Disabled
@Autonomous(name = "Distance Sensor", group = "Abilities")
public class SensorDistance extends OpMode{
    private DistanceSensor distSensor;
    public double valueDist;
    /**************************************************************************
     * distSensor - distance sensor                                           *
     * valueDist - distance value                                             *
     * ************************************************************************/

    @Override
    public void init() {
        distSensor = hardwareMap.get(DistanceSensor.class, "Distance Sensor");
    }

    @Override
    public void loop() { captureDistance(); }

    /* Get the distance in centimeters */
    public void captureDistance() {
        valueDist = distSensor.getDistance(DistanceUnit.CM);
        telemetry.addData("Distance: ", valueDist);
        telemetry.update();
    }
}