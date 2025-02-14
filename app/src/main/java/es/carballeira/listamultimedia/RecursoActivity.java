package es.carballeira.listamultimedia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RecursoActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recurso);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvTitulo = findViewById(R.id.tvRecursoTitulo);
        TextView tvTipo = findViewById(R.id.tvRecursoTipo);
        TextView tvUrl = findViewById(R.id.tvRecursoUrl);
        TextView tvDuracion = findViewById(R.id.tvRecursoDuracion);
        Button btnReproducir = findViewById(R.id.btnReproducir);
        Button btnVolver = findViewById(R.id.btnVolver);

        Intent intent = getIntent();

        if (intent != null) {

            String titulo = intent.getStringExtra("titulo");
            String tipo = intent.getStringExtra("tipo");
            String url = intent.getStringExtra("url");
            String duracion = intent.getStringExtra("duracion");

            tvTitulo.setText("Título: " + titulo);
            tvTipo.setText("Tipo: " + tipo);
            tvUrl.setText("URL: " + url);

            // Mostrar duración solo si es aplicable
            if (duracion != null && !duracion.isEmpty()) {
                tvDuracion.setText("Duración: " + duracion);
                tvDuracion.setVisibility(View.VISIBLE);
            }

            videoView = findViewById(R.id.videoView);
            WebView webView = findViewById(R.id.webView);

            videoView.setVisibility(View.GONE);
            webView.setVisibility(View.GONE);

            btnReproducir.setOnClickListener(v -> {
                switch (tipo) {
                    case "video":
                        videoView.setVideoPath(url);
                        videoView.setVisibility(View.VISIBLE);

                        MediaController mediaController = new MediaController(this);
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);

                        videoView.start();
                        break;

                    case "audio":
                        MediaPlayer mp = MediaPlayer.create(this, Uri.parse(url));
                        if (mp != null) {
                            mp.start();
                        } else {
                            Toast.makeText(this, "Error al reproducir el audio", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case "web":
                        webView.setVisibility(View.VISIBLE);
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.setWebViewClient(new WebViewClient());
                        webView.setWebChromeClient(new WebChromeClient());
                        webView.loadUrl(url);
                        break;

                    default:
                        Toast.makeText(this, "Tipo de recurso no válido", Toast.LENGTH_SHORT).show();
                        break;
                }
            });

            btnVolver.setOnClickListener(v -> finish());


        }
    }
}