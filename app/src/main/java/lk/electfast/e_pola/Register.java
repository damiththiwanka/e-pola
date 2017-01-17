package lk.electfast.e_pola;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Register extends AppCompatActivity {

    private EditText Name_txt;

    private EditText contact_txt;
    private EditText email_txt;
    private EditText pass_text;
    private EditText pass_text2;

private String state,msg;
    private Button save_btn;

    private String Name,Contact,email,pass,pass2;


    View focusView = null;

    private ProgressDialog pDialog;
    private String createtext="192.168.137.25";
    private String JsonResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name_txt = (EditText) findViewById(R.id.name_txt);

        contact_txt = (EditText) findViewById(R.id.contact_txt);
        email_txt = (EditText) findViewById(R.id.email_txt);
        pass_text = (EditText) findViewById(R.id.pass_text);
        pass_text2 = (EditText) findViewById(R.id.pass_text2);



        save_btn = (Button) findViewById(R.id.save);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateSet();
            }
        });

    }

    private Boolean ValidateSet(){
        Name_txt.setError(null);

        contact_txt.setError(null);
        email_txt.setError(null);
        pass_text.setError(null);
        pass_text2.setError(null);

        Name=Name_txt.getText().toString();

        Contact=contact_txt.getText().toString();
        email = email_txt.getText().toString();
        pass=pass_text.getText().toString();
        pass2=pass_text2.getText().toString();



        if(TextUtils.isEmpty(Name)){
            Name_txt.setError("Field cannot be empty");
            focusView=Name_txt;
            focusView.requestFocus();
            return false;
            }else

        if(TextUtils.isEmpty(Contact)){
            contact_txt.setError("Field cannot be empty");
            focusView=contact_txt;
            focusView.requestFocus();
            return false;
        }else
        if(TextUtils.isEmpty(email)){
            email_txt.setError("Field cannot be empty");
            focusView=email_txt;
            focusView.requestFocus();
            return false;
        }else
        if(isEmailValid(email)){
            email_txt.setError("Please enter a valid email");
            focusView=email_txt;
            focusView.requestFocus();
            return false;
        }else
        if(TextUtils.isEmpty(pass)){
            pass_text.setError("Field cannot be empty");
            focusView=pass_text;
            focusView.requestFocus();
            return false;
        }else

        if(pass.length()>5){
            pass_text.setError("Password Length must be over 6 Characters ");
            focusView=pass_text;
            focusView.requestFocus();
            return false;
        }else

        if(TextUtils.isEmpty(pass2)){
            pass_text2.setError("Field cannot be empty");
            focusView=pass_text2;
            focusView.requestFocus();
            return false;
        }else
        if(pass==pass2){
            pass_text2.setError("password dosent match ");
            focusView=pass_text2;
            focusView.requestFocus();
            return false;
        }else
        {
            return true;
        }





    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@") && email.contains(".");
    }

    public class savedata extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            JSONObject Jbody = new JSONObject();

            try {
                Jbody.put("FullName",Name);
                Jbody.put("Contact",Contact);
                Jbody.put("email",email);
                Jbody.put("password",pass);

            }catch (Exception e){
                e.printStackTrace();
            }

            String JsonDATA =String.valueOf( Jbody);

            Log.i("TAG_jDATA", JsonDATA);


            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("http://"+createtext+":3000/userRouter/authenticate");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                // is output buffer writter
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");
//set headers and method
                Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                writer.write(JsonDATA);
// json data
                writer.close();
                InputStream inputStream = urlConnection.getInputStream();
//input stream
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String inputLine;
                while ((inputLine = reader.readLine()) != null)
                    buffer.append(inputLine + "\n");
                if (buffer.length() == 0) {
                    // Stream was empty. No point in parsing.
                    return null;
                }
                JsonResponse = buffer.toString();
//response data
                Log.i("TAG", JsonResponse);

                return null;



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
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            if(!JsonResponse.isEmpty()){

                try {

                    JSONObject respond = new JSONObject(JsonResponse);

                    state =respond.getString("success");
                    msg =respond.getString("msg");



                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        }
    }


    private String readFromFile(Context context) {

        try {
            File sdcard = Environment.getDataDirectory();
            File myFile = new File(sdcard,"config.txt");
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow + "\n";
            }
            createtext=aBuffer;
            myReader.close();
            Toast.makeText(getBaseContext(),
                    "Done reading SD 'mysdfile.txt'",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

        return createtext;
    }
}
