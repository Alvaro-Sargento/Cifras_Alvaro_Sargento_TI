package playfair;

public class CifraPlayfair {
    private char[][] matrix; // Matriz utilizada para a cifra de Playfair
    private String chave; // Chave utilizada para gerar a matriz

    public CifraPlayfair(String chave) {
        this.chave = chave;
        this.matrix = new char[5][5]; // Cria uma matriz 5x5 para a cifra de Playfair
        criarMatrix(); // Chama o método para criar a matriz
    }


    private void criarMatrix() {
        chave = chave.replaceAll("j", "i").replaceAll("\\s", ""); // Substitui 'j' por 'i' e remove espaços em branco na chave
        String alfabeto = "abcdefghiklmnopqrstuvwxyz"; // Alfabeto sem a letra 'j'
        String chaveAlfabeto = chave + alfabeto; // Concatena a chave com o alfabeto

        for (int i = 0, row = 0, col = 0; i < chaveAlfabeto.length(); i++) {
            char c = chaveAlfabeto.charAt(i);
            if (!contem(matrix, c) && Character.isLetter(c)) { // Verifica se a letra não está presente na matriz e se é uma letra do alfabeto
                matrix[row][col] = c; // Atribui a letra à posição correspondente na matriz
                col++;
                if (col == 5) { // Se a coluna atingiu o tamanho máximo (5), passa para a próxima linha
                    row++;
                    col = 0;
                }
            }
        }
    }


    private boolean contem(char[][] arr, char c) {
        for (char[] row : arr) { // Itera sobre cada linha do array bidimensional
            for (char ch : row) { // Itera sobre cada elemento da linha
                if (ch == c) { // Verifica se o elemento é igual ao caractere 'c'
                    return true; // Retorna true se o caractere for encontrado
                }
            }
        }
        return false; // Retorna false se o caractere não for encontrado
    }


    private String formatarTexto(String textoClaro) {

        return textoClaro.replaceAll("[^a-zA-Z]", "").replaceAll("j", "i");
    }

    private String[] bigramas(String formattedtextoClaro) {
        int n = formattedtextoClaro.length(); // Obtém o tamanho do texto formatado
        int m = (n % 2 == 0) ? n / 2 : n / 2 + 1; // Calcula o número de bigramas necessários com base no tamanho do texto
        String[] bigrams = new String[m]; // Cria um array para armazenar os bigramas
        int i = 0;

        for (int j = 0; j < n - 1; j += 2) { // Itera sobre o texto formatado em incrementos de 2 para formar os bigramas
            bigrams[i] = formattedtextoClaro.substring(j, j + 2); // Obtém um par de caracteres consecutivos para formar o bigrama
            i++;
        }

        if (n % 2 != 0) { // Se o tamanho do texto for ímpar
            bigrams[m - 1] = formattedtextoClaro.substring(n - 1) + "x"; // Adiciona o último caractere com 'x' para formar um bigrama completo
        }

        return bigrams; // Retorna o array de bigramas
    }


    private int[] getCharPosition(char c) {
        for (int row = 0; row < 5; row++) { // Itera pelas linhas da matriz
            for (int col = 0; col < 5; col++) { // Itera pelas colunas da matriz
                if (matrix[row][col] == c) { // Verifica se o caractere na posição atual da matriz é igual ao caractere 'c'
                    return new int[] {row, col}; // Retorna um array contendo a posição (linha e coluna) do caractere na matriz
                }
            }
        }
        return null; // Retorna null se o caractere não for encontrado na matriz
    }


    public String encrypt(String textoClaro) {
        String formattedtextoClaro = formatarTexto(textoClaro); // Formata o texto claro
        String[] bigrams = bigramas(formattedtextoClaro); // Divide o texto formatado em bigramas
        StringBuilder cipherText = new StringBuilder(); // Cria um StringBuilder para armazenar o texto cifrado

        for (String bigram : bigrams) { // Itera sobre os bigramas do texto formatado
            char c1 = bigram.charAt(0); // Obtém o primeiro caractere do bigrama
            char c2 = bigram.charAt(1); // Obtém o segundo caractere do bigrama
            int[] pos1 = getCharPosition(c1); // Obtém a posição do primeiro caractere na matriz
            int[] pos2 = getCharPosition(c2); // Obtém a posição do segundo caractere na matriz

            if (pos1[0] == pos2[0]) { // Mesma linha
                cipherText.append(matrix[pos1[0]][(pos1[1] + 1) % 5]); // Adiciona o caractere cifrado correspondente ao primeiro caractere, deslocado uma posição para a direita (considerando o wrap-around)
                cipherText.append(matrix[pos2[0]][(pos2[1] + 1) % 5]); // Adiciona o caractere cifrado correspondente ao segundo caractere, deslocado uma posição para a direita
            } else if (pos1[1] == pos2[1]) { // Mesma coluna
                cipherText.append(matrix[(pos1[0] + 1) % 5][pos1[1]]); // Adiciona o caractere cifrado correspondente ao primeiro caractere, deslocado uma posição para baixo (considerando o wrap-around)
                cipherText.append(matrix[(pos2[0] + 1) % 5][pos2[1]]); // Adiciona o caractere cifrado correspondente ao segundo caractere, deslocado uma posição para baixo
            } else { // Diferente linha e coluna
                cipherText.append(matrix[pos1[0]][pos2[1]]); // Adiciona o caractere cifrado correspondente ao primeiro caractere, trocando a coluna com a do segundo caractere
                cipherText.append(matrix[pos2[0]][pos1[1]]); // Adiciona o caractere cifrado correspondente ao segundo caractere, trocando a coluna com a do primeiro caractere
            }
        }

        return cipherText.toString(); // Retorna o texto cifrado como uma String
    }

    public String decrypt(String cipherText) {
        String formattedCipherText = formatarTexto(cipherText); // Formata o texto cifrado
        String[] bigrams = bigramas(formattedCipherText); // Divide o texto formatado em bigramas
        StringBuilder textoClaro = new StringBuilder(); // Cria um StringBuilder para armazenar o texto decifrado

        for (String bigram : bigrams) { // Itera sobre os bigramas do texto formatado
            char c1 = bigram.charAt(0); // Obtém o primeiro caractere do bigrama
            char c2 = bigram.charAt(1); // Obtém o segundo caractere do bigrama
            int[] pos1 = getCharPosition(c1); // Obtém a posição do primeiro caractere na matriz
            int[] pos2 = getCharPosition(c2); // Obtém a posição do segundo caractere na matriz

            if (pos1[0] == pos2[0]) { // Mesma linha
                textoClaro.append(matrix[pos1[0]][(pos1[1] + 4) % 5]); // Adiciona o caractere decifrado correspondente ao primeiro caractere, deslocado quatro posições para a esquerda (considerando o wrap-around)
                textoClaro.append(matrix[pos2[0]][(pos2[1] + 4) % 5]); // Adiciona o caractere decifrado correspondente ao segundo caractere, deslocado quatro posições para a esquerda
            } else if (pos1[1] == pos2[1]) { // Mesma coluna
                textoClaro.append(matrix[(pos1[0] + 4) % 5][pos1[1]]); // Adiciona o caractere decifrado correspondente ao primeiro caractere, deslocado quatro posições para cima (considerando o wrap-around)
                textoClaro.append(matrix[(pos2[0] + 4) % 5][pos2[1]]); // Adiciona o caractere decifrado correspondente ao segundo caractere, deslocado quatro posições para cima
            } else { // Diferente linha e coluna
                textoClaro.append(matrix[pos1[0]][pos2[1]]); // Adiciona o caractere decifrado correspondente ao primeiro caractere, trocando a coluna com a do segundo caractere
                textoClaro.append(matrix[pos2[0]][pos1[1]]); // Adiciona o caractere decifrado correspondente ao segundo caractere, trocando a coluna com a do primeiro caractere
            }
        }

        return textoClaro.toString(); // Retorna o texto decifrado como uma String
    }

//Funcao para imprimir  matriz

    public void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}