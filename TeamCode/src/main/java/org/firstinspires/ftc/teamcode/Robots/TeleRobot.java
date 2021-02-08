package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TeleRobot extends Robot {

    public TeleRobot(Gamepad gamepad1, Gamepad gamepad2, HardwareMap hm, Telemetry t) {
        super(gamepad1, gamepad2, hm, t);
        for(DcMotor motor : driveMotors){
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    protected void setWheelPower(){
        double drive;
        double turn;
        double drift;
        double adjValue = 0.4;

        if (driverController.left_stick_y != 0 || driverController.right_stick_x != 0 || driverController.left_stick_x != 0) {
            drive = -driverController.left_stick_y;
            turn = driverController.right_stick_x;
            drift = driverController.left_stick_x;

            this.frontL.setPower(drive + turn + drift);
            this.backL.setPower(drive + turn - drift);
            this.frontR.setPower(drive - turn - drift);
            this.backR.setPower(drive - turn + drift);
        } else if(driverController.dpad_right){
            this.frontL.setPower(adjValue);
            this.backL.setPower(adjValue);
            this.frontR.setPower(-adjValue);
            this.backR.setPower(-adjValue);
        } else if(driverController.dpad_left){
            this.frontL.setPower(-adjValue);
            this.backL.setPower(-adjValue);
            this.frontR.setPower(adjValue);
            this.backR.setPower(adjValue);
        } else {
            this.frontL.setPower(0);
            this.backL.setPower(0);
            this.frontR.setPower(0);
            this.backR.setPower(0);
        }

    }

    public void driverControl(){
        setWheelPower();

        //DRIVER CONTROLLER

        //LIFT - up, down, stop
        if(assistantController.right_trigger !=0){
            moveLift(Direction.UP);
        } else if(assistantController.left_trigger != 0){
            moveLift(Direction.DOWN);
        } else {
            moveLift(Direction.NONE);
        }

        //SCISSOR - in, out
        if(driverController.right_bumper){
            moveScissor(Direction.UP);
        } else if(driverController.left_bumper){
            moveScissor(Direction.DOWN);
        } else {
            moveScissor(Direction.NONE);
        }

        //CLAW
        if(driverController.a){
            this.moveClaw(Direction.FORWARD);
        } else if (driverController.b){
            this.moveClaw(Direction.BACKWARD);
        }


        //Assistant Driver
        //SHOOTING MECHANISM - start, stop
        if(assistantController.right_bumper){
            shoot(0.5);
        }

        if(assistantController.left_bumper){
            shoot(0);
        }

        //INTAKE MECHANISM
        if(assistantController.a){
            setIntakePower(1);
        }

        if(assistantController.b){
            setIntakePower(0);
        }

        if(assistantController.dpad_left){
            setIntakePower(-1);
        }

        //SAFETY STOP
        if(assistantController.dpad_up){
            positionSafetyStop(Direction.UP);
        }

        if(assistantController.dpad_down){
            positionSafetyStop(Direction.DOWN);
        }

        if(assistantController.dpad_right){
            threeShotFunction();
        }

        //Servo testing
        if(assistantController.x){
            pushRing(Direction.FORWARD);
        } else if(assistantController.y) {
            pushRing(Direction.BACKWARD);
        }

    }
}
