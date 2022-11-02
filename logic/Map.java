import java.util.ArrayList;
import java.util.Collections; // Tenho de ver o que faz

public class Map {
    /*********************************************
     **** >>>>>>> ATRIBUTOS DA CLASSE <<<<<<< ****
     *********************************************/
    // ArrayList<Boards> Tabuleiros = new ArrayList<Boards>();
    Boards[][] Tabuleiros = new Boards[2][3];

    /**********************************************************
     **** >>>>>>> ELEMENTOS PARA COMPOR AS FUNÇÕES <<<<<<< ****
     **********************************************************/
    final int NrOfTabuleiros = 6; // nr fixo de tabuleiros que se usam
    final int TotalOfTabuleiros = 12;

    /********************************************
     **** >>>>>>> SORTEIA TABULEIROS <<<<<<< ****
     ********************************************/
    /*
     * ListOfBoards - estrutura onde vão os índices dos tabuleiros sorteados
     */
    public void RandBoards(int ListOfBoards[]) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= TotalOfTabuleiros; i++) {
            list.add(i);
        } // Vamos preencher um array que vai controlar que não saem nrs repetidos
        Collections.shuffle(list); // Ordena aleatoriamente os nrs
        for (int i = 0; i < NrOfTabuleiros; i++) {
            ListOfBoards[i] = list.get(i); // Copiamos os índices para um array
        }
    } // No array vai a lista com os índices dos tabs sorteados

    /*********************************************
     **** >>>>>>> ASSEMBLA TABULEIROS <<<<<<< ****
     *********************************************/
    /*
     * ListOfBoards - tem os tabuleiros sorteados para a ronda
     * Mapa - Local do jogo
     * Tabuleiros - Lista com os tabuleiros todos existentes já após o build
     */
    public void AssembleMap(int ListOfBoards[], Map Mapa, Boards[] Tabuleiros) {
        for (int i = 0; i < NrOfTabuleiros / 2; i++) {
            Mapa.Tabuleiros[0][i] = Tabuleiros[ListOfBoards[i]];
            Mapa.Tabuleiros[1][i] = Tabuleiros[ListOfBoards[i + NrOfTabuleiros / 2]];
        } // Construção da matriz com os tabuleiros

        // Vai ser usado pela GUI para montar o mapa
        // Recebe uma lista com os índices dos 6 tabuleiros sorteados
        // Recebe uma estrutura - Array List - com todos os tabuleiros criados (que
        // existem)
    }
}
