package org.firstinspires.ftc.teamcode.initial_tests.autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.abilities.Localization;
import org.firstinspires.ftc.teamcode.computer_vision.tensorflow.ObjectsDetected;
import org.firstinspires.ftc.teamcode.controllers.claw.complete.CompleteClawAutonomous;
import org.firstinspires.ftc.teamcode.locomotion.autonomous.MotorEncodersSetup;

@Autonomous(name = "Initial Autonomous Test", group = "Autonomous Tests")
public class InitialAutoTests extends LinearOpMode {
    Localization GPS = new Localization();
    CompleteClawAutonomous claw = new CompleteClawAutonomous();
    MotorEncodersSetup robot = new MotorEncodersSetup();
    boolean Dir;
    boolean BotSide;
    /* Dir logic // The direction the HUB is
     *  true == Hub is to the right
     *  false == Hub is to the left
     *
     * BotSide logic // The alliance the robot belongs.
     *  true == robot belongs to blue alliance
     *  false == robot belongs to red alliance
     */
    @Override
    public void runOpMode() {
        robot.runOpMode(hardwareMap);
        telemetry.addData(">", "Press Play to Begin");
        telemetry.update();
        waitForStart();
        if (opModeIsActive()) {
            HubDirection();
            robot.setupEncoders();
            while (opModeIsActive()) {
                straightToTheHub();
                telemetry.addData("Status", "Op Mode Running");
            } telemetry.update();
        }
    }

    public void straightToTheHub(){
        robot.moveRobot(0, 0,200,200, 1);
        sleep(25);
        switch (ObjectsDetected.DuckBarcode){
            case 1:
            case 3:
            case 2:
                claw.runOpMode();
                //TODO: Adjust the claw to place the duck in the correct levels
                break;
            default:
        }
        pointToCarousel();
    }
    public void pointToCarousel() {
        robot.moveRobot(50, 50,-50,-50, 1);
        sleep(20);
        goingToCarousel();
    }
    public void goingToCarousel() {
        robot.moveRobot(500, 500,500,500, 1);
        sleep(500);
        //motor.mDDR.setPower(1);
        //motor.mDDR.setPower(1);
        walkingSideways();
    }
    public void walkingSideways() {
        robot.moveRobot(-100, 100,100,-100, 1);
        sleep(100);
        straightToTheDeposit();
    }
    public void straightToTheDeposit() {
        robot.moveRobot(100, 100,100,100, 1);
    }
    public void HubDirection() {
        double refX = 0;
        double refY = 0;
        if(GPS.LX < refX){
            BotSide = true;
            Dir = !(GPS.LY < refY);
        }
        else{
            BotSide = false;
            Dir = GPS.LY < refY;
        }
    }
}