package csc201FinalProject;
/**
 * Subclass of League, abstract class used to establish teams for each sport that extends this class. 
 */
public abstract class Team extends League
{

	public Team()
	{
		super();
	}
	public Team(String initialLeagueName)
	{
		super(initialLeagueName);
	}
	/**
	 * Abstract method to be used by subclass to generate statistics.
	 */
	public abstract void readAndCalculate();
	
		
}

