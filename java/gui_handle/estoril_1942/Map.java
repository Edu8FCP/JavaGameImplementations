package gui_handle.estoril_1942;

import java.util.ArrayList;
import java.util.Collections; // Tenho de ver o que faz
import java.util.Arrays;
import java.lang.Math;

public class Map {
    /*********************************************
     **** >>>>>>> ATRIBUTOS DA CLASSE <<<<<<< ****
     *********************************************/
    // ArrayList<Boards> Tabuleiros = new ArrayList<Boards>();
    Boards[][] Tabuleiros = new Boards[2][3];

    /**********************************************************
     **** >>>>>>> ELEMENTOS PARA COMPOR AS FUNÇÕES <<<<<<< ****
     **********************************************************/
    final int NrOfTabuleiros = 6; // nr fixo de tabuleiros que se usam
    final int TotalOfTabuleiros = 12;

    /********************************************
     **** >>>>>>> SORTEIA TABULEIROS <<<<<<< ****
     ********************************************/
    /*
     * ListOfBoards - estrutura onde vão os índices dos tabuleiros sorteados
     * FUNÇÃO - Sorteia os tabuleiros que vão a jogo
     */
    public static int RandBoards(int ListOfBoards[]) {
        if(ListOfBoards == null){
            System.out.println("Erro ao carregar os parâmetros de RandBoards");
            return -1;
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 12; i++) { // FIX Antes tinha isto FIX - for (int i = 0; i < TotalOfTabuleiros; i++) {
            list.add(i);
        } // Vamos preencher um array que vai controlar que não saem nrs repetidos
        Collections.shuffle(list); // Ordena aleatoriamente os nrs
        for (int i = 0; i < 6; i++) { // FIX antes tinha isto - for (int i = 0; i < NrOfTabuleiros; i++) {
            ListOfBoards[i] = list.get(i); // Copiamos os índices para um array
            System.out.println("ListofBoards["+i+"]="+ ListOfBoards[i]);
        }
        return 1; // significa que funcionou. retorna também um array com os nrs sorteados
    } // No array vai a lista com os índices dos tabs sorteados

    /********************************************
     **** >>>>>>> RAND ORIENTATIONSS <<<<<<< ****
     ********************************************/
    /*
     * Orientations - vetor onde se vão guardar as orientações sorteadas
     * FUNÇÃO - Sorteia as orientações para os tabuleiros que vão a jogo
     */   
    public static void RandOrientations(int Orientations[]){
        /* TESTES */
        if(Orientations == null){
            System.out.println("Erro ao carregar os parâmetros da função RandOrientations");
            return ;
        }

        // SORTEIA ORIENTAÇÕES
        for(int i=0; i<6; i++){
            Orientations[i] = (int)(Math.random()*4+1);
            
        }
    }

    /**********************************************
     **** >>>>>>> ATTACH ORIENTATIONSS <<<<<<< ****
     **********************************************/
    /*
     * Tabuleiros - Estrutura que contém TODOS OS TABULEIROS
     * ListOfBoards - Estrutura que indica que tabuleiros vão ser USADOS e devem ser ALTERADOS
     * game - handler do jogo; nesta função serve para FORNECER os prémios
     * ListOfPrizes - prémios para associar a cada tabuleiro
     * FUNÇÃO - Associa os prémios e orientações ao tabuleiros de jogo
     */   

    public static void AttachOrientationsPrizes(Boards Tabuleiros[], int ListOfBoards[], int Orientations[], Game game, int ListOfPrizes[]){
        /* TESTES */
        if(Tabuleiros == null || ListOfBoards == null || Orientations == null || game == null || ListOfPrizes == null){
            System.out.println("Erro ao carregar os parâmetros da função AttachOrienationPrizes");
            return;
        }

        Cards aux=new Cards();

        /* CÓDIGO */
        for(int i=0; i<6; i++){
            Tabuleiros[ListOfBoards[i]-1].orientation = Orientations[i];
            System.out.println("ID>:" +game.Cartas.get(ListOfPrizes[i]-1).ID);
            switch(Orientations[i]){
                case(1):
                    aux=game.Cartas.get(ListOfPrizes[i]-1);
                    System.out.println("aux_1>:" +aux.ID);
                    Tabuleiros[ListOfBoards[i]-1].slots[0][1].Card = game.Cartas.get(ListOfPrizes[i]-1);
                    break;
                case(2):
                    aux=game.Cartas.get(ListOfPrizes[i]-1);
                    System.out.println("aux_2>:" +aux.ID);
                    Tabuleiros[ListOfBoards[i]-1].slots[0][1].player = 0;
                    Tabuleiros[ListOfBoards[i]-1].slots[1][1].player = 6;
                    Tabuleiros[ListOfBoards[i]-1].slots[1][1].Card = game.Cartas.get(ListOfPrizes[i]-1);
                    break;
                case(3):
                    aux=game.Cartas.get(ListOfPrizes[i]-1);
                    System.out.println("aux_3>:" +aux.ID);
                    Tabuleiros[ListOfBoards[i]-1].slots[0][1].player = 0;
                    Tabuleiros[ListOfBoards[i]-1].slots[1][0].player = 6;
                    Tabuleiros[ListOfBoards[i]-1].slots[1][0].Card = game.Cartas.get(ListOfPrizes[i]-1);
                    break;
                case(4):
                    aux=game.Cartas.get(ListOfPrizes[i]-1);
                    System.out.println("aux_4>:" +aux.ID);
                    Tabuleiros[ListOfBoards[i]-1].slots[0][1].player = 0;
                    Tabuleiros[ListOfBoards[i]-1].slots[0][0].player = 6;
                    Tabuleiros[ListOfBoards[i]-1].slots[0][0].Card = game.Cartas.get(ListOfPrizes[i]-1);
                    break;
            }
        }
    }

    /*************************************************
     **** >>>>>>> REMOVE PRIZES FROM LIST <<<<<<< ****
     *************************************************/
    /*
     * game - Handler do jogo, de onde vamos retirar os prémios da sua estrutura
     * ListOfPrizes - Lista dos prémios a serem retirados do jogo
     * FUNÇÃO - Só é chamada imediatamente antes de se inicializar a ronda seguinte
     */   

    public static void RemovePrizesFromList(Game game, int[] ListOfPrizes){
        /* TESTES */
        if(game == null || ListOfPrizes == null){
            System.out.println("Erro ao carregar os parâmetros RemovePrizesFromList");
            return;
        }

        /* CÓDIGO */
        // Game para aceder às Cartas
        Arrays.sort(ListOfPrizes);
        game.Cartas.remove(ListOfPrizes[5]-1);
        game.Cartas.remove(ListOfPrizes[4]-1);
        game.Cartas.remove(ListOfPrizes[3]-1);
        game.Cartas.remove(ListOfPrizes[2]-1);
        game.Cartas.remove(ListOfPrizes[1]-1); // FIX - Game vai precisar de redundancia. Uma estrutura de cartas para os prémios e outra para as figures
        game.Cartas.remove(ListOfPrizes[0]-1); // FIX - Acho que preciso deste -1
    }


    /*********************************************
     **** >>>>>>> ASSEMBLA TABULEIROS <<<<<<< ****
     *********************************************/
    /*
     * ListOfBoards - tem os tabuleiros sorteados para a ronda
     * Mapa - Local do jogo
     * Tabuleiros - Lista com os tabuleiros todos existentes já após o build, e após a função Attach
     * FUNÇÃO - Constrói o Mapa no handler do jogo, game (aqui subentendido em game.GameMap)
     */
    public static void AssembleMap(int ListOfBoards[], Map Mapa, Boards[] Tabuleiros) {
        /* TESTES */
        if(ListOfBoards == null || Mapa == null || Tabuleiros == null){
            System.out.println("Erro ao carregar os parâmetros da função AssembleMap");
            return;
        }

        /* CÓDIGO */
        for (int i = 0; i < 6 / 2; i++) { //NrOfTabuleiros
            Mapa.Tabuleiros[0][i] = Tabuleiros[ListOfBoards[i]-1];
            Tabuleiros[ListOfBoards[i]-1].linha = 0;
            Tabuleiros[ListOfBoards[i]-1].coluna = i;
            Mapa.Tabuleiros[1][i] = Tabuleiros[ListOfBoards[i + 6 / 2]-1]; //NrOfTabuleiros
            Tabuleiros[ListOfBoards[i + 6 / 2]-1].linha = 1; //NrOfTabuleiros
            Tabuleiros[ListOfBoards[i + 6 / 2]-1].coluna = i; //NrOfTabuleiros
        } // Construção da matriz com os tabuleiros

    }

    /*************************************
     **** >>>>>>> LIMPAR MAPA <<<<<<< ****
     *************************************/
    /*
     * game - handler do jogo
     * FUNÇÃO - Limpa logicamente o Mapa de jogo!
     * 
     */

    public static void ClearMap(Game game){
        /* TESTES */
        if (game == null){
            System.out.println("Erro ao carregar o jogo...");
            return ;
        }

        /* CÓDIGO */
        for(int i = 0; i<2; i++){
            for(int j = 0; j<3; j++){
                game.GameMap.Tabuleiros[i][j].slots[0][0].Card = null;
               // if(game.GameMap.Tabuleiros[i][j].slots[0][0].player != 6) game.GameMap.Tabuleiros[i][j].slots[0][0].player = 0;
                game.GameMap.Tabuleiros[i][j].slots[0][0].player = 0;
                game.GameMap.Tabuleiros[i][j].slots[0][0].shield = false;

                game.GameMap.Tabuleiros[i][j].slots[0][1].Card = null;
               // if(game.GameMap.Tabuleiros[i][j].slots[0][1].player != 6) game.GameMap.Tabuleiros[i][j].slots[0][1].player = 0;
                game.GameMap.Tabuleiros[i][j].slots[0][1].shield = false;
                game.GameMap.Tabuleiros[i][j].slots[0][1].player = 6; // Volta ao standard do SlotPrize

                game.GameMap.Tabuleiros[i][j].slots[1][0].Card = null;
               // if(game.GameMap.Tabuleiros[i][j].slots[1][0].player != 6) game.GameMap.Tabuleiros[i][j].slots[1][0].player = 0;
                game.GameMap.Tabuleiros[i][j].slots[1][0].player = 0; 
                game.GameMap.Tabuleiros[i][j].slots[1][0].shield = false;

                game.GameMap.Tabuleiros[i][j].slots[1][1].Card = null;
               // if(game.GameMap.Tabuleiros[i][j].slots[1][1].player != 6) game.GameMap.Tabuleiros[i][j].slots[1][1].player = 0; 
                game.GameMap.Tabuleiros[i][j].slots[1][1].player = 0; 
                game.GameMap.Tabuleiros[i][j].slots[1][1].shield = false;
            }
        }
    }
}
