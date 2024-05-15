package cat.boscdelacoma.portbarcelona.model.persistence.exceptions;


/**
 * Classe que implementa un tipus de dades per la gestió d'Excepcions de la capa 
 * de persistència.
 * 
 * @author Marc Nicolau
 */
public class DAOException extends Exception {

    public DAOException() {
        super();
    }
    
    public DAOException(String message) {
        super(message);
    }
    
}
