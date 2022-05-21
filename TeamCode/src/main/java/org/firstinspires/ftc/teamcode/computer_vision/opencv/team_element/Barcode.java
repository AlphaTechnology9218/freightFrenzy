package org.firstinspires.ftc.teamcode.computer_vision.opencv.team_element;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class Barcode extends OpMode {
    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

    static class BarcodeVision extends OpenCvPipeline {
        Mat mat = new Mat();
        Telemetry telemetry;

        public enum TargetPosition {
            LEFT,
            CENTER,
            RIGHT,
            NONE
        }

        private TargetPosition targetPosition;

        static final Rect LEFT_RECT = new Rect(
                new Point(20, 20),
                new Point(40, 40)
        );

        static final Rect CENTER_RECT = new Rect(
                new Point(40, 40),
                new Point(60, 60)
        );

        static final Rect RIGHT_RECT = new Rect(
                new Point(60, 60),
                new Point(80, 80)
        );

        static double THRESHOLD = 10;

        public BarcodeVision(Telemetry t) {
            telemetry = t;
        }

        @Override
        public Mat processFrame(Mat input) {
            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGBA2RGB);
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV);

            // Lower and upper bound for PINK
            Scalar lowerBound = new Scalar(331.0 / 2, 30, 30);
            Scalar upperBound = new Scalar(345.0 / 2, 255, 255);
            Core.inRange(mat, lowerBound, upperBound, mat);

            Mat leftMat = mat.submat(LEFT_RECT);
            Mat rightMat = mat.submat(RIGHT_RECT);
            Mat centerMat = mat.submat(CENTER_RECT);

            double leftValue = Core.sumElems(leftMat).val[0] / LEFT_RECT.area() / 255;
            double rightValue = Core.sumElems(rightMat).val[0] / RIGHT_RECT.area() / 255;
            double centerValue = Core.sumElems(centerMat).val[0] / CENTER_RECT.area() / 255;

            leftMat.release();
            rightMat.release();
            centerMat.release();

            if (leftValue > THRESHOLD) {
                targetPosition = TargetPosition.LEFT;
                telemetry.addData("Position", "Left");
            }
            if (rightValue > THRESHOLD) {
                targetPosition = TargetPosition.RIGHT;
                telemetry.addData("Position", "Right");
            }
            if (centerValue > THRESHOLD) {
                targetPosition = TargetPosition.CENTER;
                telemetry.addData("Position", "Center");
            }
            return mat;
        }

        public TargetPosition getTargetPosition() {
            return targetPosition;
        }
    }
}

