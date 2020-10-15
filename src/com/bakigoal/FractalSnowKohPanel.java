package com.bakigoal;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class FractalSnowKohPanel extends JPanel {
    private static final int SPEED = 1000;
    private static final int PHASE_COUNT = 6;
    private static final java.util.List<Color> COLORS = Arrays.asList(
            Color.WHITE, Color.GREEN, Color.MAGENTA,
            new Color(255, 111, 0), Color.YELLOW, Color.CYAN);
    private int phase = 0;

    public FractalSnowKohPanel() {
        setBackground(new Color(40, 42, 54));
        new Timer(SPEED, event -> {
            phase = (phase + 1) % PHASE_COUNT;
            repaint();
        }).start();
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        drawSnow(g, getRandomColor(), phase, 128);
        drawPhase(g);
    }

    private void drawPhase(Graphics g) {
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(128F);
        g.setFont(newFont);
        g.drawString(String.format("%d", phase), getWidth() / 2 - 32, getHeight() / 2 + 32);
    }

    private Color getRandomColor() {
        return COLORS.get(phase);
    }

    private void drawSnow(Graphics g, Color color, int recursions, int delta) {
        g.setColor(color);

        int w = getWidth();
        int h = getHeight();
        int x = h / 4 + delta;
        int cy = h / 2 - x;
        int y = h / 2 + x / 2;
        int ax = (int) (w / 2 - Math.sqrt(3) * x / 2);
        int bx = (int) (w / 2 + Math.sqrt(3) * x / 2);

        Point a = new Point(ax, y);
        Point b = new Point(bx, y);
        Point c = new Point(w / 2, cy);
        drawKochLine(g, a, b, 0, recursions);
        drawKochLine(g, c, a, Math.PI / 3 * 2, recursions);
        drawKochLine(g, b, c, -Math.PI / 3 * 2, recursions);
    }

    public void drawKochLine(Graphics g, Point a, Point b, double angle, int n) {
        if (n <= 0) {
            g.drawLine(a.x, a.y, b.x, b.y);
            return;
        }

        double length = Math.sqrt(Math.pow(b.y - a.y, 2) + Math.pow(b.x - a.x, 2));
        double length1of3 = length / 3;
        long cos1of3 = Math.round((length1of3 * Math.cos(angle)));
        long sin1of3 = Math.round((length1of3 * Math.sin(angle)));

        Point m1 = new Point(a.x + (int) cos1of3, a.y + (int) sin1of3);
        Point m2 = new Point(m1.x + (int) cos1of3, m1.y + (int) sin1of3);
        int cx = m1.x + (int) Math.round((length1of3 * Math.cos(angle + Math.PI / 3)));
        int cy = m1.y + (int) Math.round((length1of3 * Math.sin(angle + Math.PI / 3)));
        Point c = new Point(cx, cy);

        n--;
        drawKochLine(g, m1, c, angle + Math.PI / 3, n);
        drawKochLine(g, c, m2, angle - Math.PI / 3, n);
        drawKochLine(g, a, m1, angle, n);
        drawKochLine(g, m2, b, angle, n);
    }

}
