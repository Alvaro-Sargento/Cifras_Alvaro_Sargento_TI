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
    public static String encrypt(String key, String message) {
        // Verifica se a chave não contém letras repetidas
        Set<Character> uniqueChars = new HashSet<>();
        for (char c : key.toCharArray()) {
            if (Character.isLetter(c)) {
                if (!uniqueChars.add(Character.toUpperCase(c))) {
                    throw new IllegalArgumentException("A chave contém letras repetidas.");
                }
            }
        }

        int columns = key.length();
        int rows = (int) Math.ceil((double) message.length() / columns);
        int paddingSize = rows * columns - message.replaceAll("\\s", "").length();

        // Preenche o texto com letras aleatórias para ajustar o tamanho das linhas
        Random random = new Random();
        for (int i = 0; i < paddingSize; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A'); // Gera uma letra maiúscula aleatória
            message += randomChar;
        }

        String mensagemSemEspacos = message.replaceAll("\\s", "");
        char[][] matrix = new char[rows][columns];
        char[] sortedKey = key.toCharArray();
        Arrays.sort(sortedKey);
        int[] indices = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            char letra = sortedKey[i];
            indices[key.indexOf(letra)] = i + 1;
        }

        // Preencher a matriz com a mensagem sem espaços
        int index = 0;
        for (int linha = 0; linha < rows; linha++) {
            for (int coluna = 0; coluna < columns; coluna++) {
                if (index < mensagemSemEspacos.length()) {
                    matrix[linha][coluna] = mensagemSemEspacos.charAt(index);
                    index++;
                } else {
                    break; // Caso a mensagem seja preenchida completamente antes do fim da matriz
                }
            }
        }

        int colunas = matrix[0].length;
        int[] cloneChave = indices.clone();
        Arrays.sort(cloneChave);

        StringBuilder mensagemCifrada = new StringBuilder();
        for (int i = 0; i < cloneChave.length; i++) {
            int valor = cloneChave[i];
            for (int j = 0; j < indices.length; j++) {
                if (indices[j] == valor) {
                    int indeX = j;

                    for (int linha = 0; linha < matrix.length; linha++) {
                        if (indeX < colunas) {
                            mensagemCifrada.append(matrix[linha][indeX]);
                        }
                    }
                    mensagemCifrada.append(" ");
                    break;
                }
            }
        }


        return mensagemCifrada.toString();
    }
}
