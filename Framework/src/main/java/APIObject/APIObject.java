package APIObject;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class APIObject {

    private OkHttpClient client;
    private Request request;
    private Response response;
    private String path;


    public Request getRequest() {
        return request;
    }

    public void setRequest() {
        this.request = new Request.Builder()
                .url(this.path)
                .build();
    }

    public Response getResponse() {
        return response;
    }

    public String setResponseCode() throws IOException {
        this.response = this.client.newCall(this.request).execute();
        return String.valueOf(response.code()) ;
    }

    public ResponseBody setResponseBody() throws IOException {
        return this.client.newCall(this.request).execute().body();
    }

    public void setRequestAuth(String auth,String token){
        this.request = new Request.Builder()
                .header("Authorization", "your token")
                .url(this.path)
                .build();
    }

    public void postRequest(String auth,String token,String post,String body){
        final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
        this.request = new Request.Builder()
                        .addHeader(auth,token)
                        .url(this.path)
                        .post(RequestBody.create(MEDIA_TYPE_MARKDOWN,body))
                        .build();
    }

    public void ConfigureTimeouts(int connectTime, int writetimeout, int readtimeout) throws Exception {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(connectTime, TimeUnit.SECONDS)
                .writeTimeout(writetimeout, TimeUnit.SECONDS)
                .readTimeout(readtimeout, TimeUnit.SECONDS)
                .build();
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.client = new OkHttpClient();
        this.path = path;
    }








}
