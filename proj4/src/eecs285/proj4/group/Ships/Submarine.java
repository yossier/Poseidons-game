package eecs285.proj4.group.Ships;

import eecs285.proj4.group.Graphics.Sprite;

public class Submarine extends Ship
{
  private int health;

  public Submarine(){
    super();
    health = 60;
  }
  
  @Override
  public int getSpeed()
  {
    return 5;
  }

  @Override
  public int getAttackPower()
  {
    return 5;
  }
  
  @Override
  public int getInitialHealth(){
	  return 60;
  }

  @Override
  public int getHealth()
  {
    return health;
  }

  @Override
  public void setHealth(int healthIn){
    health = healthIn;
  }
  
  
  // Radius from the center of the ship determined by the Location
  @Override
  public int getVisibilityRadius(){
    return 0;
  }
  
//Radius from the center of the ship determined by the Location
  @Override
  public int getAttackRadius(){
    return 15;
  }

  @Override
  public int getSize()
  {
    return 1;
  }
  
  public String getShipType()
  {
    return "Submarine";
  }

  @Override
  public Sprite getSprite() {
    return Sprite.SUBMARINE;
  }

}