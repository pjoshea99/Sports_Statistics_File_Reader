package csc201FinalProject;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
/**
 * The Basketball class is used to create statistics for the NBA.
 */
public class Basketball extends Team implements Statistics
{

	private Basketball[] basketballStats = new Basketball[NUMBER_OF_TEAMS];
	private static final int NUMBER_OF_GAMES = 72;
	private static final int NUMBER_OF_TEAMS = 30;
	private double fieldGoals = 0; //number of field goals
	private double fieldGoalAttempts = 0; //number of attempted field goals
	private double wins = 0;
	private double winPercentage = 0; //(wins/NUMBER_OF_GAMES) *100
	private double fieldGoalPercentage = 0; //(fieldGoals/fieldGoalAttempts)*100
	private String teamName = null;
	private String filePath = null;
	
	
	
	
	public Basketball()
	{
		super();
	}
	public Basketball(String initialLeagueName, String initialTeamName, double initialFieldGoals, double initialWinPercentage, double initialFieldGoalPercentage, double initialWins)
	{
		super(initialLeagueName);
		teamName = initialTeamName;
		fieldGoals = initialFieldGoals;
		winPercentage = initialWinPercentage;
		fieldGoalPercentage = initialFieldGoalPercentage;
		wins = initialWins;
	}
	/**
	 * @return fieldGoals
	 */
	public double getFieldGoals()
	{
		return fieldGoals;
	}
	/**
	 * @return winPercentage 
	 * (wins/NUMBER_OF_GAMES)*100
	 */
	public double getWinPercentage()
	{
		return winPercentage;
	}
	/**
	 * @return fieldGoalPercentage
	 * (fieldGoals/fieldGoalAttempts)*100
	 */
	public double getFieldGoalPercentage()
	{
		return fieldGoalPercentage;
	}
	/**
	 * @return teamName
	 */
	public String getTeamName()
	{
		return teamName;
	}
	
	/**
	 * @return wins
	 */
	public double getWins()
	{
		return wins;
	}
	
	/**
	 * reads the file path as a string for the leagues statistics
	 * @return filePath
	 */
	public String readFilePath()
	{
		System.out.println("Please type the file path for the CSV file to read");
		Scanner keyboard = new Scanner(System.in);
		filePath = keyboard.next();
		return filePath;
	}

	/**
	 * Class specific method to read the values from the csv file read in the readFilePath method.
	 * Each line is stored into the basketballStats array as a new instance of the Basketball class.
	 * When all lines are read method returns filled array of basketball teams.
	 * @return basketballStats
	 * @throws FileNotFoundException if the file cannot be found
	 * @throws IOException if there is a problem with file I/O.
	 */
	public Basketball[] readBasketballStats()
	{
		try
		{
			Scanner inputStream = new Scanner(new File(filePath));
			String nextLine = inputStream.nextLine();
			int index = 0;
			while (inputStream.hasNextLine() && (index <= (NUMBER_OF_TEAMS -1)))
			{
				nextLine = inputStream.nextLine();
				String [] basketballArray = nextLine.split(",");
				teamName = basketballArray[0];
				fieldGoals = Double.parseDouble(basketballArray[1]);
				fieldGoalAttempts = Double.parseDouble(basketballArray[2]);
				wins = Double.parseDouble(basketballArray[3]);
				winPercentage = ((wins/NUMBER_OF_GAMES) * 100);
				fieldGoalPercentage = ((fieldGoals / fieldGoalAttempts)*100);
				basketballStats[index] = new Basketball(this.getLeagueName(), teamName, fieldGoals, this.winPercentage, this.fieldGoalPercentage, wins);
				index++;
				
			}
			inputStream.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(filePath + " not found.");
			System.exit(0);
		}
		catch(IOException e)
		{
			System.out.println("There are issues whith " + filePath);
		}
		return basketballStats;
	}
	
	/**
	 * prints the completed calculations to the console.
	 */
	public void outputAllStatistics()
	{
		
		System.out.println("                                     " + this.getLeagueName().toUpperCase() + " Season Statistics:");
		System.out.printf("%-22s %-19s %-25s %-14s %-16s %n","Team:", "Total Field Goals:", "Field Goal Percentage:", "Total Wins:", "Win Percentage:" );// Total Field Goals:   Field Goal percentage:   Total Wins:   Win percentage:");
		for(int index = 0; index <= (NUMBER_OF_TEAMS - 1); index++)
		{
			System.out.printf("%-23s %17d %22.2f%% %14d %17.2f%% %n", basketballStats[index].getTeamName(), (int)basketballStats[index].getFieldGoals(), (basketballStats[index].getFieldGoalPercentage()), (int)basketballStats[index].getWins(), basketballStats[index].getWinPercentage());
		}
	}
	/**
	 * void method using readFilepath(), outputAllStatistics(), and readBaksetballStats() to read, calculate, and output all statistics.
	 */
	public void readAndCalculate()
	{
		this.readFilePath();
		this.readBasketballStats();
		this.outputAllStatistics();
		
	}
	

}







