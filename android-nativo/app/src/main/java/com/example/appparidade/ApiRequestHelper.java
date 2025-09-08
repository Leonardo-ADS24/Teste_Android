package com.example.appparidade;


import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class ApiRequestHelper {
    private final OkHttpClient httpClient = new OkHttpClient();
    private final Gson gson = new Gson();

    public void fetchData(String url, final ApiRequestListener listener) {
        Request request = new Request.Builder().url(url).build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (listener != null) {
                    listener.onFailure(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String jsonData = response.body().string();

                    // AQUI está a mudança crucial:
                    // Define o tipo de objeto que o Gson deve esperar (uma lista de objetos Pais)
                    Type paisListType = new TypeToken<List<Post>>(){}.getType();

                    // Converte o JSON para uma lista de objetos Pais
                    List<Post> paises = gson.fromJson(jsonData, paisListType);

                    // Agora, verifique se a lista não está vazia
                    if (!paises.isEmpty()) {
                        Post pais = paises.get(0); // Pega o primeiro (e único) item da lista

                        String nomePais = pais.getNome().getAbreviado();
                        //String nomeCapital = pais.getCapital().getNome();
                        String historicoPais = pais.getHistorico();

                        String respostaFormatada = "País: " + nomePais +
                                //"\n\nCapital: " + nomeCapital +
                                "\n\nHistórico:\n" + historicoPais;

                        if (listener != null) {
                            listener.onSuccess(respostaFormatada);
                        }
                    } else {
                        // Se a lista estiver vazia, significa que o ID não foi encontrado
                        if (listener != null) {
                            listener.onFailure("ID de país não encontrado.");
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.onFailure("Requisição falhou: " + response.code());
                    }
                }
            }
        });
    }

    public interface ApiRequestListener {
        void onSuccess(String responseData);
        void onFailure(String errorMessage);
    }
}
