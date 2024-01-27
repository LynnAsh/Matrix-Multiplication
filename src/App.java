import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static void fillMatrix(int[][] arr, int rows){
        System.out.println("Filling matrix.");
        Random rand = new Random();
        for(int i=0;i<rows;i++){
            for(int j=0;j<rows;j++){
                arr[i][j] = rand.nextInt(10);
            }
        }
    }


    public static void writeToFile(int[][] arr, int rows, String file) throws IOException{
        System.out.println("Writing matrix to file.");
        FileWriter writer = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(writer);

        bw.write("%d %d\n".formatted(arr.length,arr[0].length));
        for(int i=0;i<rows;i++){
            for(int k=0;k<rows;k++){
                bw.write("%d ".formatted(arr[i][k]));
            }
            if(i != arr.length-1){
                bw.write("\n");
            }
        }

        bw.close();
    }
    

    public static int[][] readFromFile(String file) throws IOException{
        int rows = 0;
        int cols = 0;
        int[][] mat = new int[rows][cols];

        try{
            System.out.println("Reading matrix.");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while((line = reader.readLine()) != null){
                String[] elements = line.split(" ");
                cols = elements.length;
                rows++;
            }
            mat = new int[rows][cols];

            reader.close();
            reader = new BufferedReader(new FileReader(file));

            int row = 0;
            while((line = reader.readLine()) != null){
                String[] elements = line.split(" ");
                for(int col=0;col<cols;col++){
                    mat[row][col] = Integer.parseInt(elements[col]);
                }
                row++;
            }

            reader.close();
        } catch(Exception e){
            System.out.println("Error in reading files.");
        }

        return mat;
    }
    

    public static void printMatrix(int[][] arr, String matrix){
        System.out.println("----------\n" + matrix);
        int rows = arr.length;
        int cols = arr[0].length;
        for(int i=0;i<rows;i++){
            System.out.print("[ ");
            for(int k=0;k<cols;k++){
                System.out.print(arr[i][k] + " ");
            }
            System.out.print("]\n");
        }
    }


    public static void multiplyMatrices(int[][] mat1, int[][] mat2, int[][] mat3){
        int rows1 = mat1.length;
        int cols1 = mat1[0].length;
        int cols2 = mat2[0].length;

        try{
            for(int row=0;row<rows1;row++){
                for(int col=0;col<cols2;col++){
                    mat3[row][col] = 0;
                    for(int i=0;i<cols1;i++){
                        mat3[row][col] += mat1[row][i] * mat2[i][col];
                    }
                }
            }
        } catch(Exception e){
            System.out.println("Error in multiplying.");
        }
    }


    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        String usrIn;
        int[][] mat1, mat2, mat3;

        System.out.println("Hello.\nPlease enter file names or\nenter sizes of matrices.\n----------");

        //checks if input is an integer or string
        if(scnr.hasNextInt()){
            usrIn = scnr.nextLine();

            //sets matrices size to the inputed integer and fills them randomly
            int rows = Integer.parseInt(usrIn);
            mat1 = new int[rows][rows];
            mat2 = new int[rows][rows];
            mat3 = new int[rows][rows];

            fillMatrix(mat1,rows);
            fillMatrix(mat2,rows);
            printMatrix(mat1, "Matrix 1");
            printMatrix(mat2, "Matrix 2");
            writeToFile(mat1, rows, "matrix1.txt");
            writeToFile(mat2, rows, "matrix2.txt");

            //multiplies mat1 and mat2 to mat3
            multiplyMatrices(mat1, mat2, mat3);

            printMatrix(mat3, "Matrix 3");
            writeToFile(mat3,rows,"matrix3.txt");
        }else{
            usrIn = scnr.nextLine();

            //splits file names into two seperate strings
            String[] files = usrIn.split(" ");
            String file1 = files[0];
            String file2 = files[1];
            
            mat1 = readFromFile(file1);
            mat2 = readFromFile(file2);
            mat3 = new int[mat1.length][mat2[0].length];
            printMatrix(mat1, "Matrix 1");
            printMatrix(mat2, "Matrix 2");

            multiplyMatrices(mat1, mat2, mat3);

            printMatrix(mat3, "Matrix 3");
            writeToFile(mat3, mat3.length, "matrix3.txt");
        }

        scnr.close();
        System.out.println("Done.");
    }
}
