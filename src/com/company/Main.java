package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        System.out.println("Введите N");
        Scanner in = new Scanner(System.in);
        int nValue = in.nextInt();
        System.out.println("Веедите M");
        int mValue = in.nextInt();
        String line;
        int result = 0;
        int ura=0;
        int price = 0;
        boolean overlay = true;
        int finalX = 0;
        int finalY = 0;



        char[][] matrix = new char[3*nValue+2][3*mValue+2];


        for (char[] row : matrix)
            Arrays.fill(row, '.');

        try {
            BufferedReader br = new BufferedReader(new FileReader("test"));

//            считывание дома
        for (int i = 1; i < nValue + 1; i++) {
            line = br.readLine();
            for (int j = 1; j < line.length() + 1; j++) {
                matrix[i][j] = line.charAt(j-1);
            }
        }

//        считывание озера
        for (int i = nValue + 1; i < 2*nValue + 1; i++) {
            line = br.readLine();
            for (int j = mValue + 1, k = 0; j < line.length() + mValue + 1; j++, k++) {
                matrix[i][j] = line.charAt(k);
            }
        }


//      Начало наложения дома на озеро и общую карту
            for (int xField = 0; xField < nValue*2; xField++) {
                for (int yField = 0; yField < mValue*2; yField++) {
                    overlay =true;
                    price=0;
//                    Если дом накладывается на озеро, считаем максимальную длину наложения
                    for (int i = 0; i < nValue; i++) {
                        for (int j = 0; j < mValue; j++) {
                            if ((matrix[i+1][j+1] == 'H' && matrix[xField+i+1][yField+j+1] != 'W')
                            || matrix[i+1][j+1] == '.') {
                                if (matrix[i+1][j+1] == 'H' && matrix[xField+i+1][yField+j+2] =='W') price++;
                                if (matrix[i+1][j+1] == 'H' && matrix[xField+i+1][yField+j] =='W') price++;
                                if (matrix[i+1][j+1] == 'H' && matrix[xField+i+2][yField+j+1] =='W') price++;
                                if (matrix[i+1][j+1] == 'H' && matrix[xField+i][yField+j+1] =='W') price++;
                            } else {
                                overlay = false;
                            }
                        }
                    }
                    if (overlay) {
                        ura++;
                        if (price > result) {
                            finalX = xField;
                            finalY = yField;
                        }
                        result = Math.max(result,price);
                    }
                }
            }


        } catch (Exception e) {
            System.out.println("file error");
        }



//        распечатка всей карты до передвижения дома
        for (int i = 0; i < 3*nValue + 2; i++) {
            for (int j = 0; j < matrix[nValue].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


//        передвижение дома
        for (int i = 1; i < nValue+1; i++) {
            for (int j = 1; j < mValue+1; j++) {
                if (matrix[finalX+i][finalY+j] != 'W') matrix[finalX+i][finalY+j] = matrix[i][j];
                matrix[i][j] = '.';
            }
        }

//        Распечатка после передвижения дома
        for (int i = 0; i < 3*nValue + 2; i++) {
            for (int j = 0; j < matrix[nValue].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

        System.out.println("result = " + result);
    }
}
