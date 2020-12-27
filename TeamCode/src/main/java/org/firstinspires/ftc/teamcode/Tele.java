package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robots.TeleRobot;

@TeleOp
public class Tele extends LinearOpMode {


    @Override
    public void runOpMode(){
        TeleRobot tr = new TeleRobot(gamepad1, gamepad2, hardwareMap, telemetry);
        waitForStart();

        while(opModeIsActive()){
            tr.driverControl();
        }
    }
}
