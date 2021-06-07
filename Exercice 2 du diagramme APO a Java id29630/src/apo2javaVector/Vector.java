package apo2javaVector;

// Complétez la classe Vector d'après le diagramme APO
class Vector {
	double x;
	double y;
	double z;
	
	
	Vector(){
		this.x=0;
		this.y=0;
		this.z=0;
	}
	
	Vector(double x,double y,double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	void translate(Vector v){
		this.x+=v.x;
		this.y+=v.y;
		this.z+=v.z;
	}
	boolean isEqual(Vector v) {
		return (this.x==v.x && this.y==v.y && this.z==v.z);
	}
}
