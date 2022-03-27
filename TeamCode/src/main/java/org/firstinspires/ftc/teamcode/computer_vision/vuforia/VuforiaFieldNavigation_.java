package org.firstinspires.ftc.teamcode.computer_vision.vuforia;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "Vuforia Navigation using RC Phone Camera", group = "Computer Vision")
public class VuforiaFieldNavigation_ extends LinearOpMode {
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = false;
    private static final String VUFORIA_KEY = "AQV2x3v/////AAABmR7YRPgGBk1qj0WFSQ4LFt1z+UiyM9AC" +
            "60gmkqBNr2qIo9N8YsunPo1FXuzFM5RJwDrIaY4NNkwX9MPpT2gIlSXJ9sVpp+jmOhrQ69XSzoEp46dr76" +
            "DCp/Nq3IJvzuq9/cmS1+c5VK7vXkM/y+QJjjdIgwYCO8eDGKldugYmTZRWXW2Vzs48jElCug8hkKMDeVgS" +
            "mk9D+H0oI/XzulgOUasttCiw7CQRmL0a4AFioC6+C4qcphnpSg0HV0kVhmLpdxoNIwaVsZdAbOtch5+Sww" +
            "IAcej9pJU7l7TgLTXkKlToMnWJg6P8QDORSh9kUpqmOYD7q3jUQhBnLijX1HW9dyQPc5zWwRcukxKwlj9H5mM4";

    /* Since ImageTarget trackable use mm to specify their dimensions, we must use mm for all the
     * physical dimension. We will define some constants and conversions here. These are useful for
     * the Freight Frenzy field. */
    private static final float mmPerInch        = 25.4f;
    private static final float mmTargetHeight   = 6 * mmPerInch;   // the height of the center of the target image above the floor
    private static final float halfField        = 72 * mmPerInch;
    private static final float halfTile         = 12 * mmPerInch;
    private static final float oneAndHalfTile   = 36 * mmPerInch;

    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia  = null;
    private VuforiaTrackables targets = null;

    private boolean targetVisible = false;
    private float phoneXRotate     = 0;
    private float phoneYRotate     = 0;
    private float phoneZRotate     = 0;

    @Override
    public void runOpMode() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("CameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CAMERA_CHOICE;

        parameters.useExtendedTracking = false; // set to Vuforia only track target images

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        targets = this.vuforia.loadTrackablesFromAsset("FreightFrenzy");

        List<VuforiaTrackable> allTrackables = new ArrayList<>();
        allTrackables.addAll(targets);

        identifyTarget(0, "Blue Storage",       -halfField,  oneAndHalfTile, mmTargetHeight, 90, 0, 90);
        identifyTarget(1, "Blue Alliance Wall",  halfTile,   halfField,      mmTargetHeight, 90, 0, 0);
        identifyTarget(2, "Red Storage",        -halfField, -oneAndHalfTile, mmTargetHeight, 90, 0, 90);
        identifyTarget(3, "Red Alliance Wall",   halfTile,  -halfField,      mmTargetHeight, 90, 0, 180);

        setCameraChoice();

        final float CAMERA_FORWARD_DISPLACEMENT  = 0.0f * mmPerInch;   // eg: Enter the forward distance from the center of the robot to the camera lens
        final float CAMERA_VERTICAL_DISPLACEMENT = 6.0f * mmPerInch;   // eg: Camera is 6 Inches above ground
        final float CAMERA_LEFT_DISPLACEMENT     = 0.0f * mmPerInch;   // eg: Enter the left distance from the center of the robot to the camera lens

        OpenGLMatrix robotFrontCamera = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, phoneYRotate, phoneZRotate, phoneXRotate));

        for (VuforiaTrackable trackable : allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(
                    robotFrontCamera, parameters.cameraDirection);
        }

        // waitForStart();
        targets.activate();
        while (!isStopRequested()) {
            targetVisible = false;
            for (VuforiaTrackable trackable : allTrackables) {
                if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                    telemetry.addData("Visible Target", trackable.getName());
                    targetVisible = true;

                    OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)
                            trackable.getListener()).getUpdatedRobotLocation();
                    if (robotLocationTransform != null) {
                        lastLocation = robotLocationTransform;
                    }
                    break;
                }
            }
            displayTargetResults();
            telemetry.update();
        }
        targets.deactivate();
    }

    /**
     * Identify a target by naming it, and setting its position and orientation on the field
     * @param targetIndex - index of the target image
     * @param targetName - name of the target image
     * @param dx, dy, dz - Target offsets in x,y,z axes
     * @param rx, ry, rz - Target rotations in x,y,z axes
     */
    void identifyTarget(int targetIndex, String targetName, float dx, float dy, float dz, float rx,
                        float ry, float rz) {
        VuforiaTrackable aTarget = targets.get(targetIndex);
        aTarget.setName(targetName);
        aTarget.setLocation(OpenGLMatrix.translation(dx, dy, dz)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, rx, ry, rz)));
    }

    private void setCameraChoice() {
        if (CAMERA_CHOICE == BACK) {
            phoneYRotate = -90;
        } else {
            phoneYRotate = 90;
        }
        if (PHONE_IS_PORTRAIT) {
            phoneXRotate = 90;
        }
    }

    private void displayTargetResults() {
        if (targetVisible) {
            VectorF translation = lastLocation.getTranslation();
            telemetry.addData("Pos (inches)", "{X, Y, Z} = %.1f, %.1%f, %.1f",
                    translation.get(0) / mmPerInch, translation.get(1) / mmPerInch, translation.get(2) / mmPerInch);

            Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
            telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f",
                    rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);
        } else {
            telemetry.addData("Visible Target", "none");
        }
    }
}
