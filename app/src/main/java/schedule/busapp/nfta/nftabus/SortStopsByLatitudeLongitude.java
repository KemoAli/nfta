package schedule.busapp.nfta.nftabus;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;

import java.util.Comparator;

public class SortStopsByLatitudeLongitude implements Comparator<Stops>{
    double _latitude;
    double _longitude;
   // GPSDriver gpsDriver;
    Location location;

    public SortStopsByLatitudeLongitude(double _latitude, double _longitude,GPSDriver gpsDriver) {
        this._latitude = _latitude;
        this._longitude = _longitude;
        this.location=gpsDriver.getLocation();
    }

    @Override
    public int compare(Stops location1, Stops location2) {

        Location new_location=new Location(location);
        new_location.setLatitude(location1.getStop_lat());
        new_location.setLongitude(location1.getStop_lon());

        Location new_location2 = new Location(location);
        new_location2.setLatitude(location2.getStop_lat());
        new_location2.setLongitude(location2.getStop_lon());

        double locat1= location.distanceTo(new_location);
        double locat2= location.distanceTo(new_location2);

        if (locat1> locat2) {
            return 1;
        }
        if (locat1 < locat2) {
            return -1;
        }
        return 0;

    }



}
