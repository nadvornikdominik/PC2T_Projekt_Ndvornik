import java.util.*;
import java.util.stream.Collectors;

public class WorkerDatabase {
	private Map<Integer, Worker> workers;
	private Map<Integer, Set<Integer>> cooperations;
	
	public WorkerDatabase() {
		workers = new HashMap<>();
		cooperations = new HashMap<>();
	}
	
	public boolean addWorker(Worker w) {
		workers.put(w.getId(), w);
		
		return true;
	}
	
	public boolean addCoop(int id1, int id2, WorkCoop level) {
		Worker w1 = workers.get(id1);
		Worker w2 = workers.get(id2);
		if(w1 == null || w2 == null )return false;
		
		w1.addCoorker(id2, level);
		w2.addCoorker(id1, level);
		
		cooperations.get(id1).add(id2);
		cooperations.get(id2).add(id1);
		
		return true;
		
	}
	
	public Worker getWorker(int id) {
		return workers.get(id);
	}
	
	public int getSize() {
		return workers.size();
	}
}
