package org.osii.nwsapp.ui.location;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

import android.text.Spanned;
import android.content.res.Resources;
import android.text.Html;

import org.osii.nwsapp.injection.component.ActivityComponent;
import org.osii.nwsapp.ui.base.BaseActivity;

import android.text.TextUtils;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import org.osii.nwsapp.R;

import javax.inject.Inject;

public class LocationActivity extends BaseActivity implements LocationMvpView, PlaceSelectionListener {

    private TextView mPlaceDetailsText;
    private TextView mPlaceAttribution;

    @Inject  LocationPresenter mLocationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.getActivityComponent().inject(this);
        this.setContentView(R.layout.activity_location);
        //Retrieve the PlaceAutoCompleteFragment
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
            getFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        //With autocompleteFragment set, register listener to receive callback when a place
        //has been selected or an error has occurred
        autocompleteFragment.setOnPlaceSelectedListener(this);

        //Temporary text views that display details about the selected place.
        mPlaceDetailsText = (TextView) findViewById(R.id.place_details);
        mPlaceAttribution = (TextView) findViewById(R.id.place_attribution);
        mLocationPresenter.attachView(this);
        mLocationPresenter.setup();
    }

    @Override
    public void onPlaceSelected(Place place){
        //Eventually will want to use SQLHelper to save PlaceId and et Latitude and Longitude
        //within the app. For now, just display Place details
        mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(), place.getId(),
                place.getLatLng()));
        CharSequence attributions = place.getAttributions();
        if (!TextUtils.isEmpty(attributions)) {
            mPlaceAttribution.setText(Html.fromHtml(attributions.toString()));
        } else {
            mPlaceAttribution.setText("");
        }
    }

    @Override
    public void onError(Status status){
        Toast.makeText(this, "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLocations(){

    }


    //Temporary helper for formatting information about the place to display
    private static Spanned formatPlaceDetails (Resources res, CharSequence name, String id, LatLng latLong){
        return Html.fromHtml(res.getString(R.string.place_details, name, id, latLong));
    }

}
