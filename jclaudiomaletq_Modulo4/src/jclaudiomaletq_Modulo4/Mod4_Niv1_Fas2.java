package jclaudiomaletq_Modulo4;

import java.util.*;
import javax.swing.*;

/* Fase 1
 * L’exercici consisteix a mostrar per consola una carta d’un restaurant on afegirem diferents plats i després 
 * escollirem que volem per menjar. Un cop fet això s’haurà de calcular el preu del menjar, 
 * i el programa ens dirà amb quins bitllets hem de pagar. 
 * Creeu una variable int per cada un dels bitllets i/o monedes que existeixen des de 1€ a 500€. 
 * Haureu de crear una altra variable per guardar el preu total del menjar.  
 * Creeu dos arrays, un on guardarem el menú i un altre on guardarem el preu de cada plat.
 * Fase 2
 * Haurem d’omplir els dos arrays anteriorment creats, afegint el nom del plat i despanrés el preu. 
 * Es pot fer de dues formes:  Es pot fer us d'un diccionari de dades precarregat (Java HashMap) o amb un bucle demanant les dades a l'usuari. 
 * Un cop plens els dos arrays haurem de mostrar-los i preguntar que es vol per menjar, guardarem la informació en una List. 
 * Haurem de preguntar si es vol seguir demanant menjar. Podeu fer servir el sistema (1:Si / 0:No), 
 * per tant haureu de crear un altre variable int per guardar la informació. 
 */
public class Mod4_Niv1_Fas2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	// Variables
		HashMap<String,Integer> preuPlat = new HashMap<String,Integer>();
		preuPlat.put("Bufalina", 12);
		preuPlat.put("4 Formaggi",12 );
		preuPlat.put("Pino Daniele",18 );
		preuPlat.put("Margherita", 9);
		preuPlat.put("Prosciutto", 10);
		preuPlat.put("Parmiggiana",11 );
		preuPlat.put("Al Tonno",13 );
		preuPlat.put("Carbonara",11 );
		preuPlat.put("Massimo Troise", 17 );
		preuPlat.put("Ortolana", 12);
		preuPlat.put("Ortolana grande", 13);
		final int long_array=preuPlat.size();  // número de platos del ejemplo
		TreeMap<String,Integer> preuPlat_ord = new TreeMap<String,Integer>();
		String plats[] = new String[long_array];   // nombre de los platos
		int preus[] = new int [long_array];   // valor de los platos
		int preu_total=0;
		int canti_platos[] = new int [long_array];  // cantidad de platos pedidos de cada plato
		
	// proceso	
		preuPlat_ord.putAll(preuPlat);
		rellena_carta(plats,preuPlat_ord);
		rellena_precios(preus, preuPlat_ord);
		init_platos(canti_platos , long_array);
		
		do {
			pregunta_platos(plats,preus,canti_platos, long_array);
			preu_total = calcula_precio(preus,canti_platos, long_array);
		   System.out.println("el valor total es "+preu_total+" €");
		}while (continuar());
	// visualiza	
		System.out.println("el valor total es "+preu_total+" €");
		calcula_forma_pago(preu_total);
	}
public static void rellena_carta(String [] lista,TreeMap<String,Integer> preuPlat) {
	int i = 0;  
	for(Map.Entry<String, Integer> entry:preuPlat.entrySet()) {
        lista[i]= entry.getKey();
        i++;
	  }
	}	

public static void rellena_precios(int [] lis_pre, TreeMap<String,Integer> preuPlat) {
	int i = 0;  
	for(Map.Entry<String, Integer> entry:preuPlat.entrySet()) {
        lis_pre[i]= entry.getValue();
        i++;
	  }
	}	


public static void init_platos( int [] cant_plat ,int num) {
	for(int i=0; i<=num-1; i++) {  
		cant_plat[i]=0;    // inicializa vector de cantidades
		  }
}
public static void pregunta_platos(String [] lista, int [] lis_pre, int [] cant_plat ,int num) {
	int inter =1;
	String s_inter="";
	int nu_err =0;	
	Scanner leer = new Scanner(System.in);	
	System.out.println("menu disponible: ");    
	for(int i=0; i<=num-1; i++) {     /// presentar menu
		System.out.printf("%-4s%-20s%-15s%-2s%-2s%-10s\n",(i+1), lista[i] ,"  precio del plato ",lis_pre[i]," €  pedidos ",cant_plat[i] );
		//System.out.println((i+1)+" "+ lista[i] +"  precio del plato "+lis_pre[i]+" €  pedidos "+cant_plat[i] );
		  }
	System.out.println("pide platos introduciendo el número, para finalizar un 0 ");
	do {      // leer platos
		try { 	s_inter = leer.nextLine(); 
		        inter= Integer.parseInt( s_inter);
				if(inter >num ) {inter=lis_pre[num];}
				if (inter != 0) {
					   cant_plat[inter-1]=cant_plat[inter-1]+1; nu_err=0;  }}
		   catch (Exception e)
				{ nu_err++; 
				 System.out.println("introducido : "+s_inter+" el valor ha de ser un número de plato o 0 para finalizar te quedan "+(3-nu_err)+" intentos");
				} 
		//   finally {  leer.close();     }
	} while (inter != 0 && nu_err <3);
	for(int i=0; i<=num-1; i++) {     /// presentar selección
		if (cant_plat[i] !=0) {
		//System.out.println(cant_plat[i]+" de  "+ lista[i] +"  a "+lis_pre[i]+" €" );
			System.out.printf("%-3s%-5s%-15s%-2s%-3s%-2s\n",cant_plat[i]," de  ", lista[i] ,"  a ",lis_pre[i]," €" );
		}
	} 
}
static boolean continuar() {
	Scanner entrada = new Scanner(System.in);
	System.out.println("continuar pidiendo platos S para Sí otro para No");
	String pregu = entrada.next();
	if (pregu.equals("s") || pregu.equals("S") ) {
		return true;
	}
	else {return false;}
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
