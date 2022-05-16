package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class BlueBoxDetectionHSV extends OpenCvPipeline {
    Mat mat      = new Mat();
    Mat leftMat  = new Mat();
    Mat rightMat = new Mat();
    Mat outPut   = new Mat();

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGBA2RGB);
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV);

        Scalar lowerScale = new Scalar(220.0 / 2, 100, 100);
        Scalar upperScale = new Scalar(260.0 / 2, 100, 100);

        Rect leftRect = new Rect(1, 1, 120, 160);
        Rect rightRect = new Rect(120, 1, 120, 160);

        input.copyTo(outPut);

        return null;
    }
}
