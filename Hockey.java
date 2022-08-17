package csc201FinalProject;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
/**
 * The Hockey class is used to create statistics for the NHL.
 */
public class Hockey extends Team implements Statistics
{
	private Hockey[] hockeyStats = new Hockey[NUMBER_OF_TEAMS];
	private static final int NUMBER_OF_GAMES = 82;
	private static final int NUMBER_OF_TEAMS = 32;
	private double goalsFor = 0;   //goals made
	private double shotsOnGoal = 0; //attempted shots
	private double scoringPercentage = 0; //(goalsFor/shotsOnGoal)*100
	private double shotsAgainst = 0; // shots on teams goal
	private double goalsAgainst = 0; //goals scored against team
	private double savePercentage = 0; // ((1 - (goalsAgainst / shotsAgainst))*100)
	private double wins = 0;
	private double winPercentage = 0; //(wins/NUMBER_OF_GAMES) *100
	private String teamName = null;
	private String filePath = null;
	
	public Hockey()
	{
		super();
	}
	public Hockey (String initialLeagueName, String initialTeamName, double initialGoalsFor, double initialWinPercentage, double initialScoringPercentage, double initialWins, double initialGoalsAgainst, double initialSavePercentage)
	{
		super(initialLeagueName);
		teamName = initialTeamName;
		goalsFor = initialGoalsFor;
		winPercentage = initialWinPercentage;
		scoringPercentage = initialScoringPercentage;
		wins = initialWins;
		goalsAgainst = initialGoalsAgainst;
		savePercentage = initialSavePercentage;
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
	 *@return scoringPercentage 
	 *Percentage of goals made
	 *(goalsFor/shotsOnGoal) *100 
	 */
	public double getScoringPercentage()
	{
		return scoringPercentage;
	}
	
	/**
	 * percentage of shots saved by the goalie. 
	 * @return savePErcentage
	 * (goalsAgainst/shotsAgainst)*100
	 */
	public double getSavePercentage()
	{
		return savePercentage;
	}
	
	/**
	 * @return goalsFor
	 */
	public double getGoalsFor()
	{
		return goalsFor;
	}
	/**
	 * @return goalsAgainst
	 */
	public double getGoalsAgainst()
	{
		return goalsAgainst;
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
	 * Each line is stored into the hockeyStats array as a new instance of the Hockey class.
	 * When all lines are read method returns filled array of hockey teams.
	 * @return hockeyStats
	 * @throws FileNotFoundException if the file cannot be found
	 * @throws IOException if there is a problem with file I/O.
	 */
	public Hockey[] readHockeyStats()
	{
		try
		{
			Scanner inputStream = new Scanner(new File(filePath));
			String nextLine = inputStream.nextLine();
			int index = 0;
			while (inputStream.hasNextLine() && (index <= (NUMBER_OF_TEAMS -1)))
			{
				nextLine = inputStream.nextLine();
				String [] hockeyArray = nextLine.split(",");
				teamName = hockeyArray[0];
				goalsFor = Double.parseDouble(hockeyArray[1]);
				shotsOnGoal = Double.parseDouble(hockeyArray[2]);
				goalsAgainst = Double.parseDouble(hockeyArray[3]);
				shotsAgainst = Double.parseDouble(hockeyArray[4]);
				wins = Double.parseDouble(hockeyArray[5]);
				winPercentage = ((wins/NUMBER_OF_GAMES) * 100);
				scoringPercentage = ((goalsFor / shotsOnGoal)*100);
				savePercentage = ((1 - (goalsAgainst / shotsAgainst))*100);
				hockeyStats[index] = new Hockey(this.getLeagueName(), this.teamName, this.goalsFor, this.winPercentage, this.scoringPercentage, this.wins, this.goalsAgainst, this.savePercentage);
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
		return hockeyStats;
	}
	
	/**
	 * prints the completed calculations to the console.
	 */
	public void outputAllStatistics()
	{
		
		System.out.println("                                     " + this.getLeagueName().toUpperCase() + " Season Statistics:");
		System.out.printf("%-22s %-14s %-21s %-16s %-18s %-13s %-16s %n","Team:", "Total Goals:", "Scoring Percentage:", "Goals Against:", "Save Percentage:", "Total Wins:", "Win Percentage:" );// Total Field Goals:   Field Goal percentage:   Total Wins:   Win percentage:");
		for(int index = 0; index <= (NUMBER_OF_TEAMS - 1); index++)
		{
			System.out.printf("%-22s %12d %20.2f%% %16d %17.2f%% %13d %16.2f%% %n", hockeyStats[index].getTeamName(), (int)hockeyStats[index].getGoalsFor(), (hockeyStats[index].getScoringPercentage()), (int)hockeyStats[index].getGoalsAgainst(), (hockeyStats[index].getSavePercentage()), (int)hockeyStats[index].getWins(), hockeyStats[index].getWinPercentage());
		}
	}
	
	/**
	 * void method using readFilepath(), outputAllStatistics(), and readHockeyStats() to read, calculate, and output all statistics.
	 */
	public void readAndCalculate()
	{
		this.readFilePath();
		this.readHockeyStats();
		this.outputAllStatistics();
		
	}
}
