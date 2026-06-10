//Written by Laura Stephenson(steph933) and Anniken Rabben(rabbe014)
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SparseIntMatrix {
    private int numRows;
    private int numCols;
    private String inputFile;
    private MatrixEntry head;

    private MatrixEntry[] cols;
    private MatrixEntry[] rows;

    public SparseIntMatrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.rows = new MatrixEntry[numRows];
        this.cols = new MatrixEntry[numCols];
    }

    public SparseIntMatrix(int numRows, int numCols, String inputFile) throws FileNotFoundException {
        this.numRows = numRows;
        this.numCols = numCols;
        this.inputFile = inputFile;
        this.rows = new MatrixEntry[numRows];
        this.cols = new MatrixEntry[numCols];
        File f = new File(inputFile);
        fileToSparseMatrix(f);
    }

    public SparseIntMatrix fileToSparseMatrix(File inputFile) {
        Scanner s;
        try {
            s = new Scanner(inputFile);
            while (s.hasNext()) {
                String str = s.nextLine();

                String[] line = str.split(",");
                if (line.length == 3) {
                    int row = Integer.parseInt(line[0]);
                    int col = Integer.parseInt(line[1]);
                    int data = Integer.parseInt(line[2]);
                    if (data != 0) {
                        setElement(row, col, data);
                    }
                } else return this;
            }
            s.close();

        } catch (Exception e) {
            System.out.println("Error: File not found!" + e.getMessage());
            System.exit(1);
        }
        return this;
    }

    //check if row and column are within bounds and make sure it's not null (return data) otherwise return 0
    public int getElement(int row, int col) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            return 0;
        }
        //traverse through matrix, returning the element at the given spot
        MatrixEntry current = rows[row];
        while (current != null) {
            if (current.getColumn() == col) {
                return current.getData();
            }
            current = current.getNextCol();
        }
        return 0;
    }

    //Source 1 used here
    public boolean setElement(int row, int col, int data) {
        if (row >= numRows || row < 0 || col >= numCols || col < 0) {
            return false;
        }

        if (data == 0) {
            return removeElement(row, col);
        }

        // Check if something already exists in that spot
        MatrixEntry curr = rows[row];
        while (curr != null) {
            if (curr.getColumn() == col) {
                curr.setData(data);
                return true;
            }
            curr = curr.getNextCol();
        }

        MatrixEntry newEntry = new MatrixEntry(row, col, data);

        // add newEntry to the beginning of the row
        newEntry.setNextCol(rows[row]);
        rows[row] = newEntry;

        // add newEntry to the beginning of the column
        newEntry.setNextRow(cols[col]);
        cols[col] = newEntry;

        return true;
    }

    public boolean removeElement(int row, int col) {
        //checking that input variables are in bounds
        if (row >= numRows || row < 0 || col >= numCols || col < 0) {
            return false;
        }
        MatrixEntry prevRow = null;
        MatrixEntry currRow = rows[row];
        //traverse matrix to find element
        while (currRow != null) {
            if (currRow.getColumn() == col) {
                if (prevRow == null) {
                    rows[row] = currRow.getNextCol();
                } else {
                    prevRow.setNextCol(currRow.getNextCol());
                }
                return true;
            }
            //updating pointers
            prevRow = currRow;
            currRow = currRow.getNextCol();
            //source 2 used here
        }
        MatrixEntry prevCol = null;
        MatrixEntry currCol = cols[col];
        while (currCol != null) {
            if (currCol.getRow() == row) {
                if (prevCol == null) {
                    cols[col] = currCol.getNextRow();
                } else {
                    prevCol.setNextRow(currCol.getNextRow());
                }
                return true;
            }
            prevCol = currCol;
            currCol = currCol.getNextRow();
        }
        return false;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumRows() {
        return numRows;
    }

    public boolean plus(SparseIntMatrix otherMat) {
        // Checking that the matricies are the same size
        if (this.numRows != otherMat.numRows || this.numCols != otherMat.numCols) {
            return false;
        }
        //looping through other matrix, finding the corresponding element of
        //this matrix and adding the values, updating This.
        for (int row = 0; row < numRows; row++) {
            MatrixEntry otherCurr = otherMat.rows[row];
            while (otherCurr != null) {
                int col = otherCurr.getColumn();
                int data = otherCurr.getData();
                int thisData = this.getElement(row, col);

                this.setElement(row, col, thisData + data);
                otherCurr = otherCurr.getNextCol();
            }
        }

        return true;
    }

    public boolean minus(SparseIntMatrix otherMat) {
        // Checking that the matricies are the same size
        if (this.numRows != otherMat.numRows || this.numCols != otherMat.numCols) {
            return false;
        }
        //looping through other matrix, finding the corresponding element of
        //this matrix and subtracting the values, updating This.
        for (int row = 0; row < numRows; row++) {
            MatrixEntry otherCurr = otherMat.rows[row];
            while (otherCurr != null) {
                int col = otherCurr.getColumn();
                int data = otherCurr.getData();
                int thisData = this.getElement(row, col);

                this.setElement(row, col, thisData - data);
                otherCurr = otherCurr.getNextCol();
            }
        }
        return true;
    }
}