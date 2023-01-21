package gui_handle.estoril_1942;
import java.util.Scanner;

public class Abilities {
    /*
    // 1 - SEDUÇÃO, 2 - KILL, 3 - NATIONS, 4 - PROTECT, 5 - CONSPIRE
     * Vamos sempre precisar:
     * slot - A posição onde está a carta que se pretende "atacar", algo do tipo
     * Map.Board[1][2].Slot[1][1]
     * map - precisamos do mapa do jogo para verificar os restantes
     * ??? da carta que estamos a usar
     */

    /*****************************************
     **** >>>>>>> CHECK ABILITIES <<<<<<< ****
     *****************************************/
    /*
     * Carta - Recebe uma carta e vê se esta tem uma habilidade para usar
     * Em caso AFIRMATIVO pergunta se quer usar
     */

    public static boolean CheckAbilities(Cards Carta) {
        /* TESTES */
        if(Carta == null){
            System.out.println("Erro ao carregar a Carta");
            return false;
        } 

        /* CÓDIGOS */
        if(Carta.abilities[0] != 0){ // FIX - aqui é == mas vou manter != para debug
            return false;
        } else{
            System.out.println("Queres usar a habilidade: ?");
            // TODO - Perguntar ao jogador se este quer usar uma habilidade
            // SERVER - Envia a trama para o jogador
            return true; // True significa que tem habilidade
        } 
    }

    /*****************************************
     **** >>>>>>> ASK ABILITIES <<<<<<< ****
     *****************************************/
    /*
     * Carta - Recebe uma carta e vê se esta tem uma habilidade para usar
     * Em caso AFIRMATIVO pergunta se quer usar
     */

    public static String AskAbilitie(Cards Carta, int i){
        /* TESTES */
        if(Carta == null){
            System.out.println("Erro ao carregar os parâmetros da função AskAbilitie");
            return null;
        }

        /* CÓDIGOS */
        switch(Carta.abilities[i]){
            case(1): System.out.println("Sedução");
                Scanner s = new Scanner(System.in); // Depois temos de ver como ler diretamente da GUI
                String n = s.nextLine();
                s.close(); // Ele estava a pedir para se encerrar
                switch(n){
                    case("y"): return n;
                    case("n"): return n;
                }
                break;
            case(2): System.out.println("Kill"); 
                Scanner s2 = new Scanner(System.in); // Depois temos de ver como ler diretamente da GUI
                String n2 = s2.nextLine();
                s2.close(); // Ele estava a pedir para se encerrar
                switch(n2){
                    case("y"): return n2;
                    case("n"): return n2;
                }                    
                break;
            case(5): System.out.println("Conspire");
                Scanner s3 = new Scanner(System.in); // Depois temos de ver como ler diretamente da GUI
                String n3 = s3.nextLine();
                s3.close(); // Ele estava a pedir para se encerrar
                switch(n3){
                    case("y"): return n3;
                    case("n"): return n3;
                }  
                break;
            case(4): System.out.println("Protect");
                Scanner s4 = new Scanner(System.in); // Depois temos de ver como ler diretamente da GUI
                String n4 = s4.nextLine();
                s4.close(); // Ele estava a pedir para se encerrar
                switch(n4){
                    case("y"): return n4;
                    case("n"): return n4;
                }  
                break;
            case(3): System.out.println("Nations");
                Scanner s5 = new Scanner(System.in); // Depois temos de ver como ler diretamente da GUI
                String n5 = s5.nextLine();
                s5.close(); // Ele estava a pedir para se encerrar                    switch(n5){
                switch(n5){
                    case("y"): return n5;
                    case("n"): return n5;
                }
            }  
        return null;
    }

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
    public static void Seduction(Map Mapa, int[] origem, int[] destino) { // mudei para static pq estava a
        /* TESTES */
        if(Mapa == null || origem == null || destino == null){
            System.out.println("Erro ao carregar os parâmetros da função Sedução");
            return ;
        }

        /* CÁLCULO PARA O DESTINO */
        int BTR=0, BTC=0; // BTR/C - Board Target Row/Column
        int STR, STC; // STR/C - Slot Target Row/Column
        if(destino[0]<=2){ // Cálculo da posição do Board
            BTR = 0;
        } else BTR = 1;
        if(destino[0] == 0 || destino[0] == 3){
            BTC = 0;
        } else if(destino[0] == 1 || destino[0] == 4){
            BTC = 1;
        } else if(destino[0] == 2 || destino[0] == 5){
            BTC = 2;
        }
        STR = destino[1];
        STC = destino[2];

        /* CÁLCULO PARA A ORIGEM */
        int BOR=0, BOC=0; // BTR/C - Board Target Row/Column
        int SOR, SOC; // STR/C - Slot Target Row/Column
        if(origem[0]<=2){ // Cálculo da posição do Board
            BOR = 0;
        } else BOR = 1;
        if(origem[0] == 0 || origem[0] == 3){
            BOC = 0;
        } else if(origem[0] == 1 || origem[0] == 4){
            BOC = 1;
        } else if(origem[0] == 2 || origem[0] == 5){
            BOC = 2;
        }
        SOR = origem[1];
        SOC = origem[2];

        // O tabuleiro tem de estar adjacente
        if ((BTR != (BOR - 1)) && (BTR != (BOR + 1)) && (BTC != (BOC - 1)) && (BTC != (BOC + 1))) {
            // MENSAGEM DE IMPOSSÍVEL JOGAR ALI!
            System.out.println("Impossível jogar neste tabuleiro, não cumpre os requisitos de adjacência");
            return;
        }
        if (Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].player != 0) {
            // MENSAGEM DE SLOT JÁ OCUPADO
            System.out.println("Impossível seduzir para aqui, espaço já ocupado");
            return;
        }
        if (Mapa.Tabuleiros[BOR][BOC].slots[SOR][SOC].shield == true) {
            System.out.println("Impossível seduzir esta carta, protegida com um escudo");
            // MENSAGEM DE CARTA COM ESCUDO
            return;
        }

        // Se tudo for válido copiamos a entidade
        Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].Card = Mapa.Tabuleiros[BOR][BOC].slots[SOR][SOC].Card;
        Mapa.Tabuleiros[BOR][BOC].slots[SOR][SOC].Card = null; // TROCA A CARTA DE POSIÇÃO
        return;
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
    public static void Kill(Map Mapa, int[] destino, int OriginBoard) {
        /* TESTES */
        if(Mapa == null || destino == null || OriginBoard == -1){
            System.out.println("Erro ao carregar os parâmetro da função Kill");
            return ;
        }

        /* CÓDIGOS */
        int BTR=0, BTC=0; // BTR/C - Board Target Row/Column
        int STR, STC; // STR/C - Slot Target Row/Column
        if(destino[0]<=2){ // Cálculo da posição do Board
            BTR = 0;
        } else BTR = 1;
        if(destino[0] == 0 || destino[0] == 3){
            BTC = 0;
        } else if(destino[0] == 1 || destino[0] == 4){
            BTC = 1;
        } else if(destino[0] == 2 || destino[0] == 5){
            BTC = 2;
        }
        STR = destino[1];
        STC = destino[2];

        int BOR=0, BOC=0;

        if(OriginBoard <=2){ // Cálculo da posição do Board
            BOR = 0;
        } else BOR = 1;
        if(OriginBoard == 0 || OriginBoard == 3){
            BOC = 0;
        } else if(OriginBoard == 1 || OriginBoard == 4){
            BOC = 1;
        } else if(OriginBoard == 2 || OriginBoard == 5){
            BOC = 2;
        }

        // O tabuleiro tem de estar adjacente
        if ((BTR != (BOR - 1)) && (BTR != (BOR + 1)) && (BTC != (BOC - 1)) && (BTC != (BOC + 1))) {
        // MENSAGEM DE IMPOSSÍVEL JOGAR ALI!
            System.out.println("Impossível jogar neste tabuleiro, não cumpre os requisitos de adjacência");
            return;
        }

        if (Mapa.Tabuleiros[BOR][BOC].slots[STR][STC].shield == true) {
            System.out.println("Impossível derrotar esta carta, protegida com um escudo");
            // MENSAGEM DE CARTA COM ESCUDO
            return;
        }

        // Se tudo for válido copiamos a entidade
        Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].Card = null;
        // Tira a carta do tabuleiro - A carta mantém se na mão porque só estavamos a fazer get
        // Limpamos o espaço original da entidade que foi transportada
    }

    public static void Conspire(Map Mapa, int OriginBoard, Game game) {
        /* TESTES */
        if(Mapa == null || OriginBoard == -1 || game == null){
            System.out.println("Erro ao carregar os parâmetros da função Conspire");
            return;
        }

        /* CÓDIGOS */
        int BOR = 0, BOC = 0;
        if(OriginBoard <=2){ // Cálculo da posição do Board
            BOR = 0;
        } else BOR = 1;
        if(OriginBoard == 0 || OriginBoard == 3){
            BOC = 0;
        } else if(OriginBoard == 1 || OriginBoard == 4){
            BOC = 1;
        } else if(OriginBoard == 2 || OriginBoard == 5){
            BOC = 2;
        }

        Cards aux = new Cards();
        aux = game.Cartas.get(0);

    }

    public static void Protect(Map Mapa, int[] destino, Game game) {
        /* TESTE */
        if(Mapa == null || destino == null || game == null){
            System.out.println("Erro ao carregar os parâmetros da função Protect");
            return;
        }
        
        /* CÓDIGOS */
        /* CÁLCULO PARA O DESTINO */
        int BTR=0, BTC=0; // BTR/C - Board Target Row/Column
        int STR, STC; // STR/C - Slot Target Row/Column
        if(destino[0]<=2){ // Cálculo da posição do Board
            BTR = 0;
        } else BTR = 1;
        if(destino[0] == 0 || destino[0] == 3){
            BTC = 0;
        } else if(destino[0] == 1 || destino[0] == 4){
            BTC = 1;
        } else if(destino[0] == 2 || destino[0] == 5){
            BTC = 2;
        }
        STR = destino[1];
        STC = destino[2];

        if(Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].shield == false){
            Mapa.Tabuleiros[BTR][BTC].slots[STR][STC].shield = true;
        } else System.out.println("Esta carta já tem escudo");
    }

    public static void Nations(Map Mapa, int[] posicao, Game game) {
        /* TESTES */
        if(game == null || posicao == null){
            System.out.println("Erro ao carregar os parâmetros da função Nations");
            return;
        }

        /* CÓDIGOS */
        int BOR=0, BOC=0; // BTR/C - Board Target Row/Column
        int SOR, SOC; // STR/C - Slot Target Row/Column
        if(posicao[0]<=2){ // Cálculo da posição do Board
            BOR = 0;
        } else BOR = 1;
        if(posicao[0] == 0 || posicao[0] == 3){
            BOC = 0;
        } else if(posicao[0] == 1 || posicao[0] == 4){
            BOC = 1;
        } else if(posicao[0] == 2 || posicao[0] == 5){
            BOC = 2;
        }
        SOR = posicao[1];
        SOC = posicao[2];

        if(BOR - 1 > -1){  // VÊ SE EXISTE TABULEIRO ADJACENTE
            for(int i = 0; i<2; i++){
                for(int j = 0; j<2; j++){
                    if(i == SOR && j == SOC){
                        // DO NOTHING :)
                    } else if((game.GameMap.Tabuleiros[BOR][BOC].slots[SOR][SOC].Card.Nationality == game.GameMap.Tabuleiros[BOR-1][BOC].slots[i][j].Card.Nationality) && game.GameMap.Tabuleiros[BOR-1][BOC].slots[i][j].player != 6 && game.GameMap.Tabuleiros[BOR-1][BOC].slots[i][j].player != 0){
                        game.GameMap.Tabuleiros[BOR][BOC].slots[SOR][SOC].Card.Power++; // Não sei se isto funciona ou se preciso de tirar do array list
                    }
                    
                }
            }
        }
        if(BOR + 1 < 3){
            for(int i = 0; i<2; i++){
                for(int j = 0; j<2; j++){
                    if(i == SOR && j == SOC){
                        // DO NOTHING :)
                    } else if((game.GameMap.Tabuleiros[BOR][BOC].slots[SOR][SOC].Card.Nationality == game.GameMap.Tabuleiros[BOR+1][BOC].slots[i][j].Card.Nationality) && game.GameMap.Tabuleiros[BOR+1][BOC].slots[i][j].player != 6 && game.GameMap.Tabuleiros[BOR+1][BOC].slots[i][j].player != 0){
                        game.GameMap.Tabuleiros[BOR][BOC].slots[SOR][SOC].Card.Power++; // Não sei se isto funciona ou se preciso de tirar do array list
                    }
                    
                }
            }
        }
        if(BOC - 1 > -1){
            for(int i = 0; i<2; i++){
                for(int j = 0; j<2; j++){
                    if(i == SOR && j == SOC){
                        // DO NOTHING :)
                    } else if((game.GameMap.Tabuleiros[BOR][BOC].slots[SOR][SOC].Card.Nationality == game.GameMap.Tabuleiros[BOR][BOC-1].slots[i][j].Card.Nationality) && game.GameMap.Tabuleiros[BOR][BOC-1].slots[i][j].player != 6 && game.GameMap.Tabuleiros[BOR][BOC-1].slots[i][j].player != 0){
                        game.GameMap.Tabuleiros[BOR][BOC].slots[SOR][SOC].Card.Power++; // Não sei se isto funciona ou se preciso de tirar do array list
                    }
                    
                }
            }
        }
        if(BOC + 1 < 4){
            for(int i = 0; i<2; i++){
                for(int j = 0; j<2; j++){
                    if(i == SOR && j == SOC){
                        // DO NOTHING :)
                    } else if((game.GameMap.Tabuleiros[BOR][BOC].slots[SOR][SOC].Card.Nationality == game.GameMap.Tabuleiros[BOR][BOC+1].slots[i][j].Card.Nationality) && game.GameMap.Tabuleiros[BOR][BOC+1].slots[i][j].player != 6 && game.GameMap.Tabuleiros[BOR][BOC+1].slots[i][j].player != 0){
                        game.GameMap.Tabuleiros[BOR][BOC].slots[SOR][SOC].Card.Power++; // Não sei se isto funciona ou se preciso de tirar do array list
                    }
                    
                }
            }
        }
        for(int i = 0; i<2; i++){
            for(int j = 0; j<2; j++){
                if(i == SOR && j == SOC){
                    // DO NOTHING :)
                } else if((game.GameMap.Tabuleiros[BOR][BOC].slots[SOR][SOC].Card.Nationality == game.GameMap.Tabuleiros[BOR][BOC].slots[i][j].Card.Nationality) && game.GameMap.Tabuleiros[BOR][BOC].slots[i][j].player != 6 && game.GameMap.Tabuleiros[BOR][BOC].slots[i][j].player != 0){
                    game.GameMap.Tabuleiros[BOR][BOC].slots[SOR][SOC].Card.Power++; // Não sei se isto funciona ou se preciso de tirar do array list
                }
                
            }

        }
    }
}
