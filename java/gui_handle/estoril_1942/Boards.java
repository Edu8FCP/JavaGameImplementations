package gui_handle.estoril_1942;

public class Boards {
    // private static final int NumOfSlots = 4;

    Slots slots[][];
    int ID; // Identifica o numero de cada tabuleiro
    //Slots[][] slots = new Slots[2][2];
    int orientation;// { 1, 2, 3, 4 }; // (i-1)*90 graus
    int board_power;
    int linha, coluna; // position
    String image; // Para GUI

    /**********************************************
     **** >>>>>>> CONSTRUIR TABULEIROS <<<<<<< ****
     **********************************************/
    /*
     * Tabuleiros - estrutura onde vão ser construídos e guardados os tabuleiros na
     * inicialização
     * BoardPowers - estrutura com os poderes para construir os tabuleiros
     * Tabs - estrutura onde estão guardadas as imagens
     * -> Componentes da estrutura
     * Slots - inicializar a estrutura dos tabuleiros
     * orientation - diz quanto se deve rodar o tabuleiro (1 = não rodar)
     * linha, coluna vai ser preenchido sempre que o tabuleiro vá para o Mapa (matriz de jogo)
     * ID  identificação do Tab
     * 
     * SIMPLIFICAÇÃO
     * 
     * Neste caso vai ser criada simplemente com ID e associando o nome das imagens
     * Funciona um pouco como construtor ao adicionar e inicializar as estruturas de Slots!
     */
    
    public static void BuildBoards(Boards Tabuleiro[], String Tabs[]) {
        /* TESTES */
        if(Tabuleiro == null || Tabs == null){
            System.out.println("Erro com a estrutura de Tabuleiros (não criada)");
            return;
        } 

        /* CÓDIGO */
        for (int i = 0; i < 12; i++) {
            Slots aux1=new Slots();
            Slots aux2=new Slots();
            Slots aux3=new Slots();
            Slots aux4=new Slots();
            aux1.ID = 0;
            aux1.linha = 0;
            aux1.coluna = 0;
            aux2.ID = 1;
            aux2.linha = 0;
            aux2.coluna = 1;
            aux3.ID = 2;
            aux3.linha = 1;
            aux3.coluna = 0;
            aux4.ID = 3; //
            aux4.linha = 1;
            aux4.coluna = 1; // INICIALIZAR ESTRUTURA DO SLOT
            // Slots[][] slot = new Slots[2][2];
            Boards Tabuleiros =  new Boards();
            Tabuleiros.ID = i; // Tab1 -> 0
            Tabuleiros.orientation = 1;
            Tabuleiros.board_power = i;
            Tabuleiros.slots= new Slots[2][2];
            Tabuleiros.slots[0][0]=aux1;
            Tabuleiros.slots[0][1]=aux2;
            Tabuleiros.slots[0][1].player = 6;
            Tabuleiros.slots[1][0]=aux3;
            Tabuleiros.slots[1][1]=aux4; // PREENCHER ESTRUTURA SLOTS QUE PERTENCE AO TABULEIRO ASSOCIADO
            // Tabuleiros[ID].slots = slot;
            Tabuleiros.linha = 0;
            Tabuleiros.coluna = 0;
            Tabuleiros.image = Tabs[i]; // carrega imagem
            Tabuleiro[i] = Tabuleiros;  // COLOCAR NA ESTRUTURA FIXA (ARRAY) QUE TEM TODOS OS TABULEIROS
            // HelloController.set_board_image(Tabs[0])
        }
        // Adicionar atributo das imagens - Tabs[] vai conter as imagens

    }

    /*************************************
     **** >>>>>>> SOLVE BOARD <<<<<<< ****
     *************************************/
    /*
     * Recebe um Board
     * Aplica as habilidades
     * Conta a força
     * Atribui os prémios
     */

    public static int SolveBoard(Boards Tabuleiros){
        /* TESTES */
        if(Tabuleiros == null){
            System.out.println("Erro ao carregar os parâmetros da função Tabuleiros");
            return -1;
        }

        /* CÓDIGOS */
        for(int l = 0; l<2; l++){
            for(int c = 0; c<2; c++){ // Percorre matriz
                if(Tabuleiros.slots[l][c].player != 0){ // Verifica se tem lá carta
                    if(Abilities.CheckAbilities(Tabuleiros.slots[l][c].Card)){ // Verifica se tem habilidade
                        System.out.println("Carta com habilidade");
                        Abilities.AskAbilitie(Tabuleiros.slots[l][c].Card, 0);
                        Abilities.AskAbilitie(Tabuleiros.slots[l][c].Card,1);
                        Abilities.AskAbilitie(Tabuleiros.slots[l][c].Card, 2);
                    }
                }
            }
        }
       // int vencedor = max(Tabuleiros);
        //return vencedor;
        /*************************************
        **** >>>>>>> CICLO FORÇA <<<<<<< ****
        *************************************/
        int[] forca = new int[6];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) { // Percorre
                if (Tabuleiros.slots[i][j].player != 0) { // Verifica se tem carta
                    switch (Tabuleiros.slots[i][j].player) { // Vê de quem é
                        // Vemos a quem pertence a carta
                        case (1):
                            forca[0] += Tabuleiros.slots[i][j].Card.Power; break;
                        case (2):
                            forca[1] += Tabuleiros.slots[i][j].Card.Power; break;
                        case (3):
                            forca[2] += Tabuleiros.slots[i][j].Card.Power; break;
                        case (4):
                            forca[3] += Tabuleiros.slots[i][j].Card.Power; break;
                        case (5):
                            forca[4] += Tabuleiros.slots[i][j].Card.Power; break;
                    }

                }
            }
        }
        System.out.println("Força 1: " + forca[0]);
        System.out.println("Força 2: " + forca[1]);
        int winner = max(forca);
        return ((winner != 0) ? (winner) : 0); // Mede as forças e atribui o vencedor

        }

        /***********************************
        **** >>>>>>> FUNÇÃO MAX <<<<<<< ****
        ************************************/
        
        public static int max(int array[]) {
            /* TESTES */
            if(array == null){
                System.out.println("Erro ao carregar parâmetros do jogo");
                return -1;
            }
            
            /* CÓDIGOS */
            int maxval = 0, posmax = -1;
            for (int i = 0; i < array.length; i++) {
                if (array[i] > maxval) {
                    maxval = array[i];
                    posmax = i;
                } else if(array[i] == maxval){ // TODO - Aqui só funciona para 2 jogadores!!!
                    posmax = -1; 
                }
            }
            return (posmax != -1) ? posmax+1 : -1;
        }

}

