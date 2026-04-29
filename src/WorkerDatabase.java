import java.util.*;
import java.util.stream.Collectors;

public class WorkerDatabase {
	private Map<Integer, Worker> workers;
	
	public WorkerDatabase() {
		workers = new HashMap<>();
	}
	
	public boolean addWorker(Worker w) {
		workers.put(w.getId(), w);
		
		return true;
	}
}
