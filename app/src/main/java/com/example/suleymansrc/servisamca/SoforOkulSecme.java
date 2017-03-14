package com.example.suleymansrc.servisamca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SoforOkulSecme extends AppCompatActivity {
    Button kaydet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofor_okul_secme);
        kaydet=(Button) findViewById(R.id.btnSoforOkulSecme);
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SoforOkulSecme.this, "Veriler gönderilip alınıyor...", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SoforOkulSecme.this,SoforAnasayfa.class);
                startActivity(intent);

            }
        });
    }
}
