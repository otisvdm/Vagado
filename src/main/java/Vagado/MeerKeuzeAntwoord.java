package Vagado;

public class MeerKeuzeAntwoord extends Antwoord{
    private MockedDataService mockedDataService = new MockedDataService();

    private String[] mogelijkheden = mockedDataService.getOpties();

    @Override
    public String[] getAlleOpties() {
        String[] opties = new String[mogelijkheden.length+1];
        opties[0] = super.antwoord;
        for (int i = 1; i <= mogelijkheden.length; ++i) {
            opties[1] = mogelijkheden[i];
        }
        return opties;
    }
}
