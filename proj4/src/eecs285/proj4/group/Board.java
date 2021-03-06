/**
 * 
 */
package eecs285.proj4.group;

import eecs285.proj4.group.Ships.Ship;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Board
{
  private ArrayList<Ship> shipList;
  private ArrayList<Ship> opponentShipList;

  /**
   * 
   */
  public Board()
  {
	shipList = new ArrayList<Ship>();
	opponentShipList = new ArrayList<Ship>();
  }

  public ArrayList<Ship> getShipArray()
  {
    return shipList;
  }
  
//---------------------------------------------------------------
  public Ship getShip(int shipId)
  {
	  for(Ship curShip : shipList){
		  if(curShip.getID() == shipId){
			  return curShip;
		  }
	  }
	  // should never reach here
	  return null;
  }
  
//--------------------------------------------------------------- 
  public Ship getShip(Location curLoc)
  {
    for (Ship curShip : shipList)
    {
      for (Location newLoc = 
          new Location(curShip.getCurrentLocation().getX(), curShip.getCurrentLocation().getY()),
          endLoc = newLoc.add(curShip.getSize()); 
          !newLoc.compareLoc(endLoc); 
          newLoc.incrementX())
      {
        if (newLoc.compareLoc(curLoc))
        {
          return curShip;
        }  
      }
    }
    return null;
  }
  
//---------------------------------------------------------------
  public Ship getOpponentShip(Location curLoc)
  {
	  for (Ship curShip : opponentShipList)
	  {
	    for (Location newLoc = 
	        new Location(curShip.getCurrentLocation().getX(),
              curShip.getCurrentLocation().getY()),
	            endLoc = newLoc.add(curShip.getSize());
	        !newLoc.compareLoc(endLoc); 
	        newLoc.incrementX())
	    {
	      if (newLoc.compareLoc(curLoc)){
	        return curShip;
	      }  
	    }
	  }
	  return null;
  }
  
//---------------------------------------------------------------  
  public Ship getOpponentShip(int id){
	  for(Ship curShip : opponentShipList){
		  if(curShip.getID() == id){
			  return curShip;
		  }
	  }
	  // should never reach here
	  return null;
  }

//---------------------------------------------------------------  
  //in case of things other than ships that occupy locations
  //and maybe to check if an attack was successful
  public boolean isLocOccupied(Location curLoc)
  {
    for (Ship curShip : shipList)
    {
      if (curShip.getCurrentLocation().compareLoc(curLoc))
      {
        return true;
      }



    }

    for (Ship curShip : opponentShipList)
    {
      if (curShip.getCurrentLocation().compareLoc(curLoc))
      {
        return true;
      }
    }

    return false;
  }

//---------------------------------------------------------------
//---------------------------------------------------------------  
  //helper to set up ship locations initially
  public void addShip(Ship curShip, Location curLoc)
  {
    curShip.setCurrentLocation(curLoc);
    shipList.add(curShip);
  }
  
//---------------------------------------------------------------  
  public void addShip(Ship curShip)
  {
    shipList.add(curShip);
  }
  
//---------------------------------------------------------------  
  public void resetShips(){
	  shipList.clear();
  }

//---------------------------------------------------------------  
  public ArrayList<Ship> getShips(){
	  return shipList;
  }

//---------------------------------------------------------------  
  public boolean shipIsEmpty(){
	  return shipList.isEmpty();
  }

//---------------------------------------------------------------  
  public void addOpponentShip(Ship shipIn){
	  opponentShipList.add(shipIn);
  }

//---------------------------------------------------------------  
  public ArrayList<Ship> getOpponentShips(){
	  return opponentShipList;
  }

//---------------------------------------------------------------  
  public boolean opponentShipIsEmpty(){
	  return opponentShipList.isEmpty();
  }

//---------------------------------------------------------------  
  public boolean isAttackLocationInRange(Ship ship, Location location)
  {
    int yRange;
    int xRange = ship.getAttackRadius();
    Location current = ship.getCurrentLocation();
    Location basePoint;

    for(int i = 0; i < ship.getSize(); ++i)
    {
    	basePoint = new Location(current.getX() + i, current.getY());
    	yRange = ship.getAttackRadius();
	    for(int xx = 0; xx <= xRange; ++xx)
	    {
	      for(int yy = 0; yy <= yRange; ++yy)
	      {
	        Location checkUpLoc = new Location(basePoint.getX() + xx, basePoint.getY() + yy);
	        Location checkDownLoc = new Location(basePoint.getX() + xx, basePoint.getY() - yy);
	        Location checkUpBackLoc = new Location(basePoint.getX() - xx, basePoint.getY() + yy);
	        Location checkDownBackLoc = new Location(basePoint.getX() - xx, basePoint.getY() - yy);
	        
	        if(location.compareLoc(checkUpLoc) || location.compareLoc(checkDownLoc)
	            || location.compareLoc(checkUpBackLoc) || location.compareLoc(checkDownBackLoc))
	        {
	          return true;
	        }
	      }
	      --yRange;
	    }
    }

    return false;
  }

//---------------------------------------------------------------  
  public boolean isMoveValid(Ship ship, Location location)
  {
    int size = ship.getSize();
    int y = location.getY();
    int x = location.getX();
    int yRange;
    int xRange = yRange = ship.getSpeed();
    Location basePoint = ship.getCurrentLocation();

    if(size == 3)
    {
      //compensate for aircraft carrier.
      location.decrementX();
      --x;
    }

    for(int i = 0; i < size; ++i)
    {
      if((x < 0) || (y < 0) || ((x + i) > 22) || (y > 19))
      {
        return false;
      }

      Location checkLoc = new Location(location.getX() + i, location.getY());
      Ship checkShip = getShip(checkLoc);
      if(checkShip != null)
      {
        if(checkShip.getID() != ship.getID())
          {
            //if ship is in location, and it is not the same ship the move is invalid
            return false;
          }
      }

      checkShip = getOpponentShip(checkLoc);
      if(checkShip != null)
      {
        if(checkShip.getID() != ship.getID())
        {
          //if ship is in location, and it is not the same ship the move is invalid
          return false;
        }
      }


    }
      for(int xx = 0; xx <= xRange; ++xx)
      {
        for(int yy = 0; yy <= yRange; ++yy)
        {
          Location checkUpLoc = new Location(basePoint.getX() + xx, basePoint.getY() + yy);
          Location checkDownLoc = new Location(basePoint.getX() + xx, basePoint.getY() - yy);
          Location checkUpBackLoc = new Location(basePoint.getX() - xx, basePoint.getY() + yy);
          Location checkDownBackLoc = new Location(basePoint.getX() - xx, basePoint.getY() - yy);

          if(location.compareLoc(checkUpLoc) || location.compareLoc(checkDownLoc)
              || location.compareLoc(checkUpBackLoc) || location.compareLoc(checkDownBackLoc))
            {
              return true;
          }
        }
        --yRange;
      }
    return false;
  }
  
//---------------------------------------------------------------  
  public boolean moveShip(Ship ship, Location location, GamePlay game)
  {
    if(isMoveValid(ship, location))
    {
      System.out.println("valid move!");
      ship.setCurrentLocation(location);
      game.getNetwork().sendShipMove(ship);
      game.decrementActions();
      return true;
    }
    //error message when a ship's move in invalid
    JOptionPane frame = new JOptionPane();
    JOptionPane.showMessageDialog(frame,
        "ERROR: Invalid Move! Please Choose a New Location in the Green Highlighted Tiles",
        "Move Error",
        JOptionPane.ERROR_MESSAGE);
   
    return false;

  }

//---------------------------------------------------------------  
  public boolean attack(Ship ship, Location location)
  {
    if(isAttackLocationInRange(ship, location))
    {
      System.out.println("ATTACK!!!!");
      return true;
    }
    else
    {
      JOptionPane frame = new JOptionPane();
      JOptionPane.showMessageDialog(frame,
          "ERROR: Invalid Attack! You Have to Attack in Range!",
          "Attack Error",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }

  }
  
  
}
