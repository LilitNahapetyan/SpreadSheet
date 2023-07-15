import java.util.Arrays;

/*
 
   - Methods:
 
     - `setValueAt(int, int, Object)`: Sets the value of the cell at the specified row and column.
     - `getValueAt(int, int) : Object`: Returns the value stored in the cell at the specified row and column.
     - `setColorAt(int, int, Color) : void`: Sets the color of the cell at the specified row and column.
     - `getColorAt(int, int) : Color`: Returns the color of the cell at the specified row and column.
     - `reset(): void`: Resets all cells in the spreadsheet.
     - `resetCellAt(int, int): void`: Resets the value and color of the cell at the specified row and column.
     - `getColumnSum(int) : int or double`: Returns the sum of the values in the specified column.
     - `getRowSum(int) : int or double`: Returns the sum of the values in the specified row.
     - `getAreaSum(int, int, int, int) : int or double`: Returns the sum of the values in the specified area of the spreadsheet.
     - `getColumnAverage(int) : int or double`: Returns the average of the values in the specified column.
     - `getRowAverage(int) : int or double`: Returns the average of the values in the specified row.
     - `getAreaAverage(int, int, int, int) : int or double`: Returns the average of the values in the specified area of the spreadsheet.
 */

class Spreadsheet {

    private Cell[][] cells;
    private int column;
    private int row;

    public Spreadsheet(int column, int row) {
        this.column = column;
        this.row = row;
        this.cells = new Cell[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private boolean isValidCell(int row, int column) {
        return row >= 0 && row < this.row && column >= 0 && column < this.column;
    }

    public void addColumn(int newColumnIndex) {
        if (newColumnIndex < 0 || newColumnIndex > column) {
            throw new IllegalArgumentException("Invalid column position.");
        }

        Cell[][] newCells = new Cell[row][column + 1];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j <= column; j++) {
                if (j < newColumnIndex) {
                    newCells[i][j] = cells[i][j];
                } else if (j > newColumnIndex) {
                    newCells[i][j] = cells[i][j - 1];
                } else {
                    newCells[i][j] = new Cell();
                }
            }
        }
        cells = newCells;
        column++;
    }

    public void addRow(int newRowIndex) {
        if (newRowIndex < 0 || newRowIndex > row) {
            throw new IllegalArgumentException("Invalid row position.");
        }

        Cell[][] newCells = new Cell[row + 1][column];
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j < column; j++) {
                if (i < newRowIndex) {
                    newCells[i][j] = cells[i][j];
                } else if (i > newRowIndex) {
                    newCells[i][j] = cells[i - 1][j];
                } else {
                    newCells[i][j] = new Cell();
                }
            }
        }
        cells = newCells;
        row++;
    }

    
    public void setValueAt(int row, int column, Object value) {
        if (!isValidCell(row, column)) {
            throw new IllegalArgumentException("Invalid cell position.");
        }

        cells[row][column].setValue(value);
    }

    public Object getValueAt(int row, int column) {
        if (!isValidCell(row, column)) {
            throw new IllegalArgumentException("Invalid cell position.");
        }

        return cells[row][column].getValue();
    }

    public void setColorAt(int row, int column, Color color) {
        if (!isValidCell(row, column)) {
            throw new IllegalArgumentException("Invalid cell position.");
        }

        cells[row][column].setColor(color);
    }

    public Color getColorAt(int row, int column) {
        if (!isValidCell(row, column)) {
            throw new IllegalArgumentException("Invalid cell position.");
        }

        return cells[row][column].getColor();
    }

    public Cell getCellAt(int row, int column) {
        if (!isValidCell(row, column)) {
            throw new IllegalArgumentException("Invalid cell position.");
        }

        return cells[row][column];
    }

    public void reset() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                cells[i][j].reset();
            }
        }
    }

    public void resetCellAt(int row, int column) {
        if (!isValidCell(row, column)) {
            throw new IllegalArgumentException("Invalid cell position.");
        }

        cells[row][column].reset();
    }


    public double getColumnSum(int column) {
        if (column < 0 || column >= this.column) {
            throw new IllegalArgumentException("Invalid column index.");
        }

        double sum = 0.0;
        for (int i = 0; i < row; i++) {
            Object value = cells[i][column].getValue();
            if (value instanceof Number) {
                sum += ((Number) value).doubleValue();
            }
        }
        return sum;
    }

    public double getRowSum(int row) {
        if (row < 0 || row >= this.row) {
            throw new IllegalArgumentException("Invalid row index.");
        }

        double sum = 0.0;
        for (int j = 0; j < column; j++) {
            Object value = cells[row][j].getValue();
            if (value instanceof Number) {
                sum += ((Number) value).doubleValue();
            }
        }
        return sum;
    }

    public double getAreaSum(
            int startRow,
            int startColumn,
            int endRow,
            int endColumn) {
        if (startRow < 0 ||
                startRow >= row ||
                startColumn < 0 ||
                startColumn >= column ||
                endRow < 0 ||
                endRow >= row ||
                endColumn < 0 ||
                endColumn >= column ||
                startRow > endRow ||
                startColumn > endColumn) {
            throw new IllegalArgumentException("Invalid area boundaries.");
        }

        double sum = 0.0;
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startColumn; j <= endColumn; j++) {
                Object value = cells[i][j].getValue();
                if (value instanceof Number) {
                    sum += ((Number) value).doubleValue();
                }
            }
        }
        return sum;
    }

    public double getColumnAverage(int col) {
        if (col < 0 || col >= column) {
            throw new IllegalArgumentException("Invalid column index.");
        }

        return getColumnSum(col) / row;
    }

    public double getRowAverage(int r) {
        if (r < 0 || r >= row) {
            throw new IllegalArgumentException("Invalid row index.");
        }

        return getRowSum(r) / column;
    }

    public double getAreaAverage(
            int startRow,
            int startColumn,
            int endRow,
            int endColumn) {
        if (startRow < 0 ||
                startRow >= row ||
                startColumn < 0 ||
                startColumn >= column ||
                endRow < 0 ||
                endRow >= row ||
                endColumn < 0 ||
                endColumn >= column ||
                startRow > endRow ||
                startColumn > endColumn) {
            throw new IllegalArgumentException("Invalid area boundaries.");
        }

        double sum = getAreaSum(startRow, startColumn, endRow, endColumn);
        int areaSize = (endRow - startRow + 1) * (endColumn - startColumn + 1);
        return sum / areaSize;
    }

    public void swapRows(int row1, int row2) {
        if (row1 < 0 || row1 >= row || row2 < 0 || row2 >= row) {
            throw new IllegalArgumentException("Invalid row index.");
        }

        Cell[] tempRow = cells[row1];
        cells[row1] = cells[row2];
        cells[row2] = tempRow;
    }

    public void swapColumns(int col1, int col2) {
        if (col1 < 0 || col1 >= column || col2 < 0 || col2 >= column) {
            throw new IllegalArgumentException("Invalid column index.");
        }
        for (int i = 0; i < row; i++) {
            Cell tempCell = cells[i][col1];
            cells[i][col1] = cells[i][col2];
            cells[i][col2] = tempCell;
        }
    }

    public void removeColumn(int col) {
        if (col < 0 || col >= column) {
            throw new IllegalArgumentException("Invalid column index.");
        }

        Cell[][] newCells = new Cell[row][column - 1];

        for (int i = 0; i < row; i++) {
            int newColumn = 0;
            for (int j = 0; j < column; j++) {
                if (j != col) {
                    newCells[i][newColumn] = cells[i][j];
                    newColumn++;
                }
            }
        }

        cells = newCells;
        column--;
    }

    public void removeRow(int r) {
        if (r < 0 || r >= row) {
            throw new IllegalArgumentException("Invalid row index.");
        }

        Cell[][] newCells = new Cell[row - 1][column];
        int newRow = 0;

        for (int i = 0; i < row; i++) {
            if (i != r) {
                newCells[newRow] = cells[i];
                newRow++;
            }
        }

        cells = newCells;
        row--;
    }

}
