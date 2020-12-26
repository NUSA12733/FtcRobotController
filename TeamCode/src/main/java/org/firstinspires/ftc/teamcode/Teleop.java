package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robots.Robot;
import org.firstinspires.ftc.teamcode.Robots.TeleRobot;


@TeleOp
public class Teleop extends OpMode {

    TeleRobot robot;
    HardwareMap hw;

    public void init(){
        robot = new TeleRobot(gamepad1, gamepad2, hardwareMap, telemetry);
    }
    public void loop(){
        robot.driverControl();



    }

}
