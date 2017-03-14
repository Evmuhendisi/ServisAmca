package com.example.suleymansrc.servisamca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button ogrenciDurumu, bilgilerim, servisnerede, aidatbilgileri, servismesaj, ogrencimgelmeyecek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ogrenciDurumu = (Button) findViewById(R.id.btnVeliOgrenciDurumu);
        bilgilerim = (Button) findViewById(R.id.btnVeliBilgileri);
        servisnerede = (Button) findViewById(R.id.btnServisNerede);
        aidatbilgileri = (Button) findViewById(R.id.btnVeliAidat);
        servismesaj = (Button) findViewById(R.id.btnVeliMesaj);
        ogrencimgelmeyecek = (Button) findViewById(R.id.btnVeliOgrencimGelmeyecek);


        //Sayfa geçişleri tıklamalar başlangıcı
        ogrencimgelmeyecek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VeliOgrencimGelmeyecek.class);
                startActivity(intent);
            }
        });
        ogrenciDurumu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VeliOgrenciDurumu.class);
                startActivity(intent);
            }
        });
        bilgilerim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VeliBilgileriGuncelleme.class);
                startActivity(intent);
            }
        });
        servisnerede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ServisBilgisi.class);
                startActivity(intent);
            }
        });
        aidatbilgileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VeliAidatListesi.class);
                startActivity(intent);
            }
        });
        servismesaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VeliSmsGonder.class);
                startActivity(intent);
            }
        });
        //Bitişi.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenuanasayfa, menu);
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
            Intent intent=new Intent(MainActivity.this,SifreDegistir.class);
            startActivity(intent);
        }else if(id==R.id.hakkında){
            Intent intent=new Intent(MainActivity.this,Hakkimizda.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }
}
