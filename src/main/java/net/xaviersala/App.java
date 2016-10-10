package net.xaviersala;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

/**
 * Plantilla base per fer un programa fent servir les llibreries
 * ACM.
 *
 */
public class App extends GraphicsProgram
{

    /**
    *
    */
   private static final long serialVersionUID = 1299094805237490891L;
   public static final int AMPLADAPANTALLA = 1000;
   public static final int ALTURAPANTALLA = 600;
   private static final int NUMEROGRANOTES = 6;
   private static final int VELOCITATGRANOTA = 5;

   private static Random aleatori = new Random();

    /**
     * Programa principal...
     */
    @Override
    public final void run() {
       setSize(AMPLADAPANTALLA, ALTURAPANTALLA);

       List<Granota> granotes = generarGranotes(NUMEROGRANOTES);

       Bassa bassa = new Bassa(AMPLADAPANTALLA, ALTURAPANTALLA);
       bassa.afegirGranotes(granotes);

       clicaPerComencar();

       bassa.start(this);
    }

/**
 * Genero les granotes amb les imatges i les poso en una llista.
 *
 * @param num Número de granotes
 * @return llista de granotes generades
 */
private List<Granota> generarGranotes(int num) {
        List<Granota> granotes = new ArrayList<>(num);

        for(int i=0; i<num; i++) {
            GImage granota = generaGranota();
            granotes.add(new Granota(granota, VELOCITATGRANOTA));
        }

        granotes.get(aleatori.nextInt(granotes.size())).setPrincesa(true);

        return granotes;
    }

/**
 * Generar una granota i es posa per pantalla.
 *
 * @return
 */
private GImage generaGranota() {
    GImage granota = new GImage("granota.png");
    add(granota);
    return granota;
}

/**
 * Clica per començar.
 */
private void clicaPerComencar() {
    GLabel label = new GLabel("Clica per començar");
    double x = (getWidth() - label.getWidth()) / 2;
    double y = (getHeight() + label.getAscent()) / 2;
    add(label, x, y);
    waitForClick();
    remove(label);
}

}
