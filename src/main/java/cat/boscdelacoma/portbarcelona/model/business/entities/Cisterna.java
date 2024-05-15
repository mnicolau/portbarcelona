package cat.boscdelacoma.portbarcelona.model.business.entities;

public class Cisterna extends Contenidor {

    public Cisterna() {
        super();
    }
    
    public Cisterna(String numSerie, float capacitat, boolean estat) {
        super(numSerie, capacitat, estat);
    }
    
    public Cisterna(Contenidor contenidor) {
        super(contenidor);
    }
    
    @Override
    public void addMercaderia(Mercaderia mercaderia) {
        if (getnMercaderies() == 1) {
            // TODO: què ha de fer en cas d'haver-hi ja 1 líquid?
        } else {
            super.addMercaderia(mercaderia);
        }
    }

    /**
     * Obtenir el volum de la cisterna, en litres.
     * @return volumn de la cisterna, en litres.
     */
    @Override
    public float getVolum() {
        return super.getVolum() / 1000f;
    }

    
}
