package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {

    //Hardware Declaration
    protected DcMotor frontR;
    protected DcMotor frontL;
    protected DcMotor backR;
    protected DcMotor backL;
    protected DcMotor rearIntake;
    protected DcMotor frontIntake;
    protected DcMotor shooterRight;
    protected DcMotor shooterLeft;
    protected Servo rightLaunch;
    protected Servo leftLaunch;
    protected Servo clamp;
    protected Servo safetyStop;
    protected CRServo lift;
    protected CRServo scissor;
    protected Gamepad driverController;
    protected Gamepad assistantController;
    protected CentimetersToEncodersConverter converter;
    protected Telemetry t;
    ElapsedTime timer = new ElapsedTime();

//    private OpenCV openCV;


    protected enum Direction{
        FORWARD,
        BACKWARD,
        UP,
        DOWN,
        STRAFE_LEFT,
        STRAFE_RIGHT,
        TURN_LEFT,
        TURN_RIGHT,
        NONE
    }

    protected DcMotor driveMotors [];
    
    //Constructor for TeleOp Robot
    public Robot(Gamepad gamepad1, Gamepad gamepad2, HardwareMap hm, Telemetry t){
        this(hm, t);
        driverController = gamepad1;
        assistantController = gamepad2;

    }

    //Constructor for autonomous robot
    public Robot(HardwareMap hm, Telemetry t){
        this.t = t;
        init(hm, t);
    }

    //Hardware Initialization
    protected void init(HardwareMap hm, Telemetry t){

        frontR = hm.get(DcMotor.class, "frontR");
        frontL = hm.get(DcMotor.class, "frontL");
        backR = hm.get(DcMotor.class, "backR");
        backL = hm.get(DcMotor.class, "backL");
        rearIntake = hm.get(DcMotor.class, "rearIntake");
        frontIntake = hm.get(DcMotor.class, "frontIntake");
        shooterRight = hm.get(DcMotor.class, "shooterRight");
        shooterLeft = hm.get(DcMotor.class, "shooterLeft");
        leftLaunch = hm.get(Servo.class, "leftLaunch");
        rightLaunch = hm.get(Servo.class, "rightLaunch");
        clamp = hm.get(Servo.class, "clamp");
        safetyStop = hm.get(Servo.class, "safetyStop");
        lift = hm.get(CRServo.class, "lift");
        scissor = hm.get(CRServo.class, "scissor");

        //Reversing right motors, because they turn counter clockwise
        frontR.setDirection(DcMotorSimple.Direction.REVERSE);
        backR.setDirection(DcMotorSimple.Direction.REVERSE);
        frontIntake.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        converter = new CentimetersToEncodersConverter();
        leftLaunch.setPosition(0);
        positionSafetyStop(Direction.UP);

         driveMotors = new DcMotor[] {frontR, frontL, backR, backL};
    }

    protected void setIntakePower(double power){
        rearIntake.setPower(power);
        frontIntake.setPower(power);
    }

    protected void shoot(double power){
        shooterRight.setPower(power);
        shooterLeft.setPower(power);
    }

    protected void pushRing(Direction pushDirection){

      if(pushDirection.equals(Direction.FORWARD)){
//          rightLaunch.setPosition(1);
          leftLaunch.setPosition(0);
      }

      if(pushDirection.equals(Direction.BACKWARD)){
//          rightLaunch.setPosition(0);
          leftLaunch.setPosition(1);
      }
    }

    protected void moveLift(Direction direction){
        moveCRServo(lift, direction);
    }

    protected void moveScissor(Direction direction){
        moveCRServo(scissor, direction);
    }

    protected void moveCRServo(CRServo servo, Direction direction){
        if (direction.equals(Direction.UP)) {
            servo.setPower(1);
        } else if(direction.equals(Direction.DOWN)){
            servo.setPower(-1);
        } else {
            servo.setPower(-0.06);
        }
    }

    protected void moveClaw(Direction dir) {
        if(dir.equals(Direction.FORWARD)){
            clamp.setPosition(1);
        } else if(dir.equals(Direction.BACKWARD)){
            clamp.setPosition(0.4);
        }
    }

    protected void positionSafetyStop(Direction direction){
        if (direction.equals(Direction.UP)) {
            safetyStop.setPosition(1);
        } else if(direction.equals(Direction.DOWN)){
            safetyStop.setPosition(0.5);
        }
    }




    //AUTO METHODS
    public void go(){

    }

    protected void shoot(double power, double duration){
        this.shoot(power);
    }

    protected void threeShotFunction(){
        try {
            shoot(0.55);
            Thread.sleep(3000);
            positionSafetyStop(Direction.DOWN);
            for(int i =0; i< 3; i++){
                Thread.sleep(2000);
                pushRing(Direction.FORWARD);
                Thread.sleep(2000);
                pushRing(Direction.BACKWARD);
            }
        } catch (InterruptedException e){
            t.addData("Exception was thrown", e);
            t.update();
        }


    }
}
