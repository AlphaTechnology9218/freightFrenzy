package org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class YCrCbChannel extends OpenCvPipeline {
    Mat YCrCb  = new Mat();
    Mat outPut = new Mat();

    Mat leftMat;
    Mat rightMat;
    Mat centerMat;

    double leftValue;
    double rightValue;
    double centerValue;

    Scalar rectColor = new Scalar(255.0, 0.0, 0.0);
    private final Object sync = new Object();

    @Override
    public Mat processFrame(Mat input) {
        synchronized (sync) {
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
        }
        return (outPut);
    }

    /*
     * Synchronize these operations as the user code could be incorrect otherwise, i.e a property is
     * read while the same rectangle is being processed in the pipeline, leading to some values being
     * not synced.
     */

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
