package cat.boscdelacoma.portbarcelona.model.business.entities;

public class Refrigerat extends Contenidor implements Inspeccionable {
    private final static float MIN_TEMP = -10;

    private float minTemp;

    public Refrigerat() {
        super();
        minTemp = 0;
    }

    public Refrigerat(String numSerie, float capacitat, boolean estat, float minTemp) {
        super(numSerie, capacitat, estat);
        this.minTemp = minTemp;
    }       

    public Refrigerat(Contenidor contenidor, float minTemp) {
        super(contenidor);
        this.minTemp = minTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }
    
    /**
     * Obtenir el volum, el qual es veu incrementat en 0.01% respecte el volum
     * original
     *
     * @return el volum final del contenidor
     */
    @Override
    public float getVolum() {
        return (super.getVolum() * 0.01f / 100) + super.getVolum();
    }
    
    @Override
    public boolean isValid() {
        return minTemp < MIN_TEMP;
    }
}
