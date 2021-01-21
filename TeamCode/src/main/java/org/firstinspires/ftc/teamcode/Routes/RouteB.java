package org.firstinspires.ftc.teamcode.Routes;

import org.firstinspires.ftc.teamcode.Route;

public class RouteB implements Route {

    @Override
    public int getForwardInches() {
        return 70;
    }

    @Override
    public int getCircumInches() {
        return 24;
    }

    @Override
    public int getRightInches() {
        return 0;
    }

    @Override
    public int getLeftInches(){
        return 0;
    }

    @Override
    public int getBackwardInches() {
        return 0;
    }

    @Override
    public int getParkInches() {
        return 0;
    }
}
