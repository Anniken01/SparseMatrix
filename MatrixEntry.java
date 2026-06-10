//package CSCI1933P3-Meyers;
//Written by Laura Stephenson(steph933) and Anniken Rabben(rabbe014)
public class MatrixEntry {
    private int row;
    private int col;
    private int data;
    private MatrixEntry nextRow;
    private MatrixEntry nextCol;

    public MatrixEntry(int row, int col, int data){
        this.row = row;
        this.col = col;
        this.data = data;
        this.nextRow = null;
        this.nextCol = null;

    }
    public int getColumn(){
        return col;
    }
    public void setColumn(int col){
        this.col = col;
    }
    public int getRow(){
        return row;
    }
    public void setRow(int row){
        this.row = row;
    }
    public int getData(){
        return data;
    }
    public void setData(int data){
        this.data = data;
    }
    public MatrixEntry getNextRow(){
        return nextRow;
    }
    public void setNextRow(MatrixEntry el){
        this.nextRow = el;
    }
    public MatrixEntry getNextCol(){
        return nextCol;
    }
    public void setNextCol(MatrixEntry el){
        this.nextCol = el;
    }
}