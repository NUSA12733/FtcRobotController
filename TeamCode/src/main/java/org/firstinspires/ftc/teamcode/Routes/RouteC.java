package org.firstinspires.ftc.teamcode.Routes;

import org.firstinspires.ftc.teamcode.Route;
//45 speed
public class RouteC implements Route {

    @Override
    public int getForwardInches() {
        return 105;
    }

    @Override
    public double getForwardSpeed() {
        return 1;
    }

    @Override
    public int getCircumInches() {
        return 36;
    }

    @Override
    public double getCircumSpeed() {
        return 0.5;
    }

    @Override
    public int getRightInches() {
        return 25;
    }//17

    @Override
    public double getRightSpeed() {
        return 0.5;
    }

    @Override
    public int getLeftInches(){
        return 0;
    }//15

    @Override
    public double getLeftSpeed() {
        return 0.5;
    }

    @Override
    public int getBackwardInches() {
        return 56 ;
    }

    @Override
    public double getBackwardSpeed() {
        return 0.5;
    }

    @Override
    public int getParkInches() {
        return 20;
    }

    @Override
    public double getParkSpeed() {
        return 1;
    }
}
