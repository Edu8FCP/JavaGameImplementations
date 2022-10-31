import java.util.ArrayList;
import java.util.Collections; // Tenho de ver o que faz

public class Map {
    ArrayList<Boards> Tabuleiros = new ArrayList<Boards>();
    final int NrOfTabuleiros = 6;

    public void RandBoards(int ListOfBoards[]) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= NrOfTabuleiros; i++) {
            list.add(i);
        } // Vamos preencher um array que vai controlar que não saem nrs repetidos
        Collections.shuffle(list); // Ordena aleatoriamente os nrs
        for (int i = 0; i < NrOfTabuleiros; i++) {
            ListOfBoards[i] = list.get(i); // Copiamos os índices para um array
        }
    } // No array vai a lista com os índices dos tabs sorteados

    public void AssembleMap(int ListOfBoards[], ArrayList<Boards> Tabs) {
        // Vai ser usado pela GUI para montar o mapa
        // Recebe uma lista com os índices dos 6 tabuleiros sorteados
        // Recebe uma estrutura - Array List - com todos os tabuleiros criados (que
        // existem)
    }
}
