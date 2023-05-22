/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transpColunar;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Alvaro Sargento
 */
public class CifraColunar {
    public String encrypt(String key, String message) {
        // Verifica se a chave não contém letras repetidas
        Set<Character> uniqueChars = new HashSet<>(); // Cria um conjunto para armazenar caracteres únicos
        for (char c : key.toCharArray()) {
            if (Character.isLetter(c)) { // Verifica se o caractere é uma letra
                if (!uniqueChars.add(Character.toUpperCase(c))) { // Tenta adicionar o caractere convertido para maiúsculo ao conjunto
                    throw new IllegalArgumentException("A chave contém letras repetidas."); // Lança uma exceção se o caractere já estiver presente no conjunto
                }
            }
        }

        int columns = key.length(); // Obtém o número de colunas da matriz a partir do comprimento da chave
        int rows = (int) Math.ceil((double) message.length() / columns); // Calcula o número de linhas com base no tamanho da mensagem e do número de colunas
        int paddingSize = rows * columns - message.replaceAll("\\s", "").length(); // Calcula o tamanho do preenchimento necessário

        // Preenche o texto com letras aleatórias para ajustar o tamanho das linhas
        Random random = new Random();
        for (int i = 0; i < paddingSize; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A'); // Gera uma letra maiúscula aleatória
            message += randomChar; // Adiciona a letra aleatória à mensagem
        }

        String mensagemSemEspacos = message.replaceAll("\\s", ""); // Remove os espaços em branco da mensagem
        char[][] matrix = new char[rows][columns]; // Cria uma matriz para armazenar os caracteres da mensagem
        char[] sortedKey = key.toCharArray(); // Converte a chave em um array de caracteres
        Arrays.sort(sortedKey); // Ordena o array de caracteres da chave em ordem crescente
        int[] indices = new int[key.length()]; // Cria um array para armazenar os índices das colunas da matriz

        // Atribui os índices às letras da chave
        for (int i = 0; i < key.length(); i++) {
            char letra = sortedKey[i]; // Obtém a letra na posição i do array ordenado
            indices[key.indexOf(letra)] = i + 1; // Atribui o índice (i + 1) ao caractere na chave não ordenada
        }

        // Preenche a matriz com a mensagem sem espaços
        int index = 0;
        for (int linha = 0; linha < rows; linha++) {
            for (int coluna = 0; coluna < columns; coluna++) {
                if (index < mensagemSemEspacos.length()) {
                    matrix[linha][coluna] = mensagemSemEspacos.charAt(index); // Atribui o caractere correspondente ao índice atual da mensagem à matriz
                    index++;
                } else {
                    break; // Caso a mensagem seja preenchida completamente antes do fim da matriz
                }
            }
        }

        int colunas = matrix[0].length; // Obtém o número de colunas da matriz
        int[] cloneChave = indices.clone(); // Cria uma cópia do array de índices
        Arrays.sort(cloneChave); // Ordena a cópia do array de índices em ordem crescente

        StringBuilder mensagemCifrada = new StringBuilder(); // Cria um objeto StringBuilder para construir a mensagem cifrada
        for (int i = 0; i < cloneChave.length; i++) {
            int valor = cloneChave[i]; // Obtém o valor do índice atual
            for (int j = 0; j < indices.length; j++) {
                if (indices[j] == valor) { // Encontra o índice correspondente ao valor atual
                    int indeX = j;

                    for (int linha = 0; linha < matrix.length; linha++) {
                        if (indeX < colunas) {
                            mensagemCifrada.append(matrix[linha][indeX]); // Adiciona o caractere da matriz à mensagem cifrada
                        }
                    }
                    mensagemCifrada.append(" "); // Adiciona um espaço em branco após cada coluna
                    break;
                }
            }
        }

        return mensagemCifrada.toString(); // Retorna a mensagem cifrada como uma String
    }
}
