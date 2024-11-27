import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    JFrame displayZoneFrame;
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;


    public Main() throws Exception{
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400,600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //test pour faire apparaître une des images du hero à une certaine position :
        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("./img/heroTileSheetLowRes.png")),60,300,48,50, 100);

        //création d'une instance de RenderEngine
        renderEngine = new RenderEngine(displayZoneFrame);
        //création d'instance de timer exécutant le RenderEngine
        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());

        //création d'une instance de GameEngine
        gameEngine = new GameEngine(hero);
        //création d'instance de timer exécutant le GameEngine
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());

        //création d'une instance de PhysicEngine
        physicEngine = new PhysicEngine();
        //création d'instance de timer exécutant le PhysicEngine
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());

        //On démarre les 3 différents timers
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        //test pour  faire apparaître un arbre :
        //Sprite test = new DynamicSprite(ImageIO.read(new File("./img/tree.png")),200,300,64,64);
        //renderEngine.addToRenderList(test);
        //test pour faire apparaître un rocher :
        //SolidSprite testSprite = new SolidSprite(ImageIO.read(new File("./img/rock.png")),250,300,64,64);
        //renderEngine.addToRenderList(testSprite);
        //physicEngine.setEnvironment(new ArrayList<SolidSprite>(testSprite)); //le test ne fonctionnait pas, je ne sais pas pourquoi

        Playground level = new Playground("./data/level1.txt");

        renderEngine.addToRenderList(level.getSpriteList());
        //fait apparaître le hero
        renderEngine.addToRenderList(hero);
        //permet de faire bouger notre hero
        physicEngine.addSpriteToMovingList(hero);

        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main (String[] args) throws Exception {
        Main main = new Main();
    }
}