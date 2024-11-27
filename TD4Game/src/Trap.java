import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;

public class Trap extends SolidSprite{
    //attributs du piège:
    private int damage;
    private boolean active;
    private Timer timer;
    private final long DELAI_MS = 2000;

    //constructeur du piège:
    public Trap(Image image,double x, double y, double width, double height, int damage){
        super(image,x,y,width,height);
        this.damage = damage;
        active = true;
        timer = new Timer();
    }

    //methodes obtention des dommages
    public int getDamage() {
        return damage;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        if (active) {
            active = false;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    active = true;
                }
            }, DELAI_MS);
        }
    }


    @Override
    public Rectangle2D getHitBox() {
    return new Rectangle2D.Double(x, y, width, height);
}
    public boolean intersect(Rectangle2D rectangle) {
    return getHitBox().intersects(rectangle);
}

}