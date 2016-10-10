package net.xaviersala;

import java.util.ArrayList;
import java.util.List;

import acm.graphics.GRectangle;

/**
 * Objecte que representa la bassa on hi haurà les granotes
 * i el cavaller.
 *
 * @author xavier
 *
 */
public class Bassa {

    /**
     * Velocitat a la que es mouen
     */
    private static final int SLEEP = 80;

    GRectangle dimensions;

    List<Granota> granotes;
    boolean acabat;

    public Bassa(int ample, int alt) {
        // TODO: Canviar-ho per una cosa més digna
        dimensions = new GRectangle(100,100,ample-200, alt-200);
        granotes = new ArrayList<>();
        acabat = false;
    }

    /**
     * Afegir les granotes a la bassa.
     *
     * @param granotes llista de granotes
     */
    public void afegirGranotes(List<Granota> granotes) {
        this.granotes = granotes;
    }

    /**
     * Afegeix una granota a la bassa.
     *
     * @param granota nova granota
     */
    public void afegirGranota(Granota granota) {
        granotes.add(granota);
    }

    /**
     * Barreja les granotes per aconseguir una nova posició.
     */
    public void barrejaGranotes() {

        for(Granota granota: granotes) {
                granota.collocaGranota();
        }

    }

    /**
     * Començar la partida
     *
     */
    public void start(App app) {
        while(!acabat) {
            for(Granota granota: granotes) {
                granota.mou();
                if (!granota.getImatge().getBounds().intersects(dimensions)) {
                    granota.inverteixDireccio((int) (dimensions.getX() + dimensions.getWidth()/2),
                                              (int) (dimensions.getY() + dimensions.getHeight()/2));
                }
            }
            app.pause(SLEEP);
        }

    }

}
