package cat.boscdelacoma.portbarcelona.model.persistence.daos.contracts;

import cat.boscdelacoma.portbarcelona.model.business.entities.Contenidor;
import cat.boscdelacoma.portbarcelona.model.business.entities.Mercaderia;
import cat.boscdelacoma.portbarcelona.model.persistence.exceptions.DAOException;
import java.util.List;

/**
 * Interface que estableix els mètodes que ha de tenir un objecte d'accés a dades
 * de tipus Mercaderia.
 * 
 * @author Marc Nicolau
 */
public interface MercaderiaDAO {    

    /**
     * Obté totes les mercaderies del sistema de persistència.
     * 
     * @return Una llista amb totes les mercaderies del sistema de persistència.
     * @throws DAOException Es produeix en cas que hi hagi algun problema en el 
     *                      moment de recuperar les dades del sistema de persistència
     */
    List<Mercaderia> getMercaderies() throws DAOException;
    
    /**
     * Obté totes les mercaderies que estan dins un contenidor.
     * 
     * @param container El contenidor del qual es volen obtenir les mercaderies.
     * @return Una llista amb totes les mercaderies del contenidor indicat.
     * @throws DAOException Es produeix en cas que hi hagi algun problema en el 
     *                      moment de recuperar les dades del sistema de persistència
     */
    List<Mercaderia> getMercaderiesFromContainer(Contenidor container) throws DAOException;

    /**
     * Grava otes les mercaderies de la llista en el fitxer de mercaderies.
     * Si el fitxer ja extisteix, en substitueix el contingut per la nova llista.
     * 
     * @param mercaderies La llista de mercaderies que es vol desar
     * @throws DAOException Es produeix en cas que hi hagi algun problema en el 
     *                      moment de desar les dades de la llista en el sistema
     *                      de persistència.
     */
    void saveMercaderies(List<Mercaderia> mercaderies) throws DAOException;
}
