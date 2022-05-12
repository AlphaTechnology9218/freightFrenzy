package org.firstinspires.ftc.teamcode.abilities;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous(name = "IMU Sensor", group = "Abilities")
public class SensorIMU extends LinearOpMode {
    /**********************************************************************************************
     * IMU sensors: a 3-axis accelerometer, a 3-axis gyroscope, and a 3-axis geomagnetic sensor.  *
     * The accelerometer measures the affect of forces on acceleration along the three axes.      *
     * The gyroscope measures the rotational location of the the Hubs along the axes.             *
     * The geomagnetic or magnetometer sensor uses the Earth's magnetic field to find orientation.*
     *                                                                                            *
     * Applications for Autonomous Op Modes:                                                      *
     * Use the Gyroscope to drive in the straight lines and turn during autonomous                *
     * Use the Accelerometer in conjunction with the gyroscope to avoid drift and give an         *
     * approximation of position/travel                                                           *
     * Use the IMU with motor encoders to track and determine robot placement on a field          *
     * ********************************************************************************************/
    BNO055IMU imu;
    public Orientation angles;
    public float degree;

    public void init(HardwareMap hardwareMap){
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

    }
    public void runOpMode() {
        waitForStart();
        init(hardwareMap);
        while (opModeIsActive()) {
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            degree = angles.firstAngle;
            /*
             * Robot Orientation:
             * Heading: Z axis
             * Pitch: x axis
             * Roll: y axis
             */
            telemetry.addData("Heading", angles.firstAngle);
            telemetry.addData("Pitch", angles.secondAngle);
            telemetry.addData("Roll", angles.thirdAngle);

            telemetry.update();
        }
    }
}