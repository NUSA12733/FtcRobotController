package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robots.AutoRobot;
import org.firstinspires.ftc.teamcode.Robots.Robot;
@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class Autonomous extends LinearOpMode {

    public void initialize(){}


    @Override
    public void runOpMode() throws InterruptedException {
        AutoRobot robot = new AutoRobot(hardwareMap, telemetry, this);
        robot.execAuto();
        sleep(10000);





    }
}
