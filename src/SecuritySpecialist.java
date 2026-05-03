
public class SecuritySpecialist extends Worker {

	private static final double MAX_RISK = 10;
	public SecuritySpecialist(String name, String surname, int year) {
		super(name, surname, year, WorkerGroup.SECURITY_SPECIALIST);
	}
	
	@Override
	public String ability(WorkerDatabase database) {
		int amount = getCoworkersAmount();
		double avg = getAvgCompatibility();
		
		double risk = Math.min(MAX_RISK, amount * 0.5 + (3-avg) *2);
		String evaluate;
		
		if(risk > 7) evaluate = "Vysoké";
		else if(risk > 4) evaluate = "Střední";
		else evaluate = "Nízké";
		
		return String.format("Rizikové skóre: %.1f/10 (%s), Počet vazeb: %d, Průměr: %.1f", risk, evaluate, amount, avg);
	}

}
