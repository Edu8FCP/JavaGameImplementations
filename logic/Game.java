import java.util.ArrayList;

public class Game {
    int Turn; // Conta em que turno vamos
    int Round; // 1 a 4
    int FirstPlayer; // Indica quem vai ser o primeiro jogador
    int NumOfPlayers; // Guardar as definições de quantos players vão jogar
    int NumOfTurns; // Para calcular quantos turnos vão existir por ronda
    Map GameMap; // Vamos criar só um objeto do tipo Map e depois vamos usar os seus métodos
    // sempre que houver uma nova ronda
    MissionCards GameMissionCards;
    int MapBoards[], ListOfMissions[]; // estrutura para guardar os mapas que vão ser usados na ronda
    int NumOfMissions;

    public Game(int NrPlayers) {
        NumOfPlayers = NrPlayers; // inicializa o jogo para NrPlayers jogadores
        Round = 1; // inicializa a ronda
        FirstPlayer = (int) (Math.random() * NumOfPlayers); // sorteia o jogador inicial
        NumOfTurns = NrPlayers * (8 - NrPlayers); // Nr os turns for round
    }

    private static void BuildCards() {
        ArrayList<Cards> Cards = new ArrayList<Cards>();
        // Cards.add(Power:2,Nationality:'Portugal');
    }

    private static void BuildMissionCards() {
        ArrayList<MissionCards> Cards = new ArrayList<MissionCards>();
        // Cards.add(Power:2,Nationality:'Portugal');
    }

    private static void BuildBoards() {
        ArrayList<Boards> Boards = new ArrayList<Boards>();
        // Cards.add(Power:2,Nationality:'Portugal');
    }

    public void Initialize() { // série de ações que se executam para inicializar o jogo
        BuildCards(); // Construir as cartas (conteúdos do jogo)
        BuildMissionCards();
        BuildBoards();
        GameMissionCards.DrawMissionCards(NumOfMissions, ListOfMissions);
        GameMissionCards.DisplayMissions(ListOfMissions, null);// Depois temos de meter aqui as cartas
    }

    public void StartGame() {
        // Dá o token ao jogador que tem o direito a jogar uma carta
    }

    public void AdvanceTurn() {
        // Passa o Token ao jogador seguinte
    }

    public void AdvanceRound() {
        Turn = 0; // Turno do jogo volta a 0
        Round++; // Avança a ronda do jogo
        GameMap.RandBoards(MapBoards); // sorteia mapas
        GameMap.AssembleMap(MapBoards, null); // monta mapas
    }

    public void Solve() {
        // Este método só executa quando o turno se esgotar
    }

    public void CountPoints() {
        // No final do jogo calcula o nr de pontos de cada jogador
    }

}
