package lk.electfast.e_pola;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MADHAWA on 1/15/2017.
 */

public class geo_list_Adapter extends AsyncTask<Void, Void, Boolean> {
   private  String mEmail;
    private  String mPassword;

    private static final int REQUEST_READ_CONTACTS = 0;
    public String JsonResponse = null;
    private  String state,msg;

    ArrayList<HashMap<String, String>> LocationList;

    geo_list_Adapter() {
        /*mEmail = email;
        mPassword = password;*/
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.


        /*JSONObject post_dict = new JSONObject();

        try {
            post_dict.put("username" ,mEmail );
            post_dict.put("password", mPassword);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        String JsonDATA =String.valueOf( post_dict);

        Log.i("TAG_jDATA", JsonDATA);


        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://192.168.178.165/DsiSgcs/serviselist.json");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            // is output buffer writter
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
//set headers and method
          *//*  Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
            writer.write(JsonDATA);*//*
// json data
           // writer.close();
            InputStream inputStream = urlConnection.getInputStream();
//input stream
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return false;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = reader.readLine()) != null)
                buffer.append(inputLine + "\n");
            if (buffer.length() == 0) {
                // Stream was empty. No point in parsing.
                return false;
            }
            JsonResponse = buffer.toString();
//response data
            Log.i("TAG", JsonResponse);

            return true;



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("TAG", "Error closing stream", e);
                }
            }
        }*/


       HttpHandler sh = new HttpHandler();

        // Making a request to url and getting response
         JsonResponse = sh.makeServiceCall("http://192.168.178.165/DsiSgcs/serviselist.json");

        Log.i("TAG Responce", JsonResponse);


        return true;


    }

    @Override
    protected void onPostExecute(final Boolean success) {



        if (JsonResponse != null) {
            try {

                JSONObject jsonObj = new JSONObject(JsonResponse);

                state =jsonObj.getString("success");
                msg =jsonObj.getString("msg");

                Log.i("TAG_State", state);

            } catch (JSONException e) {

                Log.e("TAG", "Json parsing error: " + e.getMessage());
            }


        }
    }

    @Override
    protected void onCancelled() {

    }



}

