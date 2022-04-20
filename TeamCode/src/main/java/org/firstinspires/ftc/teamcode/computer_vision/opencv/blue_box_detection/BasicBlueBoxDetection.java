package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot_components.SetupCellphone;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

import java.lang.annotation.Target;
import java.nio.channels.Pipe;

/*****************************
 * The OpenCV Process is divided in the four steps bellow:                         *
 *                                                                                 *
 * 1. Threshold - assignment of pixel values in relation to the threshold value    *
 * 2. Divide    - divide two rectangle view points                                 *
 * 3. Average   - measure the percentage of the amount of blue                     *
 * 4. Compare   - compare the results with the threshold value                     *
 * ****************************
 */

public class BasicBlueBoxDetection extends OpenCvPipeline {
    Mat mat = new Mat();
    Mat lowerMat;
    Mat upperMat;

    Rect lowerROI = new Rect(new Point(100, 50), new Point(200, 100));
    Rect upperROI = new Rect(new Point(100, 100), new Point(200, 150));

    public double lowerValue;
    public double upperValue;

    final double THRESHOLD = 10; // Adjust in the future
    public boolean seeingBox = false;

    private Telemetry telemetry;
    private OpenCvCamera cam;

    BasicBlueBoxDetection myPipeline;




    public static double borderLeftX    = 0.0;   //fraction of pixels from the left side of the cam to skip
    public static double borderRightX   = 0.0;   //fraction of pixels from the right of the cam to skip
    public static double borderTopY     = 0.0;   //fraction of pixels from the top of the cam to skip
    public static double borderBottomY  = 0.0;   //fraction of pixels from the bottom of the cam to skip






    public BasicBlueBoxDetection() {
        cam = OpenCvCameraFactory.getInstance().createInternalCamera
                (OpenCvInternalCamera.CameraDirection.BACK);

        cam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                cam.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                cam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);
                cam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {
                telemetry.addData("Status: ", "An error occurred with OpenCV!");
            }
        });
        //cam.setPipeline(myPipeline = new BasicBlueBoxDetection());
    }

    @Override
    public Mat processFrame(Mat input) {

        // Threshold | Color blue - 240°
        Imgproc.cvtColor(mat, input, Imgproc.COLOR_RGBA2RGB); // input RGBA to mat RGB
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV);    // input RGB to mat HSV

        Scalar lowerBound = new Scalar(220.0 / 2, 100, 100); // lower HSV scale
        Scalar upperBound = new Scalar(260.0 / 2, 200, 200); // upper HSV scale
        Core.inRange(mat, lowerBound, upperBound, mat);

        // Divide | 2 Rectangles
        lowerMat = mat.submat(lowerROI);
        upperMat = mat.submat(upperROI);

        // Average
        lowerValue = Math.round(Core.mean(lowerMat).val[2] / 255);
        upperValue = Math.round(Core.mean(upperMat).val[2] / 255);

        upperMat.release();
        lowerMat.release();
        mat.release();


        // Compare
        // TODO: address a function to the robot based in the percentage of the average

        if (lowerValue > THRESHOLD && upperValue > THRESHOLD) { seeingBox = true; }
        return null;
    }
}