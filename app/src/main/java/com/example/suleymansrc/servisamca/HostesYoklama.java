package com.example.suleymansrc.servisamca;

import android.app.AlertDialog;
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

public class HostesYoklama extends AppCompatActivity {

    Button hostesYoklamaKaydet;
    private String[] ogrenciListesi={"Süleyman SÜRÜCÜ","Esma KARAMAN","Merve YILMAZ"};
    private Spinner spinnerHostesYoklamaListesi;
    private ArrayAdapter<String> arrayAdapterHostesYoklamaListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostes_yoklama);
        spinnerHostesYoklamaListesi=(Spinner) findViewById(R.id.spinnerHostesogrenciListesi);
        arrayAdapterHostesYoklamaListesi=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ogrenciListesi);
        spinnerHostesYoklamaListesi.setAdapter(arrayAdapterHostesYoklamaListesi);

        hostesYoklamaKaydet=(Button) findViewById(R.id.btnHostesYoklamaKaydet);
        hostesYoklamaKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HostesYoklama.this, "Verileriniz kaydediliyor...", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.cikis){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if(id==R.id.sifremidegitir){
            Intent intent=new Intent(HostesYoklama.this,SifreDegistir.class);
            startActivity(intent);
        }else if(id==R.id.hakkında){
            Intent intent=new Intent(HostesYoklama.this,Hakkimizda.class);
            startActivity(intent);
        }
        else if(id==R.id.anasayfa){
            Intent intent=new Intent(HostesYoklama.this,HostesAnasayfa.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }
}
