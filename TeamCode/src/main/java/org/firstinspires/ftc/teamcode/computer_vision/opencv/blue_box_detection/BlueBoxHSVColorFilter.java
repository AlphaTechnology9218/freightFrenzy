package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        MatOfPoint2f[] contorusPoly = new MatOfPoint2f[contours.size()];
        Rect[] boundRect = new Rect[contours.size()];
        for (int i = 0; i < contours.size(); i++) {
            contorusPoly[i] = new MatOfPoint2f();
            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), contorusPoly[1], 3, true);
            boundRect[i] = Imgproc.boundingRect(new MatOfPoint(contorusPoly[i].toArray()));
        }

        double left_x = 0.25 * width;
        double right_x = 0.75 * width;
        boolean left = false;
        boolean right = false;

        for (int i = 0; i != boundRect.length; i++) {
            if (boundRect[i].x < left_x) left = true;
            if (boundRect[i].x + boundRect[i].width < right_x) right = true;

            Imgproc.rectangle(mat, boundRect[i], new Scalar(0.5, 76.9, 89.8));
        }

        if (!left) location = BlueBoxLocation.LEFT;
        else if (!right) location = BlueBoxLocation.RIGHT;
        else location = BlueBoxLocation.NONE;

        return mat;
    }
    public BlueBoxLocation getLocation() {
        return this.location;
    }
}
