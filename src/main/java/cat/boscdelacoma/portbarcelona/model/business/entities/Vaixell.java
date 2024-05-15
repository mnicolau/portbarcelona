package cat.boscdelacoma.portbarcelona.model.business.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Vaixell {

    public static final int MAX_CONTENIDORS = 1000;

    private List<Contenidor> contenidors;
    private int nContenidors;
    private float volumOcupat;

    public Vaixell() {
        this.contenidors = new ArrayList<Contenidor>();
        this.volumOcupat = 0;
    }

    public Vaixell(float volumOcupat) {
        this();
        this.volumOcupat = volumOcupat;
    }

    /**
     * Afegeix un contenidor en el vaixell.
     *
     * Pot generar excepcions en cas que no es compleixin les restriccions
     * indicades.
     *
     * @param contenidor el contenidor que es vol afegir.
     */
    public void addContenidor(Contenidor contenidor) throws ArrayIndexOutOfBoundsException, UnsupportedOperationException {

        if (nContenidors == MAX_CONTENIDORS) {
            // el contenidor està ple
            // TODO: què ha de fer en cas que el vaixell tingui el màxim de vaixells?
        } else if (!contenidor.isValid()) {
            // el contenidor no es pot carregar => inspecció DUANA
            // TODO: què ha de fer en cas que el contenidor no passi la inspecció de la DUANA?
        } else if (contenidor.getnMercaderies() == 0) {
            // el contenidor no té mercaderies
            // TODO: què ha de fer en cas que el contenidor que es vol carrgar no tingui mercaderies?
        } else if (contenidor.isObert()) {
            // el contenidor està  obert
            // TODO: què ha de fer si el contenidor està obert?
        } else {
            this.contenidors.add( contenidor);

            // acumular el volum del contenidor
            this.volumOcupat += contenidor.getVolum();
        }
    }

    /**
     * Obtenir el volum ocupat per les mercaderies dels contenidors del vaixell
     *
     * @return obtenir el volum ocupat per les mercaderies dels contenidors del
     * vaixell
     */
    public float getVolum() {
        return volumOcupat;
    }

    /**
     * Obetnir el contenidor indicat.
     *
     * @param position la posició del contenidor que es vol obtenir
     * @return el contenidor a que s'ha demanat.
     */
    public Contenidor getContenidor(int pos) {
        return contenidors.get(pos);
    }

    public int getnContenidors() {
        return nContenidors;
    }

    public List<Contenidor> getContenidors() {
        return this.contenidors;
    }

    public float getMinVolum() {
        return (float)contenidors.stream().mapToDouble(x->x.getVolum()).min().getAsDouble();
    }
    public float getMaxVolum() {
        return (float)contenidors.stream().mapToDouble(x->x.getVolum()).max().getAsDouble();
    }
    public float getMitjaVolum() {
        return (float)contenidors.stream().mapToDouble(x->x.getVolum()).average().getAsDouble();
    }
    /**
     * Descarrega el vaixell
     */
    public void descarregar() {
        contenidors.clear();
        this.volumOcupat = 0;
    }

}
