package com.example.appparidade;


import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiRequestHelper {

    private final OkHttpClient httpClient = new OkHttpClient();
    private final Gson gson = new Gson();

    public void fetchData(String url, final ApiRequestListener listener) {
        Request request = new Request.Builder().url(url).build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Notifique o listener em caso de falha na requisição
                if (listener != null) {
                    listener.onFailure(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String jsonData = response.body().string();
                    // Suponha que a resposta seja um objeto JSON
                    // Aqui você pode converter o JSON para um objeto Java
                    // Ex: MeuObjeto obj = gson.fromJson(jsonData, MeuObjeto.class);
                    // No nosso caso, vamos retornar o JSON puro
                    if (listener != null) {
                        listener.onSuccess(jsonData);
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
