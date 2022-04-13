package org.firstinspires.ftc.robotcontroller.opencv;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

@Disabled
public class PipelineExample extends OpenCvPipeline {
    Mat grey = new Mat();

    int lastResult = 0;

    @Override
    public void init(Mat firstFrame) {
        grey = firstFrame.submat(0, 50, 0,50);
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, grey, Imgproc.COLOR_RGB2GRAY);
        return grey;
    }

    public int getLastResult() {
        return lastResult;
    }
}

