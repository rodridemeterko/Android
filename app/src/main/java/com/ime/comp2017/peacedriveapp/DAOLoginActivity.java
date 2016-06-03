package com.ime.comp2017.peacedriveapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

import static java.lang.String.valueOf;


public class DAOLoginActivity extends AppCompatActivity implements OnClickListener {

    Button logBut;
    TextView FaillureText;
    EditText userLog;
    EditText passwLog;
    public static final String URL  = "http://172.16.36.171/myproject/ServletLoginApp";//"http://172.16.36.171/myproject/loginApp";
    private static AsyncHttpClient client = new AsyncHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daologin);

        findViewsById();

        logBut.setOnClickListener(DAOLoginActivity.this);
    }

    private void findViewsById(){
        logBut = (Button) findViewById(R.id.login_button);
        FaillureText = (TextView) findViewById(R.id.denied);
        userLog = (EditText) findViewById(R.id.userID);
        passwLog = (EditText) findViewById(R.id.userPassw);
    }


    public void onClick(View view){

        RequestParams param = new RequestParams();

        param.put("user_name", valueOf(userLog.getText().toString()));
        param.put("password", valueOf(passwLog.getText()));

        client.get(URL, param, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                FaillureText.setText("Erro na Conexão");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                if(responseString.equals("")){FaillureText.setText(":" + responseString +":"+ userLog.getText().toString());}
                else if(Integer.parseInt(responseString)==-1){
                    FaillureText.setText("Usuario ou senha incorretos...");
                }else {
                    int id = Integer.parseInt(responseString);
                    Intent i = new  Intent(DAOLoginActivity.this, MapPickActivity.class);
                    i.putExtra("user_ID",id);
                    startActivity(i);
                }
            }
        });

    }



}
