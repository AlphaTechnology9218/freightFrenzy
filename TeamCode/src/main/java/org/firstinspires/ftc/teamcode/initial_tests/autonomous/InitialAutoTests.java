package org.firstinspires.ftc.teamcode.initial_tests.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.abilities.Localization;
import org.firstinspires.ftc.teamcode.computer_vision.tensorflow.ObjectsDetected;
import org.firstinspires.ftc.teamcode.controllers.carousel.CarouselControl;
import org.firstinspires.ftc.teamcode.controllers.claw.CompleteClawAutonomous;
import org.firstinspires.ftc.teamcode.locomotion.autonomous.MotorEncodersSetup;

@Autonomous(name = "Initial Autonomous Test", group = "Autonomous Tests")
public class InitialAutoTests extends MotorEncodersSetup {
    CarouselControl carousel = new CarouselControl();
    Localization GPS = new Localization();
    CompleteClawAutonomous claw = new CompleteClawAutonomous();

    boolean Dir; // The direction the HUB is
    boolean BotSide; // The alliance the robot belongs.

    @Override
    public void runOpMode() {
        telemetry.addData(">", "Press Play to Begin");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            HubDirection();

            while (opModeIsActive()) {
                straightToTheHub();
            } telemetry.update(); }
    }

    /* Dir logic
     *  true == Hub is to the right
     *  false == Hub is to the left
     *
     * BotSide logic
     *  true == robot belongs to blue alliance
     *  false == robot belongs to red alliance
     */
    public void moveStorageUnit(){
        moveRobot(500, 500,500,500, 1);
    }

    public void straightToTheHub(){
        if(Dir) {
            moveRobot(200, 200,0,0, 1);
        }
        else moveRobot(0, 0, 200, 200, 1);
        sleep(5000);

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
        moveRobot(50, 50,-50,-50, 1);
        sleep(5000);
        goingToCarousel();
    }

    public void goingToCarousel() {
        moveRobot(500, 500,500,500, 1);
        sleep(5000);
        carousel.mDD.setPower(1);
        walkingSideways();
    }

    public void walkingSideways() {
        moveRobot(-100, 100,100,-100, 1);
        sleep(5000);
        straightToTheDeposit();
    }

    public void straightToTheDeposit() {
        moveRobot(100, 100,100,100, 1);
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
