package gui_handle.estoril_1942;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.jar.Attributes;

/*********Learnings
 * https://www.youtube.com/watch?v=9XJicRt_FaI&t=1366s&ab_channel=BroCode
 *
 * Event detection:
 *      When we want to detect an event, we use the controller for it. We don't need to create a controller class in main, because it is already linked on the .fxml file
 *      When creating an event on controller.java, we then need to link it to SceneBuilder, and then choose the method we are refering to on te button.
 *
 * General:
 *      When creating objects on scene builder, the ones disposed first will be under the ones disposed later (in front of them)
 *      Our main where events will be handled will have to be Initializable on HelloController
 *      If we want to resize, don't use AnchorPane. The problem is after that it is needed to Scale everything up, and it gets buggy as x and y wont change but the visuality will
 *
 *
 *
 */




public class HelloApplication extends Application {

    MenuController menu;
    Client connection=new Client();


    public void handle_game(Player player, Game game, Stage stage, HelloController gui){

        int[][]dados;

        Game.AdvanceRoundClient(game, player);
        System.out.println("Game round: "+ game.Round);
        if(game.Round == 4){
            int data[]=new int[6];

            game.FinalSituation(player, game, data);

            try {   //SENDS info
                Client.sendInfo(4, data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            dados=Client.receive_info();
            int[] aux = new int[game.NumOfPlayers];
            int vencedor;
            for(int i = 0; i<game.NumOfPlayers;i++){
                aux[i] = dados[0][i];
            }
            vencedor = Boards.max(aux);

            System.out.println("Pontuaçao ID_1: "+dados[0][0]);
            System.out.println("Pontuaçao ID:2: "+dados[0][1]);
            System.out.println("Game successfully finished!");

            if(vencedor==-1){
                System.out.println("Empate");
            }else{
                System.out.println("O vencedor foi o Jogador "+vencedor);

            }

            //chama funçao qualquer
            /**
             * Back to Menu
             */
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("results_ui.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ResultsController results=fxmlLoader.getController();
            stage.setScene(scene);
            stage.show();
            results.set_values(game, player, stage, vencedor, dados[0][0], dados[0][1]);
            results.place_cards();

            return;
        }


        System.out.println("Advancing round and sending info");

        try {   //SENDS info
            Client.sendInfo(3, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dados=Client.receive_info();

        //Gives info to logic
        game.MapBoards=dados[0];
        game.ListOfPrizes=dados[2];
        Map.AttachOrientationsPrizes(game.GameBoards, game.MapBoards, dados[1], game, dados[2]);
        Map.AssembleMap(dados[0], game.GameMap, game.GameBoards);

        //Hoping this resets the controller
        gui.getValues(game, player, stage);
        gui.set_board_image(game.MapBoards);
        gui.set_card_images();
        gui.set_mission_images();
        gui.set_username(player.ProfileID);
        gui.initiate_round();
    }


    public void initiate_game(HelloController gui, Stage stage, int NumberOfPlayers, String Username, String server_h){

        System.out.println("here stage starts to lose its identity, it is sad, but nature must advance: "+stage);
        //set_connection();
        int n = NumberOfPlayers;
        n=2;    //because only 2 players working
        int b_c_m_p[];
        //TODO: need to know this player ID to use

        /* AUXILIARES */

        int MyID; // Qual o meu ID
        String Nome; // O meu nome
        int[][] dados;


        int[] ola = new int[1]; // FIXME: não percebo isto
        ola[0] = 2;
        int order;
        // TIRA O NR DE JOGADORES DO GUI
        // TIRA O NOME DO JOGADOR DO GUI
        // Request ao Servidor
        Client.establish_connection(ola, server_h);
        System.out.println("Connection established");
        MyID=Client.player_order();
        System.out.println("MyID "+MyID);
        dados = Client.receive_info();
        System.out.println("Eu acho que recebi algo... ACHO");
        /* GAME HANDLER VARIABLES */
        Nome = Username;
        //MyID = 1;
        Game game = new Game(NumberOfPlayers); // Cria estrutura do jogo
        game.InitializeGameClient(game, Nome, MyID);
        System.out.println("MyID "+MyID);
        Player Jogador = new Player(Nome, game.BaseCards, MyID);    //Isto esta a ser bem passado
        game.MapBoards=dados[0];
        game.ListOfPrizes=dados[3];
        game.ListOfMissions=dados[2];
        //Cartas estao bem criadas e ID existe
        Map.AttachOrientationsPrizes(game.GameBoards, game.MapBoards, dados[1], game, dados[3]);
        Map.AssembleMap(dados[0], game.GameMap, game.GameBoards);
        Jogador.ProfileID=Username;
        System.out.println("PlayerID:"+Jogador.CardsID);

        /***************************
         *
         * INITIATES GAME BEGIN
         *
         ***************************/
        // Wait for Data from Server
        //dados = Client.receive_info();
        //System.out.println("After second receive info");
        //System.out.println("Tabuleiros " + dados[0][0] + " Orientação" + dados[1][0]);
       // System.out.println("Stage: "+stage);
        gui.getValues(game, Jogador, stage);
        gui.set_board_image(game.MapBoards);
        gui.set_card_images();
        gui.set_mission_images();
        gui.set_username(Username);
        gui.initiate_round();




    }

    @Override
    public void start(Stage stage) throws IOException {

        //Displays first scene
        System.out.println("after launch");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu_ui.fxml"));     //For each event, we have a method
        System.out.println("After fxml loader initialize");
        Scene scene = new Scene(fxmlLoader.load());
        menu = fxmlLoader.getController();
        menu.set_stage(stage);
        System.out.println("After scene loader initialize");
        stage.setTitle("Estoril 1942");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        System.out.println("After stage show");


        //Players[1].PlayCard(game.GameMap, Players[1],, card_pressed)

        //Plays game Turns


    }

    public static void main(String[] args) {
        System.out.println("before launch");
        launch(args);
        System.out.println("ENDDDD");
    }
}