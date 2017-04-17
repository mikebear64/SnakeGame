package ru.software.games.snake;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by MikeBear on 15.04.2017.
 */
public class KeyPressed extends Observable implements Runnable {

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
        super.addObserver(o);
    }

    @Override
    public void notifyObservers(Object arg) {
        setChanged();
        super.notifyObservers(arg);
    }

    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("--- === G A M E   C L O S E D === ---");
                System.exit(0);
            }
        });/**/

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                notifyObservers(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        frame.setSize(100, 100);
        frame.setVisible(true);
    }

}