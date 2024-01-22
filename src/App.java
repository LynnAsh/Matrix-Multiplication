import java.util.Scanner;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static void fillMatrix(int[][] arr,int rows){
        System.out.println("Filling matrix.");
        Random rand = new Random();
        for(int i=0;i<rows;i++){
            for(int j=0;j<rows;j++){
                arr[i][j] = rand.nextInt(10);
            }
        }
    }

    public static void writeToFile(int[][] arr,int rows,String file) throws IOException{
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

    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        String usrIn;
        int[][] mat1,mat2,mat3;

        System.out.println("Hello.\nPlease enter file names or\nenter sizes of matrices.\n----------");
        if(scnr.hasNextInt()){ //checks if input is integer
            usrIn = scnr.nextLine();

            int rows = Integer.parseInt(usrIn);
            mat1 = new int[rows][rows];
            mat2 = new int[rows][rows];
            mat3 = new int[rows][rows];
            fillMatrix(mat1,rows);
            fillMatrix(mat2,rows);

            //multiplies mat1 and mat2 to mat3
            for(int i=0;i<rows;i++){
                for(int j=0;j<rows;j++){
                    mat3[i][j] = 0;
                    for(int k=0;k<rows;k++){
                        mat3[i][j] += mat1[i][k] * mat2[k][j];
                    }
                }
            }

            writeToFile(mat3,rows,"matrix3.txt");
        }else{
            usrIn = scnr.nextLine();
        }

        scnr.close();
        System.out.println("Done.");
    }
}
