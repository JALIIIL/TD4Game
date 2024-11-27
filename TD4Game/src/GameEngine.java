import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    //référence vers le héro
    private final DynamicSprite hero;

    // constructeur qui fixe la référence vers le héro
    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    @Override
    public void update() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //on surcharge la méthode keyPressed pour faire la mise à jour de la varible direction du héro
    @Override
    public void keyPressed(KeyEvent e) {
        //on positionne la variable "direction" du héro avec celle obtenue par les différents cas "e.getKeyCode"
        switch(e.getKeyCode()){
            //cas pour la flèche du haut sur le clavier
            case KeyEvent.VK_UP :
                hero.setDirection(Direction.NORTH);
                break;
            //cas pour la flèche du bas sur le clavier
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            //cas pour la flèche de gauche sur le clavier
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            //cas pour la flèche de droite sur le clavier
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;
            default:
                // Par défault ne rien faire pour les autres touches
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
