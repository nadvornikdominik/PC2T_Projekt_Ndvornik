import java.io.*;
import java.util.*;

public class FileHandler {

	public void createFile(String file) {
		try {
			File myFile = new File(file);
			if(myFile.createNewFile()) {
				System.out.println("Vytvořen soubor: " + myFile.getName());
			}
			else {
				System.out.println("Soubor už existuje");
			}
		}
		catch(IOException e) {
			System.out.println("Nastala chyba");
			e.printStackTrace();
		}
	}
	
	public void addToFile(WorkerDatabase database) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("Memory.txt"));
			for(int i = 1; i <= database.getSize(); i++) {
				Worker w = database.getWorker(i);
				
				writer.write(w.getName()+";"+w.getSurname()+";"+w.getYear()+";"+w.getGroup()+";"+database.getCoop(w)+";"+w.getCoworkers());
				writer.newLine();
			}
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFromFile(WorkerDatabase database) {
		try (BufferedReader reader = new BufferedReader(new FileReader("Memory.txt"))) {
			String line;
			
			while((line = reader.readLine()) != null) {
				String[] in = line.split(";", 6);
				String name = in[0];
				String surname = in[1];
				int year = Integer.parseInt(in[2]);
				String group = in[3];
				String coop[] = in[4].replaceAll("[\\[\\] ]", "").split(",");
				String cleaned = in[5].replace("{", "").replace("}", "").trim();
				String pairs[] = cleaned.split(",");
				Set<Integer> coop2 = convertArrayToSet(coop);
				Worker worker;
				if(group.equals("DATA_ANALYST")) {
					worker = new DataAnalyst(name, surname, year);
					for (String pair : pairs) {
					    String[] cowork = pair.split("=");
					    
					    int coworkerId = Integer.parseInt(cowork[0].trim());
					    WorkCoop relations = WorkCoop.valueOf(cowork[1].trim());

					    worker.addCoworker(coworkerId, relations);
					}
					database.addWorker(worker);
					database.loadCoop(coop2, worker.getId());
					System.out.printf("\nZaměstnanec úspěšně načten, ID: %d, skupina: %s",worker.getId(), worker.getGroup());
				}
				else if (group.equals("SECURITY_SPECIALIST")) {
					worker = new SecuritySpecialist(name, surname, year);
					for (String pair : pairs) {
					    String[] cowork = pair.split("=");
					    
					    int coworkerId = Integer.parseInt(cowork[0].trim());
					    WorkCoop relations = WorkCoop.valueOf(cowork[1].trim());

					    worker.addCoworker(coworkerId, relations);
					}
					database.addWorker(worker);
					database.loadCoop(coop2, worker.getId());
					System.out.printf("\nZaměstnanec úspěšně načten, ID: %d, skupina: %s",worker.getId(), worker.getGroup());
				}
				else {
					System.out.println("Špatný formát");
				}
			}
		}
		catch(NumberFormatException e){
			System.out.println("Pamět je prázdná");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Došli pracovníci");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Set<Integer> convertArrayToSet(String in[]){
		Set<Integer> out = new HashSet<>();
		for(String s: in) {
			out.add(Integer.parseInt(s));
		}
		return out;
	}
}
