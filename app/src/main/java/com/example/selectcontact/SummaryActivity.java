package com.example.selectcontact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SummaryActivity extends AppCompatActivity implements AdapterContactListing.RemoveContactInterface {
    String msg="";
    List<Contact> contactList;
    EditText et_msg;
    String number="";
    TextView textView;
     AlertDialog  alertDialog;
     ProgressDialog progressDialog;
    AdapterContactListing adapterContactListing;
    TextView tv_toolbar;
    int noOfCharacters;
    int noOf160s;
    TextView tv_charactercount;
    TextView tv_noOf160s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        RecyclerView rv_contactlist=findViewById(R.id.rv_contactlist);
        tv_toolbar=findViewById(R.id.tv_toolbar);
        tv_charactercount=findViewById(R.id.tv_charactercount);
        tv_noOf160s=findViewById(R.id.tv_noOf160s);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        contactList= (List<Contact>) getIntent().getSerializableExtra("selectedContactList");
        rv_contactlist.setLayoutManager(linearLayoutManager);
        adapterContactListing=new AdapterContactListing(this,contactList);
        rv_contactlist.setAdapter(adapterContactListing);
        msg=getIntent().getStringExtra("msg");
        et_msg=findViewById(R.id.et_msg);
        if(msg !=null)
        {et_msg.setText(msg);}
        else
        {et_msg.setText("");}
        tv_charactercount.setText(String.valueOf(Constants.noOfCharacters));
        tv_noOf160s.setText(String.valueOf(Constants.noOf160s));
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
        Button bt_click = findViewById(R.id.bt_click);
        bt_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setCancelable(false);
                progressDialog.show();
                callApi();
            }
        });

        // All Progress Dialogs defined here
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending message..");
        AlertDialog.Builder alertbuilder= new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.alert_dialog_box,null,false);
        textView=view.findViewById(R.id.tv_alertmsg);
        Button ok=view.findViewById(R.id.ok);
        alertbuilder.setView(view);
        alertDialog=alertbuilder.create();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });
        getNoOfMessageLeft();

    }
    public void getNoOfMessageLeft()
    {
     //   ApiClient.BASE_URL="http://psms.hakimisolution.com";
        final Call<String> call = ApiClient.getInstance(SummaryActivity.this).getApi().getNoOfMessage("7093A92QtOEpQ2X85c3585c6","6");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressDialog.dismiss();
                if(response.isSuccessful())
                {
//                    JsonObject jsonObject=response.body().toString();
                    if(response.body() !=null)
                    {
                        tv_toolbar.setText("Total Balance Left :" + response.body());
                    }
//                    else
//                    {
//                        tv_toolbar.setText("Total Balance Left :" + "0");
//                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
        public void callApi()
        {
          //  ApiClient.BASE_URL="http://sms53.hakimisolution.com";
            progressDialog.show();
            String contactString="";
            for(int i=0;i<contactList.size();i++)
            {
                if(i < contactList.size()-1) {
                    contactString = contactString + contactList.get(i).number + ",";

                }
                else
                {contactString=contactString+contactList.get(i).number;}
            }
            contactString=contactString.replace(" ","");
            String abc=et_msg.getText().toString();
            final Call<String> call = ApiClient.getInstance(SummaryActivity.this).getApi().sendmsg("7093A92QtOEpQ2X85c3585c6",contactString,abc,"HSKINC","6","0");
            call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
               progressDialog.dismiss();
               if(response.isSuccessful())
               {
                   getNoOfMessageLeft();
                   alertDialog.show();
                   textView.setText("Message sent Successfully");
                //   Toast.makeText(SummaryActivity.this,String.valueOf(response.code()),Toast.LENGTH_LONG).show();
               }
               else
               {
                   progressDialog.dismiss();
                   alertDialog.show();
                   textView.setText("Message Delivery Failed, Please try again.");
               }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressDialog.dismiss();
                alertDialog.show();
                textView.setText("Message Delivery Failed, Please try again.");
            }
        });
    }

    @Override
    public void removeContact(int position) {
        contactList.remove(position);
        adapterContactListing.notifyDataSetChanged();
    }

}
