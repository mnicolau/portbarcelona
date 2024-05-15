/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.boscdelacoma.portbarcelona.model.persistence.daos.impl.files;

import cat.boscdelacoma.portbarcelona.model.business.entities.Cisterna;
import cat.boscdelacoma.portbarcelona.model.business.entities.Contenidor;
import cat.boscdelacoma.portbarcelona.model.business.entities.DryVan;
import cat.boscdelacoma.portbarcelona.model.business.entities.Refrigerat;
import cat.boscdelacoma.portbarcelona.model.persistence.daos.contracts.ContenidorDAO;
import cat.boscdelacoma.portbarcelona.model.persistence.exceptions.DAOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Classe que implementa un sistema de persistència d'objectes Contenidor en
 * fitxers orientats a byte
 *
 * @author Marc Nicolau
 */
public class ContenidorFilesDAO implements ContenidorDAO {

    private final static String FILE_NAME = "contenidors.dat";

    @Override
    public Contenidor getContenidorByNumSerie(String numSerie) throws DAOException {
        Map<String,Contenidor> contenidors = getContenidors();
        if (contenidors != null) {
            return contenidors.get(numSerie);
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Contenidor> getContenidors() throws DAOException {
        Map<String,Contenidor> contenidors = new TreeMap<>();

        try (DataInputStream source = new DataInputStream(new FileInputStream(FILE_NAME))) {
            while (true) {
                String classe = source.readUTF();
                Contenidor contenidorBase = new Contenidor();
                contenidorBase.setNumSerie(source.readUTF());
                contenidorBase.setCapacitat(source.readFloat());
                if (source.readBoolean()) {
                    contenidorBase.obrir();
                } else {
                    contenidorBase.tancar();
                }
                Contenidor contenidor = null;
                if (classe.contains("DryVan")) {
                    contenidor = new DryVan(contenidorBase, source.readUTF());
                } else if (classe.contains("Refrigerat")) {
                    contenidor = new Refrigerat(contenidorBase, source.readFloat());
                } else if (classe.contains("Cisterna")) {
                    contenidor = new Cisterna(contenidorBase);
                }
                if (contenidor != null) {
                    contenidors.put(contenidor.getNumSerie(), contenidor);
                }
            }
        } catch (FileNotFoundException ex) {
            throw new DAOException(String.format("No s'ha trobat el fitxer %s", FILE_NAME));
        } catch (EOFException ex) {

        } catch (IOException ex) {
            throw new DAOException("ERROR d'entrada/sortida");
        } catch (SecurityException | IllegalArgumentException ex) {
            throw new DAOException();
        }
        return contenidors;
    }

    @Override
    public void saveContenidors(Map<String, Contenidor> contenidors) throws DAOException {
        try (DataOutputStream destination = new DataOutputStream(new FileOutputStream(FILE_NAME))) {
            for (Map.Entry<String,Contenidor> entry : contenidors.entrySet()) {
                Contenidor contenidor = entry.getValue();
                // guardem la classe de l'objecte per saber quin objecte és 
                // dins la jerarquia de classes de contenidors
                destination.writeUTF(contenidor.getClass().getCanonicalName());
                destination.writeUTF(contenidor.getNumSerie());
                destination.writeFloat(contenidor.getCapacitat());
                destination.writeBoolean(contenidor.isObert());
                if (contenidor instanceof DryVan) {
                    destination.writeUTF(((DryVan) contenidor).getColor());
                } else if (contenidor instanceof Refrigerat) {
                    destination.writeFloat(((Refrigerat) contenidor).getMinTemp());
                } 
            }
        } catch (FileNotFoundException ex) {
            throw new DAOException(String.format("No s'ha trobat el fitxer %s", FILE_NAME));
        } catch (IOException ex) {
            throw new DAOException("ERROR d'entrada/sortida");
        }
    }
}
