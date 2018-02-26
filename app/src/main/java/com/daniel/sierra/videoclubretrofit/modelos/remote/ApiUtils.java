package com.daniel.sierra.videoclubretrofit.modelos.remote;

/**
 * Created by daniel on 23/02/2018.
 */

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://217.217.138.201:3000/";

    public static APIService getAPIService() {

        return RetrofitCliente.getClient(BASE_URL).create(APIService.class);
    }

}
