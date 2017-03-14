package com.example.suleymansrc.servisamca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SoforAnasayfa extends AppCompatActivity {

    Button yeniKayitOlustur,servisBilgileri,haritaAyarlari,bilgilerim,aidatBilgileri,mesajGonder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofor_anasayfa);
        yeniKayitOlustur = (Button) findViewById(R.id.btnSoforYeniKayitOlustur);
        servisBilgileri = (Button) findViewById(R.id.btnSoforServisBilgileri);
        haritaAyarlari = (Button) findViewById(R.id.btnSoforHaritaBilgileri);
        bilgilerim = (Button) findViewById(R.id.btnSoforBilgilerim);
        aidatBilgileri = (Button) findViewById(R.id.btnSoforAidatBilgileri);
        mesajGonder = (Button) findViewById(R.id.btnSoforMesajGonder);

        //Sayfa geçişleri tıklamalar başlangıcı
        yeniKayitOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoforAnasayfa.this, SoforYeniKayit.class);
                startActivity(intent);
            }
        });
        servisBilgileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoforAnasayfa.this, SoforServisBilgileri.class);
                startActivity(intent);
            }
        });
        haritaAyarlari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoforAnasayfa.this, SoforHarita.class);
                startActivity(intent);
            }
        });
        bilgilerim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoforAnasayfa.this, SoforBilgilerim.class);
                startActivity(intent);
            }
        });
        aidatBilgileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoforAnasayfa.this, SoforAidatBilgileri.class);
                startActivity(intent);
            }
        });
        mesajGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoforAnasayfa.this, SoforMesajGonder.class);
                startActivity(intent);
            }
        });
        //Bitişi.
    }
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
            Intent intent=new Intent(SoforAnasayfa.this,SifreDegistir.class);
            startActivity(intent);
        }else if(id==R.id.hakkında){
            Intent intent=new Intent(SoforAnasayfa.this,Hakkimizda.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
}
