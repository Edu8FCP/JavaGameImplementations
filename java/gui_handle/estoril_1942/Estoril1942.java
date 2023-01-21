package gui_handle.estoril_1942;
import java.lang.Math;
import java.util.Scanner;

public class Estoril1942 {
    /* public static void main(String args[]){
        int n=2;        //Says we have 2 players
        //Initiates the game
        Game game = new Game(n); // standard para 2 jogadores
        Player[] Players = new Player[game.NumOfPlayers];
        game.Initialize(game, Players); // Inicializar conteúdos
        game.StartGame(game); // Distribuir conteúdos e token  */
        
        // RandBoards, Orientations e Assemble Map
        // System.out.println(game.BaseCards.get(5).Power + " " + game.BaseCards.get(14).Power);
        // System.out.println(Players[0].HandCards.get(0).abilities[0]);
        // System.out.println(Players[0].HandCards.get(1).abilities[0]);
        // System.out.println(Players[0].HandCards.get(2).ID);
        // System.out.println(Players[1].HandCards.get(0).ID);
        // System.out.println(Players[1].HandCards.get(1).ID);
        // System.out.println(Players[1].HandCards.get(2).ID);
       
        /************************************************
         **** >>>>>>> AÇÕES DO CICLO DE JOGO <<<<<<< ****
         ************************************************/
       /*  int[] pos = new int[3];
        boolean p = false;
        int i = 0;
        int j = 0;
        System.out.println("Total de Turnos :" + game.NumOfTurns);
        do {
            // Ciclo de lógica e funcionamento do jogo
            // PlayCard()
            do{
                int a = (int)(Math.random()*6);
                int b = (int)(Math.random()*2);
                int c = (int)(Math.random()*2);
                pos[0] = a;
                pos[1] = b;
                pos[2] = c;
            } while(!Player.PlayCard(game.GameMap,Players[i], pos, j));
            System.out.println("Carta Jogada! <<<<<<<<<<<");
            if (game.Turn == game.NumOfTurns) { // ou avançamos a ronda
                System.out.println("Fim da ronda!");
                Estoril1942.PrintBoard(game);
                Game.AdvanceRound(game, Players);
                while(Players[0].HandCards.size()>6){
                    System.out.println("Discard Cards, please");
                    Scanner s = new Scanner(System.in); // Depois temos de ver como ler diretamente da GUI
                    int k = 0;
                    if(s.hasNextInt()){
                        k = s.nextInt();
                        Player.DiscardCards(Players[0], k);
                    } else s.close(); 
                }
                while(Players[1].HandCards.size()>6){
                    System.out.println("Discard Cards, please");
                    Scanner s = new Scanner(System.in); // Depois temos de ver como ler diretamente da GUI
                    int k1 = 0;
                    if(s.hasNextInt()){
                        k1 = s.nextInt();
                        Player.DiscardCards(Players[1], k1);
                    } else s.close(); 
                }
                p = false;
                i = 0;
                j = 0;
            } else{
                System.out.println("Avançou Turno");
                game.AdvanceTurn(game); // ou avançamos o turno
                p = !p;
                if(p) i = 1;
                 else{
                    i = 0; 
                    j++;
                 } 
            }
        } while (game.Round < 5);




        System.out.println("O jogador " + game.CountPoints(Players, game) + " ganhou o jogo!"); // No final do jogo tem de contar os pontos
        System.out.println(game.Points[0]);
        System.out.println(game.Points[1]);
    }  */
   

    public static void PrintBoard(Game game){
        for(int z = 0; z<6; z++){
            System.out.println();
            System.out.println("Tabuleiro " + z);
            int r = 0, col = 0;
            if(z<=2) r = 0;
                else r = 1;
            if(z == 0 || z == 3) col = 0;
            if(z == 1 || z == 4) col = 1;
            if(z == 2 || z == 5) col = 2;
            for(int x = 0; x<2; x++){
                for(int k = 0; k<2;k++){
                    System.out.print(" :" + game.GameMap.Tabuleiros[r][col].slots[x][k].player);
                    if( game.GameMap.Tabuleiros[r][col].slots[x][k].player != 6 && game.GameMap.Tabuleiros[r][col].slots[x][k].player != 0){
                        System.out.print(" " + game.GameMap.Tabuleiros[r][col].slots[x][k].Card.Power + " ");
                        System.out.println(" " + game.GameMap.Tabuleiros[r][col].slots[x][k].Card.ID + " ");
                    } else if(game.GameMap.Tabuleiros[r][col].slots[x][k].player == 6){
                        System.out.println(" " + game.GameMap.Tabuleiros[r][col].slots[x][k].Card.ID);
                    } else System.out.println();
                }
            }
        }
    }

}
