package serialkey;

public class Validador {
	
	public Validador() {
		
	}
	
	public boolean validador(String key) {
		key = key.toUpperCase().replaceAll("-", "");
		int[] array = new int[25];
		for (int i = 0; i < key.length(); i++) {
			char caractere = key.charAt(i);
			if (Character.isLetter(caractere)) {
				array[i] = (int) (caractere - 'A');
			}
			else {
				if (Character.isDigit(caractere)) {
					array[i] = 26 + (int) (caractere - '0');
				}
			}
		}
		int[] verificador = new int[5];
		for (int j = 0; j < 5; j++) {
			int soma = 0;
			for(int i = 0; i < 20 + j; i++) {
				soma = soma + array[i] * (i + 1 + j);
			}
			verificador[j] = soma % 36;
		}
		for (int j = 0; j < 5; j++) {
			if (verificador[j] != array[20 + j]) {
				return false;
			}
		}
		int[][] matriz = new int[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				matriz[i][j] = array[5 * i + j];
			}
		}
		Utils utils = new Utils();
		return utils.isPrime(utils.det(matriz));
	}
	
	public String gerador() {
		int[] array = new int[25];
		for (int i = 0; i < 20; i++) {
			array[i] = (int) (36 * Math.random());
		}
		for (int j = 0; j < 5; j++) {
			int soma = 0;
			for(int i = 0; i < 20 + j; i++) {
				soma = soma + array[i] * (i + 1 + j);
			}
			array[20 + j] = soma % 36;
		}
		String key = "";
		for (int i = 0; i < array.length; i++) {
			if (i > 0 && i % 5 == 0 && i < 25) {
				key += "-";
			}
			key += array[i] < 26 ? (char) ('A' + array[i]) : (char) ('0' + array[i] - 26);
		}
		return key;
	}
	
	private void run() {
		int N = 1000;
		int contador = 0;
		for (int i = 0; i < N; i++) {
			String chave = gerador();
			boolean resultado = validador(chave);
			if (resultado) {
				System.out.println(chave + " = " + resultado);
				contador++;
			}
		}
		System.out.println("contador = " + contador);
	}
	
	public static void main(String[] args) {
		new Validador().run();
	}

}
