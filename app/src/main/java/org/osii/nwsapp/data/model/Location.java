package org.osii.nwsapp.data.model;
import com.google.android.gms.maps.model.LatLng;
import android.content.res.Resources;

public class Location {
    public Resources PlaceResource;
    public LatLng LatitudeLongitude;
    public CharSequence PlaceName;
    public String PlaceID;


    public Location(){}

    public Location(String id){PlaceID = id;}
    public Location(Resources res, String id, CharSequence name, LatLng latLong)
    {
        this.PlaceResource = res;
        this.PlaceName = name;
        this.PlaceID = id;
        this.LatitudeLongitude = latLong;
    }


}
