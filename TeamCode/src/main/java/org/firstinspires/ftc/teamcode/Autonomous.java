package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robots.AutoRobot;
import org.firstinspires.ftc.teamcode.Robots.Robot;
@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class Autonomous extends LinearOpMode {

    AutoRobot robot;
    public void initialize(){
        robot = new AutoRobot(hardwareMap, telemetry, this);
        robot.initRingDetection();
        waitForStart();
    }


    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        robot.execAuto();

    }
}
