package com.cynoteck.petofyOPHR.utils;

import com.cynoteck.petofyOPHR.response.updateProfileResponse.UserResponse;

public class VetDetailsSingleton {
    private static VetDetailsSingleton vetDetailsSingleton;
    public static VetDetailsSingleton getInstance() {
        if (vetDetailsSingleton == null)
            vetDetailsSingleton = new VetDetailsSingleton();
        return vetDetailsSingleton;
    }

    public VetDetailsSingleton() {
    }

    public UserResponse userResponse;
}
