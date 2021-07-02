package fileAttente;

import java.util.ArrayList;

public class FileAttente<T> {
	protected ArrayList<T> contenu;
	public FileAttente() {contenu = new ArrayList<T>();}
	public void entre (T elem) {
		contenu.add(elem);
	}
	public T sort() {
		T elem=null;
		if(!estVide()) {
			elem = contenu.get(contenu.size()-1);
			contenu.remove(contenu.size()-1);
		}
		return elem;
	}
	public boolean estVide() {return contenu.isEmpty();}
	@Override
	public String toString() {return ""+contenu;}
}
