package exo3;

public enum Comparaison {
    TROP_GRAND("trop grand mon petit"),TROP_PETIT("trop petit mon grand"), EGAL("fumiez ta trouvé") ;  
    
    private String message ;  
     
    private Comparaison(String message) {  
        this.message = message ;  
   }  
     
    public String getMessage() {  
        return  this.message ;  
   }  
}
