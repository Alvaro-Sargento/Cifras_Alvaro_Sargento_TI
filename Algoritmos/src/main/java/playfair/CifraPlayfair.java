package playfair;

public class CifraPlayfair {
    private char[][] matrix;
    private String chave;

    public CifraPlayfair(String chave) {
        this.chave = chave;
        this.matrix = new char[5][5];
        criarMatrix();
    }

    private void criarMatrix() {
        chave = chave.replaceAll("j", "i").replaceAll("\\s", "");
        String alfabeto = "abcdefghiklmnopqrstuvwxyz";
        String chaveAlfabeto = chave + alfabeto;

        // fill matrix with unique characters from chave and alfabeto
        for (int i = 0, row = 0, col = 0; i < chaveAlfabeto.length(); i++) {
            char c = chaveAlfabeto.charAt(i);
            if (!contem(matrix, c) && Character.isLetter(c)) {
                matrix[row][col] = c;
                col++;
                if (col == 5) {
                    row++;
                    col = 0;
                }
            }
        }
    }

    private boolean contem(char[][] arr, char c) {
        for (char[] row : arr) {
            for (char ch : row) {
                if (ch == c) {
                    return true;
                }
            }
        }
        return false;
    }

    private String formatarTexto(String textoClaro) {
        // remove non-letter characters and replace 'j' with 'i'
        return textoClaro.replaceAll("[^a-zA-Z]", "").replaceAll("j", "i");
    }

    private String[] bigramas(String formattedtextoClaro) {
        int n = formattedtextoClaro.length();
        int m = (n % 2 == 0) ? n / 2 : n / 2 + 1;
        String[] bigrams = new String[m];
        int i = 0;
        for (int j = 0; j < n - 1; j += 2) {
            bigrams[i] = formattedtextoClaro.substring(j, j + 2);
            i++;
        }
        if (n % 2 != 0) {
            bigrams[m - 1] = formattedtextoClaro.substring(n - 1) + "x";
        }
        return bigrams;
    }

    private int[] getCharPosition(char c) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (matrix[row][col] == c) {
                    return new int[] {row, col};
                }
            }
        }
        return null;
    }

    public String encrypt(String textoClaro) {
        String formattedtextoClaro = formatarTexto(textoClaro);
        String[] bigrams = bigramas(formattedtextoClaro);
        StringBuilder cipherText = new StringBuilder();
        for (String bigram : bigrams) {
            char c1 = bigram.charAt(0);
            char c2 = bigram.charAt(1);
            int[] pos1 = getCharPosition(c1);
            int[] pos2 = getCharPosition(c2);
            if (pos1[0] == pos2[0]) { // same row
                cipherText.append(matrix[pos1[0]][(pos1[1] + 1) % 5]);
                cipherText.append(matrix[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) { // same column
                cipherText.append(matrix[(pos1[0] + 1) % 5][pos1[1]]);

                cipherText.append(matrix[(pos2[0] + 1) % 5][pos2[1]]);
            } else { // different row and column
                cipherText.append(matrix[pos1[0]][pos2[1]]);
                cipherText.append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return cipherText.toString();
    }
    public String decrypt(String cipherText) {
        String formattedCipherText = formatarTexto(cipherText);
        String[] bigrams = bigramas(formattedCipherText);
        StringBuilder textoClaro = new StringBuilder();
        for (String bigram : bigrams) {
            char c1 = bigram.charAt(0);
            char c2 = bigram.charAt(1);
            int[] pos1 = getCharPosition(c1);
            int[] pos2 = getCharPosition(c2);
            if (pos1[0] == pos2[0]) { // same row
                textoClaro.append(matrix[pos1[0]][(pos1[1] + 4) % 5]);
                textoClaro.append(matrix[pos2[0]][(pos2[1] + 4) % 5]);
            } else if (pos1[1] == pos2[1]) { // same column
                textoClaro.append(matrix[(pos1[0] + 4) % 5][pos1[1]]);
                textoClaro.append(matrix[(pos2[0] + 4) % 5][pos2[1]]);
            } else { // different row and column
                textoClaro.append(matrix[pos1[0]][pos2[1]]);
                textoClaro.append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return textoClaro.toString();
    }

    public void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        CifraPlayfair cipher = new CifraPlayfair("playfair");
        cipher.printMatrix(); // imprime a matriz
        String ciphertext = cipher.decrypt("liqrpgkz");
        System.out.println(ciphertext);


    }


}
