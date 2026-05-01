import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
				
				writer.write(w.getName()+","+w.getSurname()+","+w.getYear()+","+w.getGroup());
				writer.newLine();
			}
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFromFile(WorkerDatabase database) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Memory.txt"));
			String line;
			
			while((line = reader.readLine()) != null) {
				String[] in = line.split(",", 4);
				String name = in[0];
				String surname = in[1];
				int year = Integer.parseInt(in[2]);
				String group = in[3];
				Worker worker;
				if(group.equals("DATA_ANALYST")) {
					worker = new DataAnalyst(name, surname, year);
					database.addWorker(worker);
					System.out.printf("\nZaměstnanec úspěšně načten, ID: %d, skupina: %s",worker.getId(), worker.getGroup());
				}
				else if (group.equals("SECURITY_SPECIALIST")) {
					worker = new SecuritySpecialist(name, surname, year);
					database.addWorker(worker);
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
}
