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
	
	public int getGoal(int customerId) {
		return goal;
	}
	
	public void setDeadLineStart(String deadLineStart) {
		this.deadLineStart = deadLineStart;
	}
	
	public String getDeadLineStart(int customerId) {
		return deadLineStart;
	}
	
	public void setDeadLineEnd(String deadLineEnd) {
		this.deadLineEnd = deadLineEnd;
	}
	
	public String getDeadLineEnd(int customerId) {
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
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
		} catch (SQLException e) {
			System.err.println("Could not save to database. ");
			e.printStackTrace();
		}
		return theGoal;
	}
	
	private Goals createGoalFromResultSet(ResultSet rs) throws SQLException {
		int customerId = rs.getInt("customerId");
		int stepsGoal = rs.getInt("stepsGoal");
		String goalDeadline = rs.getString("goalDeadline");
		String goalStart = rs.getString("goalStart");
		
		Goals goal = new Goals(customerId, stepsGoal, goalDeadline, goalStart);
		return goal;
	}
	
	@Override
	public String toString() {
		return "Goals [customerId=" + customerId + ", goal=" + goal + ", deadLineStart=" + deadLineStart
				+ ", deadLineEnd=" + deadLineEnd + "]";
	}

	public static void main(String[] args) {
		Goals g = new Goals(1, 1200, "15.09.2018", "15.10.2018");
		g.saveGoals(g);
		System.out.println(g.toString());
		
	}
	
	

}
