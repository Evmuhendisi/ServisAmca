package com.example.suleymansrc.servisamca;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SoforGuzergahEkle extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Button kaydet;
    private Spinner spinServisSofor, spinOkul;
    private ArrayList<String> arrayListServisSofor, arrayListOkul;
    private TextView etOkul, etSofor;

    private JSONArray result, resultOkul;
    private EditText servis_soforu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofor_guzergah_ekle);

        servis_soforu = (EditText) findViewById(R.id.editTextServisSoforu);

        etOkul = (TextView) findViewById(R.id.labelOkul);
        etSofor = (TextView) findViewById(R.id.labelServisSoforu);
        spinServisSofor = (Spinner) findViewById(R.id.spinnerServisSoforu);
        arrayListServisSofor = new ArrayList<String>();
        arrayListOkul = new ArrayList<String>();
        spinOkul = (Spinner) findViewById(R.id.spinnerOkul);

        spinOkul.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                etOkul.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                etOkul.setText("");
            }
        });

        spinServisSofor.setOnItemSelectedListener(SoforGuzergahEkle.this);



        kaydet = (Button) findViewById(R.id.btnSoforGuzergahEkle);
        kaydet.setOnClickListener(this);
        getDataServisPlakasi();
        getDataOkulListesi();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cikis) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.sifremidegitir) {
            Intent intent = new Intent(SoforGuzergahEkle.this, SifreDegistir.class);
            startActivity(intent);
        } else if (id == R.id.hakkında) {
            Intent intent = new Intent(SoforGuzergahEkle.this, Hakkimizda.class);
            startActivity(intent);
        } else if (id == R.id.anasayfa) {
            Intent intent = new Intent(SoforGuzergahEkle.this, SoforAnasayfa.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //Okul listesi Spinner ekleyen kod
    //start
    public void getDataOkulListesi() {
        StringRequest stringRequest = new StringRequest(Config.DATA_URL_OKUL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response);
                    resultOkul = jsonObject.getJSONArray(Config.JSON_ARRAY_OKUL_LISTESI);
                    getArrayOkulListesi(resultOkul);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void getArrayOkulListesi(JSONArray resultOkul) {
        for (int i = 0; i < resultOkul.length(); i++) {
            try {
                JSONObject jsonObject = resultOkul.getJSONObject(i);
                arrayListOkul.add(jsonObject.getString(Config.TAG_OKUL_LISTESI));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        spinOkul.setAdapter(new ArrayAdapter<String>(SoforGuzergahEkle.this, android.R.layout.simple_spinner_dropdown_item, arrayListOkul));
    }


    //finish


    //Servis plakalarını Spinner ekleyen kod
    // start
    private void getDataServisPlakasi() {
        StringRequest stringRequest = new StringRequest(Config.DATA_URL_SOFOR_LISTESI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject j = null;
                try {
                    j = new JSONObject(response);
                    result = j.getJSONArray(Config.JSON_ARRAY);
                    getArrayServisPlakasi(result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getArrayServisPlakasi(JSONArray j) {
        for (int i = 0; i < j.length(); i++) {
            try {
                JSONObject jsonObject = j.getJSONObject(i);
                arrayListServisSofor.add(jsonObject.getString(Config.TAG_SERVIS_SOFOR));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        spinServisSofor.setAdapter(new ArrayAdapter<String>(SoforGuzergahEkle.this, android.R.layout.simple_spinner_dropdown_item, arrayListServisSofor));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        servis_soforu.setText(getServisPlakasi(i));
        etSofor.setText(getSofor(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        servis_soforu.setText("");
    }

    private String getSofor(int position) {
        String sfr = "";
        try {
            JSONObject jsonObject = result.getJSONObject(position);
            sfr = jsonObject.getString(Config.TAG_SERVIS_SOFOR);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sfr;
    }

    public String getServisPlakasi(int position){
        String sfr="";
        try {
            JSONObject jsonObject = result.getJSONObject(position);
            sfr = jsonObject.getString(Config.TAG_SERVIS_PLAKASI);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sfr;
    }

    // finish




    //finish
    @Override
    public void onClick(View view) {

    }
}
