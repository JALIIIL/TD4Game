import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    //par défault marcher est associé à true
    private boolean isWalking =true;
    //vitesse de déplacement
    private double speed = 10;
    private final int spriteSheetNumberOfColumn = 10;
    //200 miliseconde entre 2 images
    private double timeBetweenFrame = 200;
    //direction initiale
    private Direction direction = Direction.EAST;
    //santé max du hero
    private int health;
    private int maxHealth;
    private boolean isDead;

    public DynamicSprite(Image image, double x, double y, double width, double height, int maxHealth) {
        super(image, x, y, width, height);
        this.health = maxHealth;
        this.isDead = false;
        this.maxHealth = maxHealth;
    }

    //methodes pour la santé du hero
    public int getHealth() {
        return health;
    }
    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);

        if (health <= 0 && !isDead) {
            isDead = true;
        }
    }
    public boolean isDead() {
        return isDead;
    }
    public void Heal(int amount) {
        health = Math.min(maxHealth, health + amount);
    }
    private void drawHealthBar(Graphics g) {
        //taille de la barre de vie
        int barWidth = 40;
        int barHeight = 5;
        int x = (int) this.x + (int)(48/2) - (barWidth / 2); //emplacement de la barre de vie selon l'axe x
        int y = (int) this.y - 10; //emplacement de la barre de vie selon l'axe y
        //couleur de la barre de vie par défault
        g.setColor(Color.RED);
        g.fillRect(x, y, barWidth, barHeight);

        g.setColor(Color.GREEN);
        int currentBarWidth = (int)((health / (double) maxHealth) * barWidth);
        g.fillRect(x, y, currentBarWidth, barHeight);
    }
    //setter pour la variable direction
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //méthode privée qui déplace le hero
    private void move() {
        switch (direction) {
            case NORTH -> {
                this.y -= speed;
            }
            case SOUTH -> {
                this.y += speed;
            }
            case EAST -> {
                this.x += speed;
            }
            case WEST -> {
                this.x -= speed;
            }
        }
    }
    //méthode créant une variable local que l'on retrouve dans la classe SolidSprite
    private boolean isMovingPossible(ArrayList<Sprite> environment){
        Rectangle2D.Double moved = new Rectangle2D.Double();
        switch(direction){
            case EAST: moved.setRect(super.getHitBox().getX()+speed,super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:  moved.setRect(super.getHitBox().getX()-speed,super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()-speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()+speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }

        for (Sprite sprite : environment){
            if((sprite instanceof Trap) && (sprite!=this)){
                if (((Trap) sprite).intersect(moved)){
                    if (((Trap) sprite).isActive()){
                        this.takeDamage(((Trap) sprite).getDamage());
                        ((Trap) sprite ).deactivate();
                    }
                    return true;
                }
            }
            if ((sprite instanceof SolidSprite) && (sprite!=this)){
                if (((SolidSprite) sprite).intersect(moved)){
                    return false;
                }
            }
        }
        return true;
    }

    //appel des deux fonctions (méthodes) précédentes "isMovingPossible" et "move":
    // on peut bouger si environnement nous le permet
    public void moveIfPossible(ArrayList<Sprite> environment){
        if (isMovingPossible(environment)){
            move();
        }
    }

    //surcharge de la methode draw :
    @Override
    public void draw(Graphics g) {
        if (health > 0) {
        //index donnant le numéro de l'image à afficher,
        // et on divise le temps courant par le temps entre deux trames modulo le nombre total :
            int index= (int) (System.currentTimeMillis()/timeBetweenFrame%spriteSheetNumberOfColumn);

        //on récupere un entier attitude qui correspond à la valeur numérique de la direction
            int attitude=direction.getFrameLineNumber();

        //le lutin démarre
            g.drawImage(image, (int) x, (int) y, (int) (x+width),(int) (y+height),
                    (int) (index*this.width), (int) (attitude*height),
                    (int) ((index+1)*this.width), (int)((attitude+1)*this.height),null);

            drawHealthBar(g);
        }

    }
}
