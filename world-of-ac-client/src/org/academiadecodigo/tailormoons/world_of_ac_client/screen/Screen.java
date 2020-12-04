package org.academiadecodigo.tailormoons.world_of_ac_client.screen;

import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.net.Socket;

public class Screen {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int CHAT_BOX_WIDTH = 200;


    public Screen(Socket clientSocket) {

        Canvas.getInstance().getFrame().setSize(WIDTH + 17 + CHAT_BOX_WIDTH, HEIGHT + 40 + 25);

        Picture picture = new Picture(0, 0, "assets/background/grassBackground.png");
        picture.draw();

        Rectangle rectangle = new Rectangle(0, HEIGHT, WIDTH, 25);
        rectangle.draw();

        Rectangle chatBoxBackground = new Rectangle(WIDTH + 17, 0, CHAT_BOX_WIDTH, HEIGHT + 40 +25);
        chatBoxBackground.setColor(Color.WHITE);
        chatBoxBackground.fill();


        TextBox textBox = new TextBox(0, HEIGHT, WIDTH - 83, 25);
        //textBox.draw();

        TextBoxVirtual textBoxVirtual = new TextBoxVirtual();

        Picture button = new Picture(WIDTH-88, HEIGHT-5, "assets/buttons/sendButton.png");
        button.draw();

        MouseListener mouseListener = new MouseListener(textBoxVirtual, clientSocket);
        KeyBoardListener keyBoardListener = new KeyBoardListener(clientSocket, textBoxVirtual);

    }


}
