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
	
	/*
	 *This class functions as a connector(bridge)
	 *between Riot's API and Riot's THIRD PARTY JAVA API
	 */
	
	private final String myApiKey = "";
	private Scanner sc = new Scanner(System.in);;//Used to get user input
	private ArrayList<Game> myArrayList = new ArrayList<Game>();//Stores "Games" as Game objects
	private ArrayList<Player> myFellowPlayersList = new ArrayList<Player>();//Stores "Players" as Player objects
	
	private RiotApi riotApi= new RiotApi(myApiKey);
	

	public void myFunction() throws InterruptedException{
		try{
			getUserInput_SummonerRegion();
			Summoner summoner = riotApi.getSummonerByName(riotApi.getRegion(),getUserInput_SummonerName());
			
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
	
	private Region getUserInput_SummonerRegion(){//User inputs preferred region to search 
		Region[] regions = new Region[]{Region.EUW,Region.EUNE,Region.NA};
		String regionInput;
		System.out.format("Select region.\n"
				+ "Available regions are : %s,%s and %s \n"
				+ "If invalid input is give NA will be set as searching region.\n",
					regions[0],
					regions[1],
					regions[2]);
		try{
			System.out.println("regions[0].toString() is : " + regions[0].toString());
			regionInput = sc.nextLine();
			if( regionInput.matches(regions[0].toString())){
				riotApi.setRegion(regions[0]);
				 riotApi.getRegion();
			}
			if(regionInput.matches(regions[1].toString())){
				riotApi.setRegion(regions[1]);
				 riotApi.getRegion();
			}
		}catch(Exception e){
			System.out.println("Wrong region.\n");
			getUserInput_SummonerRegion();
		}
			System.out.println("Region: " + riotApi.getRegion() + " region");
		return Region.NA;
	}
	
	private String getUserInput_SummonerName(){
		System.out.println("Enter the name of the summoner you want to search. \nInput: ");
		return sc.next();
	}
}
