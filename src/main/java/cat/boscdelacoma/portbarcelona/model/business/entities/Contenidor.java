package cat.boscdelacoma.portbarcelona.model.business.entities;

public class Contenidor {

    //<editor-fold defaultstate="collapsed" desc="ATRIBUTS DE CLASE">
    public final static int MAX_MERCADERIES = 100;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ATRIBUTS D'INSTÀNCIA">
    private String numSerie;
    private float capacitat;
    private Mercaderia[] mercaderies;
    private int nMercaderies;
    private float volumOcupat;
    private boolean estat;  // true = obert; false = tancat
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CONSTRUCTORS">
    public Contenidor() {
        this("", 0, true);
    }

    public Contenidor(String numSerie, float capacitat, boolean estat) {
        this.numSerie = numSerie;
        this.capacitat = capacitat;
        this.mercaderies = new Mercaderia[MAX_MERCADERIES];
        this.nMercaderies = 0;
        this.estat = estat;
    }
    
    public Contenidor(Contenidor contenidor) {
        this(contenidor.getNumSerie(), contenidor.getCapacitat(), contenidor.isObert());
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="MÈTODES D'INSTÀNCIA">
    //<editor-fold defaultstate="collapsed" desc="GETTERS/SETTERS">
    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public float getCapacitat() {
        return capacitat;
    }

    public void setCapacitat(float capacitat) {
        this.capacitat = capacitat;
    }

    public int getnMercaderies() {
        return nMercaderies;
    }

    public void setnMercaderies(int nMercaderies) {
        this.nMercaderies = nMercaderies;
    }

    public boolean isObert() {
        return estat;
    }

    public void obrir() {
        this.estat = true;
    }
    
    public void tancar() {
        this.estat = false;
    }
    //</editor-fold>

    public void addMercaderia(Mercaderia mercaderia) {
        if (!this.estat) {
            // està tancat
            // TODO: què ha de fer en cas de no estar tancat?
        } else if (nMercaderies == MAX_MERCADERIES) {
            // està ple
            // TODO: què ha de fer en cas d'estar ple?
        } else if (getVolum() + mercaderia.getVolum() > this.capacitat) {
            // la mercaderia pot cabre dins el contenidor
            // TODO: què ha de fer en cas que la mercaderia no capiga dins el contenidor?
        } else {
            // afegir la nova mercaderia a l'array de mercaderies
            this.mercaderies[this.nMercaderies] = mercaderia;
            this.nMercaderies++;
            // assignar la refereència al contenidor a la mercaderia afegida
            mercaderia.setContenidor(this);
            // actualitzem el volum ocupat
            volumOcupat += mercaderia.getVolum();
        }
    }
 
    
    public float getVolum() {
        return volumOcupat;
    }

    
    public boolean isValid() {
        boolean valid = true;

        if (this instanceof Inspeccionable) {
            valid = this.isValid();
        }
        return valid;
    }
    

    @Override
    public String toString() {
        return "Contenidor{" + "numSerie=" + numSerie + ", capacitat=" + capacitat + ", mercaderies=" + mercaderies + ", nMercaderies=" + nMercaderies + ", estat=" + estat + '}';
    }
    //</editor-fold>

}
