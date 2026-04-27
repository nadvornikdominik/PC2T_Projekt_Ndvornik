
public enum WorkCoop {
	
	BAD(1), AVG(2), GOOD(3);
	
	private int value;
	
	WorkCoop(int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
