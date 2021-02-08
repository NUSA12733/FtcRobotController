package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Route;
import org.firstinspires.ftc.teamcode.Routes.RouteA;
import org.firstinspires.ftc.teamcode.Routes.RouteB;
import org.firstinspires.ftc.teamcode.Routes.RouteC;

public class AutoRobot extends Robot {
    private LinearOpMode op;
    private ObjectDetector ringDetector;
    private Route route;
    private static final double ENCODER_TICKS_PER_REV = 537.6;
    private static final double WHEEL_CIRCUMFERENCE = 2.95 * Math.PI;
    private static final int TICKS_PER_INCH = (int) Math.round(ENCODER_TICKS_PER_REV / WHEEL_CIRCUMFERENCE);

    public AutoRobot(HardwareMap hm, Telemetry t, LinearOpMode op) {
        super(hm, t);
        this.op = op;
        this.ringDetector = new ObjectDetector(hm, t, op);
        this.moveClaw(Direction.FORWARD);
    }

    public int inchesToTicks(int inches, Direction dir){
        return dir.equals(Direction.STRAFE_RIGHT) || dir.equals(Direction.STRAFE_LEFT)?
                (int)Math.round(inches * TICKS_PER_INCH / 1.33): inches * TICKS_PER_INCH;
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

    public void setAllMotorsTargetPos(int inches, Direction dir){
        //GOING FORWARD
        if(dir.equals(Direction.FORWARD)){
            for (DcMotor motor: driveMotors){
                motor.setTargetPosition(inchesToTicks(inches, dir));
            }
        }
        //GOING BACKWARD
        else if(dir.equals(Direction.BACKWARD)){
            for (DcMotor motor: driveMotors){
                motor.setTargetPosition(-inchesToTicks(inches, dir));
            }
        }
        //STRAFING RIGHT
        else if (dir.equals(Direction.STRAFE_RIGHT)){
            for(int i = 0; i < driveMotors.length; i++ ) {
                driveMotors[i].setTargetPosition(i % 2 == 0 ? -inchesToTicks(inches, dir): inchesToTicks(inches, dir));
            }
        }
        //STRAFING LEFT
        else if (dir.equals(Direction.STRAFE_LEFT)){
            for(int i = 0; i < driveMotors.length; i++ ) {
                driveMotors[i].setTargetPosition(i % 2 == 0 ? inchesToTicks(inches, dir): -inchesToTicks(inches, dir));
            }
        }
    }

    public void setAllMotorsPower(double power){

        //If motor is going forward or backward, set all motor powers the same

            for (DcMotor motor: driveMotors){
                motor.setPower(power);
            }


//        if(dir.equals(Direction.STRAFE_LEFT)|| dir.equals(Direction.STRAFE_RIGHT)){
//            for(int i = 0; i < driveMotors.length; i++ ){
////                driveMotors[i].setPower(i % 2 == 0 ? -power: power);
//                if(i % 2 == 0){
//                    driveMotors[i].setPower(power);
//                }else {
//                    driveMotors[i].setPower(-power);
//                }
//            }
//        }

    }

    public void drive(Direction dir, double speed, int inches){
        resetAllEncoders();

//        if (dir.equals(Direction.FORWARD) || dir.equals(Direction.STRAFE_RIGHT)) {
//            setAllMotorsTargetPos(inches);
//        } else if(dir.equals(Direction.BACKWARD)||dir.equals(Direction.STRAFE_LEFT)){
//            setAllMotorsTargetPos(-inches);
//        }
        setAllMotorsTargetPos(inches, dir);
        setMotorsToRunToPosition();
        setAllMotorsPower(speed);


        while(frontR.isBusy() || backL.isBusy() || frontL.isBusy() || backR.isBusy()){
            t.addData("Moving to position:", frontR.getTargetPosition());
            t.update();
        }
        setAllMotorsPower(0);
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
        this.setRoute();
        if(route instanceof RouteA || route instanceof  RouteC){
//            this.moveClaw(Direction.FORWARD);
            op.sleep(250);
            if(route.getCircumInches() != 0){
                this.drive(Direction.STRAFE_LEFT, route.getCircumSpeed(), route.getCircumInches());
                op.sleep(250);
            }
            this.drive(Direction.FORWARD, route.getForwardSpeed(), route.getForwardInches());
            op.sleep(250);
            this.drive(Direction.STRAFE_RIGHT, route.getRightSpeed(), route.getRightInches() + route.getCircumInches());
            op.sleep(250);
            this.moveClaw(Direction.BACKWARD);
            op.sleep(750);
            this.drive(Direction.STRAFE_LEFT,route.getLeftSpeed(), route.getLeftInches());
            op.sleep(250);
            this.drive(Direction.BACKWARD, route.getBackwardSpeed(), route.getBackwardInches());
            op.sleep(250);
            threeShotFunction();
            op.sleep(2000);
            this.drive(Direction.FORWARD, route.getParkSpeed(), route.getParkInches());
        } else {
//            this.moveClaw(Direction.FORWARD);
            op.sleep(250);
            this.drive(Direction.STRAFE_LEFT, route.getCircumSpeed(), route.getCircumInches());
            op.sleep(250);
            this.drive(Direction.FORWARD, route.getForwardSpeed(), route.getForwardInches());
            op.sleep(250);
            this.drive(Direction.STRAFE_RIGHT, 0.5, 10);
            op.sleep(250);
            this.moveClaw(Direction.BACKWARD);
            op.sleep(500);
            this.drive(Direction.STRAFE_LEFT, 0.5, 10);
            op.sleep(250);
            this.drive(Direction.BACKWARD, 0.6, 27);
            op.sleep(250);
            this.drive(Direction.STRAFE_RIGHT, 0.6, 45);
            op.sleep(250);
            this.threeShotFunction();
            op.sleep(1500);
            this.drive(Direction.FORWARD, 1, 25);

        }

    }


}
