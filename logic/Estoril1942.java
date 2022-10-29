public class Estoril1942 {
    public static void main(String[] args) {

        Game game = new Game(2); // standar para 2 jogadores
        game.Initialize();
        do {
            // Ciclo de lógica e funcionamento do jogo
            // }while
            game.AdvanceTurn();
        } while (game.Round != 4);
    }
}
