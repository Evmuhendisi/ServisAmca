package com.example.suleymansrc.servisamca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HostesAnasayfa extends AppCompatActivity {

    Button yoklamaAl,bilgilerimHostes,smsGonder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostes_anasayfa);
        yoklamaAl=(Button) findViewById(R.id.btnYoklamaAl);
        bilgilerimHostes=(Button) findViewById(R.id.btnBilgilerim);
        smsGonder=(Button) findViewById(R.id.btnMesaj);



        //Sayfa geçişleri tıklamalar başlangıcı
        yoklamaAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HostesAnasayfa.this,HostesOturmaSekli.class);
                startActivity(intent);
            }
        });
        bilgilerimHostes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HostesAnasayfa.this,HostesBilgileriGuncelleme.class);
                startActivity(intent);
            }
        });
        smsGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HostesAnasayfa.this,HostesSms.class);
                startActivity(intent);
            }
        });
        //Bitişi.
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenuanasayfa,menu);
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
            Intent intent=new Intent(HostesAnasayfa.this,SifreDegistir.class);
            startActivity(intent);
        }else if(id==R.id.hakkında){
            Intent intent=new Intent(HostesAnasayfa.this,Hakkimizda.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }
}
