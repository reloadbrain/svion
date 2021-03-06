package com.svion.client;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;



import java.io.IOException;

/**
 * Created by victor on 14.03.14.
 */
public class GameInitializer extends AsyncTask {
    public GameInitializer(MyActivity myActivity) {
        this.myActivity = myActivity;
    }
    private MyActivity myActivity;
    private boolean isAuthorized() throws IOException {
        DefaultHttpClient httpclient = HttpClientHandler.getInstance().getHttpClient();
        HttpParams params = httpclient.getParams();
        params.setParameter("http.protocol.handle-redirects",false);
        httpclient.setParams(params);
        HttpGet authorizeRequest = new HttpGet(myActivity.getResources().getString(R.string.server));
        HttpResponse response = httpclient.execute(authorizeRequest);
        response.getEntity().consumeContent();
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return true;
        }
        if ((response.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) || ((response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED) )){
            return false;
        }
        else {
            throw new IOException("server responded with code "+response.getStatusLine().getStatusCode());
        }
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            if (isAuthorized()) {
                myActivity.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        Toast.makeText(myActivity, "successfully logged in", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent startGameActivityIntent = new Intent(myActivity, SearchGame.class);
                myActivity.startActivity(startGameActivityIntent);
            }
            else {
                myActivity.askUserAccountAndAuthorize();//starts new activity, then after this is closed callback uses chosen activity to start new thread for authorization
            }
        } catch (IOException e) {
            final String message = e.getMessage();
            myActivity.runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    Toast.makeText(myActivity, message, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return null;
    }
}
