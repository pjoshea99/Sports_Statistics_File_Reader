package csc201FinalProject;
import java.util.Scanner;
/**
 * The League class is the base class for this project. 
 */
public abstract class League 
{
	private String leagueName = null;
	
	public League()
	{
		leagueName = "No name yet";
	}
	public League(String initialLeagueName)
	{
		leagueName = initialLeagueName;
	}
	/**
	 * @return returns leagueName.
	 */
	public String getLeagueName()
	{
		return leagueName;
	}
	
	/**
	 * Sets the name of the league. Only accepts the strings NBA, NFL, or NHL. Type the league for each sport.
	 * @return leagueName
	 */
	public String setLeagueName()
	{
		boolean acceptableName = false;
		while (acceptableName == false)
		{
			System.out.println("Please insert the sports league, either NBA, NFL, or NHL:");
			Scanner keyboard = new Scanner(System.in);
			leagueName = keyboard.next();
			{
				if(leagueName.equalsIgnoreCase("NBA"))
				{
					System.out.println("Ensure you are using the Basketball class to upload your file to receive correct statistics.");
					acceptableName = true;
				}
				else if(leagueName.equalsIgnoreCase("NFL"))
				{
					System.out.println("Ensure you are using the Football class to upload your file to receive correct statistics.");
					acceptableName = true;
				}
				else if(leagueName.equalsIgnoreCase("NHL"))
				{
					System.out.println("Ensure you are using the Hockey class to upload your file to receive correct statistics.");
					acceptableName = true;
				}
				else
				{
					System.out.println("Please type a correct league name.");
				}
			}
		}
		
		return leagueName;
	}
	
	
	/**
	 * abstract method to be used by subclasses to calculate statistics. This allows for polymorphism.
	 */
	public abstract void readAndCalculate();
	
	
}
