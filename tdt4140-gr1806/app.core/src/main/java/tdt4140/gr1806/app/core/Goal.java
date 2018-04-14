package tdt4140.gr1806.app.core;

public class Goal {
	
	private int customerId;
	private int goal;
	private String deadLineStart;
	private String deadLineEnd;
	

	public Goal (int customerId, int goal, String deadLineStart, String deadLineEnd) {
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
		
	@Override
	public String toString() {
		return "Goals [customerId=" + customerId + ", goal=" + goal + ", deadLineStart=" + deadLineStart
				+ ", deadLineEnd=" + deadLineEnd + "]";
	}
}
