package org.firstinspires.ftc.teamcode.odometry.control;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Config
public class DriveConstants {
        public static final double TICKS_PER_REV = 1120;
        public static final double MAX_RPM = 150;

        public static final boolean RUN_USING_ENCODER = true;
        public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(22, 0, 0.8, 12.481373);

        public static double WHEEL_RADIUS = 1.4763;
        public static double GEAR_RATIO = 1.33;
        public static double TRACK_WIDTH = 10.6;

        public static double kV = 1.0 / rpmToVelocity(MAX_RPM);
        public static double kA = 0;
        public static double kStatic = 0; // don't change!

        public static double MAX_VEL = 20.1798392; // 80% of max velocity
        public static double MAX_ACCEL = 20.1798392;
        public static double MAX_ANG_VEL = Math.toRadians(182.073254);
        public static double MAX_ANG_ACCEL = Math.toRadians(182.073254);

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
         // max velocity = 25.224799
        // documentation: https://docs.google.com/document/d/1tyWrXDfMidwYyP_5H4mZyVgaEswhOC35gvdmP-V-5hA/edit#heading=h.61g9ixenznbx



