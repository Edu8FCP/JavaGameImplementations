package gui_handle.estoril_1942;/*import java.io.*;
import java.net.*;

public class comms.Client{
    public static void main(String[] arg) throws UnknownHostException, IOException{
        Socket s = new Socket("localhost", 5000); //onde diz localhost põem-se o IP do PC q faz de servidor
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        dout.writeUTF("1 3");
        dout.flush();
        dout.close();

        DataInputStream din = new DataInputStream(s.getInputStream());

        s.close();
    }
}*/

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class Client2 {
    static Socket socket;

    public static void main(String[] args) {

        int[] ola = new int[1]; // FIX não percebo isto
        ola[0] = 2;
        int order;
        int[][] dados = new int[4][6];

        establish_connection(ola);
        System.out.println("Connection established");
        //order=player_order();
        //order=1;
        dados = receive_info();
        System.out.println("Received info");
        System.out.println("Dados:"+dados[0][0]);
    }


    public static void establish_connection(int[] jogadores) {
        try {

            socket = new Socket("localhost", 5000);

            System.out.println("Ok");

            sendInfo(1, jogadores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendInfo(int codigo, int[] dados) throws IOException {

        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        String string_dados = int_to_string(dados);

        int codigo_byte = codigo;
        for (int i = 0; i < 4; i++) {
            output.write((byte) (codigo_byte % 256));
            codigo_byte = codigo_byte / 256;
        }

        output.write((byte) string_dados.length());
        byte[] nBytes = string_dados.getBytes();
        output.write(nBytes, 0, (byte) string_dados.length());
    }

    public static int[][] receive_info() {
        try {
            int codigo;
            int aux;
            int nchars;
            int[] buildboards, buildmission, buildprizes, rotations;
            int[][] ret = new int[4][6];
            int player, board, posboard, card;
            String[] sboards = new String[6];
            String[] smissions = new String[30];
            String[] sprizes = new String[30];
            String[] srotations = new String[30];
            String[] aauxs = new String[80]; // Correção é rpreciso criar a estrutura
            String dadosr;


            while (true) {
                DataInputStream input = new DataInputStream(socket.getInputStream());

                byte[] data = new byte[1024];

                System.out.println("Ok antes for");
                aux = 1;
                codigo = 0;

                for (int i = 0; i < 4; i++) {
                    codigo = codigo + aux * input.read();
                    System.out.println("codigo for:"+codigo);
                    aux = aux * 256;
                }
                System.out.println("Client code:"+codigo);

                nchars = input.read();
                input.read(data, 0, nchars);
                dadosr = new String(data, 0, nchars);
                System.out.println(dadosr);

                switch (codigo) {
                    case 1 -> {
                        System.out.println("Pelo menos recebi algo, alguém gosta de mim :o...");
                        aauxs = dadosr.split("\\|"); // TODO - GOD'S GIFT TEXT https://stackoverflow.com/questions/32715552/java-split-not-working
                        //System.out.println(aauxs[0] + " " + aauxs[1] + " " + aauxs[30]);
                        aauxs[0] = aauxs[0].replaceAll(" ", "");
                        sboards = aauxs[0].split("\\,");
                        aauxs[1] = aauxs[1].replaceAll(" ", "");
                        srotations = aauxs[1].split("\\,");
                        aauxs[2] = aauxs[2].replaceAll(" ", "");
                        smissions = aauxs[2].split("\\,");
                        aauxs[3] = aauxs[3].replaceAll(" ", "");
                        sprizes = aauxs[3].split("\\,");
                        buildboards = new int[sboards.length];
                        rotations = new int[srotations.length];
                        buildmission = new int[smissions.length];
                        buildprizes = new int[sprizes.length];
                        int j = 0;
                        int k = 0;
                        System.out.println(sboards.length);
                        for (int i = 0; i < sboards.length; i++) {
                            System.out.println(sboards[i]);
                            if(sboards[i]!=" "){  // FIX - Tive de fazer isto pq estava a ler " "
                                buildboards[k] = Integer.parseInt(sboards[i]);
                                System.out.println(buildboards[k]);
                                k++;
                            }
                        }
                        for (int i = 0; i < srotations.length; i++) {
                            if(srotations[i]!=" "){
                                rotations[j] = Integer.parseInt(srotations[i]);
                                j++;
                            }
                        }
                        System.out.println("Passei mais um pouco!!!");
                        k = 0;
                        for (int i = 0; i < smissions.length; i++) {
                            if(smissions[i]!=" "){
                                buildmission[k] = Integer.parseInt(smissions[i]);
                                k++;
                            }
                        }
                        k = 0;
                        for (int i = 0; i < sprizes.length; i++) {
                            if(sprizes[i]!=" "){
                                buildprizes[k] = Integer.parseInt(sprizes[i]);
                                k++;
                            }
                        }
                        ret[0] = buildboards;
                        ret[1] = rotations;
                        ret[2] = buildmission;
                        ret[3] = buildprizes;
                        System.out.println("Acho que recebi algo :)");
                        return ret;
                    }
                    case 2 -> {
                        aauxs = dadosr.split("\\,");
                        player = Integer.parseInt(aauxs[0]);
                        board = Integer.parseInt(aauxs[1]);
                        aauxs[2] = aauxs[2].concat(aauxs[3]);
                        posboard = Integer.parseInt(aauxs[2]);
                        card = Integer.parseInt(aauxs[4]);
                        ret[0][0] = player;
                        ret[0][1] = board;
                        ret[0][2] = posboard;
                        ret[0][3] = card;
                        return ret;
                    }
                    case 3 -> {
                        aauxs = dadosr.split("\\|");
                        sboards = aauxs[0].split("\\,");
                        srotations = aauxs[1].split("\\,");
                        sprizes = aauxs[2].split("\\,");
                        buildboards = new int[sboards.length];
                        rotations = new int[srotations.length];
                        buildprizes = new int[sprizes.length];

                        for (int i = 0; i < sboards.length; i++) {
                            buildboards[i] = Integer.parseInt(sboards[i]);
                            rotations[i] = Integer.parseInt(srotations[i]);
                        }
                        for (int i = 0; i < sprizes.length; i++) {
                            buildprizes[i] = Integer.parseInt(sprizes[i]);
                        }
                        ret[0] = buildboards;
                        ret[1] = rotations;
                        ret[2] = buildprizes;
                        return ret;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sai do for");
        return null;
    }

    public static String int_to_string(int[] dados) {

        String auxs = Arrays.toString(dados);
        //auxs = auxs.replaceAll("[", "");
        auxs = auxs.replaceAll("\\]", "");
        return auxs;
    }

    public static int player_order() {

        int nchars;
        int ret;
        String dadosr;
        byte[] data = new byte[1024];

        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());

            nchars = input.read();
            /*
            input.read(data, 0, nchars);
            dadosr = new String(data, 0, nchars);
/*
            int codigo_byte = codigo;
            for (int i = 0; i < 4; i++) {
                output.write((byte) (codigo_byte % 256));
                codigo_byte = codigo_byte / 256;
            }


            System.out.println("Data order: "+dadosr.getBytes());
            System.out.println("Data order: "+dadosr);
            //ret = Integer.parseInt(dadosr);
            //System.out.println("ret: "+ret);

             */
            return nchars;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }



}