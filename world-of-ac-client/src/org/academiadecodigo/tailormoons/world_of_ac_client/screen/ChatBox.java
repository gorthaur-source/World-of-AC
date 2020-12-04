package org.academiadecodigo.tailormoons.world_of_ac_client.screen;

import org.academiadecodigo.simplegraphics.graphics.Text;

import java.util.LinkedList;

public class ChatBox {
    private LinkedList<Message> messages;
    private int maxMessagesDisplaying;
    private final static int MAX_CHARACTERS_ROW = 21;
    private int xOfLatestMessage;
    private int yOfLatestMessage;

    public ChatBox(int maxMessagesDisplaying, int xOfLatestMessage, int yOfLatestMessage) {
        this.maxMessagesDisplaying = maxMessagesDisplaying;
        this.xOfLatestMessage = xOfLatestMessage;
        this.yOfLatestMessage = yOfLatestMessage;
        messages = new LinkedList<>();
    }


    public void addMessageToChatBox(String content) {
        Message messageToAdd = new Message(content);
        if (messages.size() == maxMessagesDisplaying) {
            Message messageToRemove = messages.pollLast();
            messageToRemove.delete();
            moveAllMessagesInYAxis(messageToAdd.getNumberOflines());
            messages.addFirst(messageToAdd);
            return;
        }
        if (messages.size() > 0) {
            moveAllMessagesInYAxis(messageToAdd.getNumberOflines());
        }
        messages.addFirst(messageToAdd);
    }

    private void moveAllMessagesInYAxis(int numberOfLines) {
        for (Message message : messages) {
            message.translate(0, -24 * numberOfLines);
        }
    }


    private class Message {

        private LinkedList<Text> messageLines;
        private int numberOfLines;

        public Message(String rawData) {
            messageLines = new LinkedList<>();
            transformToMessage(rawData);
        }

        private int getNumberOflines() {
            return numberOfLines;
        }

        private void delete() {
            for (Text message : messageLines) {
                message.delete();
            }
        }

        private void translate(int x, int y) {
            for (Text line : messageLines) {
                line.translate(x, y);
            }
        }

        //BUG A SEGUNDA LINHA NÃO A DIVIDIR CORRETAMENTE.
        private void transformToMessage(String message) {

            numberOfLines=1;

            if (message.length() <= MAX_CHARACTERS_ROW) {
                messageLines.add(new Text(xOfLatestMessage, yOfLatestMessage, message));
                messageLines.get(0).draw();
                return;
            }

            int caracterCounter = 0;

            String[] msgSplited = message.split(" ");
            StringBuilder line = new StringBuilder();

            for (int i = 0; i < msgSplited.length; i++) {

                caracterCounter += msgSplited[i].length();
                caracterCounter += 1;

                line.append(msgSplited[i] + " ");



                if (caracterCounter % MAX_CHARACTERS_ROW == 0 || msgSplited[i + 1].length() + caracterCounter > MAX_CHARACTERS_ROW * numberOfLines) {
                    numberOfLines++;
                    moveLinesInYAxis();
                    Text newline = new Text(xOfLatestMessage, yOfLatestMessage, line.toString());
                    newline.draw();
                    messageLines.add(newline);
                    line = new StringBuilder();
                }

                if (i == msgSplited.length - 2) {
                    line.append(msgSplited[i + 1]);
                    break;
                    //Estalinha
                }

            }

            if (line == null) return;

            moveLinesInYAxis();
            Text newLine = new Text(xOfLatestMessage, yOfLatestMessage, line.toString());
            newLine.draw();
            messageLines.add(newLine);

        }

        private void moveLinesInYAxis() {
            for (Text line : messageLines) {
                line.translate(0, -14);
            }
        }
    }
}
