package gui_handle.estoril_1942;

import java.util.ArrayList;

public class Player {
    int CardsID; // Nr do jogador
    String ProfileID; // Nome do jogador?
    ArrayList<Cards> HandCards = new ArrayList<Cards>(); // Mão do jogador
    ArrayList<Cards> PlayerDiscardedCards = new ArrayList<Cards>(); // Cartas descartadas pelo jogador
    // Numa fase inicial até podíamos ter só uma variável int para contar quantas
    // cartas foram descartadas

    /*****************************************
     **** >>>>>>> CRIAR JOGADORES <<<<<<< ****
     *****************************************/
    /*
     * name - nome do jogador
     * i - ID do jogador
     * BaseCards - arrayList no handler game onde foram criadas as cartas
     * FUNÇÃO - Construtor que inicializa a estrutura e faz associações básicas sobre o cliente singular
     */

    // CONSTRUTOR
    public Player(String name, ArrayList<Cards> BaseCards, int i) {
        CardsID = i;
        System.out.println(CardsID);
        ProfileID = name;
        AssociateCards(BaseCards, HandCards, CardsID);
    }

    /**********************************************
     **** >>>>>>> ASSOCIAR MÃO INICIAL <<<<<<< ****
     **********************************************/
    /*
     * AllBaseCards - Estrutura com todas as cartas base que existem
     * BaseCardsHand - Estrutura para onde vão ser passadas as 6 cartas do jogador
     * FUNÇÃO Associa as cartas da estrutura total à mão do jogador!
     */

    public void AssociateCards(ArrayList<Cards> AllBaseCards, ArrayList<Cards> BaseCardsHand, int ID){
        /* TESTES */
        if(AllBaseCards == null || BaseCardsHand == null || ID<1){
            System.out.println("Erro ao carregar os parâmetros na função AssociateCards");
            return;
        }

        /* LÓGICA */
        int length = (ID-1)*6+6;
        for(int i = (ID-1)*6; i<length; i++){
            Cards aux = new Cards();
            aux = AllBaseCards.get(i); // Obtem a carta
            //AllBaseCards.remove(i); // Tiramos a carta da estrutura 
            BaseCardsHand.add(aux); // Adiciona a carta
        }
    }

    /******************************************
     **** >>>>>>> DESCARTAR CARTAS <<<<<<< ****
     ******************************************/
    /*
     * Jogador - jogador em causa
     */

/*     public void Discard(Player Jogador) {
       // TESTES 
        if(Jogador == null){
            System.out.println("Erro ao carregar os parâmetros na função Discard");
            return;
        }

        // LÓGICA 
        while (Jogador.HandCards.size() > 6) {
            int num = Jogador.HandCards.size() - 6;
            System.out.println("Please Choose" + num + "cards to Discard");
            // Como vai ser selecionada?

        }
        // While tiver mais que 6 cartas tem de descartar
        // As cartas descartadas passam para uma pilha de descarte
    } */

    /*****************************************
     **** >>>>>>> CANCELAR JOGADA <<<<<<< ****
     *****************************************/
    /*
     * FUNÇÃO - Faz o inverso da função PlayCard, anulando os seus efeitos ao nível lógico
     */

    public boolean CancelPlay(Map Mapa, Player PlayerHand, int posicao[], int ChoosenCard) {
        /* TESTES */
        if (PlayerHand == null || posicao == null || posicao[0] == -1) {
            System.out.println("Parâmetros inválidos na função CancelPlay...");
            return false;
        }

        /* CÓDIGO */
        int BTR=0, BTC=0; // BTR/C - Board Target Row/Column
        int STR, STC; // STR/C - Slot Target Row/Column
        if(posicao[0] <= 2){ // Cálculo da posição do Board BUG
            BTR = 0;
        } else BTR = 1;
        if(posicao[0] == 0 || posicao[0] == 3){
            BTC = 0;
        } else if(posicao[0] == 1 || posicao[0] == 4){
            BTC = 1;
        } else if(posicao[0] == 2 || posicao[0] == 5){
            BTC = 2;
        }
        STR = posicao[1];
        STC = posicao[2];
        System.out.println("Playcard values: "+BTR + " "+ BTC+ " "+ STR+ " "+ STC);
        System.out.println(Mapa.Tabuleiros[BTR][BTC].ID);
        if (Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].player == PlayerHand.CardsID) {
            // Atribui a carta
            Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].player = 0;
           // Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].Card = PlayerHand.HandCards.get(ChoosenCard);
        } else{
            System.out.println("Slot ocupado...");
            return false;
        }
        return true;
    }


    /**************************************
     **** >>>>>>> JOGAR CARTAS <<<<<<< ****
     **************************************/
    /*
     * Mapa - Mapa com a situação atual de jogo
     * PlayerHand - ID do jogador a quem a jogada é associada
     * posicao - array de 3 valores que identificam o tabuleiro e slots de destino
     * game - estrutura que dá handler do nível lógico do jogo
     * ChoosenCard - ID da carta escolhida (vai ver na estrutura game.Cartas que tem todas as cartas)
     * TODO - Este método tem de levar um UPGRADE para os espaços interiores
     * FUNÇÃO - Logicamente "linkar" uma carta jogada ao respetivo jogador e inserir na estrutura handler game
     */
    public static boolean PlayCard(Map Mapa, int PlayerHand, int posicao[], int ChoosenCard, Game game) {
        /* TESTES */
        if (posicao == null) {
            System.out.println("Parâmetros inválidos na função PlayCard...");
            return false;
        }

        /* CÓDIGO */
        int BTR=0, BTC=0; // BTR/C - Board Target Row/Column
        int STR, STC; // STR/C - Slot Target Row/Column
        if(posicao[0]<=2){ // Cálculo da posição do Board
            BTR = 0;
        } else BTR = 1;
        if(posicao[0] == 0 || posicao[0] == 3){
            BTC = 0;
        } else if(posicao[0] == 1 || posicao[0] == 4){
            BTC = 1;
        } else if(posicao[0] == 2 || posicao[0] == 5){
            BTC = 2;
        }
        STR = posicao[1];
        STC = posicao[2];
        //System.out.println("Playcard values: "+BTR + " "+ BTC+ " "+ STR+ " "+ STC);
        System.out.println(Mapa.Tabuleiros[BTR][BTC].ID);
        if (Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].player == 0) {
            // Atribui a carta
            Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].player = PlayerHand; // Atribui ao slot a carta do jogador
            if(ChoosenCard < 30){
                Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].Card = game.BaseCards.get(ChoosenCard-1);
                System.out.println("Carta jogada com o ID: " + game.BaseCards.get(ChoosenCard-1).ID);
                System.out.println("Play in: " + BTR + " " + BTC + " " + STR + " " + STC + " " + PlayerHand);   
            } else {
                Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].Card = game.CartasGUI.get(ChoosenCard-31); 
                System.out.println("Carta jogada com o ID: " + game.CartasGUI.get(ChoosenCard-31).ID);
                System.out.println("Play in: " + BTR + " " + BTC + " " + STR + " " + STC + " " + PlayerHand);   
            }
        } else{
            System.out.println("Slot ocupado...");
            return false;
        }
        return true;
    }

    /**************************************
     **** >>>>>>> ATRIBUIR CARTA <<<<<<< ****
     **************************************/
    /*
     * Jogador - jogador em questão (em cada cliente)
     * vencedor - verifica no game (handler) qual o ID do vencedor de cada tabuleiro, serve para compara com o ID do jogador
     * Tabuleiro - é a estrutura tabuleiro que está a ser resolvido
     * FUNÇÃO - Atribuir a carta ao vencedor do tabuleiro
     */
    public static void AddCardClient(Player Jogador, int vencedor, Boards Tabuleiro){
        /* TESTES */
        if(Jogador == null || vencedor == -1 || Tabuleiro == null){
            System.out.println("Erro ao carregar os parâmetros na função AddCard");
            return;
        } 

        /* ATRIBUIÇÃO */
        if(Tabuleiro.slots[0][0].player == 6 && (vencedor==Jogador.CardsID)) Jogador.HandCards.add(Tabuleiro.slots[0][0].Card);
        if(Tabuleiro.slots[0][1].player == 6 && (vencedor==Jogador.CardsID)) Jogador.HandCards.add(Tabuleiro.slots[0][1].Card);
        if(Tabuleiro.slots[1][0].player == 6 && (vencedor==Jogador.CardsID)) Jogador.HandCards.add(Tabuleiro.slots[1][0].Card);
        if(Tabuleiro.slots[1][1].player == 6 && (vencedor==Jogador.CardsID)) Jogador.HandCards.add(Tabuleiro.slots[1][1].Card);
    }

    /*****************************************
     **** >>>>>>> DESCARTAR CARTA <<<<<<< ****
     *****************************************/
    /*
     * Jogador - estrutura jogador do cliente
     * Carta - posição da carta que quer retirar
     * FUNÇÃO - Remove a carta da mão do jogador e passa-a para a sua pilha de descarte
     */
    public static void DiscardCards(Player Jogador, int Carta){
        /* TESTES */
        if(Jogador == null || (Carta > Jogador.HandCards.size()) || (Carta < 0) ){
            System.out.println("Parâmetros inválidos na função DiscardCards");
            return;
        } 

        /* LÓGICA */
        Jogador.PlayerDiscardedCards.add(Jogador.HandCards.get(Carta));
        Jogador.HandCards.remove(Carta);
    }



}
