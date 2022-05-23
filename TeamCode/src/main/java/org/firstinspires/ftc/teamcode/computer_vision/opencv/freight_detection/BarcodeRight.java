package org.firstinspires.ftc.teamcode.computer_vision.opencv.freight_detection;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element.HSVColorFilter;
import org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element.YCrCbChannel;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name = "Right Barcode", group = "Barcode Detection")
public class BarcodeRight extends LinearOpMode {
    OpenCvCamera camera;
    HSVColorFilter vision = new HSVColorFilter();

    @Override
    public void runOpMode() {
        runPipeline(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            double left   = vision.getLeftValue();
            double right  = vision.getRightValue();
            double center = vision.getCenterValue();

            telemetry.update();

            if ((left > right) && (left > center)) {
                hubLevel1();
            } else if ((center > left) && (center > right)) {
                hubLevel2();
            } else if ((right > center) && (right > left)) {
                hubLevel3();
            } else {
                hubDefault(); }
        }
    }

    public void runPipeline(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        camera.setPipeline(vision);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);
                camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {
                telemetry.addData("Status", "An error occurred with OpenCV!");
            }
        });
    }

    public void hubLevel1() {
        telemetry.addLine("Left");
    }
    public void hubLevel2() {
        telemetry.addLine("Center");
    }
    public void hubLevel3() {
        telemetry.addLine("Right");
    }
    public void hubDefault() {
        telemetry.addLine("None");
    }
}
