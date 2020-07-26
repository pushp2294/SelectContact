package com.example.selectcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityNew extends AppCompatActivity {
    EditText et_msg;
    ProgressDialog progressDialog;
    TextView tv_balance;
    TextView tv_charactercount;
    TextView tv_noOf160s;
    int noOfCharacters;
    int noOf160s;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait..");
        TextView tv_selectcontact=findViewById(R.id.tv_selectcontact);
        tv_charactercount=findViewById(R.id.tv_charactercount);
        tv_noOf160s=findViewById(R.id.tv_noOf160s);
        Button bt_checkbalance=findViewById(R.id.bt_checkbalance);
        tv_balance=findViewById(R.id.tv_balance);
        et_msg=findViewById(R.id.et_msg);
        Button bt_Showloaction=findViewById(R.id.bt_Showloaction);
        context=this;
        et_msg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                noOfCharacters=s.length()%160;
                noOf160s=s.length()/160;
                tv_charactercount.setText(String.valueOf(noOfCharacters));
                tv_noOf160s.setText(String.valueOf(noOf160s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bt_Showloaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivityNew.this,MapActivity.class);
                startActivity(intent1);
            }
        });
        tv_selectcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivityNew.this,SelectContactActivityNew.class);
                Constants.noOfCharacters=noOfCharacters;
                Constants.noOf160s=noOf160s;
                String edittext="";
                if(et_msg.getText() !=null)
                { edittext=et_msg.getText().toString();}
                intent.putExtra("msg",edittext);
                startActivity(intent);
            }
        });
        bt_checkbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNoOfMessageLeft();
            }
        });
        getNoOfMessageLeft();
    }

    public void getNoOfMessageLeft()
    {
        //   ApiClient.BASE_URL="http://psms.hakimisolution.com";
        progressDialog.show();
        final Call<String> call = ApiClient.getInstance(MainActivityNew.this).getApi().getNoOfMessage("7093A92QtOEpQ2X85c3585c6","6");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressDialog.dismiss();
                if(response.isSuccessful())
                {
                    if(response.body() !=null)
                    {
                        tv_balance.setText("Total Balance Left :" + response.body());
                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}