package jclaudiomaletq_Modulo4;

public class mi_excepcion extends Exception {
	private int codigo_error;
	public mi_excepcion (int codigo_error, String texto) {
		super();
		System.out.println(texto+" no esta en la lista de platos: error  " + codigo_error);
		this.codigo_error=codigo_error;
	}
}


