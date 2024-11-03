package org.Game2D.demo.flappy.entities.pipes;

import org.Game2D.demo.flappy.entities.Bird;
import org.Game2D.engine.objects.advanced.Entity;
import org.Game2D.engine.utils.AssetMan;

import java.awt.*;

public class PipeTop extends Entity {

    public PipeFiller filler;
    public PipeTop(int x, int y) {
        super(new Rectangle(x, y, 104, 612), true, AssetMan.loadAsset("flappy_assets/pipe/pipe-green.png"));
        filler = new PipeFiller(x, y, false);
    }

    @Override
    public void update() {

        if(Bird.gameOver) return;

        Bird.gameOver = move(-Bird.speed, 0) == null ? false : true;
    }

    public void adjustFiller(){
        filler.adjust(this.hitBox.x, this.hitBox.y, false);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(texture, hitBox.x, hitBox.y, hitBox.width, hitBox.height, null);
    }
}
