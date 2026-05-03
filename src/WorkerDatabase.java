import java.util.*;
import java.util.stream.Collectors;

public class WorkerDatabase {
	public Map<Integer, Worker> workers;
	private Map<Integer, Set<Integer>> cooperations;
	
	public WorkerDatabase() {
		workers = new HashMap<>();
		cooperations = new HashMap<>();
	}
	
	public Worker getWorker(int id) {
		return workers.get(id);
	}
	
	public int getSize() {
		return workers.size();
	}
	
	public boolean addWorker(Worker w) {
		workers.put(w.getId(), w);
		cooperations.put(w.getId(), new HashSet<>());
		return true;
	}
	
	public boolean addCoop(int id1, int id2, WorkCoop level) {
		Worker w1 = workers.get(id1);
		Worker w2 = workers.get(id2);
		if(w1 == null || w2 == null )return false;
		
		w1.addCoworker(id2, level);
		w2.addCoworker(id1, level);
		
		cooperations.get(id1).add(id2);
		cooperations.get(id2).add(id1);
		
		return true;
		
	}
	
	public void loadCoop(Set<Integer> set, int id) {
		cooperations.get(id).addAll(set);
	}
	
	public double getAvgCoop(Worker w) {
		int sum;
		int id = w.getId();
		sum = cooperations.get(id).stream().mapToInt(Integer::intValue).sum();
		double avg = (double)sum / (double)cooperations.get(id).size();
		return avg;
	}
	
	public Set<Integer> getCoop(Worker w){
		int id = w.getId();
		return cooperations.get(id);
	}
	
	public boolean removeWorker(int id) {
		Worker w = workers.remove(id);
		if (w == null) return false;
		
		for(Worker wo : workers.values()) {
			wo.removeCoworker(id);
		}
		
		cooperations.remove(id);
		return true;
	}
	
	public Worker findWorker(int id) {
		return workers.get(id);
	}
	
	public void alphabeticalList() {
		String sorted = workers.values().stream().sorted(Comparator.comparing(Worker::getSurname).thenComparing(Worker::getName))
				.map(w -> String.format("#%d, %s %s, %d, %s", w.getId(), w.getSurname(), w.getName(), w.getYear(), w.getGroup()))
				.collect(Collectors.joining("\n"));
		System.out.println(sorted);
	}
	
	public void stats() {
		if(workers.isEmpty()) {
			System.out.println("Žádní zaměstnanci");
			return;
		}
		
		double max = 0;
		int top = 0;
		Set<Integer> bestCoworkers = new HashSet<>();
		Set<Integer> mostCoworkers = new HashSet<>();
		
		for(Worker w : workers.values()) {
			if(w.getCoworkersAmount() > top) {
				top = w.getCoworkersAmount();
			}
			if(getAvgCoop(w) > max) {
				max = getAvgCoop(w);
			}
		}
		
		for(Worker w : workers.values()) {
			if(getAvgCoop(w) == max) {
				bestCoworkers.add(w.getId());
			}
			if(w.getCoworkersAmount() == top) {
				mostCoworkers.add(w.getId());
			}
		}
		
		System.out.printf("\nNejvíce kolegů(%d) má/mají: %s\nNejlepší kolega se skóre(%.1f) je: %s", top, mostCoworkers, max, bestCoworkers);
	}
	
	public Map<Integer, Worker> getWorkers(){
		return workers;
	}
	
	public void getAmountIngroups() {
		int data = 0;
		int security = 0;
		for(Worker w : workers.values()) {
			if(w.getGroup() == WorkerGroup.DATA_ANALYST) {
				data += 1;
			}
			else if(w.getGroup() == WorkerGroup.SECURITY_SPECIALIST) {
				security += 1;
			}
		}
		
		System.out.printf("\nPočet datových analitiků: %d\nPočet bezpečnostních specialistů: %d", data, security);
	}
	
}
