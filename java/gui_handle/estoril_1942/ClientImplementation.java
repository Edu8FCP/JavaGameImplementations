package gui_handle.estoril_1942;

import java.util.List;


public class ClientImplementation {
    public static void main(String[] args) {
        System.out.println("A");
    }
}
/*
        // AUXILIARES 
        
        int Jogar = 1; // Serve para ver se somos nós a jogar
        int NumeroDeJogadores = 0; // Para saber quantos jogadores são
        int FirstPlayer = 0; // Quem é o primeiro jogador
        int ChoosenCard = 0; // Que carta foi jogada
        int MyID = 0; // Qual o meu ID
        String Nome = new String(); // O meu nome 
        int[] ListOfPrizes = new int[6]; // Dados do Rand
        int[] Orienations = new int[6];
        int[] Posicao =  new int[3];
        int[] ListOfBoards = new int[6];
        int[] Origem = new int[6];
        int[] Destino =  new int[6];


        // TIRA O NR DE JOGADORES DO GUI
        // TIRA O NOME DO JOGADOR DO GUI
        // Request ao Servidor
       
        // GAME HANDLER VARIABLES 
        Game game = new Game(NumeroDeJogadores); // Cria estrutura do jogo
        game.FirstPlayer = FirstPlayer;
        Player Jogador = new Player(Nome, game.BaseCards , MyID);
        game.InitializeGameClient(game,  Nome, MyID);

        // Wair for Data from Server
        // Boards | Orientations | Mission | Prizes
        Map.AttachOrientationsPrizes(game.GameBoards, ListOfPrizes, Orienations, game, ListOfPrizes);
      //  Map.AssembleMap(game.BoardsList, game.GameMap, game.GameBoards);
        Map.RemovePrizesFromList(game, ListOfPrizes);
        while(game.Round < 5){
            if(Jogador.CardsID+(Jogar*game.Turn) == game.Turn){
                do {
                    // ChoosenCard - Posicao na Mão
                    // Posicao[]
                    Jogar++;
        //        } while(!Player.PlayCard(game.GameMap, Jogador, Posicao, ChoosenCard));
                // CONSTRÓI FRAME
                // ENVIA FRAME
            } else{
                // WAIT FOR FRAME
            }
        }
   // }
} */
