package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
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
    private static final int ENCODER_TICKS_PER_REV = 134;
    private static final double WHEEL_CIRCUMFERENCE = 2.95 * Math.PI;
    private static final int TICKS_PER_INCH = (int) Math.round(ENCODER_TICKS_PER_REV / WHEEL_CIRCUMFERENCE);

    public AutoRobot(HardwareMap hm, Telemetry t, LinearOpMode op) {
        super(hm, t);
        this.op = op;
        this.ringDetector = new ObjectDetector(hm, t, op);
//        this.colorSensor = hm.get(NormalizedColorSensor.class, "colorSensor");
    }

    //DRIVING FUNCTIONS
    public void resetAllEncoders(){
        for (DcMotor motor: driveMotors){
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    public void setMotorsToRunToPosition(){
        for (DcMotor motor: driveMotors){
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

    public void setAllMotorsTargetPos(int ticks){
        for (DcMotor motor: driveMotors){
            motor.setTargetPosition(ticks);
        }
    }


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
