public class Boards {
    // private static final int NumOfSlots = 4;

    int ID; // Identifica o numero de cada tabuleiro
    Slots[][] slots = new Slots[2][2];
    int orientation;// { 1, 2, 3, 4 }; // (i-1)*90 graus
    int board_power;

    /**********************************************
     **** >>>>>>> CONSTRUIR TABULEIROS <<<<<<< ****
     **********************************************/
    /*
     * Tabuleiros - estrutura onde vão ser construídos e guardados os tabuleiros na
     * inicialização
     * BoardPowers - estrutura com os poderes para construir os tabuleiros
     * Tabs - estrutura onde estão guardadas as imagens
     * Slots - inicializar a estrutura dos tabuleiros
     */

    public static void BuildBoards(Boards Tabuleiros[], int BoardPowers[], String Tabs[], Slots slot[][]) {
        for (int ID = 0; ID < 12; ID++) {
            Tabuleiros[ID].ID = ID;
            Tabuleiros[ID].orientation = 1;
            Tabuleiros[ID].board_power = BoardPowers[ID];
            Tabuleiros[ID].slots = slot;
        }
        // Adicionar atributo das imagens - Tabs[] vai conter as imagens

    }

    /*************************************
     **** >>>>>>> IS PLAYABLE <<<<<<< ****
     *************************************/
    /*
     * Slot -
     */
    public static void IsPlayable(Slots Slot) {
    }
}
