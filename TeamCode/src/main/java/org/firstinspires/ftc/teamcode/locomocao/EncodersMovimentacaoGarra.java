package org.firstinspires.ftc.teamcode.locomocao;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name = "encodersMovimentaçãoGarra", group = "algoritmosAutonomo")
public class EncodersMovimentacaoGarra extends LinearOpMode {
    private DcMotor mBD, mBE;
    public int pBD, pBE;
    /*
     **************************************************************************
     * mBD - Variável motor braço direito                                     *
     * mBE - Variável motor braço esquerdo                                    *                                                                   *
     * pBD - Variavel posição braço esquerdo                                  *
     * pBE - Variavel posição braço direito                                   *
     **************************************************************************
     */

    @Override
    public void runOpMode() {
        mBD = hardwareMap.get(DcMotor.class, "mBD");
        mBE = hardwareMap.get(DcMotor.class, "mBE");
        mBD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mBE.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mBD.setDirection(DcMotor.Direction.REVERSE);
        mBE.setDirection(DcMotor.Direction.FORWARD);
    }

    public void moverGarra(int aBD, int aBE, double vel) {
        /*
         **************************************************************************
         * aBD - Variável alvo braço direito                                      *
         * aBE - Variável alvo braço esquerdo                                     *
         * vel - Variável de velocidade                                           *
         **************************************************************************
         */

        pBD += aBD;
        pBE += aBE;
        mBD.setTargetPosition(pBD);
        mBE.setTargetPosition(pBE);
        mBD.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mBE.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mBD.setPower(vel);
        mBE.setPower(vel);

        while (mBD.isBusy() && mBE.isBusy()) { idle(); }
    }
}
