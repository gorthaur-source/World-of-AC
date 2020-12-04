package org.academiadecodigo.tailormoons.world_of_ac_client.screen;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.TextBox;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.tailormoons.world_of_ac_client.utils.EncoderHeader;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MouseListener implements MouseHandler {

    private TextBoxVirtual textBox;
    private PrintWriter out;

    public MouseListener(TextBoxVirtual textBox, Socket clientSocket) {
        this.textBox = textBox;
        init();

            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    private void init() {
        Mouse mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
        mouse.addEventListener(MouseEventType.MOUSE_PRESSED);
        mouse.addEventListener(MouseEventType.MOUSE_RELEASED);
        mouse.addEventListener(MouseEventType.MOUSE_MOVED);
        mouse.addEventListener(MouseEventType.MOUSE_DRAGGED);
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        if (mouseEvent.getY() >= Screen.HEIGHT) {
            if (mouseEvent.getX() >= Screen.WIDTH - 90) {
                Canvas.getInstance().focus();
                String protocol = EncoderHeader.getTextHeader(textBox.getText());
                textBox.clearText();
                out.println(protocol);
                textBox.clearText();
                return;
            }
            //textBox.focus();
            return;
        }
        Canvas.getInstance().focus();
    }


    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }


    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }


    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
    }

}
