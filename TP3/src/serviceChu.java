
public class serviceChu {
String[] patient;
int[] anne;



	serviceChu(String[] patient,int[] anne){
		this.patient=patient;
		this.anne=anne;
	}
	
	boolean estPatient(String  nom) {
		int i=0;
		while(i<patient.length && !patient[i].equals(nom))i++;
		return (i!=patient.length);
	}
	 int getAnnee(String nom) {
		 int i=0;
		 while(i<patient.length && !patient[i].equals(nom))i++;
		 if(i==patient.length) {
			 return -1;
		 }else {
			 return anne[i];
		 }
	 }
	 String[] getGeneration(int a1,int a2) {
		 String[] generation;
		  int j=0;
		 for(int i=0;i<patient.length;i++) {
			 if(anne[i]>=a1 && anne[i]<=a2) {
				 j++;
			 }
		 }
		 generation= new String[j];
		 j=0;
		 for(int i=0;i<patient.length;i++) {
			 if(anne[i]>=a1 && anne[i]<=a2) {
				 generation[j]=patient[i];
				 j++;
			 }
		 }
		 return generation;
	 }
	
 }
