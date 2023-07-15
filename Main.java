public class Main {
    public static void main(String[] args) {
        Spreadsheet spreadsheet = new Spreadsheet(3, 4);

        spreadsheet.setValueAt(0, 0, "Academy");
        spreadsheet.setValueAt(0, 1, "123");
        spreadsheet.setValueAt(1, 2, "3.14");

        Object value1 = spreadsheet.getValueAt(0, 0);
        Object value2 = spreadsheet.getValueAt(0, 1);
        Object value3 = spreadsheet.getValueAt(1, 2);

        System.out.println("Value at (0, 0): " + value1);
        System.out.println("Value at (0, 1): " + value2);
        System.out.println("Value at (1, 2): " + value3);

        spreadsheet.getCellAt(0, 1).toInt();
        spreadsheet.getCellAt(1, 2).toDouble();

        Object convertedValue2 = spreadsheet.getValueAt(0, 1);
        Object convertedValue3 = spreadsheet.getValueAt(1, 2);

        System.out.println("Converted value at (0, 1): " + convertedValue2);
        System.out.println("Converted value at (1, 2): " + convertedValue3);

        spreadsheet.setColorAt(0, 0, Color.RED);

        Color color = spreadsheet.getColorAt(0, 0);
        System.out.println("Color at (0, 0): " + color);

        spreadsheet.reset();

        double columnSum = spreadsheet.getColumnSum(1);
        System.out.println("Sum of column 1: " + columnSum);

        double rowAverage = spreadsheet.getRowAverage(2);
        System.out.println("Average of row 2: " + rowAverage);
    }
}
