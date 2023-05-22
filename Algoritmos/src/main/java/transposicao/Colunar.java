package transposicao;

import java.util.Scanner;

public class Colunar {

    private static Scanner in;

    public static void main(String[] args){
        System.out.println("Tarnsposicao Colunar");
        in = new Scanner(System.in);

        System.out.print("1. Enciptar\n2. Decriptar\nEscolha(1,2): ");
        int choice = in.nextInt();
        in.nextLine();

        if (choice == 1){
            System.out.println("Enciptar");
            Enciptar();

        } else if (choice == 2){
            System.out.println("Decriptar");
            Decriptar();

        } else {
            System.out.println("Escolha invalida");
            System.exit(0);
        }
    } // main method

    private static void Enciptar(){
        System.out.print("Insira a menssagem: ");
        String plainText = in.nextLine().toUpperCase().replace(" ", "");
        StringBuilder msg = new StringBuilder(plainText);

        System.out.print("Insira a chave: ");
        String keyword = in.nextLine().toUpperCase();

        // assigning numbers to keywords
        int[] kywrdNumList = keywordNumAssign(keyword);

        // printing keyword
        for (int i = 0, j = 1; i < keyword.length(); i++, j++) {
            System.out.print(keyword.substring(i, j) + " ");
        }
        System.out.println();

        for (int i: kywrdNumList){
            System.out.print(i + " ");

        }

        System.out.println();
        System.out.println("-------------------------");

        // in case characters don't fit the entire grid perfectly.
        int extraLetters = msg.length() % keyword.length();
        //System.out.println(extraLetters);
        int dummyCharacters = keyword.length() - extraLetters;
//        System.out.println(dummyCharacters);

        if (extraLetters != 0){
            for (int i = 0; i < dummyCharacters; i++) {
                msg.append(".");
            }
        }

        int numOfRows = msg.length() / keyword.length();

        // Converting message into a grid
        char[][] arr = new char[numOfRows][keyword.length()];

        int z = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                arr[i][j] = msg.charAt(z);
                z++;
            }

        }

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                System.out.print(arr[i][j] + " ");
            } // inner for loop
            System.out.println();
        } // for loop

        // cipher text generation
        StringBuilder cipherText = new StringBuilder();

        System.out.println();
        // getting locations of numbers
        String numLoc = getNumberLocation(keyword, kywrdNumList);
        System.out.println("Localizacao dos numeros: " + numLoc);
        System.out.println();

        for (int i = 0, k = 0; i < numOfRows; i++, k++) {
            int d;
            if (k == keyword.length()){
                break;
            } else {
                d = Character.getNumericValue(numLoc.charAt(k));
            }
            for (int j = 0; j < numOfRows; j++) {
                cipherText.append(arr[j][d]);
            }
        }

        System.out.println("Texto claro: " + cipherText);

    } // Enciptar method

    private static void Decriptar(){


        System.out.print("Isira a Menssagem: ");
        String msg = in.nextLine().toUpperCase().replace(" ", "");

        System.out.print("A chave: ");
        String keyword = in.nextLine().toUpperCase();

        int numOfRows = msg.length() / keyword.length();

        // array with dummy values
        char[][] arr = new char[numOfRows][keyword.length()];

        // assigning numbers to keywords
        int[] kywrdNumList = keywordNumAssign(keyword);

        String numLoc = getNumberLocation(keyword, kywrdNumList);

        for (int i = 0, k = 0; i < msg.length(); i++, k++) {
            int d = 0;
            if (k == keyword.length()){
                k = 0;
            } else {
                d = Character.getNumericValue(numLoc.charAt(k));
            }

            for (int j = 0; j < numOfRows; j++, i++) {
                arr[j][d] = msg.charAt(i);
            } // for loop
            --i;
        }



        StringBuilder plainText = new StringBuilder();

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                plainText.append(arr[i][j]);
            } // inner for loop
        } // for loop

        System.out.println("Texto: " + plainText);

    } // Decriptar function

    private static String getNumberLocation(String keyword, int[] kywrdNumList) {
        String numLoc = "";
        for (int i = 1; i < keyword.length() + 1; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                if (kywrdNumList[j] == i){
                    numLoc += j;
                }
            }
        }
        return numLoc;
    } // getting number location function

    private static int[] keywordNumAssign(String keyword) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] kywrdNumList = new int[keyword.length()];

        int init = 0;
        for (int i = 0; i < alpha.length(); i ++){
            for (int j = 0; j < keyword.length(); j++) {
                if (alpha.charAt(i) == keyword.charAt(j)){
                    init++;
                    kywrdNumList[j] = init;
                }
            } // inner for
        } // for
        return kywrdNumList;
    } // keyword number assignment function

} // class