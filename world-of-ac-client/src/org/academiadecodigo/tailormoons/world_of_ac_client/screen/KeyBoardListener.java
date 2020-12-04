package org.academiadecodigo.tailormoons.world_of_ac_client.screen;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.TextBox;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.tailormoons.world_of_ac_client.utils.EncoderHeader;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class KeyBoardListener implements KeyboardHandler {

    private PrintWriter out;
    private final TextBoxVirtual textBox;
    private Map<Integer, Boolean> keyStates;
    private Keyboard myKeyboard;
    private static final int[] KEY_CODES = {
            KeyboardEvent.KEY_UP,
            KeyboardEvent.KEY_DOWN,
            KeyboardEvent.KEY_RIGHT,
            KeyboardEvent.KEY_LEFT,
            KeyboardEvent.KEY_ENTER,
            KeyboardEvent.KEY_SLASH,
            KeyboardEvent.KEY_RETURN,
            KeyboardEvent.KEY_DOT,
            KeyboardEvent.KEY_SPACE,
    };


    public KeyBoardListener(Socket clientSocket, TextBoxVirtual textBox) {
        this.textBox = textBox;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException ex) {
            System.err.println("Can't open output Stream. " + ex);
        }

        keyStates = new HashMap<>();
        myKeyboard = new Keyboard(this);
        setListeningEvents();

    }


    private void setListeningEvents() {

        for (int code : KEY_CODES) {
            for (KeyboardEventType type : KeyboardEventType.values()) {
                subscribe(code, type);
            }
        }

        for (int i = 48; i <= 57; i++) {
            for (KeyboardEventType type : KeyboardEventType.values()) {
                subscribe(i, type);
            }
        }

        for (int i = 65; i <= 90; i++) {
            for (KeyboardEventType type : KeyboardEventType.values()) {
                subscribe(i, type);
            }
        }

    }


    public void subscribe(int code, KeyboardEventType type) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(code);
        event.setKeyboardEventType(type);
        myKeyboard.addEventListener(event);
    }


    @Override
    public void keyPressed(KeyboardEvent e) {

        if (e.getKey() == KeyboardEvent.KEY_RETURN) {
            textBox.erase();
            return;
        }

        if (e.getKey() == KeyboardEvent.KEY_ENTER) {
            if(textBox.getText().startsWith(".quit")) {
                System.exit(0);
                return;
            }
            out.println(EncoderHeader.getTextHeader(textBox.getText()));
            textBox.clearText();
            return;
        }

        if (e.getKey() > 40 || e.getKey() == KeyboardEvent.KEY_SPACE) {
            textBox.addToText(e.getKey());
            return;
        }

        keyStates.put(e.getKey(), true);
        sendToMoveHeader();
    }


    @Override
    public void keyReleased(KeyboardEvent e) {
        keyStates.put(e.getKey(), false);
    }


    public void sendToMoveHeader() {
        Canvas.getInstance().focus();
        String protocol;
        protocol = EncoderHeader.getMoveHeader(this);
        out.println(protocol);
    }


    public boolean isPressed(int code) {
        return keyStates.getOrDefault(code, false);
    }

}
