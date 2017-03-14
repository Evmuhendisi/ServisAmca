package com.example.suleymansrc.servisamca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VeliLogin extends AppCompatActivity {

    Button button;
    EditText sifre;
    TextView sifreyiunuttum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veli_login);

        sifre = (EditText) findViewById(R.id.editText2);


        button = (Button) findViewById(R.id.button);
        sifreyiunuttum=(TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Tsifre = sifre.getText().toString();
                int Ttsifre=Integer.parseInt(Tsifre);
                if (Ttsifre == 1) {
                    Intent intent = new Intent(VeliLogin.this, MainActivity.class);
                    startActivity(intent);
                } else if (Ttsifre == 2) {
                    Intent intent = new Intent(VeliLogin.this, HostesAnasayfa.class);
                    startActivity(intent);
                } else if (Ttsifre == 3) {
                    Intent intent = new Intent(VeliLogin.this, SoforOkulSecme.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(VeliLogin.this, "Girdiğiniz şifre hatalıdır.Lütfen tekrar deneyiniz.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        sifreyiunuttum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(VeliLogin.this,SifremiUnuttum.class);
                startActivity(intent);
            }
        });


    }

}
