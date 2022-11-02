import java.util.ArrayList;

public class Player {
    int CardsID; // Cor do jogador?
    int ProfileID; // Nome do jogador?
    ArrayList<Cards> HandCards = new ArrayList<Cards>(); // Mão do jogador
    ArrayList<Cards> PlayerDiscardedCards = new ArrayList<Cards>(); // Cartas descartadas pelo jogador
    // Numa fase inicial até podíamos ter só uma variável int para contar quantas
    // cartas foram descartadas

    /******************************************
     **** >>>>>>> DESCARTAR CARTAS <<<<<<< ****
     ******************************************/
    /*
     * HandCards - Cartas na mão do jogador
     * PlayerDiscardedCards - Cartas descartadas pelo jogador
     */
    public void Discard() {
        // While tiver mais que 6 cartas tem de descartar
        // As cartas descartadas passam para uma pilha de descarte
    }

    /**************************************
     **** >>>>>>> JOGAR CARTAS <<<<<<< ****
     **************************************/
    /*
     * HandCards - Cartas na mão do jogador
     */
    public void PlayCard() {

    }
}
