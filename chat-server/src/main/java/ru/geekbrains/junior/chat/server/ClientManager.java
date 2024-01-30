package ru.geekbrains.junior.chat.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
/*
1. Разработайте простой чат на основе сокетов как это было показано на самом семинаре. Ваше приложение должно включать в себя
сервер, который принимает сообщения от клиентов и пересылает их всем участникам чата. (Вы можете просто переписать наше приложение
с семинара, этого будет вполне достаточно)

2*. Подумайте, как организовать отправку ЛИЧНЫХ сообщений в контексте нашего чата, доработайте поддержку отправки личных сообщений,
небольшую подсказку я дал в конце семинара.
 */

public class ClientManager implements Runnable {

    //region Поля

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;

    public static ArrayList<ClientManager> clients = new ArrayList<>();

    //endregion

    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clients.add(this);
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Server: " + name + " подключился к чату.");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Удаление клиента из коллекции
     */
    private void removeClient() {
        clients.remove(this);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server: " + name + " покинул чат.");
    }

    @Override
    public void run() {
        String massageFromClient;

        while (socket.isConnected()) {
            try {
                // Чтение данных
                massageFromClient = bufferedReader.readLine();
                if (massageFromClient == null) {
                    // для  macOS
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }
                // Отправка данных всем слушателям
                broadcastMessage(massageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }

    }

    /**
     * Доработанный метод для отправки сообщений всем слушателям и в личку
     *
     * @param message сообщение
     */
    private void broadcastMessage(String message) {
        String[] wordsOfMessage = message.split(" ");

        String targetWord = wordsOfMessage[1];
        char firstSymbol = targetWord.charAt(0);

        for (ClientManager client : clients) {
            try {
                if (firstSymbol == '@' && wordsOfMessage.length > 2) {
                    String recipientName = targetWord.substring(1);
                    String[] removeRecipientName = remove(wordsOfMessage, 1);
                    String editedMessage = buildMessage(removeRecipientName);
                    if (client.name.equals(recipientName)) {
                        client.bufferedWriter.write(editedMessage);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                        return;
                    }
                } else {
                    if (!client.name.equals(name)) {
                        client.bufferedWriter.write(message);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    }
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // Удаление клиента из коллекции
        removeClient();
        try {
            // Завершаем работу буфера на чтение данных
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            // Завершаем работу буфера для записи данных
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            // Закрытие соединения с клиентским сокетом
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Вспомогательный метод для удаления имени адресата в личном сообщении
     * @param strings
     * @param index
     * @return
     */
    private String[] remove(String[] strings, int index) {
        String[] result = new String[strings.length - 1];

        for (int i = 0; i < strings.length; i++) {
            if (i != index) {
                int newIndex = i < index ? i : i - 1;
                result[newIndex] = strings[i];
            }
        }

        return result;
    }

    /**
     * Вспомогательный метод для построения текста личного сообщения из массива строк
     * @param strings
     * @return
     */
    private String buildMessage(String[] strings) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            strBuilder.append(strings[i]);
            strBuilder.append(" ");
        }
        strBuilder.append("(Личное сообщение)");
        String newString = strBuilder.toString();
        return newString;
    }
}

