import exo3.LectureClavier;

public class Palindrome {
	
	
	 public static boolean estPalindrome(String s) {
		 s=s.replace(" ", "");
		int i=0;
		while((i<s.length()) &&((s.charAt(i)==s.charAt(s.length()-1-i)))) {
			System.out.println("a l'indide i ="+i+" le char trouver est "+s.charAt(i)+" et dans le vrai mot "+s.charAt(s.length()-1-i));
			i++;
		}
		if(i==s.length()){
			return true;
		}
		return false;
	 }
	 
	 public static void main(String[] args){
		 char suivant;
		 suivant= LectureClavier.lireChar("voulez vous voir si votre mot est un palindrôme [n] or [y] : ");
		 while(suivant!='n') {
			 String mot = LectureClavier.lireChaine("entrez une chaine :");
			 if(estPalindrome(mot)) {
				 System.out.println("le mot "+mot+" est un palindrome");
			 }else {
				 System.out.println("le mot "+mot+" n'est pas un palindrome");
			 }
			 suivant= LectureClavier.lireChar("voulez vous continuez [n] or [y] : ");
		 }
		 System.out.println("merci de vous etre servir de mon programme");
	 }
}
