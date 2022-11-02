public class Cards {
    int Power; // strengh of the card
    String Nationality; // int or string?
    int PV; // Victory Points
    int Player; // Players that has the vard
    int[] abilities = { 0, 0, 0 }; // array of 3 values
    boolean gender; // 0 - homem, 1 - mulher
    // each position represents the possibility to have a power
    // 0 representes no power.

    // Em vez de sobrecarregar o GAME vou passar para aqui e o build fica a ação de
    // inicialização por intermédio do construtor - é possível?

    /******************************************
     **** >>>>>>> CONSTRUIR CARTAS <<<<<<< ****
     ******************************************/
    /*
     * Cartas - Estrutura onde vão ser guardadas as cartas após a inicialização
     * CardPowers - Força das cartas
     * PV - Pontos de vitória de cada carta
     * Nacionalidades - estrutura com as nacionalidades para as cartas
     * ImagemCartas - estrutura com as imagens para as cartas
     */
    public static void BuildCards(Cards Cartas[], int CardPowers[], int PV[], String Nacionalidades[],
            String ImagemCartas[]) {

        for (int ID = 0; ID < 12; ID++) {
            Cartas[ID].Nationality = Nacionalidades[ID];
            Cartas[ID].PV = PV[ID];
            Cartas[ID].Player = 0; // ninguém tem a carta
            Cartas[ID].Power = CardPowers[ID];
            // Cartas[ID].gender = slots;
        }
        // Adicionar atributo das imagens - Tabs[] vai conter as imagens

        // na estrutura Cards passada vão as cartas
    }

    public void CheckAbilities() {

    }

    public void UseAbility(Cards Card, Map Mapa, Slots Posicao) {
        for (int i = 0; i < 3; i++) {
            if (abilities[i] != 0) {
                // lança uma pergunta se a pessoa quer usar o poder
                switch (abilities[i]) {
                    case (1): // Seduction
                        Abilities.seduction(Posicao, Mapa);
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
