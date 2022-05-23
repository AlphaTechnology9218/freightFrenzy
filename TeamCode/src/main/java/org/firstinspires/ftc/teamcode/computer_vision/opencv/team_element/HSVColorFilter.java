package org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class HSVColorFilter extends OpenCvPipeline {
    Mat mat  = new Mat();

    Mat leftMat;
    Mat rightMat;
    Mat centerMat;

    double leftValue;
    double rightValue;
    double centerValue;

    private final Object sync = new Object();

    @Override
    public Mat processFrame(Mat input) {
        synchronized (sync) {
            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGBA2RGB);
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV);

            Rect leftRect = new Rect(1, 60, 80, 120);
            Rect centerRect = new Rect(80, 60, 80, 120);
            Rect rightRect = new Rect(160, 60, 80, 120);

            // Lower and upper bound for PINK
            Scalar lowerBound = new Scalar(300.0 / 2, 20, 20);
            Scalar upperBound = new Scalar(330.0 / 2, 255, 255);

            // Return Binary Mask
            Core.inRange(mat, lowerBound, upperBound, mat);

            // Remove Noise
            Imgproc.morphologyEx(mat, mat, Imgproc.MORPH_OPEN, new Mat());
            Imgproc.morphologyEx(mat, mat, Imgproc.MORPH_CLOSE, new Mat());

            // -- DIVIDE
            leftMat = mat.submat(leftRect);
            rightMat = mat.submat(rightRect);
            centerMat = mat.submat(centerRect);

            // -- AVERAGE
            leftValue = Core.sumElems(leftMat).val[0] / leftRect.area() / 255;
            rightValue = Core.sumElems(rightMat).val[0] / rightRect.area() / 255;
            centerValue = Core.sumElems(centerMat).val[0] / centerRect.area() / 255;

            leftMat.release();
            rightMat.release();
            centerMat.release();
        }
        return mat;
    }

    public double getLeftValue() {
        synchronized (sync) {
            return leftValue;
        }
    }

    public double getRightValue() {
        synchronized (sync) {
            return rightValue;
        }
    }

    public double getCenterValue() {
        synchronized (sync) {
            return centerValue;
        }
    }
}
