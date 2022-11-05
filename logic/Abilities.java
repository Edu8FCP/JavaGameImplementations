public class Abilities {
    /*
     * Vamos sempre precisar:
     * slot - A posição onde está a carta que se pretende "atacar", algo do tipo
     * Map.Board[1][2].Slot[1][1]
     * map - precisamos do mapa do jogo para verificar os restantes
     * ??? da carta que estamos a usar
     */

    // FIXME, depois ver porquê?

    /*****************************************
     **** >>>>>>> SEDUCTION POWER <<<<<<< ****
     *****************************************/
    /*
     * PosicaoBoardOrigin - Tabuleiro onde está a carta que vai usar a habilidade
     * Mapa - Mapa de jogo com a situação atual
     * PosicaoBoardTarget - Posicao do Board alvo do poder
     * PosicaoSlotTarget - Posição do Slot alvo do poder
     * PosicaoSlotOrigin - Posicao de onde vem a carta
     */
    public static void seduction(Map Mapa, Boards PosicaoBoardOrigin, Slots PosicaoSlotOrigin,
            Boards PosicaoBoardTarget, Slots PosicaoSlotTarget) { // mudei para static pq estava a
        int BOR, BOC; // BOR/C - Board Origin Row/Column
        int BTR, BTC; // BTR/C - Board Target Row/Column
        int STR, STC; // STR/C - Slot Target Row/Column
        int SOR, SOC; // SOR/C - Slot Origin Row/Column
        BOR = PosicaoBoardOrigin.linha;
        BOC = PosicaoBoardOrigin.coluna;
        BTR = PosicaoBoardTarget.linha;
        BTC = PosicaoBoardTarget.coluna;
        STR = PosicaoSlotTarget.linha;
        STC = PosicaoSlotTarget.coluna;
        SOR = PosicaoSlotOrigin.linha;
        SOC = PosicaoSlotOrigin.coluna;
        // O tabuleiro tem de estar adjacente
        if ((BTR != (BOR - 1)) && (BTR != (BOR + 1)) && (BTC != (BOC - 1)) && (BTC != (BOC + 1))) {
            // MENSAGEM DE IMPOSSÍVEL JOGAR ALI!
            return;
        }
        if (Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].player != 0) {
            // MENSAGEM DE SLOT JÁ OCUPADO
            return;
        }
        if (Mapa.Tabuleiros[BOR][BOC].slots[SOR][SOC].shield == true) {
            // MENSAGEM DE CARTA COM ESCUDO
            return;
        }
        // Se tudo for válido copiamos a entidade
        Mapa.Tabuleiros[BTR][BTC].slots[STR][STC] = Mapa.Tabuleiros[BOR][BOC].slots[SOR][SOC];
        // Limpamos o espaço original da entidade que foi transportada
    }

    /***********************************
     **** >>>>>>> GUN POWER <<<<<<< ****
     ***********************************/
    /*
     * PosicaoBoardOrigin - Tabuleiro onde está a carta que vai usar a habilidade
     * Mapa - Mapa de jogo com a situação atual
     * PosicaoBoardTarget - Posicao do Board alvo do poder para garantir que está no
     * mesmo tabuleiro
     * PosicaoSlotTarget - Posição do Slot alvo do poder
     */
    public static void kill(Map Mapa, Boards PosicaoBoardOrigin, Boards BoardPosicaoTarget, Slots PosicaoSlotTarget) {

    }

    public static void conspire(Map Mapa, Boards PosicaoBoardOrigin) {

    }

    public static void protect(Map Mapa, Slots PosicaoSlot, Boards PosicaoBoard) {

    }

    public static void nations(Map Mapa, Slots PosicaoSlot, Boards PosicaoBoard) {

    }
}
