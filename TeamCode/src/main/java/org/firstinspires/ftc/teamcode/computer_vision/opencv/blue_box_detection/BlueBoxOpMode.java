package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name = "Blue Box Detection", group = "Computer Vision")
public class BlueBoxOpMode extends OpMode {
    int width = 320;
    int height = 240;

    OpenCvCamera camera;
    BlueBoxHSVColorFilter boxVision = new BlueBoxHSVColorFilter(width);

    @Override
    public void init() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        camera.setPipeline(boxVision);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);
                camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                camera.startStreaming(width, height, OpenCvCameraRotation.SIDEWAYS_LEFT);
            }
            @Override
            public void onError(int errorCode) {
                telemetry.addData("Status", "An error occurred with OpenCV!");
            }
        });

        BlueBoxHSVColorFilter.BlueBoxLocation location = boxVision.getLocation();

        if (location == BlueBoxHSVColorFilter.BlueBoxLocation.RIGHT) {
            telemetry.addLine("There are Objects");
//            if (location == BlueBoxHSVColorFilter.BlueBoxLocation.LEFT) {
//                telemetry.addLine("Object is on the left");
//            }
//            if (location == BlueBoxHSVColorFilter.BlueBoxLocation.RIGHT) {
//                telemetry.addLine("Object is on the right");
//            }
        }
        if (location == BlueBoxHSVColorFilter.BlueBoxLocation.LEFT) {
            telemetry.addLine("There are Objects");
        }
        else {
            telemetry.addLine("There are no Objects");
        }

    } // Initialize the cellphone camera

    @Override
    public void loop() {

    }
}
