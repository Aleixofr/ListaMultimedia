package es.carballeira.listamultimedia;

public class Recurso {
    private String titulo;
    private String url;
    private String tipo;
    private String duracion; // Opcional para videos/audio

    // Constructor para videos y audios (archivos locales en raw)
    public Recurso(String titulo, int resourceId, String tipo, String duracion) {
        this.titulo = titulo;
        this.url = "android.resource://es.carballeira.listamultimedia/" + resourceId;
        this.tipo = tipo;
        this.duracion = duracion;
    }

    // Constructor para páginas web
    public Recurso(String titulo, String url, String tipo) {
        this.titulo = titulo;
        this.url = url;
        this.tipo = tipo;
        this.duracion = null; // No aplica para páginas web
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrl() {
        return url;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDuracion() {
        return duracion;
    }
}
