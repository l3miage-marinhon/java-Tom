package Exemple;

class Test {

	public static void main(String[] args) {
		Exemple p = new Exemple();
        Exemple q = new Exemple();
        p.affiche();
        q.affiche();
        p.f();
        p.affiche();
        q.affiche();
        p = q;
        p.f();
        p.f();
        p.affiche();
        q.affiche();

	}

}
