/**
 * Your application code goes here
 */
package userclasses;

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
import com.codename1.io.Storage;
import java.io.*;

/**
 *
 * @author Your name here
 */
public class StateMachine extends StateMachineBase {

    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }

    /**
     * this method should be used to initialize variables instead of the
     * constructor/class scope to avoid race conditions
     */
    protected void initVars(Resources res) {
    }

    public static void update_location(MapComponent mc) {
        if (Storage.getInstance().readObject("Longitude").toString().length() > 0) {
            Location loc = new Location();

            loc.setAltitude(Double.parseDouble(Storage.getInstance().readObject("Altitude").toString()));
            loc.setLongitude(Double.parseDouble(Storage.getInstance().readObject("Longitude").toString()));
            loc.setLatitude(Double.parseDouble(Storage.getInstance().readObject("Latitude").toString()));

            Coord lastLocation = new Coord(loc.getLatitude(), loc.getLongitude());
            mc.removeAllLayers();
            Image i = null;
            try {
                i = Image.createImage("map_blue.png");
            } catch (IOException ex) {
                //Dialog.show("Dialog Title", "This is the dialog body, it can contain anything...", "OK", "Cancel");
            }
            PointsLayer pl = new PointsLayer();
            pl.setPointIcon(i);
            PointLayer p = new PointLayer(lastLocation, "I am here", i);
            p.setDisplayName(true);
            pl.addPoint(p);
            mc.addLayer(pl);
            mc.zoomToLayers();
        }else{
            Dialog.show("BAD GPS Signal", "Sorry currently too bad GPS signal. Please wait...", "OK", "Cancel");
        }
        
    }

    @Override
    protected void beforeMain(Form f) {
    }

    @Override
    protected void onMain_ButtonAction(Component c, ActionEvent event) {
        update_location(findMapComponent(c));
        c.getComponentForm().revalidate();
    }

   
}
