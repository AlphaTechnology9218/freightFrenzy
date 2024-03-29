package org.firstinspires.ftc.teamcode.computer_vision.opencv.basics;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class PipelineExample extends OpenCvPipeline {
    Mat grey = new Mat();

    int lastResult = 0;

    @Override
    public void init(Mat firstFrame) {
        grey = firstFrame.submat(0, 50, 0,50);
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, grey, Imgproc.COLOR_RGB2HSV);
        return grey;
    }

    public int getLastResult() {
        return lastResult;
    }
}
