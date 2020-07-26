package com.example.selectcontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectContactActivityNew extends AppCompatActivity implements AdapterContactList.GetContectListInterface {
    AdapterContactList adapterContactList;
    public static int allSelected = -1;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    List<Contact> phoneBookcontacts = new ArrayList();
    RecyclerView rv_contactlist;
    LinearLayoutManager linearLayoutManager;
    public static final int REQUEST_PERMISSION = 100;
    TextView tv_done, tv_select;
    public static boolean searchQuery = false;
    private String[] permissions = {
            Manifest.permission.READ_CONTACTS,
    };
    String editMsg = "";
    int previousQuerySize=0;
    int newQuerySize=0;
    ProgressDialog  progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contact);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Contacts....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        tv_done = findViewById(R.id.bt_done);
        tv_select = findViewById(R.id.bt_select);
        editMsg = getIntent().getStringExtra("msg");
        SearchView searchView = findViewById(R.id.searchview);
        sharedPreferences = getSharedPreferences("ContactApp", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        rv_contactlist = findViewById(R.id.rv_contactlist);
//        List<String> contactList = new ArrayList<>();
//        contactList.add("Rajni");
//        contactList.add("Pushp");
//        contactList.add("Shatru");
//        contactList.add("Adarsh");
        linearLayoutManager = new LinearLayoutManager(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterContactList.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                previousQuerySize=newQuerySize;
                newQuerySize=newText.length();
              //  if(newQuerySize>=previousQuerySize)
                allSelected=-1;
                //  phoneBookcontacts.clear();
                adapterContactList.getFilter().filter(newText);
                tv_select.setText("Select all");
                return false;
            }
        });
        tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_select.getText().toString().equalsIgnoreCase("Select all")) {
                    tv_select.setText("UnSelect all");
                    allSelected = 1;
                    final int filteredSize = adapterContactList.filteristModelCityLocality.size();
                    final int phonebookContactSize = phoneBookcontacts.size();

                    // Checking First the Duplicate numbers and then adding.
                    for (int i = 0; i < filteredSize; i++) {
                        if (phonebookContactSize != 0) {
                            for (int j = 0; j < phonebookContactSize; j++) {
                                if (!(phoneBookcontacts.get(j).number.equalsIgnoreCase(adapterContactList.filteristModelCityLocality.get(i).number))) {
                                    if (j == (phonebookContactSize - 1)) {
                                        adapterContactList.filteristModelCityLocality.get(i).setChecked(true);
                                        phoneBookcontacts.add(adapterContactList.filteristModelCityLocality.get(i));
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                        } else {
                            for(int k = 0; k < filteredSize; k++)
                            {
                                adapterContactList.filteristModelCityLocality.get(k).setChecked(true);
                            }
                            phoneBookcontacts.addAll(adapterContactList.filteristModelCityLocality);
                            break;
                        }
                    }
                    if (phoneBookcontacts.size() != 0)
                        tv_done.setVisibility(View.VISIBLE);
                    else
                        tv_done.setVisibility(View.GONE);
                    adapterContactList.notifyDataSetChanged();
                } else if (tv_select.getText().toString().equalsIgnoreCase("UnSelect all")) {
                    tv_select.setText("Select all");
                    allSelected = 0;
                    for(int k = 0; k < adapterContactList.filteristModelCityLocality.size(); k++)
                    {
                        adapterContactList.filteristModelCityLocality.get(k).setChecked(false);
                    }
                    phoneBookcontacts.removeAll(adapterContactList.filteristModelCityLocality);
                    //                  phoneBookcontacts.clear();
                    if (phoneBookcontacts.size() != 0)
                        tv_done.setVisibility(View.VISIBLE);
                    else
                        tv_done.setVisibility(View.GONE);
//                    phoneBookcontacts=adapterContactList.filteristModelCityLocality;
                    adapterContactList.notifyDataSetChanged();
                }
            }
        });
        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("xjsncs", String.valueOf(phoneBookcontacts.size()));
                allSelected = -1;
                if (phoneBookcontacts.size() != 0) {
                    Intent intent = new Intent(SelectContactActivityNew.this, SummaryActivity.class);
                    intent.putExtra("selectedContactList", (Serializable) phoneBookcontacts);
                    intent.putExtra("msg", editMsg);
                    startActivity(intent);
                } else {
                    Toast.makeText(SelectContactActivityNew.this, "Please select contact", Toast.LENGTH_LONG).show();

                }

            }
        });
        requestAllPermissions();
    }

    @SuppressLint("StaticFieldLeak")
    private void loadContacts() {
        new AsyncTask<Void, Integer, List<Contact>>() {
            List<Contact> contacts;
            ContentResolver cr;
            Cursor cur;
            //  List<HashMap<String, String>> list;

            @Override
            protected void onPreExecute() {
                contacts = new ArrayList<>();
                cr = getContentResolver();
                cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                        null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");
                //  list = new ArrayList<>();
            }

            @Override
            protected List<Contact> doInBackground(Void... voids) {
                if ((cur != null ? cur.getCount() : 0) > 0) {
                    while (cur.moveToNext()) {
                        String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String number = "";

                        if (cur.getInt(cur.getColumnIndex(
                                ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                            Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                            while (pCur.moveToNext()) {
                                number = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            }
                            pCur.close();
                            contacts.add(new Contact(id, name, number, false));
                        }
                    }
                }
                if (cur != null) {
                    cur.close();
                }
                return contacts;
            }

            @Override
            protected void onPostExecute(List<Contact> contacts) {
                List<Contact> phoneBookcontacts = new ArrayList<>();
                phoneBookcontacts.addAll(contacts);
//                progressbar.setVisibility(View.GONE);
                progressDialog.dismiss();
                if (phoneBookcontacts != null) {
                    adapterContactList = new AdapterContactList(phoneBookcontacts, SelectContactActivityNew.this);
                    rv_contactlist.setLayoutManager(linearLayoutManager);
                    rv_contactlist.setAdapter(adapterContactList);
                } else {
                    Toast.makeText(SelectContactActivityNew.this, "Contact is empty.", Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

    @Override
    public void getContectList(Contact addNewContact, boolean visibility) {
        if (visibility) {
            phoneBookcontacts.add(addNewContact);
        } else {
            tv_select.setText("Select all");
            phoneBookcontacts.remove(addNewContact);
        }
        if (phoneBookcontacts.size() != 0) {
            tv_done.setVisibility(View.VISIBLE);
        } else {
            tv_done.setVisibility(View.GONE);
        }
    }

    public void requestAllPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(SelectContactActivityNew.this, permissions, REQUEST_PERMISSION);
            } else {
                loadContacts();
                //callApis();
            }
        } else {
            loadContacts();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case REQUEST_PERMISSION: {
                // if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the
                if ((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)) {
                    loadContacts();
                    return;
                } else {
                }
                return;
            }

        }
    }
}
