package ua.grupo7.pi.prettycloud.communication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.grupo7.pi.prettycloud.SharedValues;

public class RestApi extends AsyncTask<Void, Void, Void> {

    private static String apiUrl;
    private int port;
    private SharedValues sharedValues;
    public Retrofit retrofit = null;

    public RestApi(String apiUrl,int port){
        this.apiUrl = apiUrl;
    }

    public PrettyCloudWebService getWebservice() {
        PrettyCloudWebService webService;
        if (retrofit == null) {


            retrofit = new Retrofit.Builder()
                    .baseUrl(apiUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        webService = retrofit.create(PrettyCloudWebService.class);
        return webService;

    }

    private void buildUrl() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.vogella.com/index.html")
                .build();

        //add parameters to request
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.github.help").newBuilder();
        urlBuilder.addQueryParameter("v", "1.0");
        urlBuilder.addQueryParameter("user", "vogella");
        String url = urlBuilder.build().toString();

        request = new Request.Builder()
                .url(url)
                .build();

        //request with api token
        request = new Request.Builder()
                .header("Authorization", sharedValues.API_TOKEN)
                .url("https://api.github.com/users/vogella")
                .build();
    }


    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        try {
            Log.d("API_RESPONSE",response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        };*/
        return null;
    }
}
