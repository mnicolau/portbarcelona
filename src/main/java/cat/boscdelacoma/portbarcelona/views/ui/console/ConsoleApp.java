/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.boscdelacoma.portbarcelona.views.ui.console;

import cat.boscdelacoma.portbarcelona.model.business.entities.Contenidor;
import cat.boscdelacoma.portbarcelona.model.business.entities.Vaixell;
import cat.boscdelacoma.portbarcelona.views.ui.test.PortBarcelona;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author marc
 */
public class ConsoleApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vaixell vaixell = new Vaixell();

        PortBarcelona.carregarVaixell(vaixell);
        mostrarContenidors(vaixell);
        descarregarVaixell(vaixell);
    }

    static void descarregarVaixell(Vaixell vaixell) {
        LocalDate data = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("\n\nDESCARREGAR VAIXELL");
        System.out.println("-------------------");

        System.out.println("PORT DE BARCELONA\n");
        System.out.printf("DATA: %s\n", data.format(format));
        System.out.println("CONTENIDOR         VOLUM");
        System.out.println("------------------------");
        Contenidor contenidor;

        for (int i = 0; i < vaixell.getnContenidors(); i++) {
            contenidor = vaixell.getContenidor(i);
            System.out.printf("%s %12.2f m3\n", contenidor.getNumSerie(), contenidor.getVolum());
        }
        System.out.printf("\nVOLUM TOTAL: %8.2f m3\n", vaixell.getVolum());
        System.out.println();
        vaixell.descarregar();
    }

    static void mostrarContenidors(Vaixell vaixell) {
        Contenidor contenidor;
        int countMercaderies = 0;

        System.out.println("MOSTRAR CONTENIDORS VAIXELL");
        System.out.println("---------------------------");

        for (int i = 0; i < vaixell.getnContenidors(); i++) {
            contenidor = vaixell.getContenidor(i);
            System.out.printf("%s\n", contenidor.getNumSerie());
            countMercaderies += contenidor.getnMercaderies();
        }
        System.out.printf("\nTOTAL MERCADERIES: %d\n", countMercaderies);
    }

}
