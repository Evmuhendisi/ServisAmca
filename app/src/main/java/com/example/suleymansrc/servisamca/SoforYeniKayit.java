package com.example.suleymansrc.servisamca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SoforYeniKayit extends AppCompatActivity {
        Button ogrenciEkle,soforEkle,hostesEkle,okulEkle,guzergahEkle,servisEkle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofor_yeni_kayit);
        ogrenciEkle = (Button) findViewById(R.id.btnSoforOgrenciEkle);
        soforEkle = (Button) findViewById(R.id.btnSoforEkle);
        hostesEkle = (Button) findViewById(R.id.btnSoforHostesEkle);
        okulEkle = (Button) findViewById(R.id.btnSoforOkulEkle);
        guzergahEkle = (Button) findViewById(R.id.btnSoforGuzergahEkle);
        servisEkle = (Button) findViewById(R.id.btnSoforServisEkle);

        //Sayfa geçişleri tıklamalar başlangıcı
        ogrenciEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoforYeniKayit.this, SoforOgrenciEkle.class);
                startActivity(intent);
            }
        });
        soforEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoforYeniKayit.this, SoforSoforEkle.class);
                startActivity(intent);
            }
        });
        hostesEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoforYeniKayit.this, SoforHostesEkle.class);
                startActivity(intent);
            }
        });
        okulEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoforYeniKayit.this, SoforOkulEkleme.class);
                startActivity(intent);
            }
        });
        guzergahEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoforYeniKayit.this, SoforGuzergahEkle.class);
                startActivity(intent);
            }
        });
        servisEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoforYeniKayit.this, SoforServisEkle.class);
                startActivity(intent);
            }
        });
        //Bitişi.
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
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
            Intent intent=new Intent(SoforYeniKayit.this,SifreDegistir.class);
            startActivity(intent);
        }else if(id==R.id.hakkında){
            Intent intent=new Intent(SoforYeniKayit.this,Hakkimizda.class);
            startActivity(intent);
        }else if(id==R.id.anasayfa){
            Intent intent=new Intent(SoforYeniKayit.this,SoforAnasayfa.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
