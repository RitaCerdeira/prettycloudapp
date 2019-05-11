package ua.grupo7.pi.prettycloud.communication;

import org.json.JSONObject;

import java.net.URL;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import ua.grupo7.pi.prettycloud.SharedValues;
public class RestApi {

    private URL apiUrl;
    private int port;
    private SharedValues sharedValues;

    public RestApi(URL apiUrl,int port){
        this.apiUrl = apiUrl;
        this.port = port;
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

    public void parseRequest(JSONObject jsonObject){}

}
