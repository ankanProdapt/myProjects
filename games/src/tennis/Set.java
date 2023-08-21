package tennis;

public class Set {
	
	private static final int MIN_GAMES_TO_WIN = 6;
	private static final int MIN_MARGIN = 2;

	public static void updateSet(Player winner, Player loser) {
		winner.gamesWon++;
		if ((winner.gamesWon >= MIN_GAMES_TO_WIN) && (winner.gamesWon - loser.gamesWon >= MIN_MARGIN)) {
			Match.updateMatch(winner, loser);
		}

		// Reset the game score
		winner.servesWon = 0;
		loser.servesWon = 0;
		winner.scoreString = "Love";
		loser.scoreString = "Love";
	}
}
