package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot_components.SetupCellphone;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

import java.lang.annotation.Target;
import java.nio.channels.Pipe;

/***********************************************************************************
 * The OpenCV Process is divided in the four steps bellow:                         *
 *                                                                                 *
 * 1. Threshold - assignment of pixel values in relation to the threshold value    *
 * 2. Divide    - divide two rectangle view points                                 *
 * 3. Average   - measure the percentage of the amount of blue                     *
 * 4. Compare   - compare the results with the threshold value                     *
 * *********************************************************************************
 */

@Autonomous(name = "Blue Box Detection", group = "Computer Vision")
public class BasicBlueBoxDetection extends OpenCvPipeline {
    Mat mat = new Mat();
    Mat lowerMat;
    Mat upperMat;

    Rect lowerROI = new Rect(new Point(100, 50), new Point(200, 100));
    Rect upperROI = new Rect(new Point(100, 100), new Point(200, 150));

    final double THRESHOLD = 10; // Adjust in the future
    public boolean seeingBox = false;

    private Telemetry telemetry;
    private OpenCvCamera cam;

    TargetObject target;

    public BasicBlueBoxDetection(HardwareMap hardwareMap, Telemetry telemetry) {
        cam = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK);

        cam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                cam.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                cam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);
                cam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {
                BasicBlueBoxDetection.this.telemetry.addData("Status: ", "An error occurred with OpenCV!");
            }
        });
    }

    @Override
    public Mat processFrame(Mat input) {

        // Threshold | Color blue - 240°
        Imgproc.cvtColor(mat, input, Imgproc.COLOR_RGBA2RGB); // input RGBA to mat RGB
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV);    // input RGB to mat HSV

        Scalar lowerBound = new Scalar(220.0 / 2, 100, 100); // lower HSV scale
        Scalar upperBound = new Scalar(260.0 / 2, 200, 200); // upper HSV scale
        Core.inRange(mat, lowerBound, upperBound, mat);

        // Divide | 2 Rectangles
        lowerMat = mat.submat(lowerROI);
        upperMat = mat.submat(upperROI);

        // Average
        double lowerValue = Math.round(Core.mean(lowerMat).val[2] / 255);
        double upperValue = Math.round(Core.mean(upperMat).val[2] / 255);

        upperMat.release();
        lowerMat.release();
        mat.release();

        // Compare
        // TODO: address a function to the robot based in the percentage of the average

        if (upperValue > THRESHOLD) {
            // seeingBox = true;
            telemetry.addData("Recognition Status: ", "Seeing Target Object");
            target = TargetObject.BLUE;
        } else {
            telemetry.addData("Recognition Status: ", "Not Seeing Target Object");
            target = TargetObject.NO_BLUE;
        }
        return null;
    }
    public TargetObject getTarget() {
        return target;
    }
}