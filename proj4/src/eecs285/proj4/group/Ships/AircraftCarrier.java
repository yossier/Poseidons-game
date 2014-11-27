package eecs285.proj4.group.Ships;


import eecs285.proj4.group.Graphics.Screen;
import eecs285.proj4.group.Graphics.Sprite;

public class AircraftCarrier extends Ship
{
  private int health;

  public AircraftCarrier(){
    super();
    health = 50;
  }
  
  @Override
  public int getSpeed()
  {
    return 1;
  }

  @Override
  public int getAttackPower()
  {
    return 9;
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
    return 5;
  }
  
//Radius from the center of the ship determined by the Location
  @Override
  public int getAttackRadius(){
    return 30;
  }

  @Override
  public int getSize()
  {
    return 3;
  }
  
  public String getShipType()
  {
    return "AircraftCarrier";
  }

  @Override
  public Sprite getSprite() {
    return Sprite.AIRCRAFTCARRIER;
  }

}