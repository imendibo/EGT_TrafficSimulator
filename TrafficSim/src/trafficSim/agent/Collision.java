package trafficSim.agent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import repast.simphony.space.grid.Grid;
import trafficSim.car.CarReasonerState;

/**
 * 
 * @author Javier Morales (jmoralesmat@gmail.com)
 *
 */
public class Collision implements TrafficElement
{
	/**
	 * 
	 */
	private int x, y;
	
	/**
	 * 
	 */
	private List<TrafficElement> elements;
	
	/**
	 * 
	 */
	private boolean violation;
	
	/**
	 * 
	 * @param car1
	 * @param car2
	 */
	public Collision(int x, int y, Grid<TrafficElement> map)
	{
		Iterable<TrafficElement> elems = map.getObjectsAt(x,y);
		Iterator<TrafficElement> iterator = elems.iterator();
		this.elements = new ArrayList<TrafficElement>();
		
		while(iterator.hasNext())
		{
			Car car = (Car)iterator.next();
			if(car.getReasonerState() == CarReasonerState.NormWillBeViolated)
				violation = true;
			
			this.elements.add(car);	
		}
	}
	
	/**
	 * 
	 * @param carId
	 * @return
	 */
	public boolean contains(long carId)
	{
		for(TrafficElement elem : this.elements)
			if(elem instanceof Car)
				if(((Car)elem).getId()==carId)
					return true;
		
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<TrafficElement> getElements()
	{
		return this.elements;
	}
	/**
	 * 
	 */
  public int getX()
	{
	  return this.x;
  }

	/**
	 * 
	 */
  public int getY()
	{
	  return this.y;
  }

	/**
	 * 
	 * @return
	 */
	public boolean isViolation()
	{
		return violation;
	}
	
  public void move() {
	  // TODO Auto-generated method stub
	  
  }

	/**
	 * 
	 */
  public List<Long> getAgentIds() {
		List<Long> agentIds = new ArrayList<Long>();
		
		for(TrafficElement element : elements)
		{
			agentIds.add(((Car)element).getId());
		}
	  return agentIds;
  }
}
