package org.osii.nwsapp.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.osii.nwsapp.data.remote.ApiService;



public class DataManager {
    private final ApiService mApiService;

    @Inject
    public DataManager(ApiService apiService)
    {
        mApiService = apiService;
    }
}
