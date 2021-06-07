package minifacebook;

public class DateNaissance{
	int jour;
	int mois;
	int annee;
	
	DateNaissance(){
		jour=23;
		mois=6;
		annee=1912;
	}
	DateNaissance(int jour,int mois,int annee){
			if(mois==1 || mois==3 || mois==5 || mois==7 || mois==8 || mois==10 || mois==12) {
				if(jour>=1 && jour<=31) {
					this.jour=jour;
				}else {
					this.jour=1;
				}
				if(mois>=1 && mois<=12){
					this.mois=mois;
				}else {
					this.mois=1;
				}
				if(annee> 0 && annee<2015)this.annee=annee;
				
			}else if(mois==2) {
				if((annee%400)==0 || ((annee%4==0) && !(annee%100==0))) {
					if(jour>=1 && jour<=29) {
						this.jour=jour;
					}else {
						this.jour=1;
					}
				}else {
					if(jour>=1 && jour<=28) {
						this.jour=jour;
					}else {
						this.jour=1;
					}
				}
				if(mois>=1 && mois<=12){
					this.mois=mois;
				}else {
					this.mois=1;
				}
				if(annee> 0 && annee<2015)this.annee=annee;
			}else{
				if(jour>=1 && jour<=30) {
					this.jour=jour;
				}else {
					this.jour=1;
				}
				if(mois>=1 && mois<=12){
					this.mois=mois;
				}else {
					this.mois=1;
				}
				if(annee> 0 && annee<2015)this.annee=annee;
			}
	}
	
	int getJour() {return this.jour;}
	int getMois() {return this.mois;}
	int getAnnee() {return this.annee;}
	
	void setJour(int jour) {
		if(this.mois==1 || this.mois==3 || this.mois==5 || this.mois==7 || this.mois==8 || this.mois==10 || this.mois==12) {
				if(jour>=1 && jour<=31) {
					this.jour=jour;
				}else {
					this.jour=1;
				}
		}else if(this.mois==2) {
				if((annee%400)==0 || ((annee%4==0) && !(annee%100==0))) {
					if(jour>=1 && jour<=29)this.jour=jour;
				}else {
					if(jour>=1 && jour<=28) {
						this.jour=jour;
					}else {
						this.jour=1;
					}
				}
		}else{
			if(jour>=1 && jour<=30) {
				this.jour=jour;
			}else {
				this.jour=1;
			}
		}
}
	
	
	
	void setMois(int mois) {if(mois>=1 && mois<=12)this.mois=mois;}
	
	void setAnnee(int annee) {if(annee> 0 && annee<2015)this.annee=annee;}
	
	void ecritDate(){
		System.out.print(jour+"/"+mois+"/"+annee);
	}
	int age() {return (2015-(this.annee));}
	
	
	
} 
