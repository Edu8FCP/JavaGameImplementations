import java.util.ArrayList;

public class Player {
    int CardsID; // Nr do jogador
    String ProfileID; // Nome do jogador?
    ArrayList<Cards> HandCards = new ArrayList<Cards>(); // Mão do jogador
    ArrayList<Cards> PlayerDiscardedCards = new ArrayList<Cards>(); // Cartas descartadas pelo jogador
    ArrayList<Cards> DiscardedCards = new ArrayList<Cards>();
    // Numa fase inicial até podíamos ter só uma variável int para contar quantas
    // cartas foram descartadas

    int ID = 0;

    /*****************************************
     **** >>>>>>> CRIAR JOGADORES <<<<<<< ****
     *****************************************/
    /*
     * name - nome do jogador
     * jogador - jogador a que vai ser alocada a mão
     * CardPowers - Força das cartas
     * PV - Pontos de vitória de cada carta
     * Nacionalidades - estrutura com as nacionalidades para as cartas
     * ImagemCartas - estrutura com as imagens para as cartas
     */
    // CONSTRUTOR
    public Player(String name, int CardPowers[], int PV[], String Nacionalidades[],
            String ImagemCartas[]) {
        ID++;
        CardsID = ID;
        ProfileID = name;
        Cards.BuildBaseCards(HandCards, CardPowers, PV, Nacionalidades, ImagemCartas);
    }

    /******************************************
     **** >>>>>>> DESCARTAR CARTAS <<<<<<< ****
     ******************************************/
    /*
     * HandCards - Cartas na mão do jogador
     * PlayerDiscardedCards - Cartas descartadas pelo jogador
     */
    public void Discard(Player Jogador) {
        while (Jogador.HandCards.size() > 6) {
            int num = Jogador.HandCards.size() - 6;
            System.out.println("Please Choose" + num + "cards to Discard");
            // Como vai ser selecionada?

        }
        // While tiver mais que 6 cartas tem de descartar
        // As cartas descartadas passam para uma pilha de descarte
    }

    /**************************************
     **** >>>>>>> JOGAR CARTAS <<<<<<< ****
     **************************************/
    /*
     * Mapa - Mapa com a situação atual de jogo
     * HandCards - Cartas na mão do jogador
     * BoardTarget - Tabuleiro que contém o slot onde se vai jogar
     * SlotTarget - Slot onde se vai jogar a carta
     * ChoosenCard - Carta escolhida para jogar
     */
    public void PlayCard(Map Mapa, Player PlayerHand, Boards BoardTarget, Slots SlotTarget, int ChoosenCard) {
        if (PlayerHand == null || BoardTarget == null || SlotTarget == null) {
            System.out.println("Parâmetros inválidos...");
        }
        int BTR, BTC; // BTR/C - Board Target Row/Column
        int STR, STC; // STR/C - Slot Target Row/Column
        BTR = BoardTarget.linha;
        BTC = BoardTarget.coluna;
        STR = SlotTarget.linha;
        STC = SlotTarget.coluna;
        if (Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].player == 0) {
            // Atribui a carta
            Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].player = PlayerHand.CardsID;
            Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].Card = PlayerHand.HandCards.get(ChoosenCard);
        } else
            System.out.println("Slot ocupado...");
    }
}
