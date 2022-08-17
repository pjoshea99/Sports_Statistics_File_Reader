package csc201FinalProject;
/**
 * This interface is used by the Basketball, Football, and Hockey classes to establish common methods used by all.
 */
public interface Statistics 
{
	/**
	 * reads the file location as a string
	 * @return returns filePath
	 */
	public String readFilePath();
	/**
	 * void method using readFilepath(), outputAllStatistics(), and a class specific storage method to read, calculate, and output all statistics.
	 */
	public void readAndCalculate();
	/**
	 * prints the completed calculations to the console.
	 */
	public void outputAllStatistics();
	/**
	 * @return returns wins
	 */
	public double getWins();
	/**
	 *  wins/NUMBER_OF_GAMES.
	 * @return returns winPercentage.
	 */
	public double getWinPercentage();
	/**
	 * @return teamName
	 */
	public String getTeamName();
	
}
