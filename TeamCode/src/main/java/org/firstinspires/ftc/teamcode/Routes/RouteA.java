package org.firstinspires.ftc.teamcode.Routes;

import org.firstinspires.ftc.teamcode.Route;

public class RouteA implements Route {

    @Override
    public int getForwardInches() {
        return 53;
    }

    @Override
    public double getForwardSpeed() {
        return 0;
    }

    @Override
    public int getCircumInches() {
        return 0;
    }

    @Override
    public double getCircumSpeed() {
        return 0;
    }

    @Override
    public int getRightInches() {
        return 25;
    }

    @Override
    public double getRightSpeed() {
        return 0;
    }

    @Override
    public int getLeftInches(){
        return 20;
    }

    @Override
    public double getLeftSpeed() {
        return 0;
    }

    @Override
    public int getBackwardInches() {
        return 5;
    }

    @Override
    public double getBackwardSpeed() {
        return 0;
    }

    @Override
    public int getParkInches() {
        return 14;
    }

    @Override
    public double getParkSpeed() {
        return 0;
    }
}
