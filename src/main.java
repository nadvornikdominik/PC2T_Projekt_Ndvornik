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
					removeWorker();
					break;
				case 4:
					findWorker();
					break;
				case 5:
					launchAbility();
					break;
				case 6:
					database.alphabeticalList();
					break;
				case 7: 
					break;
				case 8: 
					x();
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
	
	private static void removeWorker() {
		System.out.println("ID Zaměstnance co se má odstranit: ");
		int id = sc.nextInt();
		
		if(database.removeWorker(id)) {
			System.out.println("Zaměstnanec úspěšně odstraněn");
		}
		else {
			System.out.println("Zaměstnanec nenalezen");
		}
	}
	
	private static void findWorker() {
		System.out.println("ID zaměstnance k vyhledání: ");
		int id = sc.nextInt();
		
		Worker w = database.findWorker(id);
		
		if (w != null) {
			System.out.printf("\nZaměstnanec: %s %s narozen: %d, povoláním: %s",w.getName(), w.getSurname(), w.getYear(), w.getGroup());
			System.out.printf("\nPrůměrná kvalita spolupráce: %.2f (1 = špatná, 3 = dobrá)", database.getAvgCoop(w));
		}
		else {
			System.out.println("Zaměstnanec nenalezen");
		}
	}
	
	private static void launchAbility() {
		System.out.println("ID zaměstnance: ");
		int id = sc.nextInt();
		Worker w = database.getWorker(id);
		if(w != null) {
			System.out.println(w.ability());
		}
		else {
			System.out.println("Zaměstnanec nenalezen");
		}
	}
	
	private static void x() {
		int id = 1;
		System.out.println(database.getWorker(id).getCoworkersAmount());
	}
	
	

}
