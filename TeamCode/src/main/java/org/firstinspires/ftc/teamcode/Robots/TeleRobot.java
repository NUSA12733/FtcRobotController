package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TeleRobot extends Robot {

    public TeleRobot(Gamepad gamepad1, Gamepad gamepad2, HardwareMap hm, Telemetry t) {
        super(gamepad1, gamepad2, hm, t);
    }

    protected void setWheelPower(){
        double drive;
        double turn;
        double drift;

        drive = -driverController.left_stick_y;
        turn = driverController.right_stick_x;
        drift = driverController.left_stick_x;

        this.frontL.setPower(drive + turn + drift);
        this.backL.setPower(drive + turn - drift);
        this.frontR.setPower(drive - turn - drift);
        this.backR.setPower(drive - turn + drift);
    }

    public void driverControl(){
        setWheelPower();

        //DRIVER CONTROLLER

        //LIFT - up, down, stop
        if(driverController.right_trigger !=0){
            moveLift(Direction.UP);
        } else if(driverController.left_trigger != 0){
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
