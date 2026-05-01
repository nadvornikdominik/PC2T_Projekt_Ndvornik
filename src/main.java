import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {

	private static Scanner sc = new Scanner(System.in);
	private static WorkerDatabase database = new WorkerDatabase();
	
	
	public static void main(String[] args) {
		System.out.println("--Databáze zaměstnanců--");
		int choice;
		
		FileHandler file = new FileHandler();
		file.createFile("Memory.txt");
		
		file.readFromFile(database);
		
		do {
			menuShow();
			choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
				case 1:
					addWorker();
					break;
				case 2:
					addCoworker();
					break;
				case 3: 
					break;
				case 4: 
					break;
				case 5: 
					break;
				case 6: 
					break;
				case 7: 
					break;
				case 8: 
					break;
				case 0: 
					file.addToFile(database);
					System.out.println("Ukončeno");
					break;
				default:
					System.out.println("Neplatná volba");
			}
			
		} while(choice != 0);
	}
		
	private static void menuShow() {
		System.out.println("\n--MENU--");
		System.out.println("1. Přidat zaměstnance");
		System.out.println("2. Přidat spolupraci");
		System.out.println("3. Odebrat zaměstnance");
		System.out.println("4. Vyhledat zaměstnance");
		System.out.println("5. Spustit dovednost");
		System.out.println("6. Výpis");
		System.out.println("7. Statistiky");
		System.out.println("8. Počet ve skupinách");
		System.out.println("0. Ukončit program");
		System.out.print("Vaše volba: ");
	}
	
	private static void addWorker() {
		System.out.println("Jméno: ");
		String name = sc.nextLine();
		System.out.println("Příjmení: ");
		String surname = sc.nextLine();
		System.out.println("Rok narození: ");
		int year = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Skupina: (1.Datový nalitik 2.Bezpečnostní specialista)");
		int group = sc.nextInt();
		
		Worker worker;
		if(group == 1) {
			worker = new DataAnalyst(name, surname, year);
		}
		else if (group == 2) {
			worker = new SecuritySpecialist(name, surname, year);
		}
		else {
			System.out.println("Neplatná skupina");
			return;
		}
		
		database.addWorker(worker);
		System.out.printf("Zaměstnanec úspěšně přidán, ID: %d, skupina: %S",worker.getId(), worker.getGroup());
	}
	
	private static void addCoworker() {
		System.out.println("ID zaměstnance: ");
		int id1 = sc.nextInt();
		System.out.println("ID kolegy: ");
		int id2 = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Uroveň: 1 = špatná, 2 = průměrná, 3 = dobrá: ");
		int level = sc.nextInt();
		sc.nextLine();
		

		WorkCoop c = switch(level){
			case 1 -> WorkCoop.BAD;
			case 2 -> WorkCoop.AVG;
			case 3 -> WorkCoop.GOOD;
			default -> null;
		};
		
		if(c != null && database.addCoop(id1, id2, c)) {
			System.out.println("Spoluprace přidána ");
		}
		else
		{
			System.out.println("Chyba při zadávání spolupráce");
		}
	}

}
