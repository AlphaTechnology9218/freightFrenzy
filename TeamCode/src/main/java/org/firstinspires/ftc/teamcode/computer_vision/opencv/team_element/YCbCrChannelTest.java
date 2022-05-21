package org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class YCbCrChannelTest extends OpenCvPipeline {
    public int position;
    Mat YCrCb  = new Mat();
    Mat outPut = new Mat();

    Mat leftMat;
    Mat rightMat;
    Mat centerMat;

    double leftValue;
    double rightValue;
    double centerValue;

    Scalar rectColor = new Scalar(255.0, 0.0, 0.0);

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);

        Rect leftRect = new Rect(1, 60, 80, 120);
        Rect centerRect = new Rect(80, 60, 80, 120);
        Rect rightRect = new Rect(160, 60, 80, 120);

        input.copyTo(outPut);
        Imgproc.rectangle(outPut, leftRect, rectColor, 2);
        Imgproc.rectangle(outPut, rightRect, rectColor, 2);
        Imgproc.rectangle(outPut, centerRect, rectColor, 2);

        leftMat = YCrCb.submat(leftRect);
        rightMat = YCrCb.submat(rightRect);
        centerMat = YCrCb.submat(centerRect);

        Core.extractChannel(leftMat, leftMat, 1);
        Core.extractChannel(rightMat, rightMat, 1);
        Core.extractChannel(centerMat, centerMat, 1);

        Scalar leftAvg = Core.mean(leftMat);
        Scalar rightAvg = Core.mean(rightMat);
        Scalar centerAvg = Core.mean(centerMat);

        leftValue = Math.round(leftAvg.val[0]);
        rightValue = Math.round(rightAvg.val[0]);
        centerValue = Math.round(centerAvg.val[0]);

        if ((leftValue > rightValue) && (leftValue > centerValue)) {
            //telemetry.addLine("The Object is on the Left");
            position = 0;
        } else if ((rightValue > leftValue) && (rightValue > centerValue)) {
            //telemetry.addLine("The Object ios on the Right");
            position = 1;
        } else if ((centerValue > leftValue) && (centerValue > rightValue)) {
            //telemetry.addLine("The object is on the center");
            position = 2;
        } else {
            //telemetry.addLine("There are no objects");
            position = 3;
        }

        return (outPut);
    }
}


