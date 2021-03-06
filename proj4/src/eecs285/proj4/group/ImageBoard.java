package eecs285.proj4.group;

import eecs285.proj4.group.Graphics.Screen;
import eecs285.proj4.group.Ships.*;

import javax.swing.*;

import java.awt.*;

public class ImageBoard extends JPanel{

  private static final long serialVersionUID = 1L;
  public static int width = 368;
  public static int height = 320;
  public static int scale = 2;


  private Screen screen;

  public ImageBoard()
  {
    Dimension size = new Dimension(width * scale, height * scale);
    setPreferredSize(size);
    setDoubleBuffered(true);
    screen= new Screen(width, height);
  }

//---------------------------------------------------------------
  public void paintComponent( Graphics g)
  {
    g.drawImage(screen, 0, 0, getWidth(), getHeight(), null);
    g.dispose();
  }
  
//---------------------------------------------------------------
  /**
   * Redraws the board based on the state.
   */
  public void updateBoard(Player player)
  {
    screen.clear ();
    screen.render(player);
  }

//---------------------------------------------------------------
  public Ship identifyPlayerShip(Location clicked_Location, Board state) throws Exception
  {
    //will throw a you don't have a ship there exception
    return state.getShip(clicked_Location);
  }

//---------------------------------------------------------------

  public Screen getScreen()
  {
    return screen;
  }


}
