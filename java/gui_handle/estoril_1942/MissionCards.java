package gui_handle.estoril_1942;

import java.util.ArrayList; //para importar o package de ArrayList
import java.util.Collections;

public class MissionCards {
    int ID; // ID vai identificar qual a missão (switch case GIGANTE)
    final int PV = 6; // pontos de vitória de cada missão
    String image;

    /**************************************************
     **** >>>>>>> SORTEAR CARTAS DE MISSÃO <<<<<<< ****
     **************************************************/
    /*
     * NumOfCards - Nr de cartas de missão a sortear (4)
     * ListOfMissionCards - Índice das cartas de missão sorteadas
     * FUNÇÃO - Sorteia as cartas de missão que vão ser usadas no jogo
     */

    public static int DrawMissionCards(int NumOfCards, int[] ListOfMissionCards) {
        /* TESTES */
        if(NumOfCards > 4 || ListOfMissionCards == null){
            System.out.println("Erro ao carregar os parâmetros na função DrawMissionCards");
            return -1;
        }

        /* LÓGICA */
        final int TotalOfCards = 14; 

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= TotalOfCards; i++) { // 
            list.add(i);
        } // Vamos preencher um array que vai controlar que não saem nrs repetidos
        Collections.shuffle(list); // Ordena aleatoriamente os nrs aka BARALHA ahaha
        for (int i = 0; i < 4; i++) {
            ListOfMissionCards[i] = list.get(i); // Copiamos os índices para um array
            System.out.println("Carta Sorteada " + ListOfMissionCards[i]);
        }
        // } while (i < NumOfCards);
        return 1; // 1 = ok + array com as 4 cartas sorteadas
    }

    /*********************************************************
     **** >>>>>>> ATRIBUIÇÃO DAS CARTAS DE MISSÃO <<<<<<< ****
     *********************************************************/
    /*
     * ListOfMissionCards - Índice das cartas de missão sorteadas
     * CartasMissao - estrutura com todas as cartas de missão
     */
/*     public void DisplayMissions(int[] ListOfMissionCards, MissionCards[] CartasMissao) {
        // Recebe os índices das cartas e a estrutura onde se encontram todas as cartas
        // GUI para dispôr as cartas selecionadas
    } */

    /************************************************************
     **** >>>>>>> INICIALIZAÇÃO DAS CARTAS DE MISSÃO <<<<<<< ****
     ************************************************************/
    /*
     * ImageCartasMissão - estrutura com as imagens para as cartas de missão
     * PV - Pontos de vitória das cartas de missão - já está atribuído
     * CartasMissao - estrutura onde vão ser construídas todas as cartas de missão
     * FUNÇÃO - Constrói a estrutura das cartas de missão
     */
    public static void BuildMissionCards(MissionCards[] CartasMissao, String imagens[]) {
        /* TESTES */
        if(CartasMissao == null || imagens == null){
            System.out.println("Erro ao carregar os parâmetros na função BuildMissionCards");
            return ;
        }


        /* LÓGICA */
        final int NrOfMissions = 14; 
        for (int i = 0; i < NrOfMissions; i++) {
            MissionCards aux = new MissionCards();
            aux.ID = i+1; 
            aux.image = imagens[i]; // Associa o caminho da imagem a uma carta
            CartasMissao[i] = aux;
            // PV já está feito
            // Adicionar imagem
        }
    }

    /*****************************************
     **** >>>>>>> RESOLVE MISSÕES <<<<<<< ****
     *****************************************/
    /*
     * ListOfMissions - de 1 a 14
     */

    public static void SolveMissionCards(MissionCards[] CartasMissao, int[] ListOfMissions, int calcs[], Player Jogador) {
        /* TESTES */
        if(CartasMissao == null || ListOfMissions == null){
            System.out.println("Erro ao carregar os parâmetros na função BuildMissionCards");
            return ;
        }

        int NumberOfMissions = 4;
        for(int i = 0; i < NumberOfMissions; i++){
            switch(ListOfMissions[i]){
                case(1): calcs[i] = MissionOfNations(Jogador, "DE");
                    System.out.println("Entrou no caso " + 1);
                    break;
                case(2): calcs[i] = MissionOfAbilities(Jogador, 4); 
                    System.out.println("Entrou no caso " + 2);
                    break;
                case(3): calcs[i] = MissionOfNations(Jogador, "US");
                    System.out.println("Entrou no caso " + 3);
                    break;
                case(4): calcs[i] = MissionOfGender(Jogador, true);
                    System.out.println("Entrou no caso " + 4); 
                    break;
                case(5): calcs[i] = MissionOfNations(Jogador, "PT");
                    System.out.println("Entrou no caso " + 5);
                    break;
                case(6): calcs[i] = MissionOfAbilities(Jogador, 5);
                    System.out.println("Entrou no caso " + 6); 
                    break;
                case(7): calcs[i] += MissionOfNations(Jogador, "ES"); 
                    calcs[i] += MissionOfNations(Jogador, "FR"); 
                    calcs[i] += MissionOfNations(Jogador, "IT"); 
                    calcs[i] += MissionOfNations(Jogador, "HU"); 
                    calcs[i] += MissionOfNations(Jogador, "SU"); 
                    calcs[i] += MissionOfNations(Jogador, "YU");
                    System.out.println("Entrou no caso " + 7);  
                    break; // TODO - Substituir por uma segunda versão da MissionOfNations
                case(8): calcs[i] = Jogador.PlayerDiscardedCards.size();
                    System.out.println("Entrou no caso " + 8);
                    break;
                case(9): calcs[i] = MissionOfAbilities(Jogador, 3);
                    System.out.println("Entrou no caso " + 9); 
                    break;
                case(10): calcs[i] = MissionOfAbilities(Jogador, 1);
                    System.out.println("Entrou no caso " + 10); 
                    break;
                case(11): calcs[i] = MissionOfBeggin(Jogador);
                    System.out.println("Entrou no caso " + 11);
                    break;
                case(12): calcs[i] = MissionOfAbilities(Jogador, 2);
                    System.out.println("Entrou no caso " + 12); 
                    break;
                case(13): calcs[i] = MissionOfForce(Jogador);
                    System.out.println("Entrou no caso " + 13); 
                    break;
                case(14): calcs[i] = MissionOfNations(Jogador, "GB");
                    System.out.println("Entrou no caso " + 14);
                    break;
            }
            
        }
        return;
    }

    public static int MissionOfForce(Player Jogador){
        if(Jogador == null){
            return -1;
        }

        /* CÓDIGO */
        int count = 0;

        for(int i = 0; i<Jogador.HandCards.size(); i++){
            count += Jogador.HandCards.get(i).Power;
        }
        return count;
    }

    public static int MissionOfBeggin(Player Jogador){
        if(Jogador == null){
            return -1;
        }

        /* CÓDIGO */
        int count = 0;

        for(int i = 0; i<Jogador.HandCards.size(); i++){
            if(Jogador.HandCards.get(i).ID <30){
                count++;
            }
        }
        return count;
    }

    public static int MissionOfGender(Player Jogador, Boolean women){
        if(Jogador == null ||  women == null){
            return -1;
        }

        /* CÓDIGO */
        int count = 0;

        for(int i = 0; i<Jogador.HandCards.size(); i++){
            if(Jogador.HandCards.get(i).gender == women){
                count++;
            }
        }
        return count;
    }

    public static int MissionOfNations(Player Jogador, String Nation){
        /* TESTES */
        if(Jogador == null || Nation == null){
            return -1;
        }

        /* CÓDIGO */
        int count = 0;

        for(int i = 0; i<Jogador.HandCards.size(); i++){
            if(Jogador.HandCards.get(i).Nationality == Nation){
                count++;
            }
        }
        return count;
    }

    public static int MissionOfAbilities(Player Jogador, int AbilityValue){
        /* TESTES */
        if(Jogador == null || AbilityValue < 0){
            return -1;
        }

        /* CÓDIGO */
        int count = 0;

        for(int i = 0; i<Jogador.HandCards.size(); i++){
            if(Jogador.HandCards.get(i).abilities[0] == AbilityValue){
                count++;
            }
            if(Jogador.HandCards.get(i).abilities[1] == AbilityValue){
                count++;
            }
            if(Jogador.HandCards.get(i).abilities[2] == AbilityValue){
                count++;
            }
        }
        return count;
    }
}
