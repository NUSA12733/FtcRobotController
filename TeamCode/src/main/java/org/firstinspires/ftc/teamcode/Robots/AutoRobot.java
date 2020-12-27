package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EasyOpenCVExample;
import org.firstinspires.ftc.teamcode.Robots.Robot;
import org.firstinspires.ftc.teamcode.Robots.OpenCV;

public class AutoRobot extends Robot {
    private OpenCV openCV;
    private OpMode op;
//    private NormalizedColorSensor colorSensor;

    private enum RUNMODE {
        WITH_ENCODERS,
        WITHOUT_ENCODERS
    }

    public AutoRobot(HardwareMap hm, Telemetry t, OpMode op) {
        super(hm, t);
        this.openCV = new OpenCV(hm, t);
        this.op = op;
//        this.colorSensor = hm.get(NormalizedColorSensor.class, "colorSensor");
        openCV.init();
//        setMotorRunMode(RUNMODE.WITH_ENCODERS);
    }

    public int getThreshold(){
        return openCV.getThresholdVal();
    }


    public int getNumberRings(){
        return openCV.getNumberOfRings();
    }

//    public void drive(double speed, int encoderTicks){
//        this.resetEncoders();
//        this.setEncoderTargetPos(encoderTicks);
//        this.setMotorRunMode(RUNMODE.WITH_ENCODERS);
//        boolean isBusy = frontR.getCurrentPosition() != frontR.getTargetPosition();
//        while(isBusy){
//            this.drive(speed);
//        }
//        drive(0);
//
//    }

//    public void drive(double speed){
//
//    }

//    public void park(){
//
//    }

//    private void resetEncoders(){
//        for(DcMotor motor : driveMotors){
//            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        }
//    }

//    private void setMotorRunMode(RUNMODE rm){
//        for(DcMotor motor : driveMotors){
//            if (rm.equals(RUNMODE.WITH_ENCODERS)) {
//                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            } else if (rm.equals(RUNMODE.WITHOUT_ENCODERS)) {
//                motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            }
//        }
//    }

//    private void setEncoderTargetPos(int encoderTicks){
//        for(DcMotor motor : driveMotors){
//            motor.setTargetPosition(encoderTicks);
//        }
//    }

    public void execAuto(){
        int numRings = this.getNumberRings();
        t.addData("RINGS", numRings);
        t.update();


    }






}
