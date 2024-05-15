package cat.boscdelacoma.portbarcelona.views.ui.test;

import cat.boscdelacoma.portbarcelona.model.business.entities.Contenidor;
import cat.boscdelacoma.portbarcelona.model.business.entities.Mercaderia;
import cat.boscdelacoma.portbarcelona.model.business.entities.Vaixell;
import cat.boscdelacoma.portbarcelona.model.persistence.daos.contracts.ContenidorDAO;
import cat.boscdelacoma.portbarcelona.model.persistence.daos.contracts.MercaderiaDAO;
import cat.boscdelacoma.portbarcelona.model.persistence.daos.impl.files.ContenidorFilesDAO;
import cat.boscdelacoma.portbarcelona.model.persistence.daos.impl.files.MercaderiaFilesDAO;
import cat.boscdelacoma.portbarcelona.model.persistence.exceptions.DAOException;
import java.util.List;
import java.util.Map;

public class PortBarcelona {

    public static void carregarVaixell(Vaixell vaixell) {

        // crear instàncies de les classes que permeten accedir al sistema de 
        // persistència amb fitxers de dades
        MercaderiaDAO mercaderiaDAO = new MercaderiaFilesDAO();
        ContenidorDAO contenidorDAO = new ContenidorFilesDAO();

        try {
            // obtenir tots els contenidors emmagatzemats
            Map<String, Contenidor> totsContenidors = contenidorDAO.getContenidors();
            // obtenir tres contenidors de la llista
            Contenidor c1 = totsContenidors.get("D9873456");
            Contenidor c2 = totsContenidors.get("C4562345");
            Contenidor c3 = totsContenidors.get("R7638493");

            // obrir el contenidor per poder afegir-hi mercaderies
            c1.obrir();
            afegirMercaderies(c1, mercaderiaDAO.getMercaderiesFromContainer(c1));
            c1.tancar();
            // carregar el contenidor al vaixell
            vaixell.addContenidor(c1);

            c2.obrir();
            afegirMercaderies(c2, mercaderiaDAO.getMercaderiesFromContainer(c2));
            c2.tancar();
            vaixell.addContenidor(c2);

            c3.obrir();
            afegirMercaderies(c3, mercaderiaDAO.getMercaderiesFromContainer(c3));
            c3.tancar();
            vaixell.addContenidor(c3);

        } catch (DAOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    static void afegirMercaderies(Contenidor contenidor, List<Mercaderia> mercaderies) {
        if (mercaderies != null) {
            for (Mercaderia mercaderia : mercaderies) {
                contenidor.addMercaderia(mercaderia);
            }
        }
    }

   
}
