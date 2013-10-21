package com.agilastic.GPSPosition;


import com.codename1.ui.Display;
import com.codename1.ui.Form;
import userclasses.GPSThread;
import userclasses.StateMachine;

public class GPSPosition {
   
    private Form current;

    public void init(Object context) {
    }

    public void start() {
        GPSThread gpsthread;

        if(current != null){
            current.show();
            return;
        }
        new StateMachine("/theme");   
        
        
        
        gpsthread = new GPSThread();
        gpsthread.run();

    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }
}
