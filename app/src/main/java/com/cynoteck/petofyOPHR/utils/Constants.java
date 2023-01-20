package com.cynoteck.petofyOPHR.utils;

public interface Constants {

    // TODO Change it to your web domain
    String WEB_DOMAIN = "zoom.us";

    // TODO change it to your user ID
    String USER_ID = "0ced23e1-68d7-4380-9a24-29ab779bfbae";

    // TODO change it to your token
    String ZOOM_ACCESS_TOKEN = "Your zak from REST API";

    // TODO Change it to your exist meeting ID to start meeting
    String MEETING_ID = "91097947182";

    /**
     * We recommend that, you can generate jwttoken on your own server instead of hardcore in the code.
     * We hardcore it here, just to run the demo.
     *
     * You can generate a jwttoken on the https://jwt.io/
     * with this payload:
     * {
     *     "appKey": "string", // app key
     *     "iat": long, // access token issue timestamp
     *     "exp": long, // access token expire time
     *     "tokenExp": long // token expire time
     * }
     */
    public final static String SDK_JWTTOKEN = "";

}
