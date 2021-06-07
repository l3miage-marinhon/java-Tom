package exo3;

public class devinette {
	private int nb_deviner;
	private int min_i;
	 private int max_i;
	 
	 devinette(int min_i,int max_i){
		 if(min_i<max_i) {
			 this.min_i=min_i;
			 this.max_i=max_i;
		 }else {
			 this.min_i=max_i;
			 this.max_i=min_i;
		 }
	 }
	 
	 public void auHasar() {
		 double valeur = min_i + (max_i - min_i)*Math.random();
		 nb_deviner=(int)valeur;
	 }
	 
	 public void cheat(int nombre) {
		 if(nombre>max_i && nombre<min_i) {
			 nb_deviner=(min_i+max_i)/2;
		 }else {
			 nb_deviner=nombre;
			 }
	 }
	 
	 public int getMin() {
		 return min_i;
	 }
	 
	 public int getMax() {
		 return max_i;
	 }
	 
	 public Comparaison essayer(int n) {
		 if(n<nb_deviner) {
			 return Comparaison.TROP_PETIT;
		 }else if(n>nb_deviner) {
			 return Comparaison.TROP_GRAND;
		 }else {
			 return Comparaison.EGAL;
		 }
	 }
	 
	 public void partieContreHumain() {
		 int nb_fois_joue=0;
		 System.out.println("veuillez commencer a jouer ");
		 int nb=LectureClavier.lireEntier("veuillez rentrez un entier :");
		 while(nb!=nb_deviner) {
			 System.out.println(this.essayer(nb).getMessage());
			 nb=LectureClavier.lireEntier("veuillez rentrez un entier : ");
			 nb_fois_joue++;
		 }
		 System.out.println("tu mis "+nb_fois_joue+" fois pour trouvez le nom mistère !");
	 }
	 
	
}
