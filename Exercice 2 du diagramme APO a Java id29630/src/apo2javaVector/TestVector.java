package apo2javaVector;

// Ne pas changer le nom de cette classe.
class TestVector {

    public static void main(String[] args) {
        // Complétez et décommentez le code suivant.
        
       Vector vector = new Vector(4.5,8.2,7.3);
       Vector translation = new Vector(5.5,1.8,2.7);
       System.out.println("vector: " + vector.x + ", " + vector.y + ", " + vector.z);
       System.out.println("translation: " + translation.x + ", " + translation.y + ", " + translation.z);
       
       vector.translate(translation);
       System.out.println("vector: " + vector.x + ", " + vector.y + ", " + vector.z);
       System.out.println("translation: " + translation.x + ", " + translation.y + ", " + translation.z);
       
       Vector v2=new Vector(10,10,10);
       Vector v3=vector;
       
       boolean vectorEqualsV2=(vector==v2);
       boolean vectorEqualsV3=(vector==v3);
       boolean v2EqualsV3=(v2==v3);
 
       
       System.out.println("vector==v2 ?->"+vectorEqualsV2);
       System.out.println("vector==v3 ?->"+vectorEqualsV3);
       System.out.println("v2==v3 ?->"+v2EqualsV3);
       
       vectorEqualsV2 = vector.isEqual(v2);
       vectorEqualsV3 = vector.isEqual(v3);
       v2EqualsV3 = v2.isEqual(v3);
       
       System.out.println("vector == v2 ? -> " + vectorEqualsV2);
       System.out.println("vector == v3 ? -> " + vectorEqualsV3);
       System.out.println("v2 == v3 ? -> " + v2EqualsV3);
   }
   
}
