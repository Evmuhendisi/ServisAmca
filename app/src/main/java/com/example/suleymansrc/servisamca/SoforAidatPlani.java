package com.example.suleymansrc.servisamca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class SoforAidatPlani extends AppCompatActivity {
    Button kaydet;
    private String[] taksitSayisi={"1 gün","2 gün","3 gün","4 gün","5 gün","6 gün","7 gün","8 gün","9 gün","10 gün","11 gün","12 gün","13 gün"};
    private Spinner spinnerTaksitSayisi;
    private ArrayAdapter<String> dataAdapterForTaksitSayisi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofor_aidat_plani);
        kaydet = (Button) findViewById(R.id.btnSoforOgrenciKaydet);
        spinnerTaksitSayisi=(Spinner) findViewById(R.id.spinnerSoforPlan);
        dataAdapterForTaksitSayisi=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,taksitSayisi);
        spinnerTaksitSayisi.setAdapter(dataAdapterForTaksitSayisi);
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SoforAidatPlani.this, "Girdiğiniz öğrenci bilgileri kaydediliyor...", Toast.LENGTH_SHORT).show();
            }
        });

        spinnerTaksitSayisi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
            Intent intent = new Intent(SoforAidatPlani.this, SifreDegistir.class);
            startActivity(intent);
        } else if (id == R.id.hakkında) {
            Intent intent = new Intent(SoforAidatPlani.this, Hakkimizda.class);
            startActivity(intent);
        } else if (id == R.id.anasayfa) {
            Intent intent = new Intent(SoforAidatPlani.this, SoforAnasayfa.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
