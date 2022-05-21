package org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class Barcode extends OpenCvPipeline {

    Mat mat = new Mat();
    Telemetry telemetry;

    public enum ElementPosition {
        LEFT,
        CENTER,
        RIGHT,
        NONE
    }

    private ElementPosition elementPosition;

    Rect LEFT_RECT = new Rect(
            new Point(20, 20),
            new Point(40, 40)
    );

    Rect CENTER_RECT = new Rect(
            new Point(40, 40),
            new Point(60, 60)
    );

    Rect RIGHT_RECT = new Rect(
            new Point(60, 60),
            new Point(80, 80)
    );

    static double THRESHOLD = 10;

    public Barcode(Telemetry t) {
        telemetry = t;
    }

//    public Mat processFrame(Mat input) {
//        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
//
//        // Lower and upper bound for BLUE
//        Scalar lowerBound = new Scalar(221.0 / 2, 30, 30);
//        Scalar upperBound = new Scalar(240.0 / 2, 255, 255);



    Mat outPut = new Mat();
    Mat leftMat;
    Mat rightMat;
    Mat centerMat;

    Scalar rectColor = new Scalar(255.0, 0.0, 0.0);

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGBA2RGB);
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV);

        // Return Binary Mask
       // Core.inRange(mat, lowerBound, upperBound, mat);

        Rect leftRect = new Rect(1, 60, 80, 120);
        Rect centerRect = new Rect(80, 60, 80, 120);
        Rect rightRect = new Rect(160, 60, 80, 120);

        input.copyTo(outPut);
        Imgproc.rectangle(outPut, leftRect, rectColor, 2);
        Imgproc.rectangle(outPut, rightRect, rectColor, 2);
        Imgproc.rectangle(outPut, centerRect, rectColor, 2);

        leftMat = mat.submat(leftRect);
        rightMat = mat.submat(rightRect);
        centerMat = mat.submat(centerRect);

        // Remove Noise
        Imgproc.morphologyEx(mat, mat, Imgproc.MORPH_OPEN, new Mat());
        Imgproc.morphologyEx(mat, mat, Imgproc.MORPH_CLOSE, new Mat());

        // Find Contours
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(mat, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        // Draw Contours
        Imgproc.drawContours(input, contours, -1, new Scalar(255, 0, 0));

        double centerValue = Math.round(Core.mean(centerMat).val[2] / 255);
        double leftValue = Math.round(Core.mean(leftMat).val[2] / 255);
        double rightValue = Math.round(Core.mean(rightMat).val[2] / 255);

        //mat.release();

        return mat;
    }
}

