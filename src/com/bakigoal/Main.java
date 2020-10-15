package com.bakigoal;

import javax.swing.*;

public class Main {
    public static int size = 800;

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Снежинка Коха");
        frame.setSize(size, size);
        frame.add(new FractalSnowKohPanel());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
