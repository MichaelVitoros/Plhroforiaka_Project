import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Static.Champion;
import net.rithms.riot.dto.Game.Game;
import net.rithms.riot.dto.Game.Player;
import net.rithms.riot.dto.Game.RawStats;
import net.rithms.riot.api.*;
import net.rithms.riot.dto.Game.RecentGames;
import net.rithms.riot.dto.Summoner.Summoner;
import java.util.ArrayList;
import java.util.Scanner;

public class RiotApiConnector {
	
	private final String myApiKey = "";
	
	Scanner sc = new Scanner(System.in);
	ArrayList<Game> myArrayList = new ArrayList<Game>();
	ArrayList<Player> myFellowPlayersList = new ArrayList<Player>();
	RiotApi riotApi= new RiotApi(myApiKey,Region.EUW);
	
	public void myFunction() throws InterruptedException{
	
		try{
			System.out.println("Enter the name of the summoner you want to search. \nInput: ");
			Summoner summoner = riotApi.getSummonerByName(riotApi.getRegion(), sc.nextLine());
			RecentGames myRecentGames = riotApi.getRecentGames(summoner.getId());
			Champion myChampion;
			
			//RickyRayne lines START
			
			RawStats stats;
			
			//RickyRayne lines END
			myArrayList.addAll(myRecentGames.getGames());
			
			//System.out.println(summoner);
			System.out.format("Summoner id is : %s\nSummoner name is : %s\nProfile icon is : %s\nSummoner level is : %s\n\n",
					summoner.getId(),
					summoner.getName(),
					summoner.getProfileIconId(),
					summoner.getSummonerLevel());
			
			//System.out.format("myRecentGames result is : %s\n",myRecentGames);
			//System.out.format("myArrayList result is : %s\n", myArrayList);
		 
		 	for(Game game : myArrayList){
		 		myChampion = riotApi.getDataChampion(game.getChampionId());
		 		//RickyRayne lines START
		 		stats = game.getStats();
		 		float kda;
		 		//RickyRayne lines END
		 		myFellowPlayersList.addAll(game.getFellowPlayers());
			 	System.out.format("GameType: %s\nMapId: %s\nChampionId: %s\nChampion Name: %s\n",
			 						game.getGameType(),
			 						game.getMapId(),
			 						game.getChampionId(),
			 						myChampion.getName());
			 	
			 	//System.out.println(myFellowPlayersArrayList.get(0).getChampionId());	 	
			 	
			 //RickyRayne lines START
			 	kda = (float)( stats.getChampionsKilled() + stats.getAssists() ) / stats.getNumDeaths();
			 	System.out.println("Kills: " + stats.getChampionsKilled());
			 	System.out.println("Deaths: " + stats.getNumDeaths());
			 	System.out.println("Assists: " + stats.getAssists());
			 	
			 	System.out.println("KDA: " +  kda );
			 	System.out.println();
			 //RickyRayne lines END
			 	
			 /*	System.out.println("Fellow players played: \n");	 	
			for(Player player : myFellowPlayersList){
				
			 		myChampion = riotApi.getDataChampion(player.getChampionId());
			 		String name = riotApi.getSummonerName(player.getSummonerId());
			 		Thread.sleep(1000);
			 		System.out.format("Summoner:%s played as : %s\n",name, myChampion.getName());
				}*/
			//break;
			}		
		}catch(RiotApiException ie){
			System.err.printf("Error happened at : %s\n\n",ie);
			ie.printStackTrace();
		}
	}
}
