//Written by Laura Stephenson(steph933) and Anniken Rabben(rabbe014)
import java.io.FileNotFoundException;

public class SparseIntMatrixTest {
    public static void main(String[] args) {
        try {
            //testing file 1
            SparseIntMatrix mat = new SparseIntMatrix(1000, 1000, "matrix1_data.txt");
            MatrixViewer.show(mat);

//            testing file 2
            SparseIntMatrix mat2 = new SparseIntMatrix(1000, 1000, "matrix2_data.txt");
            MatrixViewer.show(mat2);
//
//          //testing file 3
            SparseIntMatrix matNoise = new SparseIntMatrix(1000, 1000, "matrix2_noise.txt");
            mat2.minus(matNoise);
            MatrixViewer.show(mat2);


        }
        catch (FileNotFoundException f){
            System.out.println("File not found.");
        }
    }
}
