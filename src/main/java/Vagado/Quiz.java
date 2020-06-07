package Vagado;

import java.util.UUID;

public class Quiz implements iQuiz {

    private String quizId;
    private int punten;
    private int playerId;
    private String strategie;
    private Ronde[] rondes;
    private Vragenlijst[] vragenlijsten;
    private Vragenlijst vragenlijst = new Vragenlijst(new Vraag[]{new Vraag("Vraag1", true)}, "1", new Thema());
    public Vraag[] vragen;

    public Quiz(int playerId) {
        this.playerId = playerId;
        vragenlijsten = this.getBeschikbareVragenlijsten(playerId);
        rondes = new Ronde[1];
        rondes[0] = new Ronde(1);
    }

    public String maakQuiz(String strategie) {
        this.quizId = UUID.randomUUID().toString();
        this.strategie = strategie;
        return this.quizId;
    }

    public Vragenlijst[] getBeschikbareVragenlijsten(int playerId) {
        return vragenlijst.getBeschikbareVragenlijsten(playerId);
    }

    public void kiesVragenlijst(Vragenlijst lijst) {
        vragenlijst = lijst;
        vragen = vragenlijst.getWillekeurigeVragen(lijst);
    }

    public void beantwoordVraag(String antwoord, Vraag vraag, int tijd) {
        if (rondes.length <= 10) {
            rondes[rondes.length - 1].bewaarAntwoordInRonde(rondes.length, antwoord, tijd, vraag);
        }
        if (rondes.length < 10) {
            Ronde[] newRondes = new Ronde[rondes.length + 1];
            System.arraycopy(rondes, 0, newRondes, 0, rondes.length);
            newRondes[rondes.length] = new Ronde(rondes.length + 1);
            rondes = newRondes;
        }
        if (rondes.length == 10) {
            punten = this.eindigQuiz();
        }
    }

    private int eindigQuiz() {
        if (rondes.length == 10) {
            Puntentelling puntenteller;
            if (strategie.equals("2")) {
                puntenteller = new TelStrategie2();
            }

            // Add all of your extra strategies here

            else { // strategie "1"
                puntenteller = new TelStrategie1();
            }
            return puntenteller.telPunten(rondes);
        } else {
            return 0;
        }
    }

    public int getPunten() {
        return punten;
    }
}
