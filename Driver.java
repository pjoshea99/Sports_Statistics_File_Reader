package csc201FinalProject;
/**
 * class to run program.
 * */
public class Driver 
{

	public static void main(String[] args) 
	{
		// NBA: C:\Users\pjosh\OneDrive\Documents\NBA_2021_Season.csv
		// NHL: C:\Users\pjosh\OneDrive\Documents\21_22_NHL_STATS.csv
		// NFL: C:\Users\pjosh\OneDrive\Documents\21_22_NFL.csv
		
		League nbaseason22 = new Basketball();
		nbaseason22.setLeagueName();
		nbaseason22.readAndCalculate();
		
		League nhlSeason22 = new Hockey();
		nhlSeason22.setLeagueName();
		nhlSeason22.readAndCalculate();
		
		League nflSeason22 = new Football();
		nflSeason22.setLeagueName();
		nflSeason22.readAndCalculate();
	}

}
