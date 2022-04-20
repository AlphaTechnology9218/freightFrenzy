package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

@Autonomous(name = "Blue Box Detection", group = "Computer Vision")
public class BasicBlueBoxDetection extends OpenCvPipeline {
    Mat mat = new Mat();
    Mat lowerMat;
    Mat upperMat;

    Rect lowerROI = new Rect(new Point(100, 50), new Point(200, 100));
    Rect upperROI = new Rect(new Point(100, 100), new Point(200, 150));

    public double lowerValue = Double.parseDouble(null);
    public double upperValue = Double.parseDouble(null);

    final double THRESHOLD = 10; // Adjust in the future

    @Override
    public Mat processFrame(Mat input) {
        /* The OpenCV Process is divided in the four steps bellow:
         *
         * 1. Threshold - assignment of pixel values in relation to the threshold value
         * 2. Divide    - divide two rectangle view points
         * 3. Average   - measure the percentage of the amount of blue
         * 4. Compare   - compare the results with the threshold value
         * */

        // Threshold | Color blue - 240°
        Imgproc.cvtColor(mat, input, Imgproc.COLOR_RGBA2RGB); // input RGBA to mat RGB
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV); // input RGB to mat HSV

        Scalar lowerBound = new Scalar(220.0 / 2, 100, 100); // lower HSV scale
        Scalar upperBound = new Scalar(260.0 / 2, 200, 200); // upper HSV scale
        Core.inRange(mat, lowerBound, upperBound, mat);

        // Divide
        lowerMat = mat.submat(lowerROI);
        upperMat = mat.submat(upperROI);
        upperMat.release();
        lowerMat.release();
        mat.release();

        // Average
        lowerValue = Math.round(Core.mean(lowerMat).val[2] / 255);
        upperValue = Math.round(Core.mean(upperMat).val[2] / 255);

        // Compare
        // TODO: address a function to the robot based in the percentage of the average

        return null;
    }
}