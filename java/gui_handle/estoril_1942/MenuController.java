package gui_handle.estoril_1942;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends AnchorPane implements Initializable {

    @FXML
    public Button JoinGame, CreateGame, JoinPrivate;

    @FXML
    public Spinner<Integer> PlayerNumber;
    public int NumberOfPlayers=0;
    @FXML
    public Label user_label;

    @FXML
    public TextField RoomCode, Username, IP_Menu;
    private final int UsermaxLength=15, IDmaxLength=4;

    @FXML
    public Circle PlayerIcon;


    Stage stage;
    //FXMLLoader fxmlLoader;
    HelloApplication app = new HelloApplication();

    public void set_stage(Stage stage_change){
        stage=stage_change;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Sets PlayerIcon image
        Image aux_image= new Image(getClass().getResource("/player_icon/player_icon_example.png").toExternalForm());
        PlayerIcon.setFill(new ImagePattern(aux_image));

        //Set Spinner, can add override stuff if needed
        SpinnerValueFactory<Integer> valueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(2,5);
        valueFactory.setValue(2);
        PlayerNumber.setValueFactory(valueFactory);

        //Set username maxlenght
        Username.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (Username.getText().length() > UsermaxLength) {
                    String s = Username.getText().substring(0, UsermaxLength);
                    Username.setText(s);
                }
            }
        });

        //Set roomcode maxlenght
        RoomCode.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (RoomCode.getText().length() > IDmaxLength) {
                    String s = RoomCode.getText().substring(0, IDmaxLength);
                    RoomCode.setText(s);
                }
            }
        });
    }

    public void join_game_clicked(){

        String server_h=null;

        //TODO: Verify values
        if(Username.getLength()==0){
            user_label.setVisible(true);
            return;
        }else System.out.println(Username.getText());

        if(IP_Menu.getLength()==0){
            System.out.println("No IP inserted!");
        }else{
            server_h=IP_Menu.getText();
        }

        String Name;
        NumberOfPlayers=PlayerNumber.getValue();
        Name=Username.getText();

        //Supostamente mandamos para o servidor a pedir acesso ao jogo
        //Servidor emparelha-nos com alguem
        //Ir para game Board

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game_ui.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
        app.initiate_game(fxmlLoader.getController(), stage, NumberOfPlayers, Name, server_h);
    }

    //FIXME: maybe hide the visibility of this ones
    public void create_game_clicked(){

    }

    //FIXME: maybe hide visibility
    public void join_private_clicked(){

    }

}
