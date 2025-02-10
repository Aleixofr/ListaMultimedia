package es.carballeira.listamultimedia;

import android.os.Bundle;
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


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;


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

        RecyclerView recyclerView = findViewById(R.id.lista_recursos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear la lista de recursos usando archivos en res/raw/
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(new RecursoVideo("Video Mar", R.raw.mar, 14));
        recursos.add(new RecursoVideo("Video Campo", R.raw.campo, 14));
        recursos.add(new RecursoAudio("Audio Astral", R.raw.astral, "mp3"));
        recursos.add(new RecursoAudio("Audio SCI-FY", R.raw.scify, "mp3"));
        recursos.add(new RecursoWeb("Página Web", "https://fpadistancia.edu.xunta.gal/", "WEB"));

        // Configurar el adaptador con la lista
        RecursoAdapter adapter = new RecursoAdapter(recursos);
        recyclerView.setAdapter(adapter);

        videoView = findViewById(R.id.videoView);  // Asegúrate de que este VideoView está en activity_main.xml

        // Obtener el ID del recurso de video desde el Intent
        Intent intent = getIntent();
        int videoResId = intent.getIntExtra("video_id", -1);  // Por defecto -1, si no se encuentra el ID

        if (videoResId != -1) {
            String videoPath = "android.resource://" + getPackageName() + "/" + videoResId;
            videoView.setVideoPath(videoPath);
            videoView.start();
        } else {
            // Manejar caso en que no se ha enviado un video válido
            Toast.makeText(this, "No se encontró el video", Toast.LENGTH_SHORT).show();
        }
    }
}