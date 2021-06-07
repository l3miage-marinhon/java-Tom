package menagerie;

public class Animalerie {

    public String getChenil() {
    // TODO Créez un tableau de 3 animaux et renvoyez une chaîne de carractère qui contient ce qui est demandé dans l'énoncé. Contrainte: utilier un foreach...
        Animal [] tab= new Animal[3];
        tab[0]=new Poisson("Némo");
        tab[1]=new Chien("Médor");
        tab[2]=new Chat("Tom");
        String str="";
        for(Animal a :tab)str+="nom : "+a.getNom()+", s'affiche en disant : "+a.toString()+", son cri est : "+a.cri()+"\n";
        return str;
    }
      

    public static void main(String[] args) {
        Animalerie an = new Animalerie();
        System.out.println(an.getChenil());
       
    }
}
