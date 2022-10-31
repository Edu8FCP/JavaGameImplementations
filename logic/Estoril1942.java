public class Estoril1942 {
    public static void main(String[] args) {

        Game game = new Game(2); // standar para 2 jogadores
        game.Initialize(); // Inicializar conteúdos
        game.StartGame(); // Distribuir conteúdos e token
        do {
            // Ciclo de lógica e funcionamento do jogo
            if (game.Turn == game.NumOfTurns) { // ou avançamos a ronda
                game.AdvanceRound();
            } else
                game.AdvanceTurn(); // ou avançamos o turno
        } while (game.Round != 4);

        game.CountPoints(); // No final do jogo tem de contar os pontos
    }
}
