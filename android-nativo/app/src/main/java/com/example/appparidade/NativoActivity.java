package com.example.appparidade;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class NativoActivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;
    private ActivityResultLauncher<String[]> locationPermissionLauncher;

    private TextView locationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nativo);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationTextView = findViewById(R.id.textView_Localizacao);

        // 2. Registra o launcher para o resultado das permissões
        locationPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(), permissions -> {
                    if (Boolean.TRUE.equals(permissions.get(android.Manifest.permission.ACCESS_FINE_LOCATION))) {
                        // Permissão concedida. Agora podemos chamar o método de localização.
                        getLastKnownLocation();
                    } else {
                        // Permissão negada. Você deve avisar o usuário ou desabilitar a funcionalidade.
                        Log.e("Permissão", "Permissão de localização negada pelo usuário.");
                        // Exemplo: showRationale() ou simplesmente avisar com um Toast.
                    }
                });

        // 3. Verifica e solicita as permissões ao iniciar a Activity
        checkAndRequestLocationPermissions();
    }

    // 4. Método que verifica se a permissão já foi concedida e, se não, a solicita
    private void checkAndRequestLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Permissão já foi concedida, prossegue com a leitura da localização
            getLastKnownLocation();
        } else {
            // Permissão não concedida, solicita ao usuário
            locationPermissionLauncher.launch(new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
            });
        }
    }

    // 5. O método getLastKnownLocation(), que de fato obtém a localização
    private void getLastKnownLocation() {
        // Verifica novamente se a permissão foi concedida (boas práticas)
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        // Localização obtida com sucesso!
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();

                        String locationInfo = "Latitude: " + latitude + "\n" + "Longitude: " + longitude;

                        locationTextView.setText(locationInfo);

                        Log.d("Localização", "Latitude: " + latitude + ", Longitude: " + longitude);
                        // Aqui você pode atualizar um TextView, salvar em um banco de dados, etc.
                    } else {
                        // A última localização é nula. Pode ser que o serviço esteja desativado
                        // ou o dispositivo nunca obteve uma localização.

                        locationTextView.setText("Localização não disponível.");
                        Log.e("Localização", "Última localização conhecida é nula.");
                    }
                }
            });
        }

    }
}