package cat.boscdelacoma.portbarcelona.model.business.entities;

public class DryVan extends Contenidor implements Inspeccionable {

    private String color;

    public DryVan() {
        this("", 0, true, "WHITE");
    }

    public DryVan(String color) {
        this("", 0, true, color);
    }

    public DryVan(String numSerie, float capacitat, boolean estat, String color) {
        super(numSerie, capacitat, estat);
        this.color = color;
    }

    public DryVan(Contenidor contenidor, String color) {
        super(contenidor);
        this.color = color;
    }
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    

    @Override
    public boolean isValid() {
        return !color.equalsIgnoreCase("NEGRE");
    }
}
