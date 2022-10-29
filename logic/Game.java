import java.util.ArrayList;

public class Game {
    int turn; // 2 a nr de jogadores
    int Round; // 1 a 4
    int FirstPlayer;
    int NumOfPlayers;
    int NumOfTurns;

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

    public void Initialize() { // série de ações que se executam para inicializar o jogo
        BuildCards();
    }

    public void StartGame() {
        // Dá o token ao jogador que tem o direito a jogar uma carta
    }

    public void AdvanceTurn() {
        // Passa o Token ao jogador seguinte
    }

    public void Solve() {
        // Este método só executa quando o turno se esgotar
    }

    public void CountPoints() {
        // No final do jogo calcula o nr de pontos de cada jogador
    }

}
