package com.cynoteck.petofyOPHR.api;

import retrofit2.Response;

public interface ApiResponse<T>{

    public void onResponse(Response<T> arg0, String key);
    public void onError(Throwable t, String key);
}