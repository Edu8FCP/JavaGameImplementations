package gui_handle.estoril_1942;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//
public class App {

    // Test methods used in server to initialize game
    @Test
    //@DisplayName("Verifica se os dados foram corretamente inseridos")
    public void TestData(){
        Game game = new Game(2);
        String Nome = "Edu";
        int MyID = 1;
        game.InitializeGameClient(game, Nome, MyID);
        Player Jogador = new Player(Nome, game.BaseCards, MyID);
        assertEquals(1, game.BaseCards.get(6).PV);
    }


    // Test used to see if memory was allocated to game contents
    @Test
    //@DisplayName("Verifica se foi alocada a estrutura em memória")
    public void TestMemory(){
        Game game = new Game(2);
        String Nome = "Edu";
        int MyID = 1;
        game.InitializeGameClient(game, Nome, MyID);
        Player Jogador = new Player(Nome, game.BaseCards, MyID);
        assertNotNull(game.BaseCards.get(6));
    }

/*     @Test
    @DisplayName("Verifica se as dinâmicas de jogar cartas estão a funcionar corretamente")
    public void TestPlayLogic(){
        Game game = new Game(2);
        String Nome = "Edu";
        int MyID = 1;
        game.InitializeGameClient(game, Nome, MyID);
        Player Jogador = new Player(Nome, game.BaseCards, MyID);
        assertNotNull(game.BaseCards.get(6));
    } */

    /*@Test
    //@DisplayName("Verifica se a contagem está a ser bem efetuada")
    public void TestCountPoints(){
        int MyID = 1;
        String Nome = "Edu";

        Game game = new Game(2);
        int[] ListOfBoards = {1,2,3,4,5,6};
        game.InitializeGameClient(game, Nome, MyID);
        Map.AssembleMap(ListOfBoards, game.GameMap, game.GameBoards);


        Player Jogador[] = new Player[2];
        Jogador[0] = new Player(Nome, game.BaseCards, MyID);
        Jogador[1] = new Player("Edu2", game.BaseCards, 2);
        int pos1[] = {0, 1, 0};
        int pos2[] = {1, 1, 0};
        int pos3[] = {2, 1, 0};
        Player.PlayCard(game.GameMap, Jogador[0], pos1, 2);
        Player.PlayCard(game.GameMap, Jogador[0], pos2, 4);
        Player.PlayCard(game.GameMap, Jogador[0], pos3, 1);
        assertEquals(3, game.CountPoints(Jogador, game));
    }*/

    // GUI show and interactions and Server established connections were tested "internally"
}
