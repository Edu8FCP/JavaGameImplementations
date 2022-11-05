public class Boards {
    // private static final int NumOfSlots = 4;

    int ID; // Identifica o numero de cada tabuleiro
    Slots[][] slots = new Slots[2][2];
    int orientation;// { 1, 2, 3, 4 }; // (i-1)*90 graus
    int board_power;
    int linha, coluna; // position

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

    public static void BuildBoards(Boards Tabuleiros[], String Tabs[]) {
        for (int ID = 0; ID < 12; ID++) {
            Slots[][] slot = new Slots[2][2];
            Tabuleiros[ID].ID = ID;
            Tabuleiros[ID].orientation = 1;
            Tabuleiros[ID].board_power = ID;
            Tabuleiros[ID].slots = slot;
            Tabuleiros[ID].linha = 0;
            Tabuleiros[ID].coluna = 0;
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

    public static int SolveBoard(Boards Tabuleiros) {
        /*******************************************
         **** >>>>>>> CICLO HABILIIDADES <<<<<<< ****
         ********************************************/

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (Tabuleiros.slots[i][j].player != 0) {
                    switch (Tabuleiros.slots[i][j].player) {
                        // Vemos a quem pertence a carta
                        case (1): // Perguntar se quer usar habilidade
                        case (2):
                        case (3):
                        case (4):
                        case (5):
                    }

                }
            }
        }

        /*************************************
         **** >>>>>>> CICLO FORÇA <<<<<<< ****
         *************************************/
        int[] forca = new int[5];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (Tabuleiros.slots[i][j].player != 0) {
                    switch (Tabuleiros.slots[i][j].player) {
                        // Vemos a quem pertence a carta
                        case (1):
                            forca[0] += Tabuleiros.slots[i][j].Card.Power;
                        case (2):
                            forca[1] += Tabuleiros.slots[i][j].Card.Power;
                        case (3):
                            forca[2] += Tabuleiros.slots[i][j].Card.Power;
                        case (4):
                            forca[3] += Tabuleiros.slots[i][j].Card.Power;
                        case (5):
                            forca[4] += Tabuleiros.slots[i][j].Card.Power;
                    }

                }
            }
        }
        int winner = max(forca);
        return ((winner != 0) ? winner + 1 : 0);

    }

    public static int max(int array[]) {
        int max = 0, posmax = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                posmax = i;
            }
        }
        return posmax;
    }
}
