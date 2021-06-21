package pe.edu.upeu.ril.examen;

import java.util.Scanner;

public class ResolucionExamen {



/*Pregunta 02: El Gerente de una compa;ia automotriz desea determinar el impuesto 
 que va a pagar por cada uno de los atomoviles que posee,ademas del total que va a
 pagar por cada categoria y por todos los vehiculos,basandose en la siguiente clasificacion:

 Los vehiculos co categoira 1 pagan 12% de su valor
 Los vehiculos co categoira 2 pagan 8% de su valor
 Los vehiculos co categoira 3 pagan 5% de su valor
 */
public static void ImpuestoPorAutomoviles() {

    Scanner in = new Scanner(System.in);
        int i, n;
        double categoria1, categoria2, categoria3, clave, costo;
        double impuesto, impuestoApagar;
        categoria1 = 0;
        categoria2 = 0;
        categoria3 = 0;
        impuestoApagar = 0;
        System.out.print("Ingresa # de vehiculos: ");
        n = in.nextInt();
        in.nextLine();
        for (i=1; i <=n; i++) {
            System.out.print( "auto #" + i );
            System.out.print( " Ingresa categoria del vehiculo: ");
            clave = in.nextDouble();
            in.nextLine();
            System.out.print("Ingresa el costo del vehiculo: ");
            costo = in.nextDouble();
            in.nextLine();
            impuesto=0;

            if(clave==1){
                impuesto=costo*0.12;
                categoria1=categoria1+impuesto;
            }
            if(clave==2){
                impuesto=costo*0.08;
                categoria2=categoria2+impuesto;
            }
            if(clave==3){
                impuesto=costo*0.05;
                categoria3=categoria3+impuesto;
            }
            impuestoApagar=impuestoApagar+impuesto;

            System.out.println(" impuesto a pagar: " + impuesto);
            System.out.println();

            
        }
        System.out.println("===================== SUMATORIAS DE LAS CATEGORIAS A PAGAR =========");


        System.out.println("Valor Total a Pagar por la  categoria 1: " + categoria1);
        System.out.println("Valor Total a Pagar por la  categoria 2: " + categoria2);
        System.out.println("Valor Total a Pagar por la  categoria 3: " + categoria3);
        System.out.println("Valor Total de impuestos a pagar: " + impuestoApagar);
    

    }    


//Pregunta 03 Realizar la Tabla de Multiplicacion de 1 al 20:


    public static void tablaMulticacion(){
        
        System.out.println("============TABLA DE MULTIPLICAR DEL 1 AL 20===============");

        int n=0;                                                     
        for(int r = 1; r<=20; r++){
             System.out.println();
        n++;
        System.out.println("Tabla del " + n);
        for(int i = 1; i<=10; i++){
            System.out.println(n + " * " + i + " = " + n*i);
        }
     }    
 } 
              
 /*4.- Considerando que un numero perfecto es un numero natural igual a la suma de sus
divisores propios positivos. Usando la sentencia while y condicionales if elabore un
algoritmo que imprima los n primeros números perfectos.*/

       public static void numerosPerfectos(){
        int i=0, j=0, suma=0;

        System.out.println("Números perfectos entre 1 y 1000000000000: ");

        while (i < 1000000000){    
            i++;  
            suma = 0;
            for(j = 1;j < i;j++){                            
                 if(i % j==0){
                    suma = suma + j; 
                 }
            }
          if(i == suma){                          
             System.out.println(i);
          }
        }
    }
    

/*5.- El valor 𝑥^𝑛 se puede definir recursivamente como:
𝑥 0 = 1
𝑥 𝑛=x*𝑥 𝑛−1
Escriba un método recursivo que calcule y devuelva el valor de 𝑥 𝑛.*/

public int potencia(int x,int n) {

 if ( n==0 )
  return 1;
  
 else 
 return x*potencia(x,n-1);
 

}
 





/*ejercicio extra de los numeros perfectos colocando numeros*/



public static void ejercicioextranumerosperfectos(){
   
    int a=1, b=1,c;
    int divi=0;
    System.out.println("- NUMEROS PERFECTOS - ");
    Scanner lk = new Scanner(System.in);
    System.out.print("Ingrese un número: ");
    c = lk.nextInt();
    lk.nextLine();

    while(b<=c){
        divi=0;
        a=1;

            while (a<=b/2){
            if(b%a == 0){
                divi = divi+a;}
            a++;
        }
        if (b==divi){
            System.out.println(b+" Es Perfecto");
        } 
        b++;
    }
    System.out.println("--");
}



    public static void main(String[] args) {
        //tablaMulticacion();
        //ImpuestoPorAutomoviles();
        //numerosPerfectos();
        ejercicioextranumerosperfectos();
        
    }   
}
