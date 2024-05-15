package cat.boscdelacoma.portbarcelona.model.business.entities;

public class Mercaderia {

    private String descripcio;
    private float volum;
    private Contenidor contenidor;

    public Mercaderia() {
        this("", 0, null);
    }

    public Mercaderia(String descripcio, float volum, Contenidor contenidor) {
        this.descripcio = descripcio;
        this.volum = volum;
        this.contenidor = contenidor;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public Contenidor getContenidor() {
        return contenidor;
    }

    public float getVolum() {
        return this.volum;
    }

    public void setVolum(float volum) {
        this.volum = volum;
    }

    public void setContenidor(Contenidor contenidor) {
        this.contenidor = contenidor;
    }

}
