package org.firstinspires.ftc.teamcode.locomocao;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@TeleOp(name="Locomoçao com Vuforia", group ="Auto")
@Disabled
public class Vuforia extends LinearOpMode {
    private static final VuforiaLocalizer.CameraDirection ESCOLHA_CAMERA = BACK;
    private static final boolean CAMERA_PAISAGEM = false;
    
    private static final String VUFORIA_KEY = "AQV2x3v/////AAABmR7YRPgGBk1qj0WFSQ4LFt1z+UiyM9AC" +
            "60gmkqBNr2qIo9N8YsunPo1FXuzFM5RJwDrIaY4NNkwX9MPpT2gIlSXJ9sVpp+jmOhrQ69XSzoEp46dr76" +
            "DCp/Nq3IJvzuq9/cmS1+c5VK7vXkM/y+QJjjdIgwYCO8eDGKldugYmTZRWXW2Vzs48jElCug8hkKMDeVgS" +
            "mk9D+H0oI/XzulgOUasttCiw7CQRmL0a4AFioC6+C4qcphnpSg0HV0kVhmLpdxoNIwaVsZdAbOtch5+Sww" +
            "IAcej9pJU7l7TgLTXkKlToMnWJg6P8QDORSh9kUpqmOYD7q3jUQhBnLijX1HW9dyQPc5zWwRcukxKwlj9H5mM4";
    
    private static final float mmPP    = 25.4f;
    private static final float mmAA    = 6 * mmPP; // the height of the center of the target image above the floor
    private static final float metadeA = 72 * mmPP;
    private static final float metadeT = 12 * mmPP;
    private static final float umMT    = 36 * mmPP;

    // Membros da Classe
    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia  = null;
    private VuforiaTrackables targets = null ;

    private boolean targetVisible = false;
    private float phoneXRotate    = 0;
    private float phoneYRotate    = 0;
    private float phoneZRotate    = 0;

    @Override public void runOpMode() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // Parametros do Vuforia
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection   = ESCOLHA_CAMERA;
        parameters.useExtendedTracking = false;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
        targets = this.vuforia.loadTrackablesFromAsset("FreightFrenzy");
        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targets);

        identifyTarget(0, "Blue Storage", -metadeA,  umMT, mmAA, 90, 0, 90);
        identifyTarget(1, "Blue Alliance Wall", metadeT, metadeA, mmAA, 90, 0, 0);
        identifyTarget(2, "Red Storage", -metadeA, -umMT, mmAA, 90, 0, 90);
        identifyTarget(3, "Red Alliance Wall", metadeT, -metadeA, mmAA, 90, 0, 180);

        // Rotacionar a camera do celular para vertical no eixo X e Y
        if (ESCOLHA_CAMERA == BACK) {
            phoneYRotate = -90;
        } else {
            phoneYRotate = 90;
        }

        // Rotacionar a camera do celular para horizontal no eixo X
        if (CAMERA_PAISAGEM) {
            phoneXRotate = 90 ;
        }

        final float CAMERA_FORWARD_DISPLACEMENT  = 0.0f * mmPP;   // eg: Enter the forward distance from the center of the robot to the camera lens
        final float CAMERA_VERTICAL_DISPLACEMENT = 6.0f * mmPP;   // eg: Camera is 6 Inches above ground
        final float CAMERA_LEFT_DISPLACEMENT     = 0.0f * mmPP;   // eg: Enter the left distance from the center of the robot to the camera lens

        OpenGLMatrix robotFromCamera = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, phoneYRotate, phoneZRotate, phoneXRotate));

        /**  Let all the trackable listeners know where the phone is.  */
        for (VuforiaTrackable trackable : allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(robotFromCamera, parameters.cameraDirection);
        }

        targets.activate();
        while (!isStopRequested()) {

            // check all the trackable targets to see which one (if any) is visible.
            targetVisible = false;
            for (VuforiaTrackable trackable : allTrackables) {
                if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                    telemetry.addData("Visible Target", trackable.getName());
                    targetVisible = true;

                    // getUpdatedRobotLocation() will return null if no new information is available since
                    // the last time that call was made, or if the trackable is not currently visible.
                    OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                    if (robotLocationTransform != null) {
                        lastLocation = robotLocationTransform;
                    }
                    break;
                }
            }

            // Provide feedback as to where the robot is located (if we know).
            if (targetVisible) {
                // express position (translation) of robot in inches.
                VectorF translation = lastLocation.getTranslation();
                telemetry.addData("Pos (inches)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                        translation.get(0) / mmPP, translation.get(1) / mmPP, translation.get(2) / mmPP);

                // express the rotation of the robot in degrees.
                Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
                telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);
            }
            else {
                telemetry.addData("Visible Target", "none");
            }
            telemetry.update();
        }

        // Disable Tracking when we are done;
        targets.deactivate();
    }

    void    identifyTarget(int targetIndex, String targetName, float dx, float dy, float dz, float rx, float ry, float rz) {
        VuforiaTrackable aTarget = targets.get(targetIndex);
        aTarget.setName(targetName);
        aTarget.setLocation(OpenGLMatrix.translation(dx, dy, dz)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, rx, ry, rz)));
    }
}
