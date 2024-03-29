package org.firstinspires.ftc.teamcode.computer_vision.tensorflow;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name = "TensorFlow Object Detection Webcam", group = "Computer Vision")
public class TensorFlowWebcam extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };
    ObjectsDetected objects = new ObjectsDetected();

    private static final String VUFORIA_KEY = "AQV2x3v/////AAABmR7YRPgGBk1qj0WFSQ4LFt1z+UiyM9AC" +
            "60gmkqBNr2qIo9N8YsunPo1FXuzFM5RJwDrIaY4NNkwX9MPpT2gIlSXJ9sVpp+jmOhrQ69XSzoEp46dr76" +
            "DCp/Nq3IJvzuq9/cmS1+c5VK7vXkM/y+QJjjdIgwYCO8eDGKldugYmTZRWXW2Vzs48jElCug8hkKMDeVgS" +
            "mk9D+H0oI/XzulgOUasttCiw7CQRmL0a4AFioC6+C4qcphnpSg0HV0kVhmLpdxoNIwaVsZdAbOtch5+Sww" +
            "IAcej9pJU7l7TgLTXkKlToMnWJg6P8QDORSh9kUpqmOYD7q3jUQhBnLijX1HW9dyQPc5zWwRcukxKwlj9H5mM4";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    @SuppressLint("DefaultLocale")
    @Override
    public void runOpMode() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(2.5, 16.0 / 9.0);
        }

        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        // step through the list of recognitions and display boundary info.
                        int i = 0;

                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                    recognition.getLeft(), recognition.getTop());
                            telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                    recognition.getRight(), recognition.getBottom());
                            i++;

                            /* Address each recognition possibility to a public method */
                            switch (recognition.getLabel()) {
                                case "Duck":
                                    telemetry.addData("Object: ", "Duck");
                                    objects.duckObject();
                                    break;
                                case "Ball":
                                    telemetry.addData("Object: ", "Ball");
                                    objects.ballObject();
                                    break;
                                case "Cube":
                                    telemetry.addData("Object: ", "Cube");
                                    objects.cubeObject();
                                    break;
                                case "Marker":
                                    telemetry.addData("Duck Location: ", "Marker");
                                    objects.markerBarCode(recognition.getRight(), recognition.getLeft());
                                    break;
                                default:
                                    telemetry.addData("Object", "Nothing Detected");
                            }
                        }
                    }
                    telemetry.update();
                }
            }
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Robot Webcam");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
}
