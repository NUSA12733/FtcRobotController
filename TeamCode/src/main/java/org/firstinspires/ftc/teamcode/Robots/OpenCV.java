package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robots.SkystoneDeterminationPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

class OpenCV {

    OpenCvInternalCamera phoneCam;
    SkystoneDeterminationPipeline pipeline;
    HardwareMap hm;
    Telemetry t;
    public OpenCV(HardwareMap hm, Telemetry t){
        this.hm = hm;
        this.t = t;
    }

    public void init(){
        int cameraMonitorViewId = hm.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hm.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        pipeline = new SkystoneDeterminationPipeline(t);
        phoneCam.setPipeline(pipeline);

        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
        // out when the RC activity is in portrait. We do our actual image processing assuming
        // landscape orientation, though.
        phoneCam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        phoneCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                phoneCam.startStreaming(320,240, OpenCvCameraRotation.SIDEWAYS_RIGHT);
            }
        });
    }

    public int getThresholdVal(){
        return pipeline.getAnalysis();
    }

    public int getNumberOfRings(){
        String pos = pipeline.getPosition().toString();
        if(pos.equals("FOUR")){
            return 4;
        } else if(pos.equals("ONE")){
            return 1;
        } else if(pos.equals("ZERO")){
            return 0;
        }

        return 288;
    }
}
