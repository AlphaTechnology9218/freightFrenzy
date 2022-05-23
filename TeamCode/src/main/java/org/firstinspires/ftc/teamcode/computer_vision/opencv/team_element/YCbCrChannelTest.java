package org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class YCbCrChannelTest extends OpenCvPipeline {
    public enum RobotPosition {
        LEFT,
        RIGHT,
        CENTER,
        NONE
    }

    public RobotPosition robotPosition;

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

        if ((leftValue > rightValue) && (leftValue > centerValue)) {
            position = 1;
            robotPosition = RobotPosition.LEFT;
        } else if ((rightValue > leftValue) && (rightValue > centerValue)) {
            position = 2;
            robotPosition = RobotPosition.RIGHT;
        } else if ((centerValue > leftValue) && (centerValue > rightValue)) {
            position = 3;
            robotPosition = RobotPosition.CENTER;
        } else {
            position = 4;
            robotPosition = RobotPosition.NONE;
        }

        return (outPut);
    }

    public RobotPosition getRobotPosition() {
        return this.robotPosition;
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
