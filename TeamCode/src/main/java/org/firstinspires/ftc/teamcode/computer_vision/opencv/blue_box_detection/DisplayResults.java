package org.firstinspires.ftc.teamcode.computer_vision.opencv.blue_box_detection;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.lang.annotation.Target;
@Autonomous(name = "Display Box Detection Results", group = "Computer Vision")
public class DisplayResults extends LinearOpMode {
    @Override
    public void runOpMode() {
        BasicBlueBoxDetection boxDetection = new BasicBlueBoxDetection(hardwareMap, telemetry);

        waitForStart();

        TargetObject target = boxDetection.getTarget();

        switch (target) {
            case BLUE:
                assert true;
            case NO_BLUE:
                assert true;
        }
        telemetry.update();
    }
}