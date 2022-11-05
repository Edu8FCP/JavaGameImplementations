import java.util.ArrayList; //para importar o package de ArrayList
import java.util.Collections;

public class MissionCards {
    int ID; // ID vai identificar qual a missão (switch case GIGANTE)
    final int PV = 6; // pontos de vitória de cada missão
    // Falta classe para a imagem das missões

    /**************************************************
     **** >>>>>>> SORTEAR CARTAS DE MISSÃO <<<<<<< ****
     **************************************************/
    /*
     * NumOfCards - Nr de cartas de missão a sortear
     * ListOfMissionCards - Índice das cartas de missão sorteadas
     */
    public int DrawMissionCards(int NumOfCards, int[] ListOfMissionCards) {
        final int TotalOfCards = 40; // ver quantas cartas de missão existem

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= TotalOfCards; i++) {
            list.add(i);
        } // Vamos preencher um array que vai controlar que não saem nrs repetidos
        Collections.shuffle(list); // Ordena aleatoriamente os nrs aka BARALHA ahaha
        for (int i = 0; i < 4; i++) {
            ListOfMissionCards[i] = list.get(i); // Copiamos os índices para um array
        }
        // } while (i < NumOfCards);
        return 1; // 1 = ok
    }

    /*********************************************************
     **** >>>>>>> ATRIBUIÇÃO DAS CARTAS DE MISSÃO <<<<<<< ****
     *********************************************************/
    /*
     * ListOfMissionCards - Índice das cartas de missão sorteadas
     * CartasMissao - estrutura com todas as cartas de missão
     */
    public void DisplayMissions(int[] ListOfMissionCards, MissionCards[] CartasMissao) {
        // Recebe os índices das cartas e a estrutura onde se encontram todas as cartas
        // GUI para dispôr as cartas selecionadas
    }

    /************************************************************
     **** >>>>>>> INICIALIZAÇÃO DAS CARTAS DE MISSÃO <<<<<<< ****
     ************************************************************/
    /*
     * ImageCartasMissão - estrutura com as imagens para as cartas de missão
     * PV - Pontos de vitória das cartas de missão
     * CartasMissao - estrutura onde vão ser construídas todas as cartas de missão
     */
    public static void BuildMissionCards(MissionCards[] CartasMissao, String Imagens[]) {
        final int NrOfMissions = 10; // ver quantas são
        for (int i = 0; i < NrOfMissions; i++) {
            CartasMissao[i].ID = i;
            // PV já está feito
            // Adicionar imagem
        }
    }
}
