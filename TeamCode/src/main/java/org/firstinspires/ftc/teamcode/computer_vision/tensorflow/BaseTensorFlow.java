package org.firstinspires.ftc.teamcode.computer_vision.tensorflow;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

@TeleOp(name = "Base do Tensor Flow", group = "Computer Vision")
public class BaseTensorFlow extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };
    private static final String VUFORIA_KEY = "AQV2x3v/////AAABmR7YRPgGBk1qj0WFSQ4LFt1z+UiyM9AC" +
            "60gmkqBNr2qIo9N8YsunPo1FXuzFM5RJwDrIaY4NNkwX9MPpT2gIlSXJ9sVpp+jmOhrQ69XSzoEp46dr76" +
            "DCp/Nq3IJvzuq9/cmS1+c5VK7vXkM/y+QJjjdIgwYCO8eDGKldugYmTZRWXW2Vzs48jElCug8hkKMDeVgS" +
            "mk9D+H0oI/XzulgOUasttCiw7CQRmL0a4AFioC6+C4qcphnpSg0HV0kVhmLpdxoNIwaVsZdAbOtch5+Sww" +
            "IAcej9pJU7l7TgLTXkKlToMnWJg6P8QDORSh9kUpqmOYD7q3jUQhBnLijX1HW9dyQPc5zWwRcukxKwlj9H5mM4";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    @SuppressLint("DefaultLocale")
    @Override
    public void runOpMode()
    {
        initVuforia();
        initTfod();

        if (tfod != null)
        {
            tfod.activate();
            tfod.setZoom(2.5, 16.0/9.0);
        }

        telemetry.addData(">", "Aperte Play para iniciar o op mode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive())
        {
            while (opModeIsActive())
            {
                if (tfod != null)
                {
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null)
                    {
                        telemetry.addData("Objeto Detectado: ", updatedRecognitions.size());

                        int i = 0;
                        for (Recognition recognition : updatedRecognitions)
                        {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                    recognition.getLeft(), recognition.getTop());
                            telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                    recognition.getRight(), recognition.getBottom());
                            i++;
                        }
                        telemetry.update();
                    }
                }
            }
        }
    }

    private void initVuforia()
    {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void initTfod()
    {
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
