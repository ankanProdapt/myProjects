package tennis;

public class Tennis {
	public Player player1, player2;
	public boolean matchOver;

	public Tennis(String name1, String name2) {
		player1 = new Player(name1);
		player2 = new Player(name2);
		matchOver = false;
	}
	
	public void update(String name) throws MatchOverException{
		if(matchOver) {
			throw new MatchOverException("This Match has already ended, start a new match...");
		}
		
		if(player1.name.equals(name)) {
			player1.wonServeAgainst(player2);
		}
		else {
			player2.wonServeAgainst(player1);
		}
		
		if(Match.isMatchOver(player1, player2)) {
			matchOver = true;
		}
	}
	
	public String getScoreBoard() {
		String result = "SCOREBOARD:\n"; 
		result += "Player    Score   GamesWon    SetsWon\n";
		result += String.format("%s %12s %10s %10s\n", player1.name, player1.scoreString, player1.gamesWon, player1.setsWon);
		result += String.format("%s %12s %10s %10s\n", player2.name, player2.scoreString, player2.gamesWon, player2.setsWon);
		if(matchOver) {
			result += "Winner: " + Match.getWinner(player1, player2).name;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return getScoreBoard();
	}
}