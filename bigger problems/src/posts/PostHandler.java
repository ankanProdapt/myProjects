package posts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PostHandler {
	
	public static int getNextCommentId() {
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			cnx = DriverManager.getConnection("jdbc:mysql://localhost: 3306/postsDB", "ankan2001prodapt", "we1c@me1");
			stmt = cnx.createStatement();
			rs = stmt.executeQuery("SELECT  * from comments");
			int id = 1;
			while(rs.next()) {
				id++;
			}
			
			return id;
		} catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		PreparedStatement command = null;
	    Connection cnx = null;
	    ResultSet rs = null;
	    
	    
	    String likePost = "INSERT INTO likes (userId, postId) VALUES(?, ?)";
	    String addComment = "INSERT INTO comments (id, content, userId, postId) VALUES(?, ?, ?, ?)";
	    String retrieveLikes = "SELECT posts.id, likes.userId \n"
	    						+ "FROM posts, likes\n"
	    						+ "WHERE posts.id = likes.postId AND posts.id = ?";
	    String retrieveComments = "SELECT posts.id, comments.id, comments.userId , comments.content "
	    						+ "FROM posts, comments "
	    						+ "WHERE posts.id = comments.postId AND posts.id = ?";
	    
	    
	    while(true) {
	    	try {
				cnx = DriverManager.getConnection("jdbc:mysql://localhost: 3306/postsDB", 
						"ankan2001prodapt", 
						"we1c@me1");
				
				System.out.println("MENU (Select a number 1-5): \n1. Like a Post \n2. Add Comment \n3. Retrieve Likes \n4. Retrieve Comments \n5. Quit");
		    	int query = sc.nextInt();
		    	if(query == 1) {
		    		System.out.print("Enter your userId: ");
		    		int userId = sc.nextInt();
		    		System.out.print("Enter postId: ");
		    		int postId = sc.nextInt();
		    		
		    		command = cnx.prepareStatement(likePost);
		    		command.setInt(1, userId);
		    		command.setInt(2, postId);
		    		command.executeUpdate();
		    	}
		    	else if(query == 2) {
		    		int commentId = getNextCommentId();
		    		System.out.print("Enter your userId: ");
		    		int userId = sc.nextInt();
		    		System.out.print("Enter postId: ");
		    		int postId = sc.nextInt();
		    		System.out.print("Enter content: ");
		    		String content = sc.next();
		    		
		    		command = cnx.prepareStatement(addComment);
		    		command.setInt(1, commentId);
		    		command.setString(2, content);;
		    		command.setInt(3, userId);
		    		command.setInt(4, postId);
		    		command.executeUpdate();    		
		    	}
		    	else if(query == 3) {
		    		System.out.println("Enter postId: ");
		    		int postId = sc.nextInt();
		    		
		    		command = cnx.prepareStatement(retrieveLikes);
		    		command.setInt(1, postId);
		    		rs = command.executeQuery();
		    		
		    		System.out.println("PostID    UserID");
		    		while(rs.next()) {
		    			System.out.println(rs.getInt("posts.id") + "         " + rs.getInt("likes.userId"));
		    		}
		    	}
		    	else if(query == 4) {
		    		System.out.println("Enter postId: ");
		    		int postId = sc.nextInt();
		    		
		    		command = cnx.prepareStatement(retrieveComments);
		    		command.setInt(1, postId);
		    		rs = command.executeQuery();
		    		
		    		System.out.println("PostID    UserID    Comment");
		    		while(rs.next()) {
		    			System.out.println(rs.getInt("posts.id") + "         " + rs.getInt("comments.userId") +
		    								"         " + rs.getString("comments.content"));
		    		}
		    	}
		    	else {
		    		System.out.println("Good Bye!\n\n");
		    		break;
		    	}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println("\n\n");
	    }
	    
	    sc.close();
	}
}
