public class Cards {
    int Power; // strengh of the card
    String Nationality; // int or string?
    int PV; // Victory Points
    int Player; // Players that has the vard
    int[] abilities = { 0, 0, 0 }; // array of 3 values
    // each position represents the possibility to have a power
    // 0 representes no power.

    public void CheckAbilities() {

    }

    public void UseAbility(Cards card) {
        for (int i = 0; i < 3; i++) {
            if (abilities[i] != 0) {
                // lança uma pergunta se a pessoa quer usar o poder
                switch (abilities[i]) {
                    case (1): // Seduction
                        // Selecionar uma carta - GUI
                        // Verificar se a carta está num tabuleiro adjacente
                        // Verificar se a carta escolhida não tem um escudo
                        break;
                    case (2): // Gun
                        // Selecionar uma carta - GUI
                        // Verificar se a carta está no mesmo tabuleiro
                        // Verificar se a carta escolhida não tem um escudo
                        break;
                    case (3): // Flags
                        // Selecionar uma carta - GUI
                        // Contar quantas cartas têm a mesma nacionalidade nos tabuleiros adjacentes
                        break;
                    case (4): // Shield
                        // Selecionar uma carta - GUI
                        // Verificar se já não tem um escudo
                        break;
                    case (5): // Conspiricy
                        // Allow to draw a card, compare with prize card and choose which one is the
                        // prize
                        break;
                }
            } else
                break; // faz break se encontrar um zero
        }
    }

}
