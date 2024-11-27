import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SolidSprite extends Sprite {

    public SolidSprite(Image image, double x, double y, double width, double height) {
        super(image, x, y, width, height);
    }

    //création d'une variable local de type Rectangle2D.Double pour getHitBox
    //qui correspond au sprite déplacé de Speed pixel dans la bonne direction :
    protected Rectangle2D getHitBox() {
        return new Rectangle2D.Double(x,y,(double) width-14,(double) height-18); // modification pour adapter l'intersection avec les obstacles
    }
    //ici la variable créée hitbox grâce à la variable intersect et getHitBox
    public boolean intersect(Rectangle2D.Double hitBox) {
        return this.getHitBox().intersects(hitBox);
    }
}
