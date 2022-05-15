package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class BasicBlueBoxDetection extends OpenCvPipeline {
    Mat YCbCr  = new Mat();
    Mat outPut = new Mat();
    Mat leftMat;
    Mat rightMat;

    double leftValue;
    double rightValue;

    Scalar rectColor = new Scalar(255.0, 0.0, 0.0);

    @Override
    public Mat processFrame(Mat input) {

        Imgproc.cvtColor(input, YCbCr, Imgproc.COLOR_RGB2YCrCb);
        // telemetry.addLine("pipeline is running");

        Rect leftRect   = new Rect(1, 1, 20, 106);
        Rect rightRect  = new Rect(21, 1, 40, 106);

        input.copyTo(outPut);
        Imgproc.rectangle(outPut, leftRect, rectColor, 2);
        Imgproc.rectangle(outPut, rightRect, rectColor, 2);

        leftMat = YCbCr.submat(leftRect);
        rightMat = YCbCr.submat(rightRect);

        Core.extractChannel(leftMat, leftMat, 1);
        Core.extractChannel(rightMat, rightMat, 1);

        Scalar leftValueAvg = Core.mean(leftMat);
        Scalar rightValueAvg = Core.mean(rightMat);

        leftValue = leftValueAvg.val[0];
        rightValue = rightValueAvg.val[0];

        if(leftValue > rightValue) {
            // telemetry.addLine("The object is on the left");
        } else if (rightValue > leftValue) {
            // telemetry.addLine("The object is on the right");
        } else {
            // telemetry.addLine("There are no objects");
        }

        return(outPut);
    }
}
