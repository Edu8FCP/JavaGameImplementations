import java.util.Scanner;

public class Estoril1942 {
    public static void main(String[] args) {

        /*****************************************************
         **** >>>>>>> AÇÕES DE PREPARAÇÃO AO JOGO <<<<<<< ****
         *****************************************************/
        /*
         * PERGUNTA QUANTOS JOGADORES SÃO
         * NUMA FASE INICIAL ATRIBUI AS CORES SOZINHO
         * INICIALIZA OS COMPONENTES
         * DISTRIBUI OS COMPONENTES PARA COMEÇAR O JOGO
         */
        Scanner s = new Scanner(System.in); // Depois temos de ver como ler diretamente da GUI
        int n = s.nextInt();
        s.close(); // Ele estava a pedir para se encerrar

        Game game = new Game(n); // standar para 2 jogadores
        Player[] Players = new Player[game.NumOfPlayers];
        game.Initialize(game, Players); // Inicializar conteúdos
        game.StartGame(); // Distribuir conteúdos e token

        /************************************************
         **** >>>>>>> AÇÕES DO CICLO DE JOGO <<<<<<< ****
         ************************************************/
        do {
            // Ciclo de lógica e funcionamento do jogo
            // PlayCard()
            if (game.Turn == game.NumOfTurns) { // ou avançamos a ronda
                game.AdvanceRound(game.GameMap);
            } else
                game.AdvanceTurn(); // ou avançamos o turno
        } while (game.Round != 4);

        game.CountPoints(); // No final do jogo tem de contar os pontos
    }
}

/******************************************************
 **** >>>>>>> FUNCIONAMENTO LÓGICO DO JOGO <<<<<<< ****
 ******************************************************/
/*
 * PERGUNTA QUANTOS JOGADORES SÃO
 * NUMA FASE INICIAL ATRIBUI AS CORES SOZINHO - Nr por ordem
 * INICIALIZA OS COMPONENTES
 * DISTRIBUI OS COMPONENTES PARA COMEÇAR O JOGO
 * ---------
 * DRAW_PRIZES()
 * PLAY_CARD()
 * ADVANCE_TURN()
 * CHECK_ABILITIES()
 * USE_ABILITIES()
 * CALCULATE_POWER()
 * ATTRIBUTE PRIZES()
 * DISCARD_CARDS()
 * ADVANCE_ROUND()
 * CALCULATE_POINTS()
 * GUI_FINAL
 */