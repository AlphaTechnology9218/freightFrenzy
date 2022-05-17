package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class BlueBoxHSVColorFilter extends OpenCvPipeline {
    enum BlueBoxLocation {
        LEFT,
        RIGHT,
        NONE
    }

    private int width;
    BlueBoxLocation location;

    public BlueBoxHSVColorFilter(int width) {
        this.width = width;
    }

    @Override
    public Mat processFrame(Mat input) {
        Mat mat = new Mat();
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

        if (mat.empty()) {
            location = BlueBoxLocation.NONE;
            return input;
        }

        Scalar lowerBound = new Scalar(220, 100, 100);
        Scalar upperBound = new Scalar(260, 255, 255);
        Mat thresh = new Mat();

        Core.inRange(mat, lowerBound, upperBound, thresh);

        Mat edges = new Mat();
        Imgproc.Canny(thresh, edges, 100, 300);

        return null;
    }
}
