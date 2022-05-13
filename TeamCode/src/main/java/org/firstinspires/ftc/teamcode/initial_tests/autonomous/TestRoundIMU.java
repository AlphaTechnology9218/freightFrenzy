package org.firstinspires.ftc.teamcode.initial_tests.autonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.abilities.SensorIMU;
import org.firstinspires.ftc.teamcode.locomotion.autonomous.MotorEncodersSetup;
import org.firstinspires.ftc.teamcode.robot_components.MotorComponents;

@Autonomous(name = "Test IMU", group = "Autonomous Test")
public class TestRoundIMU extends OpMode {
    MotorComponents motors = new MotorComponents();

    BNO055IMU imu;
    public Orientation angles;

    @Override
    public void init() {
        motors.init(hardwareMap);
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
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
        if(angles.firstAngle > 5){
            motors.motorPower(-0.2f,-0.2f,0.2f,0.2f);
        } else if (angles.firstAngle < -5){
            motors.motorPower(0.2f,0.2f,-0.2f,-0.2f);
        } else {
            motors.motorPower(0,0,0,0);
        }
    }
}
