package es.carballeira.listamultimedia;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecursoAdapter extends RecyclerView.Adapter<RecursoAdapter.ViewHolder> {
    private List<Recurso> recursoList;

    public RecursoAdapter(List<Recurso> recursoList) {
        this.recursoList = recursoList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvTipo;
        Button btnAccion;
        VideoView videoView;
        WebView webView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            btnAccion = itemView.findViewById(R.id.btnAccion);
            videoView = itemView.findViewById(R.id.videoView);
            webView = itemView.findViewById(R.id.webView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recurso, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recurso recurso = recursoList.get(position);
        holder.tvTitulo.setText(recurso.getTitulo());
        holder.tvTipo.setText("Tipo: " + recurso.getTipo());

        holder.btnAccion.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            if (recurso instanceof RecursoVideo) {
                // Enviar el ID del recurso de video
                intent.putExtra("video_id", ((RecursoVideo) recurso).getResource());
            } else if (recurso instanceof RecursoAudio) {
                // Si es audio, maneja otro tipo de recurso
                // Puedes enviar la URL o ID de audio si quieres reproducirlo desde aquí
            }
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recursoList.size();
    }
}
