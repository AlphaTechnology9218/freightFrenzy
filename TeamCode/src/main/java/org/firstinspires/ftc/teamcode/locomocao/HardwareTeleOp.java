package org.firstinspires.ftc.teamcode.locomocao;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class HardwareTeleOp extends OpMode{

    public DcMotor mFE, mTE, mFD, mTD;
    /*
     **************************************************************************
     * mFE - Variável motor frente esquerda                                   *
     * mTE - Variável motor tras esquerda                                     *
     * mFD - Variável motor frente direita                                    *
     * mTD - Variável motor tras direita                                      *
     **************************************************************************
     */

    @Override
    public void init() {
        mFE = hardwareMap.get(DcMotor.class, "motorFE");
        mTE = hardwareMap.get(DcMotor.class, "motorTE");
        mFD = hardwareMap.get(DcMotor.class, "motorFD");
        mTD = hardwareMap.get(DcMotor.class, "motorTD");

        mFE.setDirection(DcMotor.Direction.REVERSE);
        mTE.setDirection(DcMotor.Direction.REVERSE);
        mFD.setDirection(DcMotor.Direction.FORWARD);
        mTD.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop() {

    }
}
