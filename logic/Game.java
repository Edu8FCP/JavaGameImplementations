// import java.util.ArrayList;

public class Game {
    /*********************************************
     **** >>>>>>> ATRIBUTOS DA CLASSE <<<<<<< ****
     *********************************************/
    int Turn; // Conta em que turno vamos
    int Round = 0; // 1 a 4
    int FirstPlayer; // Indica quem vai ser o primeiro jogador
    int NumOfPlayers; // Guardar as definições de quantos players vão jogar
    int NumOfTurns; // Para calcular quantos turnos vão existir por ronda

    /**********************************************************
     **** >>>>>>> ELEMENTOS PARA COMPOR AS FUNÇÕES <<<<<<< ****
     **********************************************************/

    /****** MAPA ******/
    Map GameMap; // Vamos criar só um objeto do tipo Map e depois vamos usar os seus métodos
    // sempre que houver uma nova ronda
    int MapBoards[];

    /****** TABULEIROS ******/
    Boards[] GameBoards = new Boards[12]; // Existem 12 tabuleiros
    int BoardsList[];

    MissionCards[] GameMissionCards = new MissionCards[10]; // TODO - Substituir o 10 por quantas são
    Cards Cartas[];
    int ListOfMissions[]; // estrutura para guardar os mapas que vão ser usados na ronda
    int NumOfMissions;

    /**********************************************
     **** >>>>>>> DADOS PARA DAR BUILD-BASE <<<<<<< ****
     **********************************************/

    String CardImagesP1[] = { "Card1.png", "Card2.png", "Card3.png", "Card4.png", "Card5.png", "Card6.png" };
    String CardImagesP2[] = { "Card7.png", "Card8.png", "Card9.png", "Card10.png", "Card11.png", "Card12.png" };
    String CardImagesP3[] = { "Card13.png", "Card14.png", "Card15.png", "Card16.png", "Card17.png", "Card18.png" };
    String CardImagesP4[] = { "Card19.png", "Card20.png", "Card21.png", "Card22.png", "Card23.png", "Card24.png" };
    String CardImagesP5[] = { "Card25.png", "Card26.png", "Card27.png", "Card28.png", "Card29.png", "Card30.png" };
    int CardPowersP[] = { 1, 1, 1, 1, 1, 1 };
    int CardPVP[] = { 1, 1, 1, 1, 1, 1 };
    String CardNationsP[] = { "PT", "PT", "PT", "PT", "PT", "PT" };
    int CardAbilitiesP[][] = { { 2, 0, 0 }, { 4, 0, 0 }, { 3, 0, 0 }, { 1, 0, 0 }, { 5, 0, 0 }, { 2, 0, 0 } };

    /**********************************************
     **** >>>>>>> DADOS PARA DAR BUILD <<<<<<< ****
     **********************************************/

    String TabsImages[] = { "Tab0.png", "Tab1.png", "Tab2.png", "Tab3.png", "Tab4.png", "Tab5.png", "Tab6.png",
            "Tab7.png",
            "Tab8.png", "Tab9.png", "Tab10.png", "Tab11.png" };

    int CardPowers[] = { 2, 3, 1, 4, 4, 1, 2, 3, 2,
            1, 4, 2, 3, 5, 4, 1, 2, 1,
            3, 1, 0, 2, 0, 2, 1, 3, 2 }; // Poderes das cartas do jogo base

    int CardPV[] = { 2, 3, 4, 1, 1, 3, 4, 2, 3,
            4, 2, 3, 2, 1, 1, 4, 3, 3,
            1, 1, 2, 3, 2, 2, 3, 4, 1 }; // PV das cartas

    String MissionCardsImages[] = { "Card100.png", "Card101.png" }; // TODO - Completar

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

    /*********************************
     **** >>>>>>> MÉTODOS <<<<<<< ****
     *********************************/

    /************************************
     **** >>>>>>> CONSTRUTOR <<<<<<< ****
     ************************************/
    public Game(int NrPlayers) {
        NumOfPlayers = NrPlayers; // inicializa o jogo para NrPlayers jogadores
        Round = 1; // inicializa a ronda
        FirstPlayer = (int) (Math.random() * NumOfPlayers); // sorteia o jogador inicial
        NumOfTurns = NrPlayers * (8 - NrPlayers); // Nr os turns for round
    }

    /**************************************************
     **** >>>>>>> CONSTRUÇÃO DOS CONTEÚDOS <<<<<<< ****
     **************************************************/
    /*
     * BuildCards
     * BuildMissionCards
     * BuildBoards
     * Initialize Players
     */
    public void Initialize(Game Jogo, Player[] Jogadores) { // série de ações que se executam para inicializar o jogo
        Cards.BuildCards(Cartas, CardPowers, CardPV, CardNations, CardImages); // Construir as cartas (conteúdos do
                                                                               // jogo)
        Boards.BuildBoards(GameBoards, TabsImages);
        MissionCards.BuildMissionCards(GameMissionCards, MissionCardsImages);
        if (Jogo.NumOfPlayers == 2) {
            Jogadores[0] = new Player("Edu", CardPowersP, CardPVP, CardNationsP, CardImagesP1);
            Jogadores[1] = new Player("Edu2", CardPowersP, CardPVP, CardNationsP, CardImagesP2);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP1);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP2);
        }
        if (Jogo.NumOfPlayers == 3) {
            Jogadores[0] = new Player("Edu", CardPowersP, CardPVP, CardNationsP, CardImagesP1);
            Jogadores[1] = new Player("Edu2", CardPowersP, CardPVP, CardNationsP, CardImagesP2);
            Jogadores[2] = new Player("Edu3", CardPowersP, CardPVP, CardNationsP, CardImagesP3);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP1);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP2);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP3);
        }
        if (Jogo.NumOfPlayers == 4) {
            Jogadores[0] = new Player("Edu", CardPowersP, CardPVP, CardNationsP, CardImagesP1);
            Jogadores[1] = new Player("Edu2", CardPowersP, CardPVP, CardNationsP, CardImagesP2);
            Jogadores[2] = new Player("Edu3", CardPowersP, CardPVP, CardNationsP, CardImagesP3);
            Jogadores[3] = new Player("Edu4", CardPowersP, CardPVP, CardNationsP, CardImagesP4);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP1);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP2);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP3);
            // Cards.BuildBaseCards(null, CardPowersP, CardPVP, CardNationsP, CardImagesP4);
        }

    }

    /********************************************************
     **** >>>>>>> AÇÕES DE INICIALIZAÇÃO DO JOGO <<<<<<< ****
     ********************************************************/
    /*
     * DrawMissionCards - Sortear e Distribuir
     * AdvanceRound - Para dar início ao jogo e desencadear as ações de começo de
     * turno (válidas também para o primeiro turno)
     */
    public void StartGame() {
        // GameMissionCards.DrawMissionCards(NumOfMissions, ListOfMissions);
        // GameMissionCards.DisplayMissions(ListOfMissions, null);// Depois temos de
        // meter aqui as cartas
        Turn = 0; // Turno do jogo volta a 0
        Round++; // Avança a ronda do jogo
        GameMap.RandBoards(MapBoards); // sorteia mapas
        GameMap.AssembleMap(MapBoards, GameMap, GameBoards);
        // Dá o token ao jogador que tem o direito a jogar uma carta
    }

    /*****************************************
     **** >>>>>>> AVANÇAR O TURNO <<<<<<< ****
     *****************************************/
    /*
     * Tem de ter uma função para enviar a trama da jogada para os outros jogadores
     * Aumentar Turno
     * Passar o "token" ao próximo jogador
     * 
     */
    public void AdvanceTurn() {
        // Passa o Token ao jogador seguinte
    }

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

    public void AdvanceRound(Map Mapa) {
        Solve(Mapa);
        Turn = 0; // Turno do jogo volta a 0
        Round++; // Avança a ronda do jogo
        GameMap.RandBoards(MapBoards); // sorteia mapas
        GameMap.AssembleMap(MapBoards, GameMap, GameBoards);
    }

    /***********************************************
     **** >>>>>>> RESOLVER MAPA DE JOGO <<<<<<< ****
     ***********************************************/
    /*
     * PERCORRE A MATRIZ DE TABULEIROS
     * PERMITE QUE SE USEM AS HABILIDADES (DOS TABULEIROS SE APLICÁVEL)
     * ATRIBUI OS PRÉMIOS
     */
    public void Solve(Map Mapa) {
        if (Mapa == null) {
            System.out.println("Erro ao carregar o jogo...\n");
        }
        int vencedor;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                // retorna o nr do jogador vencedor
                vencedor = Boards.SolveBoard(Mapa.Tabuleiros[i][j]);
                if (vencedor == 0) {

                } else {
                    // Adiciona carta do slot prize à player hand
                }
            }
        }
        // Este método só executa quando o turno se esgotar
    }

    /*****************************************************
     **** >>>>>>> CONTAGEM DA PONTUAÇÃO FINAL <<<<<<< ****
     *****************************************************/
    /*
     * Executado uma única vez no final do jogo
     * Realiza a contagem final:
     * Cartas Descartadas + PV das 6 HandCards + Atribui pontuação das missões
     */
    public void CountPoints() {
        // No final do jogo calcula o nr de pontos de cada jogador
    }

}
