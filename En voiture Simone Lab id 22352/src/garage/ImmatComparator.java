package garage;

import java.util.Comparator;

public class ImmatComparator implements Comparator<Vehicule>{

	@Override
	public int compare(Vehicule o1, Vehicule o2) {
		// TODO Auto-generated method stub
		if(o1.getImmatriculation()!=o2.getImmatriculation()) {
			return 1;
		}else {
			return 0;
			}
	}

	
}
