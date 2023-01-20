package com.cynoteck.petofyOPHR.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ApiService<T> {

  public void get(final ApiResponse apiResponse, Call<T> methodName, final String key) {
    methodName.enqueue(new Callback<T>() {
      @Override
      public void onResponse(Call<T> call, Response<T> response) {

        apiResponse.onResponse(response, key);
      }
      @Override
      public void onFailure(Call<T> call, Throwable t) {
        apiResponse.onError(t, key);
      }
    });

  }
}