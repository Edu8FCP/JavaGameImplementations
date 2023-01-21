package gui_handle.estoril_1942;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server {
    static int nplayers = 0;
    static int nprizes = 27;
    static int n_codigo_3 = 0;
    static int n_codigo_4 = 0;
    static int[] npontos = new int[4];
    static final List<Socket> clientSockets = new ArrayList<>();
    static int[][] miss = new int[4][4];
    static int nr_tramas_jogadas_ronda = 0;

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Olá");
            while (true) {

                Socket clientSocket = serverSocket.accept();
                clientSockets.add(clientSocket);

                Thread thread = new Thread(new ClientHandler(clientSocket));

                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

        int codigo;
        int aux;
        int nchars;
        String dadosr;
        String dadoss;
        String auxs;
        String[] aauxs;

        try {
            while (true) {
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());

                byte[] data = new byte[1024];
                System.out.println("Ok antes for");
                aux = 1;
                codigo = 0;
                for (int i = 0; i < 4; i++) {
                    codigo = codigo + aux * input.read();
                    aux = aux * 256;
                }
                System.out.println("Código " + codigo);
                nchars = input.read();
                //System.out.println(nchars);
                input.read(data, 0, nchars);
                dadosr = new String(data, 0, nchars);

               // System.out.println(dadosr.charAt(1)); // FIX -> NÃO TIROU O "["
                //dadosr = dadosr.charAt(1)+""; // Para tornar uma string

                switch (codigo) {
                    case 1 -> {
                        if (Server.nplayers == 0) {
                            System.out.println("Tenho UM amigo");
                            Server.nplayers = Integer.parseInt(dadosr);
                            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                            output.write(1);
                            System.out.println("Enviei algo... :) ");

                        } else if (Server.nplayers != Server.clientSockets.size()) {
                            System.out.println("You shall not enter");
                            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                            output.write(Server.clientSockets.size());

                        } else if (Server.nplayers == Server.clientSockets.size()) {

                            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                            output.write(Server.nplayers);
                            System.out.println("Agora tenho DOIS amigos :). Obrigado <3");
                            List<Integer> boards = new ArrayList<>();
                            while (boards.size() < 6) {
                                double randomDouble = Math.random();
                                Integer randomInt = (int) Math.round(randomDouble * (12 - 1) + 1);
                                if ((!boards.contains(randomInt)) && (randomInt != 9)) {
                                    boards.add(randomInt);
                                }
                            }

                            dadoss = "";
                            auxs = boards.toString();
                            auxs = auxs.substring(1, auxs.length() - 1);
                            dadoss = dadoss.concat(auxs);

                            System.out.println("Boards: " + dadoss);

                            List<Integer> rotations = new ArrayList<>();
                            while (rotations.size() < 6) {
                                double randomDouble = Math.random();
                                Integer randomInt = (int) Math.round(randomDouble * (4 - 1) + 1);
                                rotations.add(randomInt);
                            }

                            auxs = rotations.toString();
                            auxs = auxs.substring(1, auxs.length() - 1);
                            dadoss = dadoss.concat(" | " + auxs);

                            System.out.println("Boards | Rotations: " + dadoss);
                            List<Integer> missions = new ArrayList<>();
                            while (missions.size() < 4) {
                                double randomDouble = Math.random();
                                Integer randomInt = (int) Math.round(randomDouble * (14 - 1) + 1);
                                if (!missions.contains(randomInt)) {
                                    missions.add(randomInt);
                                }
                            }
                            auxs = missions.toString();
                            auxs = auxs.substring(1, auxs.length() - 1);
                            dadoss = dadoss.concat(" | " + auxs);

                            System.out.println("Boards | Rotations | Missions: " + dadoss);

                            List<Integer> prizes = new ArrayList<>();
                            while (prizes.size() < 6) {
                                double randomDouble = Math.random();
                                Integer randomInt = (int) Math.round(randomDouble * (Server.nprizes - 1)) + 1;
                                if (!prizes.contains(randomInt)) {
                                    prizes.add(randomInt);
                                }
                            }
                            Server.nprizes = Server.nprizes - 6;
                            auxs = prizes.toString();
                            auxs = auxs.substring(1, auxs.length() - 1);
                            dadoss = dadoss.concat(" | " + auxs);

                            System.out.println("Boards | Rotations | Missions | Prizes: " + dadoss);
                            for (Socket socket : Server.clientSockets) {
                                sendInfo(codigo, dadoss, socket);
                                System.out.println("Enviei aos meus amigos <3");
                            }
                        }
                    }
                    case 2 -> {
                        for (Socket socket : Server.clientSockets) {
                            if (socket != clientSocket) {
                                sendInfo(codigo, dadosr, socket);
                            }
                        }
                        Server.nr_tramas_jogadas_ronda += 1;
                    }

                    case 3 -> {
                        Server.n_codigo_3 += 1;
                        System.out.println("Codigon_3: " + Server.n_codigo_3);
                        if (Server.n_codigo_3 == Server.clientSockets.size()) {
                            System.out.println("\n\nStarting to send info:\n");
                            List<Integer> boards = new ArrayList<>();
                            while (boards.size() < 6) {
                                double randomDouble = Math.random();
                                Integer randomInt = (int) Math.round(randomDouble * (12 - 1) + 1);
                                if (!boards.contains(randomInt) && randomInt != 9) {
                                    boards.add(randomInt);
                                }
                            }

                            dadoss = "";
                            auxs = boards.toString();
                            auxs = auxs.substring(1, auxs.length() - 1);
                            dadoss = dadoss.concat(auxs);

                            System.out.println("Boards: " + dadoss);

                            List<Integer> rotations = new ArrayList<>();
                            while (rotations.size() < 6) {
                                double randomDouble = Math.random();
                                Integer randomInt = (int) Math.round(randomDouble * (4 - 1) + 1);
                                rotations.add(randomInt);
                            }

                            auxs = rotations.toString();
                            auxs = auxs.substring(1, auxs.length() - 1);
                            dadoss = dadoss.concat(" | " + auxs);

                            System.out.println("Boards | Rotations: " + dadoss);

                            List<Integer> prizes = new ArrayList<>();
                            while (prizes.size() < 6) {
                                double randomDouble = Math.random();
                                Integer randomInt = (int) Math.round(randomDouble * (Server.nprizes - 2)) + 1;
                                if (!prizes.contains(randomInt)) {
                                    prizes.add(randomInt);
                                }
                            }
                            Server.nprizes = Server.nprizes - 6;
                            auxs = prizes.toString();
                            auxs = auxs.substring(1, auxs.length() - 1);
                            dadoss = dadoss.concat(" | " + auxs);

                            System.out.println("Boards | Rotations | Prizes: " + dadoss);
                            for (Socket socket : Server.clientSockets) {
                                sendInfo(codigo, dadoss, socket);
                            }
                            Server.n_codigo_3 = 0;
                        }
                    }
                    case 4 -> {
                        Server.n_codigo_4 += 1;
                        aauxs = dadosr.split(",");
                        System.out.println("UREGHFASF " + aauxs[0]);
                        switch (Integer.parseInt(aauxs[0])) {
                            case 1 -> {
                                Server.npontos[0] = Integer.parseInt(aauxs[1]);
                                for (int i = 0; i < 4; i++) {
                                    Server.miss[0][i] = Integer.parseInt(aauxs[i + 2]);
                                    System.out.println("Valor p2_: " + Server.miss[0][i]);
                                }
                                if (Server.n_codigo_4 == Server.clientSockets.size()) {
                                    Game.FinalSituationServer(Server.miss, Server.clientSockets.size(), Server.npontos);
                                    for (Socket socket : Server.clientSockets) {
                                        sendInfo(codigo, int_to_string(Server.npontos), socket);
                                    }
                                }
                            }
                            case 2 -> {
                                Server.npontos[1] = Integer.parseInt(aauxs[1]);
                                for (int i = 0; i < 4; i++) {
                                    Server.miss[1][i] = Integer.parseInt(aauxs[i + 2]);
                                    System.out.println("Valor p2: " + Server.miss[1][i]);
                                }
                                if (Server.n_codigo_4 == Server.clientSockets.size()) {
                                    Game.FinalSituationServer(Server.miss, Server.clientSockets.size(), Server.npontos);
                                    for (Socket socket : Server.clientSockets) {
                                        sendInfo(codigo, int_to_string(Server.npontos), socket);
                                    }
                                }
                            }
                            case 3 -> {
                                Server.npontos[2] = Integer.parseInt(aauxs[1]);
                                for (int i = 0; i < 4; i++) {
                                    Server.miss[2][i] = Integer.parseInt(aauxs[i + 2]);
                                }
                                if (Server.n_codigo_4 == Server.clientSockets.size()) {
                                    Game.FinalSituationServer(Server.miss, Server.clientSockets.size(), Server.npontos);
                                    for (Socket socket : Server.clientSockets) {
                                        sendInfo(codigo, int_to_string(Server.npontos), socket);
                                    }

                                }
                            }
                            case 4 -> {
                                Server.npontos[3] = Integer.parseInt(aauxs[1]);
                                for (int i = 0; i < 4; i++) {
                                    Server.miss[3][i] = Integer.parseInt(aauxs[i + 2]);
                                }
                                if (Server.n_codigo_4 == Server.clientSockets.size()) {
                                    Game.FinalSituationServer(Server.miss, Server.clientSockets.size(), Server.npontos);
                                    for (Socket socket : Server.clientSockets) {
                                        sendInfo(codigo, int_to_string(Server.npontos), socket);
                                    }
                                }
                            }

                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("After while");
        /*for (Socket socket : Server.clientSockets) {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/
    }

    public static void sendInfo(int codigo, String dados, Socket socket) throws IOException {
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        int codigo_byte = codigo;
        for (int i = 0; i < 4; i++) {
            output.write((byte) (codigo_byte % 256));
            codigo_byte = codigo_byte / 256;
        }

        output.write((byte) dados.length());
        byte[] nBytes = dados.getBytes();
        output.write(nBytes, 0, (byte) dados.length());
    }

    public static String int_to_string(int[] dados) {

        String auxs = Arrays.toString(dados);
        auxs = auxs.replaceAll("\\[", "");
        auxs = auxs.replaceAll("]", "");
        return auxs;
    }

}