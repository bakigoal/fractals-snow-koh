package com.bakigoal;

import javax.swing.*;
import java.awt.*;

public class FractalSnowKohPanel extends JPanel {
    public FractalSnowKohPanel() {
        setBackground(new Color(40,42,54));
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawSnow(g2, Color.LIGHT_GRAY,0, -140);
        drawSnow(g2, Color.GREEN,1, -110);
        drawSnow(g2, Color.MAGENTA,2, -70);
        drawSnow(g2, new Color(255,111,0),3, -10);
        drawSnow(g2, Color.YELLOW,4, 70);
        drawSnow(g2, Color.CYAN,5, 180);
    }

    private void drawSnow(Graphics2D g, Color color, int recursions, int k) {
        g.setColor(color);

        int w = getWidth();
        int h = getHeight();
        int x = h / 4 + k;
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

    public void drawKochLine(Graphics g, Point a, Point b, double fi, int n) {

        if (n <= 0) {
            // рисуем прямую, если достигнута необходимая глубина рекурсии.
            g.drawLine(a.x, a.y, b.x, b.y);
        } else {
            // находим длину отрезка (a; b).
            double length = Math.sqrt(Math.pow(b.y - a.y, 2) + Math.pow(b.x - a.x, 2));
            // находим длину 1/3 отрезка (a; b)
            double length1of3 = length / 3;

            long cos1of3 = Math.round((length1of3 * Math.cos(fi)));
            long sin1of3 = Math.round((length1of3 * Math.sin(fi)));

            // находим т., делящую отрезок как 1:3.
            Point a1 = new Point(a.x + (int) cos1of3, a.y + (int) sin1of3);

            // находим т., делящую отрезок как 2:3.
            Point b1 = new Point(a1.x + (int) cos1of3, a1.y + (int) sin1of3);

            // находим т., которая будет вершиной равностороннего
            // треугольника.
            Point c = new Point(a1.x + (int) Math.round((length1of3 * Math.cos(fi + Math.PI / 3))),
                    a1.y + (int) Math.round((length1of3 * Math.sin(fi + Math.PI / 3))));


            n--;
            drawKochLine(g, a1, c, fi + Math.PI / 3, n);
            drawKochLine(g, c, b1, fi - Math.PI / 3, n);
            drawKochLine(g, a, a1, fi, n);
            drawKochLine(g, b1, b, fi, n);
        }
    }

}
