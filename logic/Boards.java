public class Boards {
    private static final int NumOfSlots = 4;

    int ID; // Identifica o numero de cada tabuleiro
    Slots slots[] = new Slots[NumOfSlots];
    int orientation[] = { 1, 2, 3, 4 }; // (i-1)*90 graus
    int board_power;

    public static void IsPlayable() {
    }
}
