package org.osii.nwsapp.ui.location;

import org.osii.nwsapp.data.DataManager;
import org.osii.nwsapp.ui.base.BasePresenter;

import javax.inject.Inject;


public class LocationPresenter extends BasePresenter {

    private final DataManager mDataManager;

    @Inject
    public LocationPresenter(DataManager dataManager) { mDataManager = dataManager; }

    public void setup(){

    }
}
