package lk.electfast.e_pola;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Register extends AppCompatActivity {

    private EditText Name_txt;
    private EditText add_txt;
    private EditText contact_txt;
    private EditText email_txt;
    private EditText dob_txt;
    private RadioGroup sexGroup;
    private RadioButton female_rdo;
    private RadioButton male_rdo;

    private Button save_btn;

    private String Name,Address,Contact,Emali,Dob,sex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name_txt = (EditText) findViewById(R.id.name_txt);
        add_txt = (EditText) findViewById(R.id.add_txt);
        contact_txt = (EditText) findViewById(R.id.contact_txt);
        email_txt = (EditText) findViewById(R.id.email_txt);
        dob_txt = (EditText) findViewById(R.id.dob_txt);



        save_btn = (Button) findViewById(R.id.save);







    }

    private void ValidateSet(){
        Name_txt.setError(null);
        add_txt.setError(null);
        contact_txt.setError(null);
        email_txt.setError(null);
        dob_txt.setError(null);







    }
}
