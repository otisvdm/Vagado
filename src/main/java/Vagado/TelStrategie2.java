package Vagado;

public class TelStrategie2 extends Puntentelling {

    public TelStrategie2() {
        super();
    }

    @Override
    public int telPunten(Ronde[] rondes) {
        int punten = 0;
        for (Ronde ronde : rondes) {
            if (ronde.isCorrect()) {
                punten += 2;
                if (ronde.getRondeTijd() < 10) {
                    punten++;
                }
            } else if (punten > 0) {
                punten--;
            }
        }
        return punten;
    }
}
