package org.firstinspires.ftc.teamcode.Routes;

import org.firstinspires.ftc.teamcode.Route;
//45 speed
public class RouteC implements Route {

    @Override
    public int getForwardInches() {
        return 105;
    }

    @Override
    public int getCircumInches() {
        return 36;
    }

    @Override
    public int getRightInches() {
        return 20;
    }//25

    @Override
    public int getLeftInches(){
        return 15;
    }//20

    @Override
    public int getBackwardInches() {
        return 56 ;
    }

    @Override
    public int getParkInches() {
        return 10;
    }
}
