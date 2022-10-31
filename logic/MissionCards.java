import java.util.ArrayList; //para importar o package de ArrayList
import java.util.Collections;

public class MissionCards {
    final int PV = 6; // pontos de vitória de cada missão
    // Falta classe para os atributos das missões

    public int DrawMissionCards(int NumOfCards, int[] ListOfMissionCards) {
        final int TotalOfCards = 40; // ver quantas cartas de missão existem
        // do {
        // int RandomCard = (int) (Math.random() * NumberOfCards);
        // SelectedCards.add(cards.get(RandomCard - 1)); // adiciona às cartas a carta
        // de missão sorteada
        // i++; // conta quantas cartas já foram sorteadas
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= TotalOfCards; i++) {
            list.add(i);
        } // Vamos preencher um array que vai controlar que não saem nrs repetidos
        Collections.shuffle(list); // Ordena aleatoriamente os nrs aka BARALHA ahaha
        for (int i = 0; i < TotalOfCards; i++) {
            ListOfMissionCards[i] = list.get(i); // Copiamos os índices para um array
        }
        // } while (i < NumOfCards);
        return 1; // 1 = ok
    }

    public void DisplayMissions(int[] ListOfMissionCards, ArrayList<MissionCards> CartasMissao) {
        // Recebe os índices das cartas e a estrutura onde se encontram todas as cartas
        // GUI para dispôr as cartas selecionadas
    }
}
