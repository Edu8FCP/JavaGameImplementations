package gui_handle.estoril_1942;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Mnemonic;
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

public class ResultsController extends AnchorPane implements Initializable {
    @FXML
    public ImageView disc0,disc1,disc2,disc3,disc4,disc5;
    @FXML
    public Button go_to_menu_button;
    @FXML
    public Label InfoLabel, UsernamePoints1, UsernamePoints2, Points1, Points2;

    public ImageView[] disc_cards;


    int[] cards=new int[12];
    int n_cards;
    int n_clicks=0;

    HelloApplication app = new HelloApplication();

    Game game;
    Player player;
    Stage stage;
    MenuController menu;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Has been initialized!");
        disc_cards = new ImageView[]{disc0,disc1,disc2,disc3,disc4,disc5};
    }

    public void place_cards(){

        for(int i=0;i<6;i++){
            int carta= player.HandCards.get(i).ID;
            Image aux_image = new Image(getClass().getResource("/Cards/Card" + (carta) + ".PNG").toExternalForm());
            disc_cards[i].setImage(aux_image);
        }


    }

    void set_values(Game g, Player p, Stage s, int vencedor, int pontos_1, int pontos_2){
        game=g;
        player=p;
        stage = s;
        //Adicionar aqui valores dos vitoriosos
        //UsernamePoints1, UsernamePoints2, Points1, Points2;
        UsernamePoints1.setText(player.ProfileID+" Points:");
        UsernamePoints2.setText("Opponent Points:");

        if(player.CardsID==1){
            Points1.setText(""+pontos_1);
            Points2.setText(""+pontos_2);

        }else{
            Points1.setText(""+pontos_2);
            Points2.setText(""+pontos_1);
        }



        if(vencedor==-1){
            InfoLabel.setText("IT'S A TIE");
        }else if(vencedor== player.CardsID){
            InfoLabel.setText("WINNER");
        }else{
            InfoLabel.setText("GAME OVER");
        }
    }

    public void go_to_menu(){
        System.out.println("Should go to Menu");

        stage.close();
/*
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu_ui.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
        menu = fxmlLoader.getController();
        menu.set_stage(stage);
        */
    }

}
