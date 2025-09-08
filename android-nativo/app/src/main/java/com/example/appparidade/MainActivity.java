package com.example.appparidade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button ButtonTelaAPI = findViewById(R.id.button_tela_API);
        Button ButtonTelaLista = findViewById(R.id.button_tela_Lista);

        ButtonTelaAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaApiActivity.class);
                startActivity(intent);
            }
        });

        ButtonTelaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaListaActivity.class);
                startActivity(intent);

            }
        });

    }
}