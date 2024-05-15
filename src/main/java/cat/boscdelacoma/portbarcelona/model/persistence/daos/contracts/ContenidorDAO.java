package cat.boscdelacoma.portbarcelona.model.persistence.daos.contracts;

import cat.boscdelacoma.portbarcelona.model.business.entities.Contenidor;
import cat.boscdelacoma.portbarcelona.model.persistence.exceptions.DAOException;
import java.util.Map;

/**
 *
 * @author Marc Nicolau
 */
public interface ContenidorDAO {
    // obtenir un cotenidor pel seu num. sèrie
    Contenidor getContenidorByNumSerie(String numSerie) throws DAOException;
    
    // obtenir totes els contenidors en forma de Map
    Map<String,Contenidor> getContenidors() throws DAOException;
    
    // grava totes les mercaderies de la llista en el fitxer de mercaderies.
    // si el fitxer ja extisteix, en substitueix el contingut per la nova llista.
    void saveContenidors(Map<String,Contenidor> contenidors) throws DAOException;    
}
