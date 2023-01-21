package gui_handle.estoril_1942;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Objects;

public class Client {
    static Socket socket;

    public static void establish_connection(int[] jogadores, String server_h) {
        try {

            if(server_h==null){
                socket = new Socket("localhost", 5000);
            }else{
                socket = new Socket(server_h, 5000);
            }


            System.out.println("Ok");

            sendInfo(1, jogadores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendInfo(int codigo, int[] dados) throws IOException {

        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        String string_dados = int_to_string(dados);
        System.out.println("string: " + string_dados);

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
            int codigo, aux, nchars;
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
                    System.out.println("codigo for:" + codigo);
                    aux = aux * 256;
                }
                System.out.println(codigo);

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
                        sboards = aauxs[0].split(",");
                        aauxs[1] = aauxs[1].replaceAll(" ", "");
                        srotations = aauxs[1].split(",");
                        aauxs[2] = aauxs[2].replaceAll(" ", "");
                        smissions = aauxs[2].split(",");
                        aauxs[3] = aauxs[3].replaceAll(" ", "");
                        sprizes = aauxs[3].split(",");
                        buildboards = new int[sboards.length];
                        rotations = new int[srotations.length];
                        buildmission = new int[smissions.length];
                        buildprizes = new int[sprizes.length];
                        int j = 0;
                        int k = 0;
                        System.out.println(sboards.length);
                        for (String sboard : sboards) {
                            System.out.println(sboard);
                            if (!Objects.equals(sboard, " ")) {  // FIX - Tive de fazer isto pq estava a ler " "
                                buildboards[k] = Integer.parseInt(sboard);
                                System.out.println(buildboards[k]);
                                k++;
                            }
                        }
                        for (String srotation : srotations) {
                            if (!Objects.equals(srotation, " ")) {
                                rotations[j] = Integer.parseInt(srotation);
                                j++;
                            }
                        }
                        System.out.println("Passei mais um pouco!!!");
                        k = 0;
                        for (String smission : smissions) {
                            if (!Objects.equals(smission, " ")) {
                                buildmission[k] = Integer.parseInt(smission);
                                k++;
                            }
                        }
                        k = 0;
                        for (String sprize : sprizes) {
                            if (!Objects.equals(sprize, " ")) {
                                buildprizes[k] = Integer.parseInt(sprize);
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
                        aauxs = dadosr.split(",");
                        player = Integer.parseInt(aauxs[0]);
                        System.out.println("plaayer server: " + aauxs[0]);
                        System.out.println("plaayer server: " + aauxs[1]);
                        System.out.println("plaayer server: " + aauxs[2]);
                        System.out.println("plaayer server: " + aauxs[3]);
                        board = Integer.parseInt(aauxs[1]);
                        aauxs[2] = aauxs[2].concat(aauxs[3]);
                        posboard = Integer.parseInt(aauxs[2]);
                        //System.out.println("aauxs[2]->"+ posboard);
                        card = Integer.parseInt(aauxs[4]);
                        ret[0][0] = player;
                        ret[0][1] = board;
                        ret[0][2] = posboard;
                        ret[0][3] = card;
                        return ret;
                    }
                    case 3 -> {
                        aauxs = dadosr.split("\\|");
                        aauxs[0] = aauxs[0].replaceAll(" ", "");
                        sboards = aauxs[0].split(",");
                        aauxs[1] = aauxs[1].replaceAll(" ", "");
                        srotations = aauxs[1].split(",");
                        aauxs[2] = aauxs[2].replaceAll(" ", "");
                        sprizes = aauxs[2].split(",");
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
                    case 4 -> {
                        aauxs = dadosr.split(",");
                        for(int k=0; k<4; k++){
                            if(aauxs[k] != null){
                                System.out.println("Estou no while");
                                aauxs[k] = aauxs[k].replaceAll(" ", "");
                                ret[0][k] = Integer.parseInt(aauxs[k]);
                            }
                        }
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
        auxs = auxs.replaceAll("\\[", "");
        auxs = auxs.replaceAll("]", "");
        auxs = auxs.replaceAll(" ", "");
        return auxs;
    }

    public static int player_order() {

        int nchars;

        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());

            nchars = input.read();
            return nchars;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }


}