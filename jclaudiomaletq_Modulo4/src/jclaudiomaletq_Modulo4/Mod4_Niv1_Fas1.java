package jclaudiomaletq_Modulo4;

import java.util.*;
import javax.swing.*;

/*
 * L�exercici consisteix a mostrar per consola una carta d�un restaurant on afegirem diferents plats i despr�s 
 * escollirem que volem per menjar. Un cop fet aix� s�haur� de calcular el preu del menjar, 
 * i el programa ens dir� amb quins bitllets hem de pagar. 
 * Creeu una variable int per cada un dels bitllets i/o monedes que existeixen des de 1� a 500�. 
 * Haureu de crear una altra variable per guardar el preu total del menjar.  
 * Creeu dos arrays, un on guardarem el men� i un altre on guardarem el preu de cada plat.
 */
public class Mod4_Niv1_Fas1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	// Variables
		final int long_array=10;  // n�mero de platos del ejemplo
		String plats[] = new String[long_array];   // nombre de los platos
		int preus[] = new int [long_array];   // valor de los platos
		int preu_total=0;
		int canti_platos[] = new int [long_array];  // cantidad de platos pedidos de cada plato
	// proceso	
		rellena_carta(plats,long_array);
		rellena_precios(preus, long_array);
		pregunta_platos(plats,preus,canti_platos, long_array);
		preu_total = calcula_precio(preus,canti_platos, long_array);
	// visualiza	
		System.out.println("el valor total es "+preu_total+" �");
		calcula_forma_pago(preu_total);
	}
public static void rellena_carta(String [] lista, int num) {
	  for(int i=0; i<=num-1; i++) {
        lista[i]="plato "+(i+1) ;
	  }
	}	

public static void rellena_precios(int [] lis_pre, int num) {
	int pre=0;
	for (int i=0;i<num;i++) {     // rellena precio aleatorio hasta 25�
		do {
			pre=(int)(Math.random()*100);
		}while (pre>=25 || pre==0);
		lis_pre[i]=pre;	
	}
}	
public static void pregunta_platos(String [] lista, int [] lis_pre, int [] cant_plat ,int num) {
	int inter =1;
	String s_inter="";
	int nu_err =0;	
	Scanner leer = new Scanner(System.in);	
	System.out.println("menu disponible: ");    
	for(int i=0; i<=num-1; i++) {     /// presentar menu
		System.out.println((i+1)+" "+ lista[i] +"  precio del plato "+lis_pre[i]+" �" );
		cant_plat[i]=0;    // inicializa vector de cantidades
		  }
	System.out.println("pide platos introduciendo el n�mero, para finalizar un 0 ");
	do {      // leer platos
		try { 	s_inter = leer.nextLine(); 
		        inter= Integer.parseInt( s_inter);
				if(inter >num ) {inter=lis_pre[num];}
				if (inter != 0) {
					   cant_plat[inter-1]=cant_plat[inter-1]+1; nu_err=0;  }}
		   catch (Exception e)
				{ nu_err++; 
				 System.out.println("introducido : "+s_inter+" el valor ha de ser un n�mero de plato o 0 para finalizar te quedan "+(3-nu_err)+" intentos");
				} 
		//   finally {  leer.close();     }
	} while (inter != 0 && nu_err <3);
	for(int i=0; i<=num-1; i++) {     /// presentar selecci�n
		if (cant_plat[i] !=0) {
		System.out.println(cant_plat[i]+" de  "+ lista[i] +"  a "+lis_pre[i]+" �" );
		}
	} 
}
static int calcula_precio( int [] lis_pre, int [] cant_plat ,int num) {
	int inter = 0;
	for(int i=0; i<=num-1; i++) {     // calcula precio
		inter = inter + (cant_plat[i] * lis_pre[i]);		
		  }
return inter;
}
public static void calcula_forma_pago( int valor) {
	int inter = valor;  
	int mone_1=0;
	int mone_2=0;
	int billete_5=0;
	int billete_10=0;
	int billete_20=0;
	int billete_50=0;
	int billete_100=0;
	int billete_200=0;
	int billete_500=0;
	if (inter >= 500) {billete_500 = inter/500;
	                inter = inter%500;
	                System.out.println(billete_500 +" billetes de 500");
	                }
	if (inter >= 200) {billete_200 = inter/200;
					inter = inter%200;
					System.out.println(billete_200 +" billetes de 200");
    				}
	if (inter >= 100) {billete_100 = inter/100;
					inter = inter%100;
					System.out.println(billete_100 +" billetes de 100");
					}	
	if (inter >= 50) {billete_50 = inter/50;
					inter = inter%50;
					System.out.println(billete_50 +" billetes de 50");
					}	
	if (inter >= 20) {billete_20 = inter/20;
					inter = inter%20;
					System.out.println(billete_20 +" billetes de 20");
					}	
	if (inter >= 10) {billete_10 = inter/10;
					inter = inter%10;
					System.out.println(billete_10 +" billetes de 10");
					}	
    if (inter >= 5) {billete_5 = inter/5;
					inter = inter%5;
					System.out.println(billete_5 +" billetes de 5");
					}		
    if (inter >= 2) {mone_2 = inter/2;
					inter = inter%2;
					System.out.println(mone_2 +" monedas de 2");
					}
    if (inter >= 1) {System.out.println(inter +" monedas de 1");
    				 mone_1 = inter;
					}
	}
}
