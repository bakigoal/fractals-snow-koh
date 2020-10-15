package com.bakigoal;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Снежинка Коха");
        frame.setSize(800, 800);
        frame.add(new FractalSnowKohPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
