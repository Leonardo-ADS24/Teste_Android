package com.example.appparidade;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.util.Log;
import  android.widget.Button;
import android.widget.EditText;
import  android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaApiActivity extends AppCompatActivity {

    private TextView apiResponseTextView;
    private Button buttonRequisicaoApi;

    private EditText editTextPostId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_api);

        apiResponseTextView = findViewById(R.id.api_response_text_view);
        buttonRequisicaoApi = findViewById(R.id.button_requisicao_api);
        editTextPostId = findViewById(R.id.edit_text_post_id);

        buttonRequisicaoApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String postID = editTextPostId.getText().toString();

                if (postID.isEmpty()){
                    Toast.makeText(TelaApiActivity.this,"Digite a ID de um pais",Toast.LENGTH_SHORT).show();
                }else{
                    fetchDataFromApi(postID);
                }
            }
        });
    }
    private void fetchDataFromApi(String postId) {
        apiResponseTextView.setText("Carregando...");

        // Constrói a URL dinamicamente com o ID do usuário
        String url = "https://servicodados.ibge.gov.br/api/v1/paises/" + postId;
        Log.d("API_CALL", "URL da requisição: " + url);

        ApiRequestHelper apiHelper = new ApiRequestHelper();

        apiHelper.fetchData(url, new ApiRequestHelper.ApiRequestListener() {
            @Override
            public void onSuccess(String responseData) {
                Log.d("API_CALL", "Requisição bem-sucedida. Resposta: " + responseData);
                runOnUiThread(() -> {
                    apiResponseTextView.setText(responseData);
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e("API_CALL", "Erro: " + errorMessage);
                runOnUiThread(() -> {
                    apiResponseTextView.setText("Erro: " + errorMessage);
                });
            }
        });
    }
}
