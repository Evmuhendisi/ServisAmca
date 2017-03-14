package com.example.suleymansrc.servisamca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SoforOgrenciEkle extends AppCompatActivity {
        Button ileri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofor_ogrenci_ekle);
        ileri=(Button) findViewById(R.id.btnSoforOgrenciEkleileri);
        ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SoforOgrenciEkle.this,SoforOgrenciEkle2.class);
                startActivity(intent);
            }
        });
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
            Intent intent=new Intent(SoforOgrenciEkle.this,SifreDegistir.class);
            startActivity(intent);
        }else if(id==R.id.hakkında){
            Intent intent=new Intent(SoforOgrenciEkle.this,Hakkimizda.class);
            startActivity(intent);
        }else if(id==R.id.anasayfa){
            Intent intent=new Intent(SoforOgrenciEkle.this,SoforAnasayfa.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
