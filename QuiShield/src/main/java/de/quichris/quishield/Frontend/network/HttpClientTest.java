package de.quichris.quishield.Frontend.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Map;

public class HttpClientTest {


    ObjectMapper objectMapper = new ObjectMapper();

    OkHttpClient client = new OkHttpClient();
    String BASEURL = "https://mrqui.me/api/";


    public Response post(String endPoint, Map<String,String> requestBody) throws Exception {
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), objectMapper.writeValueAsString(requestBody));


        Request request = new Request.Builder()
                .post(body)
                .url(BASEURL+ endPoint)
                .build();
        ;
        return client.newCall(request).execute();
    }





}
