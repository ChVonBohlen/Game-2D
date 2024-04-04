package de.Game2D.engine.core.managers;

import de.Game2D.engine.core.Instance;
import de.Game2D.engine.utils.ConfProvider;
//import de.Game2D.engine.objects.GameObject;

import javax.swing.*;
import java.awt.*;

public class RenderMan extends JPanel implements Runnable {

    Thread renderThread;

    private final Instance instance;

    public RenderMan(Instance i) {

        this.instance = i;

        confPanel();

        startRenderThread();

    }

    private void confPanel() {

        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

        this.setBackground(Color.BLACK);

        this.setDoubleBuffered(true);

        this.addKeyListener(instance.keyHandler);

        this.setFocusable(true);

    }

    protected void startRenderThread() {

        renderThread = new Thread(this);

        renderThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / Integer.parseInt(ConfProvider.getConf(instance.confPath).getProperty("game2d.core.fps"));
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (renderThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {

                repaint();

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {

                //instance.getDebugDisplay().updateFPS(drawCount);

                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        for (GameObject go : instance.getActionManager().getGameObjects()) {

            go.draw(g2);

        }

        //instance.getDebugDisplay().draw(g2);

        g2.dispose();
    }

}
