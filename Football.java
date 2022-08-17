package csc201FinalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 * The Football class is used to create statistics for the NFL.
 */
public class Football extends Team implements Statistics
{
	private Football[] footballStats = new Football[NUMBER_OF_TEAMS];
	private static final int NUMBER_OF_GAMES = 17;
	private static final int NUMBER_OF_TEAMS = 32;
	private double fieldGoals = 0; //number of field goals
	private double fieldGoalAttempts = 0; // number of attempted field goals
	private double fieldGoalPercentage = 0; //fieldGoals/fieldGoalAttempts
	private double wins = 0;
	private double winPercentage = 0; //(wins/NUMBER_OF_GAMES) *100
	private double passesCompleted = 0; //passes completed
	private double passAttempts = 0; //attempted passes
	private double passPercentage = 0; //passesCompleted/passAttempts
	private String teamName = null;
	private String filePath = null;
	
	public Football()
	{
		super();
	}
	public Football (String initialLeagueName, String initialTeamName, double initialFieldGoals, double initialFieldGoalPercentage, double initialWins, double initialWinPercentage, double initialPassesCompleted, double initialPassPercentage)
	{
		super(initialLeagueName);
		teamName = initialTeamName;
		fieldGoals = initialFieldGoals;
		fieldGoalPercentage = initialFieldGoalPercentage;
		wins = initialWins;
		winPercentage = initialWinPercentage;
		passesCompleted = initialPassesCompleted;
		passPercentage = initialPassPercentage;
	}
	
	
	/**
	 * @return wins
	 */
	public double getWins() 
	{
		
		return wins;
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
	 * @return passPErcentage 
	 * (passesCompleted/passAttempts)*100
	 */
	public double getPassPercentage()
	{
		return passPercentage;
	}
	
	/**
	 *@return passesCompleted
	 */
	public double getPassesCompleted()
	{
		return passesCompleted;
	}
	
	/**
	 * @return fieldGoals
	 */
	public double getFieldGoals()
	{
		return fieldGoals;
	}
	
	/**
	 * @return teamName
	 */
	public String getTeamName() 
	{
		
		return teamName;
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
	 * Each line is stored into the footballStats array as a new instance of the Football class.
	 * When all lines are read method returns filled array of football teams.
	 * @return footballStats
	 * @throws FileNotFoundException if the file cannot be found
	 * @throws IOException if there is a problem with file I/O.
	 */
	public Football[] readFootballStats()
	{
		
		try
		{
			Scanner inputStream = new Scanner(new File(filePath));		
			String nextLine = inputStream.nextLine();
			int index = 0;
			while (inputStream.hasNextLine() && (index <= (NUMBER_OF_TEAMS -1)))
			{
				nextLine = inputStream.nextLine();
				String [] footballArray = nextLine.split(",");
				teamName = footballArray[0];
				fieldGoals = Double.parseDouble(footballArray[1]);
				fieldGoalAttempts = Double.parseDouble(footballArray[2]);
				passesCompleted = Double.parseDouble(footballArray[3]);
				passAttempts = Double.parseDouble(footballArray[4]);
				wins = Double.parseDouble(footballArray[5]);
				winPercentage = ((wins/NUMBER_OF_GAMES) * 100);
				fieldGoalPercentage = ((fieldGoals / fieldGoalAttempts)*100);
				passPercentage = ((passesCompleted / passAttempts)*100);
				footballStats[index] = new Football(this.getLeagueName(), this.teamName, this.fieldGoals, this.fieldGoalPercentage, this.wins, this.winPercentage, this.passesCompleted, this.passPercentage);
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
		
		return footballStats;
	}



	/**
	 * prints the completed calculations to the console.
	 */
	public void outputAllStatistics()
	{
		
		System.out.println("                                     " + this.getLeagueName().toUpperCase() + " Season Statistics:");
		System.out.printf("%-24s %-14s %-21s %-16s %-18s %-13s %-16s %n","Team:", "Field Goals:", "Field Goal Percentage:", "Passes Completed:", "Pass Percentage:", "Total Wins:", "Win Percentage:" );// Total Field Goals:   Field Goal percentage:   Total Wins:   Win percentage:");
		for(int index = 0; index <= (NUMBER_OF_TEAMS - 1); index++)
		{
			System.out.printf("%-24s %12d %23.2f%% %17d %15.2f%% %13d %16.2f%% %n", footballStats[index].getTeamName(), (int)footballStats[index].getFieldGoals(), (footballStats[index].getFieldGoalPercentage()), (int)footballStats[index].getPassesCompleted(), (footballStats[index].getPassPercentage()), (int)footballStats[index].getWins(), footballStats[index].getWinPercentage());
		}
	}
	
	/**
	 * void method using readFilepath(), outputAllStatistics(), and readFootballStats() to read, calculate, and output all statistics.
	 */
	public void readAndCalculate()
	{
		this.readFilePath();
		this.readFootballStats();
		this.outputAllStatistics();
		
	}
}
