package gui_handle.estoril_1942;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleRole;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Clock;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;


/**
 * TODO: Add interrupt to know if player is playing, beacause need it to infos like show button and stuff
 * BUGGGG: need to know if slot is available to play, because if I click on it (and not in the card), cards go to the same slot
 *
 *
 * Highlight colours:
 *  - BLUE: #0028ff
 *  - YELLOW: #ffed00
 *  - RED: #ff0000
 *
 */

public class HelloController extends AnchorPane implements Initializable {

    private int BOARDS_NUMBER = 6;

    @FXML
    public AnchorPane anchor_pane;
    @FXML
    public ImageView card_0, card_1, card_2, card_3, card_4, card_5;                 //Cartas numeradas de 0 a 5
    @FXML
    public ImageView mission_card_0,mission_card_1,mission_card_2,mission_card_3;
    @FXML
    public ImageView board_0, board_1, board_2, board_3, board_4, board_5;           //Boards numeradas de 0 a 5
    @FXML
    public ImageView card_conspiration;
    @FXML
    public Circle Player1, Player2, Player3, Player4, Player5;                      //Players numerados de 1 a 5. 1 somos nós, 5 é os outros
    @FXML
    public Label labelPlayer1, labelPlayer2;                                        //Maybe make this later an array to change with the player name
    @FXML
    public Button cancel_play, next_turn;
    @FXML
    public Rectangle highlight_board;
    @FXML
    public Label label_standard, shield_standard;
    @FXML
    public Rectangle highlight0, highlight1, highlight2, highlight3, highlight4, highlight5;
    @FXML
    public Label PerformActionLabel, InfoLabel, label_conspiration, discarded_number;
    @FXML
    public Label label_Player1, label_Player2;


    public Rectangle[][] highlights = new Rectangle[6][4];      //Slots highlights
    public Label[][] labels = new Label[6][4];      //Slots highlights
    public Label[][] shields = new Label[6][4];
    public ImageView[][] otherCards=new ImageView[6][4];

    public Rectangle[] myHighlights;        //Hand highlights
    public ImageView[] myBoards;
    public ImageView[] myCards;
    public ImageView[] myMissions;
    int slot_prize[] = new int[6];
    int board_slot[]=new int[3];


    private double[] pos_slot_ratio;
    private double card_ratio = (double) 119 / 75;
    private double card_slot_h_ratio = (double) 55 / 66;
    private int card_pressed = -1;   //Records the card that was pressed. Number is related to Image on JavaFX, not order.
    private int card_placed[] = new int[6];       //To
    private boolean play_made = false;
    private double card_x, card_y;         //To return the card to place if needed
    private double scale_x = 1, scale_y = 1;       //We wanted to scale at a certain point, but it failed
    private final double button_x = 548, button_x_stand=1044, consp_x = 650,button_y = 584;     //If questions are asked, buttons need to move
    public int abilityNumber = 0, abilitySweep = 0; //FIXME: NEED A PLACE WHERE I SETUP ability_number

    /**
     * Variables used to allow clicks and interactions
     */
    private boolean ability_decision = false;
    private boolean using_ability = false;
    private boolean solving = false;
    private boolean waiting=false;

public int wait_play_count=0;


    //Logic functions

    Game game;
    Player player;
    DiscardController discard;
    Stage stage;
    //HelloApplication app = new HelloApplication();


    //FIXME: Test parameters/functions
    public int ID = 1;                     //will later be given by main game. Is this player ID, or not lol
    public int n_players=2;
    public int TOKEN;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //System.out.println("INITIALIZED MUAHAHAH");

        //Creates boards array for easy access
        myBoards = new ImageView[]{board_0, board_1, board_2, board_3, board_4, board_5};
        myCards = new ImageView[]{card_0, card_1, card_2, card_3, card_4, card_5};
        myHighlights = new Rectangle[]{highlight0, highlight1, highlight2, highlight3, highlight4, highlight5};
        myMissions = new ImageView[]{mission_card_0,mission_card_1,mission_card_2,mission_card_3};
        pos_slot_ratio = new double[]{(double) 12 / 62, (double) 4 / 66};
        for (int i = 0; i < 24; i++) {              //Initiates highlights and adds it to AnchorPane
            highlights[i / 4][i % 4] = highlight_constructor();
            //System.out.println("["+(int)(i/4)+"]["+(i%4)+"]");
        }
        for (int i = 0; i < 24; i++) {              //Initiates highlights and adds it to AnchorPane
            labels[i / 4][i % 4] = label_constructor();
            //System.out.println("["+(int)(i/4)+"]["+(i%4)+"]");
        }
        for (int i = 0; i < 24; i++) {              //Initiates highlights and adds it to AnchorPane
            shields[i / 4][i % 4] = shield_constructor();
            //System.out.println("["+(int)(i/4)+"]["+(i%4)+"]");
        }
        for (int i = 0; i < 24; i++) {              //Initiates highlights and adds it to AnchorPane
            otherCards[i / 4][i % 4] = card_constructor();
            //System.out.println("["+(int)(i/4)+"]["+(i%4)+"]");
        }


        //Sets players Images
        //TODO: make invisible if they arent playing
        Image aux_image = new Image(getClass().getResource("/player_icon/player_icon_example.png").toExternalForm());
        Player1.setFill(new ImagePattern(aux_image));
        Player2.setFill(new ImagePattern(aux_image));
        Player3.setFill(new ImagePattern(aux_image));
        Player4.setFill(new ImagePattern(aux_image));
        Player5.setFill(new ImagePattern(aux_image));


        //Sets highlight colour

        //Hides cancel button
        cancel_play.setVisible(false);

        //To test while I dont put this with gui_handle.estoril_1942.logic
        int assemble_array[] = {2, 9, 8, 4, 10, 0};
        //set_board_image(assemble_array);
    }

    public void initiate_round(){
        //int Jogar = 0; // Serve para ver se somos nós a jogar
        game.Turn++;

        System.out.println("Game turn: "+game.Turn);
        System.out.println("numof turns: "+ game.NumOfTurns);

        if(game.Turn == game.NumOfTurns){
            solve_board();
            return;
        }

        if(((game.Turn%2)+1)==player.CardsID){
            System.out.println("I am playing!");
            //EU JOGO
            waiting=false;
        }else {
            System.out.println("You are playing! ;(");
            //Oponente joga
            waiting=true;
            wait_for_play();
        }


    }
    public void solve_board() {

        game.SolveClient(game.GameMap, player);
        System.out.println("Player Hand: " +player.HandCards.size());
        System.out.println("Inside solve board");

    for(int i=1;i< game.NumOfPlayers+1;i++){
        //Acho que assim vai dar lol
        //if(i!=ID){
            display_played_cards(i);
        //}
    }
    //Displays cards for 10 seconds
/*
        long current, last=clock.millis();
        int iteration=0;
        System.out.println("Running loop lol");
        while(iteration<(60000/60)){
            current=clock.millis();
            if((current-last)>60){
                iteration++;
                last=clock.millis();
            }
        }
        System.out.println("End of loop lol");

 */
        change_scene();
        //solving=true;
        //use_ability_question();
        //disable cards
        //make buttons invisible
        //wait for turn
        //Add solvable Label above
        //turn highlights off from the board that is being solved
    }


    public void change_scene(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("discard_ui.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        discard=fxmlLoader.getController();
        stage.setScene(scene);
        stage.show();
        discard.set_values(game, player, stage, this);
        discard.set_mission_image();
        System.out.println("number of hand cards: "+ player.HandCards.size());
        //int []cards=new int[]{1,2,3,4,5,6,36,50,42, 45};
        for(int i=0;i<player.HandCards.size();i++){
            discard.get_cards(player.HandCards.get(i).ID, i);
            //discard.get_cards(cards[i], i);
        }
    }

    void display_played_cards(int Jogador){
        int carta;

        Image aux_image = new Image(getClass().getResource("/Cards/CardBack.PNG").toExternalForm());
        for(int i=0; i<6;i++){
            for(int j=0;j<4;j++){
                if(otherCards[i][j].getImage()==aux_image){
                    //verifica se foi o jogador certo
                    if(game.GameMap.Tabuleiros[i/3][i%3].slots[j/2][j%2].player==Jogador){
                        carta=game.GameMap.Tabuleiros[i/3][i%3].slots[j/2][j%2].Card.ID;
                        Image imagem=new Image(getClass().getResource("/Cards/Card"+carta+".PNG").toExternalForm());
                        otherCards[i][j].setImage(imagem);
                    }
                }
            }
        }

    }



    /**
     * Sets the card images based on received by server/main
     */

    void set_mission_images(){
        for (int i = 0; i < 4; i++) {
            //System.out.print((game.GameMissionCards[game.ListOfMissions[i]].ID) + " ");
            Image aux_image = new Image(getClass().getResource("/MissionCards/Mission" + game.GameMissionCards[game.ListOfMissions[i]-1].ID + ".PNG").toExternalForm());
            myMissions[i].setImage(aux_image);
        }
        //System.out.println();
    }

    public void set_username(String Name){
        label_Player1.setText(Name);
    }

    public void set_card_images() {
      //  System.out.println("Player Cards:");
        for (int i = 0; i < 6; i++) {
           // System.out.print((player.HandCards.get(i).ID+1) + " ");
            Image aux_image = new Image(getClass().getResource("/Cards/Card" + (player.HandCards.get(i).ID) + ".PNG").toExternalForm());
            myCards[i].setImage(aux_image);
            //puts the image where it is supposed to go

        }
       // System.out.println();
    }

    public void use_ability_question() {
        //mover butoes
        //Switches buttons back and forth
        double x;

        cancel_play.setLayoutX(button_x);
        next_turn.setLayoutX(button_x);


        //alterar texto botoes
        cancel_play.setVisible(true);
        next_turn.setVisible(true);
        cancel_play.setText("NO");
        next_turn.setText("YES");
        cancel_play.setOpacity(1);
        next_turn.setOpacity(1);

        //cartas que ainda estao na mao invisiveis
        for (int i = 0; i < 6; i++) {
            if (card_placed[i] == 0) {
                myCards[i].setVisible(false);   //FIXME: maybe put some opacity in this
            }
        }

        PerformActionLabel.setVisible(true);
        PerformActionLabel.setText("Do you want to use the ability?");
        ability_decision = true;
        //says it needs to be double click
    }


    void change_card(ImageView card, int new_card_ID) {
        Image aux_image = new Image(getClass().getResource("/Cards/Card" + (new_card_ID) + ".PNG").toExternalForm());
        card.setImage(aux_image);
    }

    //TODO: test prize card change when I have the cards in fact
    void conspire_question() {
        //Hand cards invisible
        if (ability_decision) {
            System.out.println("Ability decision should be false, because question was already made");
        }

        for (int i = 0; i < 6; i++) {
            if (card_placed[i] == 0) {
                myCards[i].setVisible(false);   //FIXME: maybe put some opacity in this
            }
        }

        //Conspire card and label visible
        card_conspiration.setVisible(true);
        label_conspiration.setVisible(true);
        //TODO: add card change
        change_card(card_conspiration, 8);


        //move buttons, change text, and make visible
        next_turn.setLayoutX(consp_x);
        cancel_play.setLayoutX(consp_x);
        cancel_play.setVisible(true);
        next_turn.setVisible(true);
        cancel_play.setText("NO");
        next_turn.setText("YES");
        cancel_play.setOpacity(1);
        next_turn.setOpacity(1);

        //FOR CLICK (not here)
        //back in place stuff, undo changes
        //if yes change prize card by chosen
        //if no nothing

        //restore boolean


    }


    public void getValues(Game g, Player p, Stage s) {
        stage=s;
        System.out.println("Stage: "+s);
        game = g;
        player = p;
        ID=player.CardsID;
        switch(player.CardsID){
            case 2:
                Player1.setFill(Color.BLUE);
                Player2.setFill(Color.RED);
                break;
            case 1:
                Player1.setFill(Color.RED);
                Player2.setFill(Color.BLUE);
                break;
        }
        discarded_number.setText("Cards discarded: "+ player.PlayerDiscardedCards.size());
    }


    /**
     * Decodes in which slot the card is and makes it invisible
     *
     * @param x
     * @param y
     */
    int[] highlight_remove(double x, double y) {

        int board, slot;
        int b_s[] = new int[3];

      //  System.out.println("Amazing board calculation: ");

        board = (int) (x - myBoards[0].getLayoutX()) / (int) (myBoards[0].getFitWidth() * scale_x * myBoards[0].getScaleX());
       // System.out.print(board + " ");
        board = (int) (board + 3 * ((int) (y - myBoards[0].getLayoutY()) / (int) (myBoards[0].getFitHeight() * scale_y * myBoards[0].getScaleY())));
       // System.out.println(board + " " + (int) (y - myBoards[0].getLayoutY()) / (int) myBoards[0].getFitHeight() * scale_y);
        b_s[0] = board;

       // System.out.println("Amazing slot calculation: ");
        slot = (int) (x - myBoards[board].getLayoutX()) / (int) (myBoards[board].getFitWidth() * scale_x / 2);
       // System.out.print(slot + " ");
        slot = slot + (2 * (int) ((y - myBoards[board].getLayoutY()) / (int) (myBoards[board].getFitHeight() * scale_y / 2)));
       // System.out.println(slot + " ");
        b_s[1] = slot / 2;
        b_s[2] = slot % 2;


        highlights[board][slot].setVisible(false);      //Lol all this just for this AHAHAH

        return b_s;
    }


    /**
     * Starts the slots highlight constants. When placing card, choose color, and enable and disable only.
     *
     * @return
     */

    //TODO: need to figure out how to eliminate or hide all when turn ends
    //FIXME: might need to change opacity
    Rectangle highlight_constructor() {
        Rectangle high = new Rectangle();

        high.setHeight(highlight_board.getHeight());
        high.setWidth(highlight_board.getWidth());
        high.setOpacity(highlight_board.getOpacity());
        high.setFill(highlight_board.getFill());
        high.setStroke(highlight_board.getStroke());
        high.setVisible(false);     //Invisible in the beggining
        //I love this piece of code because it took be time to get there and then it actually makes sense.
        //If I hadn't changed highAccessible Role value this wouldnt work :)
        high.setAccessibleRole(AccessibleRole.NODE);     //Hot Dang I am the greatest
        anchor_pane.getChildren().add(13, high);
        high.setDisable(true);

        return high;
    }

    public void place_shield(int b_s[]){
        int board=b_s[0];
        double x, y;
        int slot= b_s[1]*2 + b_s[2];
        System.out.println("slot: "+slot);
        System.out.println("board: "+board);

        x = myBoards[board].getLayoutX() + (myBoards[board].getFitWidth()/2 -shields[board][slot].getWidth()) + (slot%2 * (myBoards[board].getFitWidth() * scale_x / 2));
        y = myBoards[board].getLayoutY() + ((int) (slot / 2) * (myBoards[board].getFitHeight() * scale_y / 2));

        shields[board][slot].setLayoutX(x);
        shields[board][slot].setLayoutY(y);
        shields[board][slot].setVisible(true);
    }


    ImageView card_constructor(){
        ImageView play=new ImageView();

        play.setFitHeight(card_0.getFitHeight());
        play.setOpacity(card_0.getOpacity());
        play.setImage(card_0.getImage());
        play.setPreserveRatio(true);
        play.setLayoutX(0);
        play.setLayoutY(0);
        play.setVisible(false);

        play.setAccessibleRole(card_0.getAccessibleRole());     //Hot Dang I am the greatest
        anchor_pane.getChildren().add(play);

        return play;
    }
    Label shield_constructor(){
        Label lab = new Label();
        //sets label parameters
        lab.setPrefHeight(shield_standard.getPrefHeight());
        lab.setPrefWidth(shield_standard.getPrefWidth());
        lab.setStyle(shield_standard.getStyle());
        lab.setFont(shield_standard.getFont());
        lab.setTextFill(shield_standard.getTextFill());
        lab.setTextAlignment(shield_standard.getTextAlignment());
        lab.setAlignment(shield_standard.getAlignment());
        lab.setOpacity(shield_standard.getOpacity());
        lab.setText("S");
        lab.setLayoutX(0);
        lab.setLayoutY(0);
        lab.setVisible(false);

        lab.setAccessibleRole(shield_standard.getAccessibleRole());     //Hot Dang I am the greatest
        anchor_pane.getChildren().add(lab);

        return lab;
    }

    Label label_constructor() {
        Label lab = new Label();
        //sets label parameters
        lab.setPrefHeight(label_standard.getPrefHeight());
        lab.setPrefWidth(label_standard.getPrefWidth());
        lab.setStyle(label_standard.getStyle());
        lab.setFont(label_standard.getFont());
        lab.setTextFill(label_standard.getTextFill());
        lab.setTextAlignment(label_standard.getTextAlignment());
        lab.setAlignment(label_standard.getAlignment());
        lab.setOpacity(label_standard.getOpacity());
        lab.setLayoutX(0);
        lab.setLayoutY(0);

        lab.setAccessibleRole(label_standard.getAccessibleRole());     //Hot Dang I am the greatest
        anchor_pane.getChildren().add(lab);


        return lab;
    }

    //TODO: change player variable
    void set_board_highlight(int player, int[] b_s) {

        RadialGradient paint = null;
        highlights[b_s[0]][b_s[1] * 2 + b_s[2]] = highlight_constructor();



        switch (player) {
            case 1:     //RED

                paint = new RadialGradient(
                        0.0, 0.0, 0.5, 0.5, 0.7477, true, CycleMethod.NO_CYCLE,
                        new Stop(0.0, new Color(1.0, 0.0, 0.0, 1.0)),
                        new Stop(0.0067, new Color(1.0, 0.0, 0.0, 1.0)),
                        new Stop(0.0067, new Color(1.0, 1.0, 1.0, 1.0)),
                        new Stop(1.0, new Color(1.0, 0.0, 0.0, 1.0)));

                break;

            case 2:     //BLUE
                paint = new RadialGradient(
                        0.0, 0.0, 0.5, 0.5, 0.7477, true, CycleMethod.NO_CYCLE,
                        new Stop(0.0, new Color(1.0, 0.0, 0.0, 1.0)),
                        new Stop(0.0067, new Color(1.0, 0.0, 0.0, 1.0)),
                        new Stop(0.0067, new Color(1.0, 1.0, 1.0, 1.0)),
                        new Stop(1.0, new Color(0.0, 0.1569, 1.0, 1.0)));

                break;

            case 3:     //YELLOW

                paint = new RadialGradient(
                        0.0, 0.0, 0.5, 0.5, 0.7477, true, CycleMethod.NO_CYCLE,
                        new Stop(0.0, new Color(1.0, 0.0, 0.0, 1.0)),
                        new Stop(0.0067, new Color(1.0, 0.0, 0.0, 1.0)),
                        new Stop(0.0067, new Color(1.0, 1.0, 1.0, 1.0)),
                        new Stop(1.0, new Color(1.0, 0.9294, 0.0, 1.0)));

                break;

            default:

                System.out.println("ERROR, Player not recognized when Highlighting Board!");

                break;
        }
        try {
            highlights[b_s[0]][b_s[1] * 2 + b_s[2]].setFill(paint);
            highlights[b_s[0]][b_s[1] * 2 + b_s[2]].setStroke(paint);
        } catch (Exception e) {
            System.out.println("ERROR Highlighting, Paint not initiated!");
        }

        double x, y;

        x = myBoards[b_s[0]].getLayoutX() + (b_s[2] * (myBoards[b_s[0]].getFitWidth() * scale_x / 2));
        y = myBoards[b_s[0]].getLayoutY() + (b_s[1] * (myBoards[b_s[0]].getFitHeight() * scale_y / 2));

        highlights[b_s[0]][b_s[1] * 2 + b_s[2]].setLayoutX(x);
        highlights[b_s[0]][b_s[1] * 2 + b_s[2]].setLayoutY(y);
        highlights[b_s[0]][b_s[1] * 2 + b_s[2]].setVisible(true);

    }

    //slot goes from 0 to 3
    //is only called if it is the players turn
    private void place_card_in_slot(int[] b_s, ImageView card) {

        //Saves where the cards were before to send them there
        card_x = card.getLayoutX();
        card_y = card.getLayoutY();

        //FIXME: for test
        //place_shield(b_s);

       // System.out.println("card_place_values: " + myBoards[b_s[0]].getLayoutX() + " " + (b_s[2] * (myBoards[b_s[0]].getFitWidth() * scale_x / 2.0)) + " " + ((myBoards[b_s[0]].getFitWidth() * scale_x) * pos_slot_ratio[0] / 2.0));

        double x, y;
        //Places card in place
        //FORMULA= Board_x + slot_x + place in center
        //System.out.println("Board Fit Width");
        //System.out.println(myBoards[b_s[0]].getFitWidth()*scale_x);
        //FitWidth doesn't update when using scale, so we must  multiply by the scale

        x = myBoards[b_s[0]].getLayoutX() + (b_s[2] * (myBoards[b_s[0]].getFitWidth() * scale_x / 2)) + ((myBoards[b_s[0]].getFitWidth() * scale_x) * pos_slot_ratio[0] / 2.0);
        y = myBoards[b_s[0]].getLayoutY() + (b_s[1] * (myBoards[b_s[0]].getFitHeight() * scale_y / 2)) + ((myBoards[b_s[0]].getFitHeight() * scale_y) * pos_slot_ratio[1] / 2.0);

      //  System.out.println("X and LayoutX: " + myBoards[b_s[0]].getX() + " " + myBoards[b_s[0]].getLayoutX());

        card.setLayoutX(x);
        card.setLayoutY(y);

        card.setFitHeight((myBoards[b_s[0]].getFitHeight() * scale_y / 2) * card_slot_h_ratio);

        //turns the card back
        /*
        Image aux_image = new Image(getClass().getResource("/Cards/CardBack.PNG").toExternalForm());
        card.setImage(aux_image);
*/
        //Disables any highlight
        for(int i=0;i<6;i++){
            myHighlights[i].setVisible(false);
        }

        //Enables cancel button
        if(!waiting){
            cancel_play.setVisible(true);
        }

               //Indiferente
    }


    public void wait_for_play() {
        waiting=true;
        /*
        try {
            wait(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
         */
        InfoLabel.setText("Opponent's Turn");
        System.out.println("Opponent play:");
        //Should expect for server info
        //will have a while
        int[] b_s=new int[3];
        int carta=0;
        /*
        wait_play_count++;
        switch(wait_play_count){
            case 1:

                b_s[0]=0;
                b_s[1]=0;
                b_s[2]=0;
                carta=2;
                break;
            case 2:
                b_s[0]=0;
                b_s[1]=0;
                b_s[2]=1;
                carta=1;
                break;
            case 3:
                b_s[0]=0;
                b_s[1]=1;
                b_s[2]=0;
                carta=4;
                break;
            case 4:
                b_s[0]=0;
                b_s[1]=1;
                b_s[2]=1;
                carta=5;
                break;

        }
         */

        int dadosr[][]=new int[6][4];
        //Waits in order to update UI
        dadosr=Client.receive_info();
        b_s[0]=dadosr[0][1];
        b_s[1]=dadosr[0][2]/10;
        b_s[2]=dadosr[0][2]%10;
        System.out.println("board and slot: " + b_s[0] + " "+ b_s[1] + " "+ b_s[2] + " carta: "+carta);

        opponent_play(dadosr[0][0], b_s, dadosr[0][3]);
    }

    public void opponent_play(int player_number, int b_s[], int carta){

        //can add this to verify but not sure
        System.out.println("Edu function value: "+ Player.PlayCard(game.GameMap, player_number,b_s, carta, game));
        System.out.println("oioioioioIOIOIOIOIOIOI " + carta);

        int slot=b_s[1]*2+b_s[2];
        otherCards[b_s[0]][slot].setVisible(true);
        if(carta<31){
            change_card(otherCards[b_s[0]][slot], game.BaseCards.get(carta-1).ID);
        } else change_card(otherCards[b_s[0]][slot], game.CartasGUI.get(carta-31).ID);
        place_card_in_slot(b_s,otherCards[b_s[0]][slot]);
        set_board_highlight(player_number, b_s);
        TOKEN=player.CardsID;
        waiting=false;
        InfoLabel.setText("Your Turn");
        initiate_round();
    }

    public void set_prize_cards(int []b_s){

        //for (int i = 0; i < BOARDS_NUMBER; i++) {

            int BTR=0, BTC=0; // BTR/C - Board Target Row/Column
            int STR, STC; // STR/C - Slot Target Row/Column
            if(b_s[0]<=2){ // Cálculo da posição do Board
                BTR = 0;
            } else BTR = 1;
            if(b_s[0] == 0 || b_s[0] == 3){
                BTC = 0;
            } else if(b_s[0] == 1 || b_s[0] == 4){
                BTC = 1;
            } else if(b_s[0] == 2 || b_s[0] == 5){
                BTC = 2;
            }
            int []board_s=new int[3];
            board_s[0]=b_s[0];
        board_s[1]=b_s[1]/2;
        board_s[2]=b_s[1]%2;

        System.out.println("BTR: "+BTR+ " btc: "+BTC);
        //game.GameMap.Tabuleiros[BTR][BTC].slots[0][0].player
            if(game.GameMap.Tabuleiros[BTR][BTC].slots[0][0].player==6){
                System.out.println("Prize Card: "+game.GameMap.Tabuleiros[BTR][BTC].slots[0][0].Card.ID);
                Image aux_image = new Image(getClass().getResource("/Cards/Card" + (game.GameMap.Tabuleiros[BTR][BTC].slots[0][0].Card.ID) + ".PNG").toExternalForm());
                place_card_in_slot( board_s,otherCards[b_s[0]][b_s[1]]);
                otherCards[b_s[0]][b_s[1]].setImage(aux_image);
                otherCards[b_s[0]][b_s[1]].setVisible(true);
                //myBoards[i].setImage(aux_image);
            }

            if(game.GameMap.Tabuleiros[BTR][BTC].slots[0][1].player==6){
                System.out.println("Prize Card: "+game.GameMap.Tabuleiros[BTR][BTC].slots[0][1].Card.ID);
                Image aux_image = new Image(getClass().getResource("/Cards/Card" + (game.GameMap.Tabuleiros[BTR][BTC].slots[0][1].Card.ID) + ".PNG").toExternalForm());
                place_card_in_slot( board_s,otherCards[b_s[0]][b_s[1]]);
                otherCards[b_s[0]][b_s[1]].setImage(aux_image);
                otherCards[b_s[0]][b_s[1]].setVisible(true);
            }


            if(game.GameMap.Tabuleiros[BTR][BTC].slots[1][0].player==6){
                System.out.println("Prize Card: "+game.GameMap.Tabuleiros[BTR][BTC].slots[1][0].Card.ID);
                Image aux_image = new Image(getClass().getResource("/Cards/Card" + (game.GameMap.Tabuleiros[BTR][BTC].slots[1][0].Card.ID) + ".PNG").toExternalForm());
                place_card_in_slot( board_s,otherCards[b_s[0]][b_s[1]]);
                otherCards[b_s[0]][b_s[1]].setImage(aux_image);
                otherCards[b_s[0]][b_s[1]].setVisible(true);
            }
            if(game.GameMap.Tabuleiros[BTR][BTC].slots[1][1].player==6){
                System.out.println("Prize Card: "+game.GameMap.Tabuleiros[BTR][BTC].slots[1][1].Card.ID);
                Image aux_image = new Image(getClass().getResource("/Cards/Card" + (game.GameMap.Tabuleiros[BTR][BTC].slots[1][1].Card.ID) + ".PNG").toExternalForm());
                place_card_in_slot( board_s,otherCards[b_s[0]][b_s[1]]);
                otherCards[b_s[0]][b_s[1]].setImage(aux_image);
                otherCards[b_s[0]][b_s[1]].setVisible(true);
            }

        cancel_play.setVisible(false);
            System.out.println("Finished!");

    }


    /***
     *
     * @param assemble_array -> Size 6 array, that says the boards for me to display in order. Only thing I need to get
     */
    public void set_board_image(int[] assemble_array) {
        //Displays Boards in the beggining of the game
        for (int i = 0; i < BOARDS_NUMBER; i++) {
           // System.out.println("/Tabuleiros/Tab" + assemble_array[i] + ".JPG");
            Image aux_image = new Image(getClass().getResource("/Tabuleiros/Tab" + (assemble_array[i]-1) + ".JPG").toExternalForm());   //FIXME: values generated are wrong, cant be 12
            myBoards[i].setImage(aux_image);

            //Rotate board
            myBoards[i].setRotate(90 * (game.GameBoards[assemble_array[i]-1].orientation - 1)); //FIXME: values generated are wrong, cant be 12
           // System.out.println(game.GameBoards[assemble_array[i]].orientation);
            slot_label_numeration(i, game.GameBoards[assemble_array[i]-1].orientation);//FIXME: values generated are wrong, cant be 12
        }
    }

    public void slot_label_numeration(int board, int orientation) {

        double x, y;

        switch (orientation) {
            case 1:
                slot_prize[board] = 1;
                break;
            case 2:
                slot_prize[board] = 3;
                break;
            case 3:
                slot_prize[board] = 2;
                break;
            case 4:
                slot_prize[board] = 0;
                break;

        }

        int b_s[] = new int[2];
        b_s[0] = board;
        b_s[1] = slot_prize[board];
        System.out.println("Board and slot: " + board + " " + b_s[1]);
        set_prize_cards(b_s);

        for (int i = 0; i < 4; i++) {

            //Sets Label place
            x = myBoards[board].getLayoutX() + (i % 2 * (myBoards[board].getFitWidth() * scale_x / 2));
            y = myBoards[board].getLayoutY() + ((int) (i / 2) * (myBoards[board].getFitHeight() * scale_y / 2));

            labels[board][i].setLayoutX(x);
            labels[board][i].setLayoutY(y);
            if (i == 0) {
                labels[board][i].setText(Integer.toString(i + 1));
            } else labels[board][i].setText(Integer.toString(i));


            if (i == slot_prize[board]) {
                labels[board][i].setVisible(false);     //TODO: set all true when other round starts
            }
        }

        //basta fazer o slot prize invisible no fim
        int aux=orientation;
        int help = 0;
        while (1 < aux) {
            help = Integer.parseInt(labels[board][0].getText());
            labels[board][0].setText(labels[board][2].getText());
            labels[board][2].setText(labels[board][3].getText());
            labels[board][3].setText(labels[board][1].getText());
            labels[board][1].setText(Integer.toString(help));
            aux--;
        }
/*
        for(int i=1;i<4;i++){
            System.out.println("label text: "+labels[board][i].getText());
            if(labels[board][i].getText().equals(Integer.toString(1))){
                if(labels[board][i-1].getText().equals(Integer.toString(3))) {


                    int b_s[] = new int[2];
                    b_s[0] = board;
                    b_s[1] = i;
                    System.out.println("Board and slot: " + board + " " + b_s[1]);
                    set_prize_cards(b_s);
                    return;
                }
            }

 */
        }





    /**
     * @param e Detects which board was clicked. Should return something in the future
     */
    //How will I reinitiate card pressed?
    //TODO: Add verification for errors or return from functions
    //TODO: Alterar slot numeration pq está errado
    public int[] board_clicked(MouseEvent e) {
        int[] board_and_slot = new int[]{-1, 0, 0};
        //TODO: change to value given by server/logic (TOKEN)
        if (/*ID == TOKEN*/!waiting) {
          //  System.out.println("pressed on:" + e.getSceneX());
            double x, y;
            ImageView board_clicked = (ImageView) e.getSource();
            board_and_slot[0]++;    //goes to 0 to not have error
            //Verifies which slot was clicked on which board
            while (board_clicked != myBoards[board_and_slot[0]]) {
                board_and_slot[0]++;
            }
            //x=e.getX();
            //y=e.getY();
            x = e.getSceneX() - myBoards[board_and_slot[0]].getLayoutX();
            y = e.getSceneY() - myBoards[board_and_slot[0]].getLayoutY();


            if (x >= (board_clicked.getFitWidth() * scale_x / 2)) {
                board_and_slot[2] = 1;
            }
            if (y >= (board_clicked.getFitHeight() * scale_y / 2)) {
                board_and_slot[1] = 1;
            }

            //Prints the values for debugging
            //System.out.println("Card pressed: " + card_pressed);
            //System.out.println("x=" + x + "; y=" + y);
            System.out.println("Slot[" + board_and_slot[1] + "][" + board_and_slot[2] + "] was pressed on Board" + board_and_slot[0]);

            //System.out.println(player.PlayCard(game.GameMap, player, board_and_slot, card_pressed));

            //Verifies if we are able to place card or not
            //TODO: Still need to add if slot is available, if special sot or not
            //TODO: Add Eduardo PlayCard function
            if ((card_pressed >= 0) && (card_pressed < 6) && (card_placed[card_pressed] == 0) && player.PlayCard(game.GameMap, player.CardsID, board_and_slot, player.HandCards.get(card_pressed).ID, game)) {
                place_card_in_slot(board_and_slot, myCards[card_pressed]);
                set_board_highlight(player.CardsID, board_and_slot);
                card_placed[card_pressed] = 1;
            }

            board_slot[0]=board_and_slot[0];
            board_slot[1]=board_and_slot[1];
            board_slot[2]=board_and_slot[2];

            return board_and_slot;      //Returns Board and slot pressed
        }

        return board_and_slot;          //Will return mandatorily board=-1. ERROR

    }

    //Adicionar highlight method
    //TODO: tenho de passar ID da carta
    //TODO: Add logic for only be able to move one card
    public void card_selected(MouseEvent e) {
        //Only available if it is this player turn
        //TODO: change to value given by server/logic (TOKEN)
        if (/*ID == TOKEN*/!waiting && !cancel_play.isVisible()) {
            if (card_pressed != -1) {
                myHighlights[card_pressed].setVisible(false);
            }
            //Verifies which card was clicked (i)
            int i = 0;
            ImageView card_clicked = (ImageView) e.getSource();
            while (card_clicked != myCards[i]) {
                i++;
            }

            //Sees if card is in hand or already played
            //Basically verifies if play is available
            if (card_placed[i] == 0) {
                card_pressed = i;
                myHighlights[card_pressed].setVisible(true);
            } else {
                card_pressed = -1;
            }

           // System.out.println("Card " + card_pressed + " was clicked!");
        }
        System.out.println("using ability: "+ using_ability);
        if(using_ability){
            switch (abilityNumber){
                case 0:
                    //System.out.println("ERROR\nERROR\nERROR\nERROR\nERROR\nERROR\nShouldn't work because 0 this question doesn't have to appear");
                    break;

                case 1: //Seduction, 2 clicks on cards/boards
                    //TODO: select 2 cards to trade
                    break;

                case 2: //Gun, kills card and disables(Invisible)
                    //TODO: select a card to detect


                    break;

                case 3: //Flag, Eduardo handles
                    System.out.println("ERROR\nERROR\nERROR\nERROR\nERROR\nERROR\nShouldn't enter here, cause Flag is handled by the logic");
                    break;

                case 4: //Shield protects any card
                    ImageView shielded = (ImageView) e.getSource();
                    for(int i=0;i<6;i++){
                        if((shielded.getLayoutX()>myBoards[i].getLayoutX()) && shielded.getLayoutX()<(myBoards[i].getLayoutX()+myBoards[i].getFitWidth())){
                            if((shielded.getLayoutY()>myBoards[i].getLayoutY()) && shielded.getLayoutY()<(myBoards[i].getLayoutY()+myBoards[i].getFitHeight())){
                                board_slot[0]=i;
                            }else{
                                i+=3;
                                board_slot[0]=i;
                            }

                            if(shielded.getLayoutX()>myBoards[i].getLayoutX()+(myBoards[i].getFitWidth()/2)){
                                board_slot[2]=1;
                            }else{
                                board_slot[2]=0;
                            }
                            if(shielded.getLayoutY()>myBoards[i].getLayoutY()+(myBoards[i].getFitHeight()/2)){
                                board_slot[1]=1;
                            }else{
                                board_slot[1]=0;
                            }
                        }
                    }
                    place_shield(board_slot);
                    using_ability=false;
                    reconstruct_hand();
                    break;

                case 5: //Conspiracy, chooses prize card to switch
                    break;
            }

        }


    }


    //TODO: Maybe add drag later
    //cancels play and it comes back to hand, For simplicity will only be able to return to hand, nothing else. Which means reinitiating other components
    public void cancel_play_button() {
        System.out.println("Cancel play button pressed!");
        int b_s[];
        if (ability_decision) {
            double x;

            cancel_play.setLayoutX(button_x_stand);
            next_turn.setLayoutX(button_x_stand);

            for (int i = 0; i < 6; i++) {
                myCards[i].setDisable(true);
                if (card_placed[i] == 0) {
                    myCards[i].setVisible(true);
                }
            }
            PerformActionLabel.setVisible(false);
            cancel_play.setVisible(false);
            next_turn.setVisible(false);
            //next player turn, send message
        } else {
            b_s = highlight_remove(myCards[card_pressed].getLayoutX(), myCards[card_pressed].getLayoutY());
            //Changes GUI
            myCards[card_pressed].setLayoutX(card_x);
            myCards[card_pressed].setLayoutY(card_y);

           // System.out.println("Card to be added: /Cards/card" + player.HandCards.get(card_pressed).ID + ".jpg");
            Image aux_image = new Image(Objects.requireNonNull(getClass().getResource("/Cards/card" + (player.HandCards.get(card_pressed).ID) + ".PNG")).toExternalForm());
            myCards[card_pressed].setImage(aux_image);

            myCards[card_pressed].setFitHeight((myBoards[0].getFitHeight() * scale_y / 2.0) * (myCards[card_pressed].getFitHeight() * scale_y) / card_slot_h_ratio);

            //Resets auxiliary values
            card_placed[card_pressed] = 0;
            card_pressed = -1;
            cancel_play.setVisible(false);
            player.CancelPlay(game.GameMap, player, b_s, card_pressed);
        }
    }

    //add if double click, next turn show
    //Only validates play when button is pressed, until then card can still change
    //FIXME: several tests with this
    //TODO: only available if play has been made
    public void next_turn_button() throws IOException {
        System.out.println("Next turn button pressed!");
        //System.out.println("solving: "+ solving +"ability_decision: "+ ability_decision+ "using ability: "+ using_ability);

        if(solving) {
            if (ability_decision) {
                //show cards again
                reconstruct_hand();
                ability_decision = false;
                //FIXME: get ability from eduardo structure
                apply_ability(4);

            }else if (using_ability) {  //Will use confirm button to validate play
                // 1 - Seduction, 2 - Gun, 3 - Flag, 4 - Shield, 5 - Conspiracy
                switch (abilityNumber) {
                    case 0:
                        System.out.println("THIS ABILITY DOESN´T NEED THE BUTTON TO BE PRESSED");
                        break;

                    case 1: //Seduction, 2 clicks
                        break;

                    case 2: //Gun, kills card and disables(Invisible)
                        break;

                    case 3: //Flag Eduardo handles
                        break;

                    case 4: //Shield protects any card
                        break;

                    case 5: //Conspiracy, chooses prize card to switch
                        //TODO: know which board is being played and where the card is
                        //TODO: get this card Image View
                        //TODO: apply a change_card function
                        System.out.println("Player chose to conspire");
                        abilityNumber = 0;
                        //change_card()
                        solving=false; //FIXME: test wise

                        break;
                }
                reconstruct_hand();
                using_ability = false;

            }
        }else if(!waiting && cancel_play.isVisible()){
            /**
             * Standard Button when playing cards
             */
            //Hides cancel button
            cancel_play.setVisible(false);
            myHighlights[card_pressed].setVisible(false);
            // play_made=false;
/*
            int count = 0;
            //test purposes
            for (int i = 0; i < 6; i++) {
                if (card_placed[i] == 1) {
                    count++;
                }
            }

 */

            //player, board, posiçao do slot, carta
            int trama[]=new int[5];
            trama[0]=player.CardsID;
            trama[1]=board_slot[0];
            trama[2]=board_slot[1];
            trama[3]=board_slot[2];
            trama[4]=player.HandCards.get(card_pressed).ID;
            System.out.println("Card pressed before sending: "+trama[4]);
            Client.sendInfo(2, trama);
            initiate_round();

            /*
            System.out.println("Count: "+count);
            if (count == 4) {
                solve_board();
            }



             */
        }

    }


    //Sets hand back as it was to not have bugs
    void reconstruct_hand(){
        for (int i = 0; i < 6; i++) {
            if (card_placed[i] == 0) {
                myCards[i].setVisible(true);   //FIXME: maybe put some opacity in this
            }
        }

        cancel_play.setLayoutX(button_x_stand);
        next_turn.setLayoutX(button_x_stand);


        cancel_play.setText("Skip Turn");
        next_turn.setText("Confirm");
        next_turn.setVisible(true);
        cancel_play.setVisible(false);

        PerformActionLabel.setVisible(false);
        card_conspiration.setVisible(false);
        label_conspiration.setVisible(false);
    }

    /**
     * This function is called when verifying if the button has been pressed when asking for ability.
     * It reinitiates hand to simplify and then applys the GUI needed for each ability
     * Somo of them won't even do, so we just have there and error code
     *
     *
      * @param //ability ability number received by logic
     */

    public void hide_hand_cards(){
        for (int i = 0; i < 6; i++) {
            if (card_placed[i] == 0) {
                myCards[i].setVisible(false);   //FIXME: maybe put some opacity in this
            }
        }

        next_turn.setVisible(false);
        cancel_play.setVisible(false);
    }
    void apply_ability(int ability) {

        int b_s[];

        reconstruct_hand();
        using_ability=true;

        switch (ability) {
            case 0:
                System.out.println("ERROR\nERROR\nERROR\nERROR\nERROR\nERROR\nShouldn't work because 0 this question doesn't have to appear");
                break;

            case 1: //Seduction, 2 clicks on cards/boards
                hide_hand_cards();
                PerformActionLabel.setVisible(true);
                PerformActionLabel.setText("Select 2 cards to switch places!");
                //TODO: select 2 cards to trade
                break;

            case 2: //Gun, kills card and disables(Invisible)
                //TODO: select a card to detect
                PerformActionLabel.setVisible(true);
                PerformActionLabel.setText("Select a card to kill!");
                hide_hand_cards();
                break;

            case 3: //Flag, Eduardo handles
                System.out.println("ERROR\nERROR\nERROR\nERROR\nERROR\nERROR\nShouldn't enter here, cause Flag is handled by the logic");
                break;

            case 4: //Shield protects any card
                PerformActionLabel.setVisible(true);
                PerformActionLabel.setText("Select a card to shield!");
                hide_hand_cards();
                abilityNumber=4;
                break;

            case 5: //Conspiracy, chooses prize card to switch
                conspire_question();
                //conspire=true;
                abilityNumber=5;        //basically boolean of conspire
                break;
        }

    }
}
