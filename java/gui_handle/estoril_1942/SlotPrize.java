package gui_handle.estoril_1942;

import java.util.ArrayList;
import java.util.Collections;

public class SlotPrize extends Slots {
    /********************************************
     **** >>>>>>> DISTRIBUIR CARTAS <<<<<<< ****
     ********************************************/
    /*
     * ListOfPrizes - estrutura onde vão os índices das cartas sorteados
     * FUNÇÃO - Sorteia as cartas prémio que serão usadas na rodada
     */

    public static void RandPrizes(int ListOfPrizes[], Game game) {
        if(ListOfPrizes == null || game == null){
            System.out.println("Erro ao carregar os parâmetros do jogo em RandPrizes");
            return;
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i<game.Cartas.size() ;i++) { // Tamanho é 27
            list.add(i);
        } // Vamos preencher um array que vai controlar que não saem nrs repetidos
        Collections.shuffle(list); // Ordena aleatoriamente os nrs
        for (int i = 0; i < 6; i++) {
            ListOfPrizes[i] = list.get(i); // Copiamos os índices para um array
            System.out.println("Carta Prémio: " + ListOfPrizes[i]);
        }
    }

}
