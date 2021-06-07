package garage;

import java.util.Comparator;

public class ConsoComparator implements Comparator<Vehicule> {

	@Override
	public int compare(Vehicule o1, Vehicule o2) {
		// TODO Auto-generated method stub
		if(o1.getConsomation()< o2.getConsomation())
            return -1;
        if(o1.getConsomation() > o2.getConsomation())
            return 1;
        if(o1.getConsomation() == o2.getConsomation())
            return 0;
        return 0;
	}
	
}	