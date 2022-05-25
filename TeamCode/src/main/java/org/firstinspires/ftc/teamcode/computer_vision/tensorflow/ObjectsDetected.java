package org.firstinspires.ftc.teamcode.computer_vision.tensorflow;

public class ObjectsDetected {

public static int DuckBarcode; // The level where the duck should be placed in the HUB

    public void duckObject() {
    }
    public void ballObject() {
    }
    public void cubeObject() {
    }
    public void markerBarCode(float right, float left) {
        // These values probably need readjusting as I don't know where one bar starts and end in
        // these parameters
        if(right > 50){ // 3° shipping level
            DuckBarcode = 3;
        }
        else if(left < 50 && right < 50) { // 2° shipping level
            DuckBarcode = 2;
        }
        else{ // 1° shipping level
            DuckBarcode = 1;
        }
    }
    // TODO: Program robot's movement to take duck object and put the freight in the correct
    //  Shipping Level.
}
