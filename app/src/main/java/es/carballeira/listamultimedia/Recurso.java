package es.carballeira.listamultimedia;


public abstract class Recurso {
    protected String titulo;

    public Recurso(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public abstract String getTipo();
}

// Subclase para Recursos de Video
class RecursoVideo extends Recurso {
    private int duracion; // Duración en segundos
    private int resource;

    public RecursoVideo(String titulo, int resource, int duracion) {
        super(titulo);
        this.duracion = duracion;
        this.resource = resource;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getResource() {
        return resource;
    }

    @Override
    public String getTipo() {
        return "Video";
    }
}

// Subclase para Recursos de Audio
class RecursoAudio extends Recurso {
    private String formato; // Ejemplo: MP3, WAV
    private int resource;

    public RecursoAudio(String titulo, int resource, String formato) {
        super(titulo);
        this.resource = resource;
        this.formato = formato;
    }

    public String getFormato() {
        return formato;
    }

    public int getResource() {
        return resource;
    }

    @Override
    public String getTipo() {
        return "Audio";
    }
}

// Subclase para Recursos Web
class RecursoWeb extends Recurso {
    private String tipoContenido;
    private String url;

    public RecursoWeb(String titulo, String url, String tipoContenido) {
        super(titulo);
        this.url = url;
        this.tipoContenido = tipoContenido;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String getTipo() {
        return "Web";
    }
}

