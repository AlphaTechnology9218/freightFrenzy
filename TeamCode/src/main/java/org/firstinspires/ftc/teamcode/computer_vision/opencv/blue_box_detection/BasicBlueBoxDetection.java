package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

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
public class BasicBlueBoxDetection extends OpenCvPipeline {
    public boolean seeing;

    Mat mat = new Mat();
    Mat lowerMat;
    Mat upperMat;

    Rect lowerROI = new Rect(new Point(100, 100), new Point(200, 150)); // low rectangle
    Rect upperROI = new Rect(new Point(100, 150), new Point(200, 200)); // up rectangle

    double lowerValue;
    double upperValue;

    final double THRESHOLD = 1; // Adjust in the future

    @Override
    public Mat processFrame(Mat input) {
        // Threshold | Color blue - 240°
        Imgproc.cvtColor(mat, input, Imgproc.COLOR_RGBA2RGB); // input RGBA to mat RGB
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV);    // input RGB to mat HSV

        if (mat.empty()) {
            seeing = false;
            return input;
        }

        Scalar lowerBound = new Scalar(220.0 / 2, 100, 100); // lower HSV scale for blue
        Scalar upperBound = new Scalar(260.0 / 2, 200, 200); // upper HSV scale for blue
        Core.inRange(mat, lowerBound, upperBound, mat);

        // Divide | 2 Rectangles
        lowerMat = mat.submat(lowerROI);
        upperMat = mat.submat(upperROI);

        // Average
        lowerValue = Math.round(Core.mean(lowerMat).val[2] / 255);
        upperValue = Math.round(Core.mean(upperMat).val[2] / 255);

        upperMat.release();
        lowerMat.release();
        mat.release();

        // Compare
        // TODO: address a function to the robot based in the percentage of the average

        if (upperValue > THRESHOLD) {
            seeing = true;
        }
        else if (lowerValue > THRESHOLD) {
            seeing = false;
        }

        return null;
    }
}