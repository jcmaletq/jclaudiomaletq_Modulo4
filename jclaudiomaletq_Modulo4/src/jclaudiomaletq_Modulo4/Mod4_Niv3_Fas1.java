package jclaudiomaletq_Modulo4;

import java.util.*;
import jclaudiomaletq_Modulo4.mi_excepcion;
import jclaudiomaletq_Modulo4.valo_cer;
import jclaudiomaletq_Modulo4.Longitud_erronea;
import jclaudiomaletq_Modulo4.nombre_dupli;

	/* Fase 1
	 * Crea excepcions personalitzades amb fitxers nous e implementa-les al codi actual. 
	 * Has de crear Excepcions personalitzades per:  Revisió de tipus, introducció de plats, 
	 * revisió de plats de la comanda. 
	 * El text ha de ser personalitzat. No cal que propaguis l’excepció.
	 * Rodeja amb un try/catch cada part que has de revisar amb una excepció. . 
	 */
public class Mod4_Niv3_Fas1 {
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
			preuPlat.put("Ortolana Grande", 0);
			final int long_array=preuPlat.size();  // número de platos del ejemplo
			TreeMap<String,Integer> preuPlat_ord = new TreeMap<String,Integer>();
			String plats[] = new String[long_array];   // nombre de los platos
			int preus[] = new int [long_array];   // valor de los platos
			int preu_total=0;
			int canti_platos[] = new int [long_array];  // cantidad de platos pedidos de cada plato
			
		// proceso	
			preuPlat_ord.putAll(preuPlat);
			rellena_carta(plats,preuPlat_ord);
			rellena_precios(preus,preuPlat_ord);
			int x= comprueba_menu (preus, plats, long_array);
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
	        if (lis_pre[i]<1) {System.out.println("*** hay algún precio a cero  ****");}
	        i++;
		  }
		}	

public static int comprueba_menu( int [] lis_pre, String [] lista ,int num) 
//		throws valo_cer , nombre_dupli
		 {
	        int y ,x = 0;
			for(int i=0; i<=num-1; i++) {  
				if (lis_pre[i] == 0) 
					{x = i; 	
				    }
			}
			for(int j=0; j<=num-2; j++) {  
					if (lista[j] == lista[j+1]) 
						{y = j; 
						System.out.println("nombre dupli");
						}
			}
				
//			if (x != 0) {throw new valo_cer ("plato con valor cero"); }	
//			if (y != 0) {throw new nombre_dupli ("plato con valor cero"); }
	return x;		
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
			        if (s_inter.length() > 2) {
			        	inter = busca_pos(s_inter,lista);
			            } 
			        else {inter= Integer.parseInt( s_inter);}
					if(inter >num ) {inter=lis_pre[num];}
					if (inter != 0) {
						   cant_plat[inter-1]=cant_plat[inter-1]+1; nu_err=0;  }}
			   catch (Exception e)
					{ nu_err++; 
					 System.out.println("introducido : "+s_inter+" el valor ha de ser un número de plato ó el nombre exacto ó 0 para finalizar te quedan "+(3-nu_err)+" intentos");
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
	
public static int busca_pos(String texto,String [] lista)
		throws Longitud_erronea, mi_excepcion {  //  dos tipos de excepciones
		int res = 999;          // excepción de este mismo fichero
		if (texto.length()<=3){throw new Longitud_erronea("La longitud del texto debe ser mayor a 3");}	
	      else {
	      int num = lista.length -1 ;
		  for(int i=0; i<=num; i++) {
		       if ( lista[i].contains(texto)) {
		    	   res = i;
		           }
			  }
		  if (res == 999) {throw new mi_excepcion(res,texto);}   // excepcion de fichero
	      }
	return (res+1);
	}
static int calcula_precio( int [] lis_pre, int [] cant_plat ,int num) {
		int inter = 0;  // calcula precio
		for(int i=0; i<=num-1; i++) {     
			inter = inter + (cant_plat[i] * lis_pre[i]); }
	return inter;
	}

public static void calcula_forma_pago( int valor){
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
		try {
			// inter = inter/0;
			if (inter >= 500) {billete_500 = inter/500;
			                inter = inter%500;
			                if (billete_500 > 1) {System.out.println(billete_500 +" billetes de 500");}
			                else {System.out.println(billete_500 +" billete de 500");} 
			                }
			if (inter >= 200) {billete_200 = inter/200;
							inter = inter%200;
							if (billete_200 > 1) {System.out.println(billete_200 +" billetes de 200");}
							else {System.out.println(billete_200 +" billete de 200");}
		    				}
			if (inter >= 100) {billete_100 = inter/100;
							inter = inter%100;
							if (billete_100 > 1) {System.out.println(billete_100 +" billetes de 100");}
							else {System.out.println(billete_100 +" billete de 100");}
							}	
			if (inter >= 50) {billete_50 = inter/50;
							inter = inter%50;
							if (billete_50 > 1) {System.out.println(billete_50 +" billetes de 50");}
							else {System.out.println(billete_50 +" billete de 50");}
							}	
			if (inter >= 20) {billete_20 = inter/20;
							inter = inter%20;
							if (billete_20 > 1) {System.out.println(billete_20 +" billetes de 20");}
							else  {System.out.println(billete_20 +" billete de 20");}
							}	
			if (inter >= 10) {billete_10 = inter/10;
							inter = inter%10;
							if (billete_10 > 1) {System.out.println(billete_10 +" billetes de 10");}
							else {System.out.println(billete_10 +" billete de 10");}
							}	
		    if (inter >= 5) {billete_5 = inter/5;
							inter = inter%5;
							if (billete_5 > 1) {System.out.println(billete_5 +" billetes de 5");}
							else {System.out.println(billete_5 +" billete de 5");}
							}		
		    if (inter >= 2) {mone_2 = inter/2;
							inter = inter%2;
							if (mone_2 > 1) {System.out.println(mone_2 +" monedas de 2");}
							else  {System.out.println(mone_2 +" moneda de 2");}
							}
		    if (inter >= 1) { mone_1 = inter;
		    	             if (mone_1 > 1) {System.out.println(inter +" monedas de 1");}
		    	             else  {System.out.println(inter +" moneda de 1");}
							}
		  }catch (ArithmeticException ex) {System.out.println("Error en calculo billetes ");
		  								ex.printStackTrace();}
		}
}
