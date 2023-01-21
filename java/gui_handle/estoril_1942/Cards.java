package gui_handle.estoril_1942;
// 
import java.util.ArrayList;

public class Cards {
    int Power; // strengh of the card
    String Nationality; // int or string?
    int PV; // Victory Points
    int Player; // Players that has the card
    int[] abilities = { 0, 0, 0 }; // array of 3 values
    // each position represents the possibility to have a power
    // 0 representes no power.
    int ID;
    boolean gender; // 0 - homem, 1 - mulher


    /***********************************************
     **** >>>>>>> CONSTRUIR CARTAS BASE <<<<<<< ****
     ***********************************************/
    // CHAMADA UMA VEZ!
    /*
     * Cartas - Estrutura onde vão ser guardadas as cartas após a inicialização
     * CardPowers - Força das cartas
     * PV - Pontos de vitória de cada carta
     * Nacionalidades - estrutura com as nacionalidades para as cartas
     * ImagemCartas - estrutura com as imagens para as cartas
     * abilities - array com as habilidades que pode usar
     * genero - se é homem ou mulher
     * IMAGENS -> DE 0 a 29 (30 cartas). A cada 6 é de um player diferente
     * FUNÇÃO - Cria e inicializa em memória a estrutura que vai ter as cartas base do jogo, que vão ter ID's de 0 a 29
     */
    public static void BuildBaseCards(ArrayList<Cards> Cartas, int CardPowers[], int PV[], String Nacionalidades[],
            String ImagemCartas[], int habilidades[][], boolean genero[]) {

        /* TESTES */
        if(Cartas == null || CardPowers == null || PV == null || Nacionalidades == null || ImagemCartas == null || habilidades == null){
            System.out.println("Erro ao carregar os parâmetros da função BuildBaseCards");
            return;
        }

        /* CÓDIGO */
        for (int ID = 0; ID < 30; ID++) {
            Cards aux = new Cards();
            aux.Nationality = Nacionalidades[ID];
            aux.PV = PV[ID];
            aux.Player = 0; // ninguém tem a carta
            aux.Power = CardPowers[ID];
            aux.abilities = habilidades[ID];
            aux.ID = ID+1;
            aux.gender = genero[ID];
            //aux.abilities = abilities[ID];
           // aux.gender = genero;
            // Cartas[ID].gender = slots;
            Cartas.add(aux);
        }
    }

    /******************************************
     **** >>>>>>> CONSTRUIR CARTAS <<<<<<< ****
     ******************************************/ 
    /*
     * Cartas - Estrutura onde vão ser guardadas as cartas após a inicialização
     * CardPowers - Força das cartas
     * PV - Pontos de vitória de cada carta
     * abilities - habilidades que as cartas podem usar
     * gender - se é homem ou mulher (bool)
     * IMAGENS - de 30 para TOTAL
     * Nacionalidades - estrutura com as nacionalidades para as cartas
     * ImagemCartas - estrutura com as imagens para as cartas
     */
/*     public static void BuildCards(Cards Cartas[], int CardPowers[], int PV[], String Nacionalidades[],
            String ImagemCartas[], int abilities[][], boolean genero[]) {
                
        for (int ID = 30; ID < 57; ID++) {
            Cards aux = new Cards();
            aux.Nationality = Nacionalidades[ID];
            aux.PV = PV[ID];
            aux.Player = 0; // ninguém tem a carta
            aux.ID = ID;
            aux.Power = CardPowers[ID];
            aux.abilities = abilities[ID];
            aux.gender = genero[ID];
            Cartas[ID] = aux;
        } 
        // Adicionar atributo das imagens - Tabs[] vai conter as imagens

        // na estrutura Cards passada vão as cartas
    } */

    /******************************************
     **** >>>>>>> CONSTRUIR CARTAS <<<<<<< ****
     ******************************************/
    /*
     * Cartas - Estrutura onde vão ser guardadas as cartas após a inicialização
     * CardPowers - Força das cartas
     * PV - Pontos de vitória de cada carta
     * abilities - habilidades que as cartas podem usar
     * gender - se é homem ou mulher (bool)
     * IMAGENS - de 30 para TOTAL
     * Nacionalidades - estrutura com as nacionalidades para as cartas
     * ImagemCartas - estrutura com as imagens para as cartas
     * FUNÇÃO - Constrói a estrutura das cartas em memória com ID's de 31 a 57
     */

    // ALTERNATIVA COM ARRAYLIST PARA SER MAIS FÁCIL O TRATAMENTO DOS DADOS
    public static void BuildCardsv2(ArrayList<Cards> Cartas, int CardPowers[], int PV[], String Nacionalidades[],
            String ImagemCartas[], int habilidades[][], boolean genero[]) {

        /* TESTES */
        if(Cartas == null || CardPowers == null || PV == null || Nacionalidades == null || ImagemCartas == null || habilidades == null){
            System.out.println("Erro ao carregar os parâmetros da função BuildCardsv2");
            return;
        }

        /* CÓDIGOS */
        for (int ID = 0; ID < 27; ID++) {
            Cards aux = new Cards();
            aux.Nationality = Nacionalidades[ID];
            aux.PV = PV[ID];
            aux.Player = 0; // ninguém tem a carta
            aux.Power = CardPowers[ID];
            aux.gender = genero[ID];
            aux.abilities = habilidades[ID];
            aux.ID = 31+ID; // Para ficarem a seguir às base
            
            Cartas.add(aux);
        }
        // Adicionar atributo das imagens - Tabs[] vai conter as imagens

        // na estrutura Cards passada vão as cartas
    }
}
