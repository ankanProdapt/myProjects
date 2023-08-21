package yahtzee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static String[] CATEGORIES = {"ONES", "TWOS", "THREES", "FOURS", "FIVES", "SIXES",
									"THREE_OF_A_KIND", "FOUR_OF_A_KIND", "FULL_HOUSE", 
									"SMALL_STRAIGHT", "LARGE_STRAIGHT", "YAHTZEE", "CHANCE"};
	private static Connection cnx = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	
	public static void setConnection() {
		try {
			cnx = DriverManager.getConnection("jdbc:mysql://localhost: 3306/yahtzeeDB", "ankan2001prodapt", "we1c@me1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<String> getScoreCard() {
		
		List<String> scoreCard = new ArrayList<>();
		try {
			stmt = cnx.createStatement();
			for(String category: CATEGORIES) {
				String command = "SELECT * FROM scoreCard WHERE category = '" + category + "'";
				rs = stmt.executeQuery(command);
				
				String s = "";
				
				while(rs.next()) {
					s += rs.getString("category") + "     " + rs.getString("score");
				}
				
				scoreCard.add(s);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return scoreCard;
	}
	
	//If Category is already filled, returns false
	public static boolean updateCategoryScore(String category, List<Die> dice) {
		
		try {
			stmt = cnx.createStatement();
			String command = "SELECT * FROM scoreCard WHERE category = '" + category + "'";
			rs = stmt.executeQuery(command);
			
			while(rs.next()) {
				String x = rs.getString("score");
				if(x == null) {
					int score = 0;
					int categoryIndex = Arrays.asList(CATEGORIES).indexOf(category);
					
					switch(categoryIndex) {
					case 0:
						score = CategoryCheck.ones(dice);
						break;
					case 1:
						score = CategoryCheck.twos(dice);
						break;
					case 2:
						score = CategoryCheck.threes(dice);
						break;
					case 3:
						score = CategoryCheck.fours(dice);
						break;
					case 4:
						score = CategoryCheck.fives(dice);
						break;
					case 5:
						score = CategoryCheck.sixes(dice);
						break;
					case 6:
						score = CategoryCheck.threeOfAKind(dice);
						break;
					case 7:
						score = CategoryCheck.fourOfAKind(dice);
						break;
					case 8:
						score = CategoryCheck.fullHouse(dice);
						break;
					case 9:
						score = CategoryCheck.smallStraight(dice);
						break;
					case 10:
						score = CategoryCheck.largeStraight(dice);
						break;
					case 11:
						score = CategoryCheck.yahtzee(dice);
						break;
					case 12:
						score = CategoryCheck.chance(dice);
						break;
					}
					
					command = "UPDATE scoreCard SET score = " + score + " WHERE category = '" + category + "'";
					
					stmt.executeUpdate(command);
					
					return true;
				}
				else {
					return false;
				}
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static int getScore() {
		
		try {
			stmt = cnx.createStatement();
			String command = "SELECT SUM(score) AS TOTAL_SCORE FROM scoreCard";
			
			rs = stmt.executeQuery(command);
			rs.next();
			return rs.getInt("TOTAL_SCORE");
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static int getHighScore() {
		
		try {
			stmt = cnx.createStatement();
			String command = "SELECT MAX(score) AS HIGH_SCORE FROM scores";
			
			rs = stmt.executeQuery(command);
			rs.next();
			return rs.getInt("HIGH_SCORE");
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static boolean isGameOver() {
		
		try {
			stmt = cnx.createStatement();
			String command = "SELECT COUNT(*) AS NULL_COUNT FROM scoreCard WHERE score is null";
			
			rs = stmt.executeQuery(command);
			rs.next();
			if(rs.getInt("NULL_COUNT") == 0) {
				return true;
			}
			
			return false;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public static void reset() {
		String command;
		
		try {
			stmt = cnx.createStatement();
			
			int score = getScore();
			command = "INSERT INTO scores VALUES(NOW()," + score + ")";
			stmt.executeUpdate(command);
			
			for(String category: CATEGORIES) {
				command = "UPDATE scoreCard SET score = null";
				stmt.executeUpdate(command);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		setConnection();
		
		YahtzeeDice yahtzeeDice = new YahtzeeDice();
		while(true) {
			for(String line: getScoreCard()) {
				System.out.println(line);
			}
			
			if(isGameOver()) {
				System.out.println("GAME OVER");
				System.out.println("Score: " + getScore());
				reset();
				System.out.println("High Score: " + getHighScore());
				System.out.print("Enter 1 to start a New Game: ");
				int key = Integer.valueOf(sc.nextLine());
				if(key != 1) {
					System.out.println("Good Bye");
					break;
				}
				System.out.println("\n\nNEW GAME");
			}
			
			List<Die> dice= yahtzeeDice.yahtzeeRoll();
			System.out.println("\nDice: " + dice);
			System.out.print("Enter Category: ");
			
			String category = sc.nextLine();
			
			
			while(!updateCategoryScore(category, dice)) {
				System.out.println("Already filled! Try again..");
				category = sc.nextLine();
			}
		}
		sc.close();
	}
}