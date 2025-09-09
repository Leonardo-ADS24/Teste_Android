package com.example.appparidade;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

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

        String nome = getIntent().getStringExtra("nome");
        String descricao = getIntent().getStringExtra("descricao");

        textNome.setText(nome);
        textDescricao.setText(descricao);
    }
}