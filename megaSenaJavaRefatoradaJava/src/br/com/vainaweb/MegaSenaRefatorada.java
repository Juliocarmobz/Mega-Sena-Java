package br.com.vainaweb;

import java.util.Random;
import java.util.Scanner;

public class MegaSenaRefatorada {
	
	private static final int VETOR_NUMEROS = 7;
	private static final int LIMITE_NUMEROS = 100;
	private static final int PREMIO_5_ACERTOS = 10000;
	private static final int PREMIO_6_ACERTOS = 50000;
	private static final int PREMIO_7_ACERTOS = 200000;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		
		System.out.println("MEGA-SENA JAVA \nLOTERIAS VNW");
		
		int[] numeros = obterNumerosUsuario(sc);
		int[] numerosSorteados = gerarNumerosSorteados(random);
		
		imprimirNumeros("Números sorteados: ", numerosSorteados);
		imprimirNumeros("Seus números:      ", numeros);
		
		int numerosCorretos = contarAcertos(numeros, numerosSorteados);
		
		avaliarResultado(numerosCorretos);
		
		sc.close();
	}
		
	
	private static int[] obterNumerosUsuario(Scanner sc) {
		int[] numeros = new int[VETOR_NUMEROS];
		
		for (int i = 0; i < VETOR_NUMEROS; i++) {
			System.out.println("Escolha o " + (i+1) + "º número");
			
			while (!sc.hasNextInt()) {
				System.out.println("Numero inválido, digite um número inteiro");
				sc.next();
			}
			
			int numeroEscolhido = sc.nextInt();
			
			if (!validarNumeroEscolhido(numeroEscolhido)) {
			     System.out.println("Número inválido, digite um número entre 0 e " + LIMITE_NUMEROS);
			     i--;
			} else if (numeroEscolhidoRepetido(numeros, numeroEscolhido)) {
				System.out.println("Você já escolheu esse número, digite outro número diferente ");
				i--;
			} else {
				numeros[i] = numeroEscolhido;
			}	 
		}	
		return numeros;
	}
	
	
	private static boolean validarNumeroEscolhido(int numeroEscolhido) {
		return numeroEscolhido >= 0 && numeroEscolhido <= LIMITE_NUMEROS;
	}
	
	
	private static boolean numeroEscolhidoRepetido(int[] array, int numero) {
		for (int num : array) {
			if (num == numero) {
				return true;
			}
		}
		return false;
	}
	
	
	private static int[] gerarNumerosSorteados(Random random) {
		int[] numerosSorteados = new int[VETOR_NUMEROS];
		
		for (int i = 0; i < VETOR_NUMEROS; i++) {
			numerosSorteados[i] = random.nextInt(LIMITE_NUMEROS + 1);
		}
		
		return numerosSorteados;
	}
	
	
	private static void imprimirNumeros(String mensagem, int [] numeros) {
		System.out.print(mensagem);
		for (int i = 0; i < VETOR_NUMEROS; i++) {
			System.out.print(numeros[i]);
			if (i < VETOR_NUMEROS - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}

	
	private static int contarAcertos(int[] numeros, int[] numerosSorteados) {
		int numerosCorretos = 0;
		for (int i = 0; i < VETOR_NUMEROS; i++) {
			for (int j = 0; j < VETOR_NUMEROS; j++) {
				if (numeros[i] == numerosSorteados[j]) {
					numerosCorretos++;
					break;
				}
			}
		}
		
		return numerosCorretos;
	}
	
		
	private static void avaliarResultado(int numerosCorretos) {
		if (numerosCorretos < 5) {
			System.out.println("Não foi dessa vez... Tente na próxima.");
		} else if (numerosCorretos == 5) {
			System.out.println("Parabéns! Você recebeu o prêmio de " + PREMIO_5_ACERTOS + "Reais!!!");
		} else if (numerosCorretos == 6){
			System.out.println("Parabéns! Você recebeu o prêmio de " + PREMIO_6_ACERTOS + " Reais!!!");
		} else {
			System.out.println("Parabéns! Você recebeu o prêmio de " + PREMIO_7_ACERTOS + " Reais!!!");
		}
	}
	
	
}