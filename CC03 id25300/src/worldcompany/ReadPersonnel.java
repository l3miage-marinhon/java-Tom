package worldcompany;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class ReadPersonnel implements ContentHandler {

    private enum State {

        NOM, PRENOM, ANNEE_EMBAUCHE, OTHER
    }

    private State state;

    private Personnel personnel;

    private ArrayList<Employe> listeEmployes;

    private String nom;

    private String prenom;

    private int anneeEmbauche;

    private double chiffreAffaire;

    private int heures;

    public Personnel getPersonnel() {
        return personnel;
    }

    @Override
    public void startDocument() throws SAXException {
    // TODO Complétez la méthode startDocument.
    	state=State.OTHER;
    	
    }

    @Override
    public void endDocument() throws SAXException {
    // TODO Complétez la méthode endDocument.
    	State=State.OTHER
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
    // TODO Complétez la méthode startElement.
    	switch(state){
		case ANNEE_EMBAUCHE:
			if(localName.equals("annneeEmbauche")) {
				this.anneeEmbauche=Integer.parseInt(qName);
				state=State.ANNEE_EMBAUCHE;
			}
			break;
		case NOM:
			if(localName.equals("nom")) {
				this.nom=qName;
			}
			break;
		case OTHER:
				if(localName.equals("nom")) {
					state=State.NOM;
				}
				if(localName.equals("anneEmbauche")) {
					state=State.ANNEE_EMBAUCHE;
				}
				if(localName.equals("prenom")) {
					state=State.PRENOM;
				}
				if(localName.equals("worldCompagny")) {
					state=State.OTHER;
				}
				if(localName.equals("vendeur")) {
					state=State.OTHER;
				}
				if(localName.equals("manutentionnaire")) {
					state=State.OTHER;
				}
				
			break;
		case PRENOM:
			if(localName.equals("prenom")) {
				state=State.PRENOM;
			}
			break;
		default:
			break;
    		
    	}
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    // TODO Complétez la méthode endElement.
    	switch (state) {
		case ANNEE_EMBAUCHE:
			break;
		case NOM:
			break;
		case OTHER:
			break;
		case PRENOM:
			break;
		default:
			break;
    	
    	}
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    // TODO Complétez la méthode characters.
    }

    // Méthodes à ne pas compléter. Elles sont ici pour implémenter intégralement l'interface ContentHandler
    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    // TODO Auto-generated method stub
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
    // TODO Auto-generated method stub
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
    // TODO Auto-generated method stub
    }

    @Override
    public void setDocumentLocator(Locator locator) {
    // TODO Auto-generated method stub
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
    // TODO Auto-generated method stub
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
    // TODO Auto-generated method stub
    }
}
