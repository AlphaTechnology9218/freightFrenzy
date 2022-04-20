package org.firstinspires.ftc.teamcode.abilities;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.computer_vision.vuforia.VuforiaFieldNavigation;

public class Localization extends VuforiaFieldNavigation {
    /*
     * LX == location in the x axis
     * LY == location in the y axis
     * RR == robot's rotation angle
     */
    public float LX;
    public float LY;
    public float RR;

    public void runOpMode(){
        while(opModeIsActive()){
            float[] coordinates = lastLocation.getTranslation().getData();

            LX = coordinates[0];
            LY = coordinates[1];
            RR = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES).thirdAngle;
        }
    }
}
