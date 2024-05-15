package cat.boscdelacoma.portbarcelona.model.persistence.daos.impl.files;

import cat.boscdelacoma.portbarcelona.model.business.entities.Contenidor;
import cat.boscdelacoma.portbarcelona.model.business.entities.Mercaderia;
import cat.boscdelacoma.portbarcelona.model.persistence.daos.contracts.ContenidorDAO;
import cat.boscdelacoma.portbarcelona.model.persistence.daos.contracts.MercaderiaDAO;
import cat.boscdelacoma.portbarcelona.model.persistence.exceptions.DAOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que implementa un sistema de persistència d'objectes Mercaderia en
 * fitxers orientats a byte
 *
 * @author Marc Nicolau
 */
public class MercaderiaFilesDAO implements MercaderiaDAO {

    private final static String FILE_NAME = "mercaderies.dat";

    @Override
    public List<Mercaderia> getMercaderies() throws DAOException {
        List<Mercaderia> mercaderies = new ArrayList<>();
        ContenidorDAO contDAO = new ContenidorFilesDAO();

        try (DataInputStream source = new DataInputStream(new FileInputStream(FILE_NAME))) {
            while (true) {
                Mercaderia mercaderia = new Mercaderia();
                mercaderia.setDescripcio(source.readUTF());
                mercaderia.setVolum(source.readFloat());

                Contenidor cont = contDAO.getContenidorByNumSerie(source.readUTF());
                if (cont != null) {
                    mercaderia.setContenidor(cont);
                }
                mercaderies.add(mercaderia);
            }
        } catch (FileNotFoundException ex) {
            throw new DAOException(String.format("No s'ha trobat el fitxer %s", FILE_NAME));
        } catch (EOFException ex) {

        } catch (IOException ex) {
            throw new DAOException("ERROR d'entrada/sortida");
        } catch (SecurityException | IllegalArgumentException ex) {
            throw new DAOException();
        }
        return mercaderies;
    }

    @Override
    public List<Mercaderia> getMercaderiesFromContainer(Contenidor container) throws DAOException {
        List<Mercaderia> mercaderies = getMercaderies();
        if (mercaderies != null) {
            return mercaderies.stream().
                               filter(x -> x.getContenidor().getNumSerie().equalsIgnoreCase(container.getNumSerie())).
                               collect(Collectors.toList());
        }
        return mercaderies;
    }

    @Override
    public void saveMercaderies(List<Mercaderia> mercaderies) throws DAOException {
        try (DataOutputStream dest = new DataOutputStream(new FileOutputStream(FILE_NAME))) {
            for (Mercaderia mercaderia : mercaderies) {
                dest.writeUTF(mercaderia.getDescripcio());
                dest.writeFloat(mercaderia.getVolum());
                dest.writeUTF(mercaderia.getContenidor().getNumSerie());
            }
        } catch (FileNotFoundException ex) {
            throw new DAOException("No s'ha trobat el fitxer " + FILE_NAME);
        } catch (IOException ex) {
            throw new DAOException("Error en escriure en el fitxer " + FILE_NAME);
        }
    }
}
