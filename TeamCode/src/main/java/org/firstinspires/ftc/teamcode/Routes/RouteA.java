package org.firstinspires.ftc.teamcode.Routes;

import org.firstinspires.ftc.teamcode.Route;

public class RouteA implements Route {

    @Override
    public int getForwardInches() {
        return 53;
    }

    @Override
    public int getCircumInches() {
        return 0;
    }

    @Override
    public int getRightInches() {
        return 25;
    }

    @Override
    public int getLeftInches(){
        return 20;
    }

    @Override
    public int getBackwardInches() {
        return 5;
    }

    @Override
    public int getParkInches() {
        return 14;
    }
}
