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

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            btnAccion = itemView.findViewById(R.id.btnAccion);
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
            Context context = v.getContext();
            Intent intent  = new Intent(context, RecursoActivity.class);
            intent.putExtra("titulo", recurso.getTitulo());
            intent.putExtra("tipo", recurso.getTipo());
            intent.putExtra("url", recurso.getUrl());
            intent.putExtra("duracion", recurso.getDuracion());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return recursoList.size();
    }
}
