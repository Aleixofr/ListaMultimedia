package es.carballeira.listamultimedia;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.widget.Toast;
import android.widget.MediaController;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Crear la lista de recursos usando archivos en res/raw/
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(new Recurso("Video Mar", R.raw.mar, "video", "120s"));
        recursos.add(new Recurso("Video Campo", R.raw.campo, "video", "140s"));
        recursos.add(new Recurso("Audio Astral", R.raw.astral, "audio", "mp3"));
        recursos.add(new Recurso("Audio SCI-FY", R.raw.scify, "audio", "mp3"));
        recursos.add(new Recurso("FP a distancia", "https://fpadistancia.edu.xunta.gal/", "web"));
        recursos.add(new Recurso("Google", "https://google.com/", "web"));

        RecyclerView recyclerView = findViewById(R.id.lista_recursos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configurar el adaptador con la lista
        RecursoAdapter adapter = new RecursoAdapter(recursos);
        recyclerView.setAdapter(adapter);
    }
}