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

    MissionCards GameMissionCards;
    Cards Cartas[];
    int ListOfMissions[]; // estrutura para guardar os mapas que vão ser usados na ronda
    int NumOfMissions;

    /**********************************************
     **** >>>>>>> DADOS PARA DAR BUILD <<<<<<< ****
     **********************************************/

    String Tabs[] = { "Tab0.png", "Tab1.png", "Tab2.png", "Tab3.png", "Tab4.png", "Tab5.png", "Tab6.png", "Tab7.png",
            "Tab8.png", "Tab9.png", "Tab10.png", "Tab11.png" };

    int CardPowers[] = { 2, 3, 1, 4, 4, 1, 2, 3, 2,
            1, 4, 2, 3, 5, 4, 1, 2, 1,
            3, 1, 0, 2, 0, 2, 1, 3, 2 }; // Poderes das cartas do jogo base

    int CardPV[] = { 2, 3, 4, 1, 1, 3, 4, 2, 3,
            4, 2, 3, 2, 1, 1, 4, 3, 3,
            1, 1, 2, 3, 2, 2, 3, 4, 1 }; // PV das cartas

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
     */
    public void Initialize() { // série de ações que se executam para inicializar o jogo
        Cards.BuildCards(Cartas, CardPowers, CardPV, CardNations, CardImages); // Construir as cartas (conteúdos do
                                                                               // jogo)

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
        GameMissionCards.DrawMissionCards(NumOfMissions, ListOfMissions);
        GameMissionCards.DisplayMissions(ListOfMissions, null);// Depois temos de meter aqui as cartas
        AdvanceRound(); // No início do jogo avançamos da ronda 0 (fictícia) para a 1
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

    public void AdvanceRound() {
        Solve();
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
    public void Solve() {
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
