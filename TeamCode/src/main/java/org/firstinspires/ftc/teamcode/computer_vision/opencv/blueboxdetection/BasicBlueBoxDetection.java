package org.firstinspires.ftc.teamcode.computer_vision.opencv.blueboxdetection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class BasicBlueBoxDetection extends OpenCvPipeline {
    Mat mat = new Mat();
    Mat lowerMat;
    Mat upperMat;

    Rect lowerROI = new Rect(new Point(100, 50), new Point(200, 100));
    Rect upperROI = new Rect(new Point(100, 100), new Point(200, 150));

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
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV); // input RGB to mat HS

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
        double lowerValue = Math.round(Core.mean(lowerMat).val[2] / 255);
        double upperValue = Math.round(Core.mean(upperMat).val[2] / 255);

        // Compare
        // TODO: address a function to the robot based in the percentage of the average

        return null;
    }
}
