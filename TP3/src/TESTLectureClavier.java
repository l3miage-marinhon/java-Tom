import exo3.LectureClavier;

public class TESTLectureClavier {
	
	 public static void main(String[] args) {
		 System.out.println("entrez un caractère :");
		 char c = LectureClavier.lireChar();
		 System.out.println("caractère lu : " + c);
		  
		 String s = LectureClavier.lireChaine("entrez une chaine :");
		 System.out.println("chaine lue : " + s);
		  
		 System.out.println("entrez un entier : ");
		 int i = LectureClavier.lireEntier();
		 System.out.println("entier lu : " + i);
		  
		 float f = LectureClavier.lireFloat("entrez une réel (float) : ");
		 System.out.println("réel (float) lu : " + f);
		  
		 System.out.println("entrez une réel (double) : ");
		 double d = LectureClavier.lireDouble();
		 System.out.println("réel (double) lu : " + d);
		  
		 System.out.println("entrez une réponse O/N : ");
		 boolean b = LectureClavier.lireOuiNon();
		 System.out.println("booleen lu : " + b);
	 }
}
