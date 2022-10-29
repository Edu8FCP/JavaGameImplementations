import java.util.ArrayList; //para importar o package de ArrayList

public class MissionCards {
    final int PV = 6; // pontos de vitória de cada missão

    public static int draw_cards(int NumOfCards, ArrayList<MissionCards> cards, ArrayList<MissionCards> SelectedCards) {
        final int NumberOfCards = 40; // ver quantas cartas de missão existem
        int i = 0;
        do {
            int RandomCard = (int) (Math.random() * NumberOfCards);
            SelectedCards.add(cards.get(RandomCard - 1)); // adiciona às cartas a carta de missão sorteada
            i++; // conta quantas cartas já foram sorteadas
        } while (i < NumOfCards);
        return 1; // 1 = ok
    }
}
