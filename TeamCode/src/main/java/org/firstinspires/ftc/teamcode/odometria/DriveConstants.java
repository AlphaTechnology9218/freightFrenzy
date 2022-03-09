package org.firstinspires.ftc.teamcode.odometria;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Config
public class DriveConstants {

        public static final double TICKS_PER_REV = 1120;
        public static final double MAX_RPM = 150;

        public static final boolean RUN_USING_ENCODER = true;
        public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(0, 0, 0,
                getMotorVelocityF(MAX_RPM / 60 * TICKS_PER_REV)); //colocar os valores do PID onde tem os 0, ordem  P I D F

        public static double WHEEL_RADIUS = 1.4763;
        public static double GEAR_RATIO = 1; // velocidade roda falsa:velocidade motor
        public static double TRACK_WIDTH = 15;

        public static double kV = 1.0 / rpmToVelocity(MAX_RPM);
        public static double kA = 0;
        public static double kStatic = 0;                      //não mexer nessa parte, pq a gente vai usar os encoders

        public static double MAX_VEL = 18.543736;              //80% da velocidade máxima
        public static double MAX_ACCEL = 18.543736;
        public static double MAX_ANG_VEL = Math.toRadians(62.74275);
        public static double MAX_ANG_ACCEL = Math.toRadians(62.74275);


        public static double encoderTicksToInches(double ticks) {
            return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
        }

        public static double rpmToVelocity(double rpm) {
            return rpm * GEAR_RATIO * 2 * Math.PI * WHEEL_RADIUS / 60.0;
        }

        public static double getMotorVelocityF(double ticksPerSecond) {

            return 32767 / ticksPerSecond;
        }
    }
         //velocidade máxima = 23,18967
        // see https://docs.google.com/document/d/1tyWrXDfMidwYyP_5H4mZyVgaEswhOC35gvdmP-V-5hA/edit#heading=h.61g9ixenznbx



