package org.firstinspires.ftc.teamcode.locomocao;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

// TODO: Internationalize this program.

@Autonomous(name = "Ecoders para Movimentacao", group = "Auto")
@Disabled
public class EncodersMovimentacao extends LinearOpMode {
    public DcMotor mFE, mTE, mFD, mTD;
    private int pFE, pTE, pFD, pTD;
    /**************************************************************************
     * mFE - Variável motor frente esquerda                                   *
     * mTE - Variável motor tras esquerda                                     *
     * mFD - Variável motor frente direita                                    *
     * mTD - Variável motor tras direita                                      *
     *                                                                        *
     * pFE - Variavel posição frente esquerda                                 *
     * pTE - Variavel posição tras esquerda                                   *
     * pFD - Variavel posição frente direita                                  *
     * pTD - Variavel posição tras direita                                    *                                                                    *
     **************************************************************************/

    @Override
    public void runOpMode() {
        mFE = hardwareMap.get(DcMotor.class, "motorFE");
        mTE = hardwareMap.get(DcMotor.class, "motorTE");
        mFD = hardwareMap.get(DcMotor.class, "motorFD");
        mTD = hardwareMap.get(DcMotor.class, "motorTD");

        mFE.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mTE.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mFD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mTD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        mFE.setDirection(DcMotor.Direction.REVERSE);
        mTE.setDirection(DcMotor.Direction.REVERSE);
        mTD.setDirection(DcMotor.Direction.FORWARD);
        mFD.setDirection(DcMotor.Direction.FORWARD);
    }

    /**
     * Move robot using encoders while motors aren't busy
     * @param tLF - left front target
     * @param tBL - back left target
     * @param tRF - right front target
     * @param tBR - back right target
     * @param vel - velocity
     */
    public void moveRobot(int tLF, int tBL, int tRF, int tBR, double vel) {
        pTD += tRF;
        pTE += tLF;
        pFD += tBR;
        pFE += tBL;

        mFE.setTargetPosition(pFE);
        mTE.setTargetPosition(pTE);
        mFD.setTargetPosition(pFD);
        mTD.setTargetPosition(pTD);

        mFE.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mTE.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mFD.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mTD.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        mFE.setPower(vel);
        mTE.setPower(vel);
        mFD.setPower(vel);
        mTD.setPower(vel);

        while (opModeIsActive() && mFE.isBusy() && mTE.isBusy() && mFD.isBusy() && mTD.isBusy()) {
            idle();
        }
    }
}
