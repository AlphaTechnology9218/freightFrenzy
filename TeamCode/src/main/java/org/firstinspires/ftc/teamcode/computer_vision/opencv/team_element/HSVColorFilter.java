package org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class HSVColorFilter extends OpenCvPipeline {
    Mat mat  = new Mat();

    @Override
    public Mat processFrame(Mat input) {

        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGBA2RGB);
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV);

        // Lower and upper bound for PINK
        Scalar lowerBound = new Scalar(331.0 / 2, 30, 30);
        Scalar upperBound = new Scalar(345.0 / 2, 255, 255);

        // Return Binary Mask
        Core.inRange(mat, lowerBound, upperBound, mat);

        // Remove Noise
        Imgproc.morphologyEx(mat, mat, Imgproc.MORPH_OPEN, new Mat());
        Imgproc.morphologyEx(mat, mat, Imgproc.MORPH_CLOSE, new Mat());

        // Find Contours
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(mat, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        // Draw Contours
        Imgproc.drawContours(input, contours, -1, new Scalar(255, 0, 0));

        return mat;
    }
}

