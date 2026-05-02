import java.util.*;

public class DataAnalyst extends Worker {

	public DataAnalyst(String name, String surname, int year) {
		super(name, surname, year, WorkerGroup.DATA_ANALYST);
	}
	
	@Override
    public String ability() {
        Map<Integer, Integer> contacts = new HashMap<>();
        
        if (contacts.isEmpty()) {
            return "Žádní spolupracovníci pro analýzu";
        }
        
        int maxSpolecnych = contacts.values().stream().mapToInt(Integer::intValue).max().orElse(0);
        return String.format("Nejvíce společných kontaktů (%d): %s", 
                maxSpolecnych, contacts.entrySet().stream()
                .filter(e -> e.getValue() == maxSpolecnych)
                .map(e -> "#" + e.getKey())
                .collect(java.util.stream.Collectors.joining(", ")));
    }

}
