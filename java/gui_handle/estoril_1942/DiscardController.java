package gui_handle.estoril_1942;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/***
 *
 * TODO: Add cards highlights
 * TODO: add cards unselection
 * TODO: Button actually does something
 *
 */

public class DiscardController extends AnchorPane implements Initializable {
    @FXML
    public ImageView disc0,disc1,disc2,disc3,disc4,disc5,disc6,disc7,disc8,disc9,disc10,disc11;
    @FXML
    private Rectangle high1,high2,high3,high4,high5,high6,high7,high8,high9,high10,high11,high12;
    @FXML
    public ImageView mission_card_0,mission_card_1,mission_card_2,mission_card_3;
    @FXML
    public Button discard_button;
    @FXML
    public Label InfoLabel;
    public ImageView[] disc_cards;
    public ImageView[] myMissions;
    public Rectangle[] highlights;



    int[] cards=new int[12];
    int n_cards;
    int n_clicks=0;

    HelloApplication app = new HelloApplication();

    Game game;
    Player player;
    Stage stage;
    HelloController gui;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Has been initialized!");
        disc_cards = new ImageView[]{disc0,disc1,disc2,disc3,disc4,disc5,disc6,disc7,disc8,disc9,disc10,disc11};
        highlights = new Rectangle[]{high1,high2,high3,high4,high5,high6,high7,high8,high9,high10,high11,high12};
        myMissions = new ImageView[]{mission_card_0,mission_card_1,mission_card_2,mission_card_3};
    }

    public void get_cards(int card_number, int position){
        System.out.println("Card_number: "+card_number);
        System.out.println("Postition: "+position);
        cards[position]=card_number;
        Image  aux_image = new Image(getClass().getResource("/Cards/Card" + card_number + ".PNG").toExternalForm());
        disc_cards[position].setImage(aux_image);
        disc_cards[position].setVisible(true);
        n_cards=position+1;
        InfoLabel.setText("Discard "+(n_cards-6) +" Cards!");
    }
/*
    void assemble_cards(){
        System.out.println("Inside assemble cards");

        for(int i=0; i<n_cards; i++){
            disc_cards[i].setVisible(true);
            Image aux_image = new Image(getClass().getResource("/Cards/Card" + cards[i] + ".PNG").toExternalForm());
            disc_cards[i].setImage(aux_image);
        }
        for(int i=n_cards;i<(12);i++){
            disc_cards[i].setVisible(false);
        }
    }
*/

    void set_mission_image(){
        for (int i = 0; i < 4; i++) {
            //System.out.print((game.GameMissionCards[game.ListOfMissions[i]].ID) + " ");
            Image aux_image = new Image(getClass().getResource("/MissionCards/Mission" + game.GameMissionCards[game.ListOfMissions[i]-1].ID + ".PNG").toExternalForm());
            myMissions[i].setImage(aux_image);
        }
        //System.out.println();
    }

    void set_values(Game g, Player p, Stage s, HelloController gui_c){
        game=g;
        player=p;
        //gui=gui_c;
        stage = s;
    }

    public void card_selected(MouseEvent e){
        ImageView card_clicked = (ImageView) e.getSource();
        int i=0;
        while(card_clicked!=disc_cards[i]){
            i++;
        }

        if(highlights[i].getOpacity()==0){
            highlights[i].setOpacity(0.85);
            n_clicks++;
        }else{
            highlights[i].setOpacity(0);
            n_clicks--;
        }

        if(n_clicks==(n_cards-6)){
            discard_button.setDisable(false);
            discard_button.setOpacity(1);
        }else{
            discard_button.setDisable(true);
            discard_button.setOpacity(0.6);
        }
    }

    public void discard(){
        System.out.println("Should discard this thing");
        for(int i=(n_cards-1);i>=0;i--){
            if(highlights[i].getOpacity()>0){
                Player.DiscardCards(player, i); //Last value is card position in hand
            }
        }
        System.out.println("Cards discarded successfully");
        System.out.println("HandCards: "+ player.HandCards.size());
        System.out.println("Discarded cards: " + player.PlayerDiscardedCards.size());
        Map.ClearMap(game); //Not Tested

        /**
         * Change Scene
         */

        System.out.println("\n\n\nLets see what this does\n\n");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game_ui.fxml"));

            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            gui=fxmlLoader.getController();
            stage.setScene(scene);
            stage.show();
            app.handle_game(player, game, stage, gui);
    }

}
