import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine{
    //Attribut privée renderList qui est une liste de Displayable
    private ArrayList<Displayable> renderList;

    //Constructeur par défaut qui initialise renderList
    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    public void setRenderList(ArrayList<Displayable> renderList) {
        this.renderList = renderList;
    }

    public void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }
    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    //surcharge de la méthode
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject:renderList) {
            renderObject.draw(g);
        }
    }
    @Override
    public void update() {
        System.out.println("Mise à jour du RenderEngine");
        this.repaint();
    }

}

