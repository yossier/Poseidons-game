package eecs285.proj4.group.Ships;


import eecs285.proj4.group.Graphics.Sprite;

public class PatrolBoat extends Ship
{
  private int health;
  private static final int ORIGINALHEALTH = 45;

  public PatrolBoat(){
    super();
    health = ORIGINALHEALTH;
  }
  
  @Override
  public int getSpeed()
  {
    return 5;
  }

  @Override
  public int getAttackPower()
  {
    return 2;
  }

  @Override
  public int getHealth()
  {
    return health;
  }

  @Override
  public void setHealth(int healthIn){
	System.out.println("in here updating health");
    health = healthIn;

    if(health < 0)
    {
      health = 0;
    }

  }
  
  
  // Radius from the center of the ship determined by the Location
  @Override
  public int getVisibilityRadius(){
    return 5;
  }
  
//Radius from the center of the ship determined by the Location
  @Override
  public int getAttackRadius(){
    return 2;
  }

  @Override
  public int getSize()
  {
    return 1;
  }
  
  public String getShipType()
  {
    return "Patrol Boat";
  }

  @Override
  public Sprite getSprite() {
    if(health <= 0)
    {
      return Sprite.ONETILEGRAVEYARD;
    }
    return Sprite.PATROLBOAT;
  }

  @Override
  public int getInitialHealth() {
	return 75;
  }

  @Override
  public int getOriginalHealth()
  {
    return ORIGINALHEALTH;
  }

}
