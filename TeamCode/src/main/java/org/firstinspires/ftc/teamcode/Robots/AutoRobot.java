package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EasyOpenCVExample;
import org.firstinspires.ftc.teamcode.Robots.Robot;
import org.firstinspires.ftc.teamcode.Robots.OpenCV;

class AutoRobot extends Robot {
    private OpenCV openCV;

    public AutoRobot(HardwareMap hm, Telemetry t) {
        super(hm, t);
        this.openCV = new OpenCV(hm);
        openCV.init();
    }

    private int getNumberOfRings(){
        return openCV.getNumberOfRings();
    }

    public void drive(Direction direction, double speed, int encoderTicks){

    }




}
