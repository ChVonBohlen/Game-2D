package org.Game2D.demo.flappy;

import org.Game2D.demo.flappy.entities.Bird;
import org.Game2D.demo.flappy.objects.BackgroundObject;
import org.Game2D.demo.flappy.objects.BaseObject;
import org.Game2D.engine.core.Instance;
import org.Game2D.engine.core.handlers.DataHand;
import org.Game2D.engine.core.managers.RenderMan;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class FlappyBird {

    public static void main(String[] args) {

        Instance instance = new Instance(null);
        instance.start("Flappy bird");

        int screenHeight = DataHand.renderMan.getHeight();
        int screenWidth = DataHand.renderMan.getWidth();

        Image texture;
        Image blueTxt;
        Image backgroundTxt;
        Image baseTxt;
        Image birdTxt;

        try {
            texture = ImageIO.read(Objects.requireNonNull(RenderMan.class.getClassLoader().getResource("default.png")));
            blueTxt = ImageIO.read(Objects.requireNonNull(RenderMan.class.getClassLoader().getResource("flappy_assets/background/blue.png")));
            backgroundTxt = ImageIO.read(Objects.requireNonNull(RenderMan.class.getClassLoader().getResource("flappy_assets/background/background-day.png")));
            baseTxt = ImageIO.read(Objects.requireNonNull(RenderMan.class.getClassLoader().getResource("flappy_assets/background/base.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Init background
        for (int i = 0; i <= screenWidth; i++) {
            new BackgroundObject(new Rectangle(i * 288, 0, 288, screenHeight - 122 - 512), false, blueTxt);
            new BackgroundObject(new Rectangle(i * 288, screenHeight - 122 - 512, 288, 512), false, backgroundTxt);
        }


        //Init bird
        new Bird(texture);

        //Init pipes

        //Init base
        for (int i = 0; i <= screenWidth; i++) {
            new BaseObject(new Rectangle(i * 336, screenHeight - 112, 336, 112), true, baseTxt);
        }

        //Init counter

    }

}
