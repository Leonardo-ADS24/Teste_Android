package com.example.appparidade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetalheActivity extends AppCompatActivity {
    TextView textNome, textDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhe);

        textNome = findViewById(R.id.textNome);
        textDescricao = findViewById(R.id.textDescricao);
        Button ButtonTelaDetalhe = findViewById(R.id.button_telaVoltar);

        String nome = getIntent().getStringExtra("nome");
        String descricao = getIntent().getStringExtra("descricao");

        textNome.setText(nome);
        textDescricao.setText(descricao);

        ButtonTelaDetalhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalheActivity.this, TelaListaActivity.class);
                startActivity(intent);
            }
        });
    }
}