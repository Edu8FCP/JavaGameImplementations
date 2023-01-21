package gui_handle.estoril_1942;

public class Slots {
    int ID; // numero associado ao slot dentro do board
    boolean Visibility; // depende se o espaço do tabuleiro é visivel ou não
    int player = 0; // vai dizer a que jogador pertence a carta no slot
    boolean shield;
    int linha, coluna; // position
    Cards Card = null; // carta que ocupa o slot

    // CONSTRUTOR
    public Slots(){
        ID = 0;
        Visibility = false;
        player = 0;
        shield = false;
        Card = null;
    }
}