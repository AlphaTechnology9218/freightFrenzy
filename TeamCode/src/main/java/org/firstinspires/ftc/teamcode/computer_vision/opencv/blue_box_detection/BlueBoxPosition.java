package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

@Autonomous
public class BlueBoxPosition extends OpMode {
    OpenCvCamera camera;

    @Override
    public void init() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        camera.setPipeline(new BlueBox());

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

    @Override
    public void loop() {

    }
}

class BlueBox extends OpenCvPipeline {
    Mat mat = new Mat();
    public String results;
    Telemetry telemetry;

    @Override
    public final Mat processFrame(Mat input) {
        input.copyTo(mat);

        if (mat.empty()) {
            return input;
        }

        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2YCrCb);

        Mat matLeft   = mat.submat(10, 20, 10, 20);
        Mat matRight  = mat.submat(30, 40, 10, 20);
        Mat matCenter = mat.submat(50, 60, 10, 20);

        Imgproc.rectangle(mat, new Rect(10, 1, 20, 10), new Scalar(0, 255, 0));
        Imgproc.rectangle(mat, new Rect(30, 1, 20, 10), new Scalar(0, 255, 0));
        Imgproc.rectangle(mat, new Rect(50, 1, 20, 10), new Scalar(0, 255, 0));

        double leftTotal   = Core.sumElems(matLeft).val[2];
        double rightTotal  = Core.sumElems(matRight).val[2];
        double centerTotal = Core.sumElems(matCenter).val[2];

        if ((leftTotal > rightTotal) && (leftTotal > centerTotal)) {
            results = "LEFT";
            telemetry.addData("Status", results);
        }
        if((rightTotal > leftTotal) && (rightTotal > centerTotal)) {
            results = "RIGHT";
            telemetry.addData("Status", results);
        }
        if((centerTotal > leftTotal) && (centerTotal > rightTotal)) {
            results = "CENTER";
            telemetry.addData("Status", results);

        }
        return mat;
    }
}
