package tdt4140.gr1806.app.core;

import java.sql.*;


public class Goals extends ConnectionManager {
	
	private int customerId;
	private int goal;
	private String deadLineStart;
	private String deadLineEnd;
	
	public Goals (int customerId, int goal, String deadLineStart, String deadLineEnd) {
		connect();
		this.setGoal(goal);
		this.setCustomerId(customerId);
		this.setDeadLineStart(deadLineStart);
		this.setDeadLineEnd(deadLineEnd);
		
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setGoal(int goal) {
		this.goal = goal;
	}
	
	public int getGoal() {
		return goal;
	}
	
	public void setDeadLineStart(String deadLineStart) {
		this.deadLineStart = deadLineStart;
	}
	
	public String getDeadLineStart() {
		return deadLineStart;
	}
	
	public void setDeadLineEnd(String deadLineEnd) {
		this.deadLineEnd = deadLineEnd;
	}
	
	public String getDeadLineEnd() {
		return deadLineEnd;
	}
	
	//Connecting goal to the DB
	
	public Goals saveGoals(Goals theGoal) {
		try {
			String update = "insert into CustomerGoal "
					+ "(customerId, "
					+ "stepsGoal, "
					+ "goalDeadline, "
					+ "goalStart) "
					+ "values (?, ?, ?, ?);";
			
			PreparedStatement pstmt = conn.prepareStatement(update,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, getCustomerId());
			pstmt.setInt(2, getGoal());
			pstmt.setString(3, getDeadLineStart());
			pstmt.setString(4,getDeadLineEnd());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Could not save to database. ");
			e.printStackTrace();
		}
		return theGoal;
	}
	
	
	
	@Override
	public String toString() {
		return "Your goal is: " + goal;
	}
	
	public static void main(String[] args) {
		Goals g = new Goals(1, 15000, "15.09.2018", "15.10.2018");
		g.saveGoals(g);
	}
	
	

}
