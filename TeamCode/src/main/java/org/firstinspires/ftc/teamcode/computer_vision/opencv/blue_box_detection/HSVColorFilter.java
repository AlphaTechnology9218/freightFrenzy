package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class HSVColorFilter extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

    class BlueBoxVision extends OpenCvPipeline {
        Mat mat  = new Mat();
        Mat mask = new Mat();

        @Override
        public Mat processFrame(Mat input) {
            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

            // lower and upper bound for BLUE
            Scalar lowerBound = new Scalar(221, 100, 100);
            Scalar upperBound = new Scalar(240, 255, 255);

            Core.inRange(mat, lowerBound, upperBound, mask);

            return null;
        }
    }
}
