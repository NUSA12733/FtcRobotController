package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EasyOpenCVExample;
import org.firstinspires.ftc.teamcode.Robots.Robot;
import org.firstinspires.ftc.teamcode.Robots.OpenCV;

class AutoRobot extends Robot {
    private OpenCV openCV;
    private NormalizedColorSensor colorSensor;

    private enum RUNMODE {
        WITH_ENCODERS,
        WITHOUT_ENCODERS
    }

    public AutoRobot(HardwareMap hm, Telemetry t) {
        super(hm, t);
        this.openCV = new OpenCV(hm);
        this.colorSensor = hm.get(NormalizedColorSensor.class, "colorSensor");
        openCV.init();
        setMotorRunMode(RUNMODE.WITH_ENCODERS);
    }

    private int getNumberOfRings(){
        return openCV.getNumberOfRings();
    }

    public void drive(Direction direction, double speed, int encoderTicks){

    }

    public void drive(Direction direction, double speed){

    }

    public void park(){

    }

    private void setMotorRunMode(RUNMODE rm){
        for(DcMotor motor : driveMotors){
            if (rm.equals(RUNMODE.WITH_ENCODERS)) {
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            } else if (rm.equals(RUNMODE.WITHOUT_ENCODERS)) {
                motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
        }
    }






}
