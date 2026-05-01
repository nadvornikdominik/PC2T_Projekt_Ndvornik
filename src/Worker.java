import java.util.*;

public abstract class Worker {
	
	private static int idCounter = 1;
	
	protected int id;
	protected String name;
	protected String surname;
	protected int year;
	protected WorkerGroup group;
	
	protected Map<Integer, WorkCoop> coworkers;
	
	public Worker(String name, String surname, int year, WorkerGroup group) {
		this.id = idCounter++;
		this.name = name;
		this.surname = surname;
		this.year = year;
		this.coworkers = new HashMap<>();
		this.group = group;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public int getYear() {
		return year;
	}
	public Map<Integer, WorkCoop> getCoworkers(){
		return new HashMap<>(coworkers);
	}
	public WorkerGroup getGroup() {
		return group;
	}
	
	public boolean addCoworker(int id2, WorkCoop level) {
		coworkers.put(id, level);
		return true;
	}
	
	public boolean removeCoworker(int id) {
		return coworkers.remove(id) != null;
	}
}
