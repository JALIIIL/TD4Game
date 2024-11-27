import java.util.ArrayList;

public class PhysicEngine implements Engine{
    //on définit les 2 attributs de cette classe :
    //dont une liste movingSpriteList qui contient l'ensemble des Lutins à mouvoir
    private ArrayList<DynamicSprite> movingSpriteList;
    //et une liste environment qui contient l'ensemble des lutins solides
    private ArrayList<Sprite> environment;

    //constructeur de PhysicEngine
    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    //méthode qui ajoute un élément à la liste "movingSpriteList":
    public void addSpriteToMovingList(DynamicSprite sprite) {
        movingSpriteList.add(sprite);
    }
    //setter pour la liste "environment":
    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    //surcharge de la méthode update
    // qui appelle la méthode "moveIfPossible" pour tous les éléments de la list "movingSpriteList":
    @Override
    public void update() {
        for (DynamicSprite dynamicsprite : movingSpriteList) {
            dynamicsprite.moveIfPossible(environment);
        }
    }
}
