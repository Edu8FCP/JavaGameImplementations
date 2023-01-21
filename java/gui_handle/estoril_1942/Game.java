package gui_handle.estoril_1942;// import java.util.ArrayList;

import java.util.ArrayList;

public class Game {
    /*********************************************
     **** >>>>>>> ATRIBUTOS DA CLASSE <<<<<<< ****
     *********************************************/
    int Turn; // Conta em que turno vamos
    int Round = 0; // 1 a 4
    int FirstPlayer; // Indica quem vai ser o primeiro jogador
    public int NumOfPlayers; // Guardar as definições de quantos players vão jogar
    int NumOfTurns; // Para calcular quantos turnos vão existir por ronda
    int Points;

    /**********************************************************
     **** >>>>>>> ELEMENTOS PARA COMPOR AS FUNÇÕES <<<<<<< ****
     **********************************************************/

    /****** MAPA ******/
    Map GameMap = new Map(); // Vamos criar só um objeto do tipo Map e depois vamos usar os seus métodos
    // sempre que houver uma nova ronda

    int MapBoards[] = new int[6]; // Lista com os tabuleiros que constituem o Mapa de jogo

    /****** TABULEIROS ******/
    Boards[] GameBoards = new Boards[12]; // Existem 12 tabuleiros
   // int BoardsList[] = new int[6]; // Quais os 6 sorteados FIX - Comentei pq acho que era useless

    MissionCards[] GameMissionCards = new MissionCards[14];
    ArrayList<Cards> Cartas = new ArrayList<Cards>(); // Carta
    ArrayList<Cards> CartasGUI = new ArrayList<Cards>();
    ArrayList<Cards> BaseCards = new ArrayList<Cards>(); // Cartas Base

    int ListOfMissions[]; // estrutura para guardar as missões que vão
    int ListOfPrizes[];
    int NumOfMissions; // Quantas missões querem

    /**********************************************
     **** >>>>>>> DADOS PARA DAR BUILD-BASE <<<<<<< ****
     **********************************************/

    String BaseCardImages[] = { "Card1.png", "Card2.png", "Card3.png", "Card4.png", "Card5.png", "Card6.png", 
            "Card7.png", "Card8.png", "Card9.png", "Card10.png", "Card11.png", "Card12.png",
            "Card13.png", "Card14.png", "Card15.png", "Card16.png", "Card17.png", "Card18.png",
            "Card19.png", "Card20.png", "Card21.png", "Card22.png", "Card23.png", "Card24.png",
            "Card25.png", "Card26.png", "Card27.png", "Card28.png", "Card29.png", "Card30.png"};
        
    int CardPowersP[] = { 0, 1, 0, 1, 1, 2, 0, 1, 0, 1, 1, 2, 0, 1, 0, 1, 1, 2,
        0, 1, 0, 1, 1, 2, 0, 1, 0, 1, 1, 2, 0, 1, 0, 1, 1, 2 };
    
    int CardPVP[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
    
    String CardNationsP[] = { "US", "UK", "PT", "PT", "DE", "PT", "US", "UK", "PT", "PT", "DE", "PT", "US", "UK", "PT", "PT", "DE", "PT",
        "US", "UK", "PT", "PT", "DE", "PT", "US", "UK", "PT", "PT", "DE", "PT", "US", "UK", "PT", "PT", "DE", "PT" };
    
    int CardAbilitiesP[][] = { { 2, 0, 0 }, { 1, 0, 0 }, { 3, 0, 0 }, { 4, 0, 0 }, { 5, 0, 0 }, { 0, 0, 0 },
        { 2, 0, 0 }, { 1, 0, 0 }, { 3, 0, 0 }, { 4, 0, 0 }, { 5, 0, 0 }, { 0, 0, 0 },
        { 2, 0, 0 }, { 1, 0, 0 }, { 3, 0, 0 }, { 4, 0, 0 }, { 5, 0, 0 }, { 0, 0, 0 },
        { 2, 0, 0 }, { 1, 0, 0 }, { 3, 0, 0 }, { 4, 0, 0 }, { 5, 0, 0 }, { 0, 0, 0 },
        { 2, 0, 0 }, { 1, 0, 0 }, { 3, 0, 0 }, { 4, 0, 0 }, { 5, 0, 0 }, { 0, 0, 0 }, };
        
    boolean genderP[] = {true, true, false, false, false, false, true, true, false, false, false, false, true, true, false, false, false, false,
        true, true, false, false, false, false, true, true, false, false, false, false, true, true, false, false, false, false};

    /**********************************************
     **** >>>>>>> DADOS PARA DAR BUILD <<<<<<< ****
     **********************************************/

    String TabsImages[] = { "Tab0.png", "Tab1.png", "Tab2.png", "Tab3.png", "Tab4.png", "Tab5.png", "Tab6.png",
            "Tab7.png",
            "Tab8.png", "Tab9.png", "Tab10.png", "Tab11.png" };

    /********************************************
     **** >>>>>>> BUILD CARTAS INFOS <<<<<<< ****
     ********************************************/

    int CardPowers[] = { 2, 3, 1, 4, 4, 1, 2, 3, 2,
            1, 4, 2, 3, 5, 4, 1, 2, 1,
            3, 1, 0, 2, 0, 2, 1, 3, 2 }; // Poderes das cartas do jogo base

    int CardPV[] = { 2, 3, 4, 1, 1, 3, 4, 2, 3,
            4, 2, 3, 2, 1, 1, 4, 3, 3,
            1, 1, 2, 3, 2, 2, 3, 4, 1 }; // PV das cartas

    String MissionCardsImages[] = { "Mission1.png", "Mission2.png", "Mission3.png", "Mission4.png","Mission5.png",
        "Mission6.png", "Mission7.png", "Mission8.png", "Mission9.png", "Mission10.png",
        "Mission11.png", "Mission12.png","Mission13.png", "Mission14.png", }; 

    String CardImages[] = { "Card31.png", "Card32.png", "Card33.png", "Card34.png", "Card35.png", "Card36.png",
            "Card37.png",
            "Card38.png", "Card39.png",
            "Card40.png", "Card41.png", "Card42.png", "Card43.png", "Card44.png", "Card45.png", "Card46.png",
            "Card47.png", "Card48.png",
            "Card49.png", "Card50.png", "Card51.png", "Card52.png", "Card53.png", "Card54.png", "Card55.png",
            "Card56.png", "Card57.png" }; // Imagem das cartas - Jogo BASE

    String CardNations[] = { "PT", "PT", "PT", "PT", "PT", "US", "US", "US", "US",
            "UK", "UK", "UK", "UK", "IT", "DE", "DE", "DE", "DE",
            "YU", "SU", "SU", "ES", "FR", "FR", "HU", "ES", "PT" }; // Nacionalidade das cartas - JOGO BASE

    // 1 - Sed, 2 - Gun, 3 - Flag, 4 - Shield, 5 - Conspiricy
    int CardAbilities[][] = { { 2, 0, 0 }, { 4, 0, 0 }, { 3, 0, 0 }, { 1, 0, 0 }, { 5, 0, 0 }, { 2, 0, 0 }, { 4, 0, 0 },
            { 3, 0, 0 }, { 1, 0, 0 },
            { 5, 0, 0 }, { 4, 0, 0 }, { 3, 0, 0 }, { 1, 0, 0 }, { 0, 0, 0 }, { 3, 0, 0 }, { 1, 0, 0 }, { 5, 0, 0 },
            { 2, 0, 0 },
            { 5, 5, 0 }, { 2, 2, 0 }, { 2, 4, 0 }, { 4, 4, 0 }, { 2, 5, 0 }, { 1, 5, 0 }, { 1, 1, 0 }, { 0, 0, 0 },
            { 3, 3, 0 } }; // Habilidades das cartas do jogo BASE
    
    boolean gender[] = {false, false, false, true, false, false, false, true, true,
            true, false, false, false, false, false, true, false, true,
            false, false, false, false, false, false, true, false, false}; 

    /*********************************
     **** >>>>>>> MÉTODOS <<<<<<< ****
     *********************************/

    /************************************
     **** >>>>>>> CONSTRUTOR <<<<<<< ****
     ************************************/
    public Game(int NrPlayers) {
        NumOfPlayers = NrPlayers; // inicializa o jogo para NrPlayers jogadores
        Round = 0; // inicializa a ronda
        FirstPlayer = 1; //(int) (Math.random() * NumOfPlayers); // sorteia o jogador inicial
        NumOfTurns = NrPlayers * (8 - NrPlayers)+1; // Nr os turns for round
        //Points = new int();
        Turn = 0;
    }

    /**************************************************
     **** >>>>>>> CONSTRUÇÃO DOS CONTEÚDOS <<<<<<< ****
     **************************************************/
    /*
     * BuildCards
     * BuildMissionCards
     * BuildBoards
     * Initialize Players
     * FUNÇÃO - Esta função vai ser substituída pelo InitializeClient
     */
   /*  public void Initialize(Game Jogo, Player[] Jogadores) { // série de ações que se executam para inicializar o jogo
        Cards.BuildCardsv2(Cartas, CardPowers, CardPV, CardNations, CardImages, CardAbilities, gender); // Construir as cartas (conteúdos do
                                                                               // jogo)
        Boards.BuildBoards(GameBoards, TabsImages);
        Cards.BuildBaseCards(BaseCards, CardPowersP, CardPVP, CardNationsP, BaseCardImages, CardAbilitiesP, genderP);
        MissionCards.BuildMissionCards(GameMissionCards, MissionCardsImages);
        if (Jogo.NumOfPlayers == 2) {
            Jogadores[0] = new Player("Edu", BaseCards, 1);
            Jogadores[1] = new Player("Edu2",BaseCards, 2);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP1);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP2);
        }
        if (Jogo.NumOfPlayers == 3) {
            Jogadores[0] = new Player("Edu", BaseCards, 1);
            Jogadores[1] = new Player("Edu2", BaseCards, 2);
            Jogadores[2] = new Player("Edu3", BaseCards, 3);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP1);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP2);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP3);
        }
        if (Jogo.NumOfPlayers == 4) {
            Jogadores[0] = new Player("Edu", BaseCards, 1);
            Jogadores[1] = new Player("Edu2", BaseCards, 2);
            Jogadores[2] = new Player("Edu3", BaseCards, 3);
            Jogadores[3] = new Player("Edu4", BaseCards, 4);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP1);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP2);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP3);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP4);
        }

    } */

    /************************************************
     **** >>>>>>> INITIALIZE GAME CLIENT <<<<<<< ****
     ************************************************/
    /*
     * Inicializa os componentes do jogo do cliente
     * Jogo - handler do jogo
     * Name - nome do jogador
     * ID - Id do jogador
     * FUNÇÃO - Criar em memória todos os componentes que precisam de ser criados, nomeadamente: Missões, Tabuleiros, Cartas Base e Cartas (x2)
     */
    public void InitializeGameClient(Game Jogo, String Name, int ID) { // série de ações que se executam para inicializar o jogo
        /* TESTES */
        if(Jogo == null || Name == null || ID < 1){
            System.out.println("Falha nos parâmetros da função InitializeGameClient");
            return;
        }

        /* CÓDIGO */
        Cards.BuildCardsv2(Cartas, CardPowers, CardPV, CardNations, CardImages, CardAbilities, gender); 
        // Construir as cartas (conteúdos do jogo)
        Cards.BuildCardsv2(CartasGUI, CardPowers, CardPV, CardNations, CardImages, CardAbilities, gender); 
        // Construir as cartas (conteúdos do jogo)                                                                       
        Boards.BuildBoards(GameBoards, TabsImages);
        Cards.BuildBaseCards(BaseCards, CardPowersP, CardPVP, CardNationsP, BaseCardImages, CardAbilitiesP, genderP);
        MissionCards.BuildMissionCards(GameMissionCards, MissionCardsImages);
       // Jogador = new Player(Name, BaseCards, ID);                                                                
    }


    /********************************************************
     **** >>>>>>> AÇÕES DE INICIALIZAÇÃO DO JOGO <<<<<<< ****
     ********************************************************/
    /*
     * DrawMissionCards - Sortear e Distribuir
     * AdvanceRound - Para dar início ao jogo e desencadear as ações de começo de
     * turno (válidas também para o primeiro turno)
     */
/*     public void StartGame(Game game) {
        // GameMissionCards.DrawMissionCards(NumOfMissions, ListOfMissions);
        // GameMissionCards.DisplayMissions(ListOfMissions, null);// Depois temos de
        // meter aqui as cartas
        Turn = 1; // Turno do jogo volta a 0
        Round++; // Avança a ronda do jogo
        GameMap.RandBoards(game.MapBoards); // sorteia mapas
        //GameMap.RandOrientations(game.GameBoards, game.MapBoards, game.Cartas, game);
        GameMap.AssembleMap(game.MapBoards, game.GameMap, game.GameBoards);
        // Dá o token ao jogador que tem o direito a jogar uma carta
    } */

    /*****************************************
     **** >>>>>>> AVANÇAR O TURNO <<<<<<< ****
     *****************************************/
    /*
     * Tem de ter uma função para enviar a trama da jogada para os outros jogadores
     * Aumentar Turno
     * Passar o "token" ao próximo jogador
     * 
     */
/*     public void AdvanceTurn(Game game) {
        // Passa o Token ao jogador seguinte
        if(game == null) return;

        game.Turn++;
    } */

    /***************************************
     **** >>>>>>> AVANÇAR RONDA <<<<<<< ****
     ***************************************/
    /*
     * Resolve os tabuleiros
     * Obriga os jogadores a descartarem cartas
     * Volta o turno a 0
     * Sorteia Tabuleiros
     * Constrói mapa de jogo
     * Sorteia prémios
     * Dispõe prémios
     * Atribui o token ao próximo jogador a começa
     * --->>> TEM DE SER FEITO NO SERVIDOR PARA O SORTEIO SER IGUAL PARA TODOS
     */

/*     public static void AdvanceRound(Game game, Player[] Jogadores) {
        Solve(game.GameMap, Jogadores);
        System.out.println("Cartas do jogador 1: " + Jogadores[0].HandCards.size());
        System.out.println("Cartas do jogador 2: " + Jogadores[1].HandCards.size());
        game.Turn = 1; // Turno do jogo volta a 0
        Map.ClearMap(game);
        game.Round++; // Avança a ronda do jogo
        if(game.Round < 5){
            Map.RandBoards(game.MapBoards); // sorteia mapas
          //  Map.RandOrientations(game.GameBoards, game.MapBoards, game.Cartas, game);
            Map.AssembleMap(game.MapBoards, game.GameMap, game.GameBoards);
        }
    } */

    /**********************************************
     **** >>>>>>> ADVANCE ROUND CLIENT <<<<<<< ****
     **********************************************/
    /*
     * game - estrutura onde se dá handle do jogo
     * Jogador - Jogador em questão
     * FUNÇÃO - Resolve a situação de jogo, reinicialize o turno, aumenta a ronda e limpa o Mapa
     */

    public static void AdvanceRoundClient(Game game, Player Jogador) {
        Map.RemovePrizesFromList(game, game.ListOfPrizes);
        System.out.println("Cartas do jogador " + Jogador.CardsID +": " + Jogador.HandCards.size());
        game.Turn = 0; // Turno do jogo volta a 0
        game.Round++;
        Map.ClearMap(game);
/*         game.Round++; // Avança a ronda do jogo
        if(game.Round < 5){
            Map.RandBoards(game.MapBoards); // sorteia mapas
          //  Map.RandOrientations(game.GameBoards, game.MapBoards, game.Cartas, game);
            Map.AssembleMap(game.MapBoards, game.GameMap, game.GameBoards);
        } */
    }

    /***********************************************
     **** >>>>>>> RESOLVER MAPA DE JOGO <<<<<<< ****
     ***********************************************/
    /*
     * PERCORRE A MATRIZ DE TABULEIROS
     * PERMITE QUE SE USEM AS HABILIDADES (DOS TABULEIROS SE APLICÁVEL)
     * ATRIBUI OS PRÉMIOS
     * 
     * FUNÇÃO - Ver quem ganhou cada tabuleiro da situação de jogo
     * No caso de o vencedor corresponder ao cliente em questão serve também para atribuir a carta
     */

    public static void SolveClient(Map Mapa, Player Jogador) {
        /* TESTES */
        if (Mapa == null || Mapa.Tabuleiros == null) {
            System.out.println("Erro ao carregar o Mapa de jogo...\n");
            return ;
        }

        /* CÓDIGO */
        int vencedor;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println("Inside second for: "+ Mapa.Tabuleiros[i][j].ID);
                // retorna o nr do jogador vencedor
                if(Mapa.Tabuleiros[i][j] == null){
                    System.out.println("Erro ao carregar o Mapa de jogo");
                    return ;
                } 
                vencedor = Boards.SolveBoard(Mapa.Tabuleiros[i][j]);
                if (vencedor == -1) {
                    System.out.println("Ninguém ganhou este tabuleiro " + Mapa.Tabuleiros[i][j].ID);
                    //return;
                } else {
                    System.out.println("O jogador " + vencedor + " ganhou o tabuleiro " + Mapa.Tabuleiros[i][j].ID);
                    Player.AddCardClient(Jogador, vencedor, Mapa.Tabuleiros[i][j]); // ATRIBUI CARTA
                    // Adiciona carta do slot prize à player hand
                }
            }
        }
        // Este método só executa quando o turno se esgotar
    } 

/*     public static void Solve(Map Mapa, Player[] Jogadores) {
        // TESTES 
        if (Mapa == null || Mapa.Tabuleiros == null) {
            System.out.println("Erro ao carregar o Mapa de jogo...\n");
            return ;
        }

        // CÓDIGO 
        int vencedor;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                // retorna o nr do jogador vencedor
                if(Mapa.Tabuleiros[i][j] == null) System.out.println("Erro ao carregar o Mapa de jogo");
                vencedor = Boards.SolveBoard(Mapa.Tabuleiros[i][j]);
                if (vencedor == -1) {
                    System.out.println("Ninguém ganhou este tabuleiro " + Mapa.Tabuleiros[i][j].ID);
                } else {
                    System.out.println("O jogador " + vencedor + " ganhou o tabuleiro " + Mapa.Tabuleiros[i][j].ID);
                    //Player.AddCard(Jogadores, vencedor, Mapa.Tabuleiros[i][j]); // ATRIBUI CARTA
                    // Adiciona carta do slot prize à player hand
                }
            }
        }
        // Este método só executa quando o turno se esgotar
    } */


    /*****************************************************
     **** >>>>>>> CONTAGEM DA PONTUAÇÃO FINAL <<<<<<< ****
     *****************************************************/
    /*
     * Executado uma única vez no final do jogo
     * Realiza a contagem final:
     * Cartas Descartadas + PV das 6 HandCards + Atribui pontuação das missões (Pontos das missões vão ser atribuídos no servidor)
     * 
     * FUNÇÃO - Conta os pontos do jogador (2/3 dos parâmetros que dão pontos)
     * RETORNO - Pontuação Total
     */
    public int CountPointsClient(Player Jogador, Game game) {
        // No final do jogo calcula o nr de pontos de cada jogador
        /* TESTES */
        if(Jogador == null || game == null){
            System.out.println("Erro ao carregar os parâmetros da função CountPoints");
            return -1;
        }

        /* CÓDIGO */
        Points = 0;
        for(int j = 0; j<6; j++){
            Points += Jogador.HandCards.get(j).PV; // Soma os pontos de vitória de cada carta~
            // que ainda está na mão
        }
        Points += Jogador.PlayerDiscardedCards.size();

        return Points;
    }

    /**********************************************************
     **** >>>>>>> CONTAGEM DA SITUAÇÃO DAS MISSÕES <<<<<<< ****
     **********************************************************/
    /*
     * Recebe a estrutura do Jogador Cliente
     * Recebe a estrutura game que é o handler do jogo
     * dados - array onde vai preencher a situação das missões de jogo do Cliente
     * FUNÇÃO - Ver a mão do jogador e perceber o que ele tem em relação às missões
     */

    public void CountMissionSituation(Player Jogador, Game game, int[] dados) {
        // No final do jogo calcula o nr de pontos de cada jogador
        /* TESTES */
        if(Jogador == null || game == null){
            System.out.println("Erro ao carregar os parâmetros da função CountPoints");
            return;
        }

        MissionCards.SolveMissionCards(GameMissionCards, game.ListOfMissions, dados, Jogador);

        return;
    }

    /*************************************************
     **** >>>>>>> FINAL SITUATION OF GAME <<<<<<< ****
     *************************************************/
    /*
     * Recebe a estrutura do Jogador Cliente
     * Recebe a estrutura game que é o handler do jogo
     * data - Recebe o array que vai ser preenchido para ser enviado ao servidor
     * FUNÇÃO - Calcula a pontuação intermédia e a situação do jogador em relação às missões
     */

     public void FinalSituation(Player Jogador, Game game, int[] data) {
        // No final do jogo calcula o nr de pontos de cada jogador
        /* TESTES */
        if(Jogador == null || game == null){
            System.out.println("Erro ao carregar os parâmetros da função CountPoints");
            return;
        }  
        int[] dados = new int[4];
        System.out.println(Jogador.CardsID);
        data[0] = Jogador.CardsID;
        System.out.println(("IMPORTANTE :  "+ data[0]));
        data[1] = CountPointsClient(Jogador, game);
        CountMissionSituation(Jogador, game, dados);
        data[2] = dados[0];
        data[3] = dados[1];
        data[4] = dados[2];
        data[5] = dados[3];
        System.out.println(("IMPORTANTE :  "+ data[0]));
        System.out.println(("IMPORTANTE :  "+ data[1]));
        System.out.println(("IMPORTANTE :  "+ data[2]));
        System.out.println(("IMPORTANTE :  "+ data[3]));

        return;
    }

        /*************************************************
     **** >>>>>>> FINAL SITUATION OF GAME <<<<<<< ****
     *************************************************/
    /*
     * Recebe a estrutura com os dados de todos jogador (mis1-mis2-mis3-mis4)
     * Recebe a quantidade de jogadores
     * Recece uma estrutura para guardar só os pontos
     * FUNÇÃO - Atruibui a pontuação das missões e prepara o vetor das pontuações finais para ser enviado
     * RETORNA - -1 em caso de erro, 0 se correr bem
     */

     public static void FinalSituationServer(int[][] data, int players, int[] DataToBeSent) {
        // No final do jogo calcula o nr de pontos de cada jogador
        /* TESTES */
        if(data == null || players < 2){
            System.out.println("Erro ao carregar os parâmetros da função CountPoints");
            return;
        }  
        int vencedor;
        int aux[] = new int[players];
        for(int j = 0; j <4; j++){ // Faz as 6 missões
            for(int i = 0; i < players; i++){
                aux[i] = data[i][j];
                System.out.println("Valor da missao " + j + " :" + aux[i]);
            }
            vencedor = Boards.max(aux);
            if(vencedor != 0 && vencedor != -1){
                // para corrigir para a ordem do vetor (jogador 1 = pos 0)
                DataToBeSent[vencedor-1] += 6; // Soma os 6 pontos da missão
            } 
        }
        for(int k = 0; k<players; k++){
            System.out.println("Jogador " + (k+1) + " fez um total de " + DataToBeSent[k] + " pontos. :)");
        }



    }

    

/*
    public void InitializeGameClient(Game Jogo, String Name, int ID) { // série de ações que se executam para inicializar o jogo
        // TESTES
        if(Jogo == null || Name == null || ID < 1){
            System.out.println("Falha nos parâmetros da função InitializeGameClient");
            return;
        }
*/
        /* CÓDIGO */
    /*
        Cards.BuildCardsv2(Cartas, CardPowers, CardPV, CardNations, CardImages, CardAbilities, gender); // Construir as cartas (conteúdos do
        // jogo)
        Boards.BuildBoards(GameBoards, TabsImages);
        Cards.BuildBaseCards(BaseCards, CardPowersP, CardPVP, CardNationsP, BaseCardImages, CardAbilitiesP, genderP);
        MissionCards.BuildMissionCards(GameMissionCards, MissionCardsImages);
        // Jogador = new Player(Name, BaseCards, ID);
    }
    */
}