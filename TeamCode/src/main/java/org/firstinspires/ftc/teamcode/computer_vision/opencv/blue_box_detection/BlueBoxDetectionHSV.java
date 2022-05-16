package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

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

public class BlueBoxDetectionHSV extends OpMode {
    OpenCvCamera camera;

    @Override
    public void init() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier
                ("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        camera.setPipeline(new BlueBoxVision());

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

class BlueBoxVision extends OpenCvPipeline {
    Mat mat      = new Mat();
    Mat leftMat  = new Mat();
    Mat rightMat = new Mat();
    Mat outPut   = new Mat();

    Scalar rectColor = new Scalar(255.0 / 2, 0.0, 0.0);

    Rect leftRect = new Rect(1, 1, 120, 160);
    Rect rightRect = new Rect(120, 1, 120, 160);

    Telemetry telemetry;

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGBA2RGB);
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV);

        Scalar lowerScale = new Scalar(220.0 / 2, 100, 100);
        Scalar upperScale = new Scalar(260.0 / 2, 100, 100);
        Core.inRange(mat, upperScale, lowerScale, mat);
        leftMat.submat(leftRect);
        rightMat.submat(rightRect);

        input.copyTo(outPut);
        Imgproc.rectangle(outPut, leftRect, rectColor, 2);
        Imgproc.rectangle(outPut, rightRect, rectColor, 2);

        double leftValue = Math.round(Core.mean(leftMat).val[2] / 255);
        double rightValue = Math.round(Core.mean(rightMat).val[2] / 255);

        leftMat.release();
        rightMat.release();
        mat.release();

        telemetry.addLine(String.valueOf(leftValue));
        telemetry.addLine(String.valueOf(rightValue));

        return null;
    }
}
