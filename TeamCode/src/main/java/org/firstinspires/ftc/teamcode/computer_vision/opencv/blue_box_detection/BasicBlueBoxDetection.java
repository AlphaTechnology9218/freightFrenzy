package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot_components.SetupCellphone;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import org.openftc.easyopencv.OpenCvPipeline;

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
class DetectionResults extends OpMode {
    SetupCellphone camera = new SetupCellphone();

    @Override
    public void init() { camera.init(hardwareMap); } // Initialize the cellphone camera

    @Override
    public void loop() { }
}

public class BasicBlueBoxDetection extends OpenCvPipeline {
    Mat mat = new Mat();
    Mat leftMat;
    Mat rightMat;

    Rect leftROI = new Rect(new Point(1, 1), new Point(159, 240));
    Rect rightROI = new Rect(new Point(160, 1), new Point(320, 240));

    Telemetry telemetry;
    TargetPosition targetPosition;

    double leftValue;
    double rightValue;

    @Override
    public Mat processFrame(Mat input) {
        if (mat.empty()) return input;

        telemetry.addLine("Pipeline is running");

        /* Threshold | Color blue - 240° */
        Imgproc.cvtColor(mat, input, Imgproc.COLOR_RGBA2RGB); // input RGBA to mat RGB
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV);    // input RGB to mat HSV

        Scalar leftBound = new Scalar(220.0 / 2, 100, 100);   // left HSV scale for blue
        Scalar rightBound = new Scalar(260.0 / 2, 255, 255);  // right HSV scale for blue

        Core.inRange(mat, leftBound, rightBound, mat);

        /* Divide */
        leftMat = mat.submat(leftROI);
        rightMat = mat.submat(rightROI);

        /* Average of the value channel from HSV */
        leftValue = Math.round(Core.mean(leftMat).val[2] / 255);
        rightValue = Math.round(Core.mean(rightMat).val[2] / 255);

        rightMat.release();
        leftMat.release();
        mat.release();

        /* Compare */
        final double THRESHOLD = 10; // adjust in the future
        if (leftValue > THRESHOLD) {
            telemetry.addLine("The Blue Box in on the left");
            targetPosition = TargetPosition.LEFT;
        } else if (rightValue > THRESHOLD) {
            telemetry.addLine("The Blue Box is on the right");
            targetPosition = TargetPosition.RIGHT;
        } else {
            telemetry.addLine("There is no Blue Box detected");
            targetPosition = TargetPosition.CENTER;
        }
        return null;
    }

    public TargetPosition getTargetPosition() {
        return targetPosition;
    }
}