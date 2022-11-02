import java.util.ArrayList;
import java.util.Collections;

public class SlotPrize extends Slots {
    /********************************************
     **** >>>>>>> DISTRIBUIR CARTAS <<<<<<< ****
     ********************************************/
    /*
     * ListOfPrizes - estrutura onde vão os índices das cartas sorteados
     */

    public void RandPrizes(int ListOfPrizes[]) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 20; i++) {
            list.add(i);
        } // Vamos preencher um array que vai controlar que não saem nrs repetidos
        Collections.shuffle(list); // Ordena aleatoriamente os nrs
        for (int i = 0; i < 6; i++) {
            ListOfPrizes[i] = list.get(i); // Copiamos os índices para um array
        }
    }

    /*******************************************
     **** >>>>>>> ATRIBUIÇÃO CARTAS <<<<<<< ****
     *******************************************/
    /*
     * ListOfPrizes - estrutura onde vão os índices das cartas sorteados
     * Cards - Cartas ainda disponíveis
     */
    public void DrawCardPrizes() {
        int x = 5;
    }
}
