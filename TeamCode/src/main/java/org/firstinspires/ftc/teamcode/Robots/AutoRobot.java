package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Route;
import org.firstinspires.ftc.teamcode.Routes.RouteA;
import org.firstinspires.ftc.teamcode.Routes.RouteB;
import org.firstinspires.ftc.teamcode.Routes.RouteC;

public class AutoRobot extends Robot {
    private OpMode op;
    private ObjectDetector ringDetector;
//    private NormalizedColorSensor colorSensor;
    private Route route;

    private enum RUNMODE {
        WITH_ENCODERS,
        WITHOUT_ENCODERS
    }

    public AutoRobot(HardwareMap hm, Telemetry t, LinearOpMode op) {
        super(hm, t);
//        this.openCV = new OpenCV(hm, t);
        this.op = op;
        this.ringDetector = new ObjectDetector(hm, t, op);
//        this.colorSensor = hm.get(NormalizedColorSensor.class, "colorSensor");
//        openCV.init();
//        setMotorRunMode(RUNMODE.WITH_ENCODERS);
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

//    private int getNumberOfRings(){
//        ringDetector.init();
//        String numRingsStr = ringDetector.run();
//        int numRings;
//        if(numRingsStr.equals("QUAD")){
//            return 4;
//        } else if(numRingsStr.equals("SINGLE")){
//            return 1;
//        } else {
//            return 0;
//        }
//    }

    public void initRingDetection(){
        ringDetector.init();
    }

    private int getNumberOfRings(){
        String output = ringDetector.run();
        if(output.equals("Quad")){
            return 4;
        } else if(output.equals("Single")){
            return 1;
        } else {
            return 0;
        }
    }

    private void setRoute(){
        int numRings = getNumberOfRings();
        if(numRings == 0){
            route = new RouteA();
            t.addLine("Executing Route A");
        } else if(numRings == 1){
            route = new RouteB();
            t.addLine("Executing Route B");
        } else if(numRings ==4){
            route = new RouteC();
            t.addLine("Excecuting Route C");
        }

        t.update();
    }

    public void execAuto(){
//        final int numRings = getNumberOfRings();
//        t.addData("Rings:", numRings);
//        t.update();

        this.setRoute();
    }






}
