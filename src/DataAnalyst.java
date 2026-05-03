import java.util.*;

public class DataAnalyst extends Worker {

	public DataAnalyst(String name, String surname, int year) {
		super(name, surname, year, WorkerGroup.DATA_ANALYST);
	}
	
	@Override
    public String ability(WorkerDatabase database) {
        
		Map<Integer, Set<Integer>> allSets = new HashMap<>();
		
		for(Worker w : database.getWorkers().values()) {
			allSets.put(w.getId(), database.getCoop(w));	
		}
		
		Set<Integer> mySet = getCoworkers().keySet();
		
		int bestId = 0;
        int bestScore = 0;
        
        for(Map.Entry<Integer, Set<Integer>> entry : allSets.entrySet()) {
        	
        	Set<Integer> copy = new HashSet<>(mySet);
        	copy.retainAll(entry.getValue());
        	
        	int score = copy.size();
        	
        	if(score > bestScore & entry.getKey() != id) {
        		bestScore = score;
        		bestId = entry.getKey();
        	}
        }
        
        return String.format("Nejvíce společných kontaktů (%d): %s", bestScore, bestId);
    }

}
