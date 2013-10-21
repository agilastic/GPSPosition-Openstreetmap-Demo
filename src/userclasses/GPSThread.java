/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import static java.lang.Thread.sleep;
import generated.StateMachineBase;
import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.codename1.ui.util.Resources;
import com.codename1.maps.MapComponent;
import com.codename1.location.LocationManager;
import com.codename1.location.*;
import com.codename1.location.Location;
import com.codename1.maps.Coord;
import com.codename1.ui.Image;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.maps.layers.PointLayer;
import java.io.IOException;
import java.lang.Throwable;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.*;


import generated.StateMachineBase;

import com.codename1.io.Preferences;
import com.codename1.io.Storage;
import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.codename1.ui.util.Resources;
import com.codename1.components.WebBrowser;
import java.util.Hashtable;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.*;
import com.codename1.ui.animations.*;
import java.util.Vector;
import java.util.Hashtable;
import java.io.*;

/**
 *
 * @author alex
 */
public class GPSThread extends Thread {

    public void run() {
        Location loc = null;
        for (int i = 0; i < 5; i++) {
            try {
                sleep(0);
                LocationManager l = LocationManager.getLocationManager();
                try {
                    loc = l.getCurrentLocation();
                    sleep(0);
                } catch (IOException ex) {
                    System.out.println("ERROR");
                }

                l.setLocationListener(new LocationListener() {
                    public void locationUpdated(Location loc) {                        
                        if (Math.abs(loc.getLongitude())  > 0) {
                            Storage.getInstance().writeObject("Altitude", loc.getAltitude());
                            Storage.getInstance().writeObject("Latitude", loc.getLatitude());
                            Storage.getInstance().writeObject("Longitude", loc.getLongitude());

                            System.out.println(Storage.getInstance().readObject("Altitude").toString());
                            System.out.println(Storage.getInstance().readObject("Latitude").toString());
                            System.out.println(Storage.getInstance().readObject("Longitude").toString());
                            System.out.println("UPDATE ");
                        }


                    }

                    public void providerStateChanged(int newState) {
                    }
                });
            } catch (InterruptedException e) {
            }
        }
    }
}
