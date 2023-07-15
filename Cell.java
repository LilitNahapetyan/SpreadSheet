import java.text.SimpleDateFormat;
import java.util.Date;

class Cell {
    private Object value;
    private Color color;
    private Type type;

    public Cell() {
        this.color = Color.WHITE;
        this.type = Type.STRING;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void reset() {
        this.value = null;
        this.color = Color.WHITE;
        this.type = Type.STRING;
    }

    public void toInt() {
        if (value instanceof String) {
            try {
                int intValue = Integer.parseInt((String) value);
                value = intValue;
            } catch (NumberFormatException e) {
                System.out.println("Can not convert value to integer.");
            }
        } else {
            System.out.println("Value is not a string.");
        }
    }

    public void toDouble() {
        if (value instanceof String) {
            try {
                double doubleValue = Double.parseDouble((String) value);
                value = doubleValue;
            } catch (NumberFormatException e) {
                System.out.println("Cannot convert value to double.");
            }
        } else {
            System.out.println("Value is not a string.");
        }
    }

     public void toDate() {
        if (value instanceof String) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse((String) value);
                value = date;
            } catch (Exception e) {
                System.out.println("Cannot convert value to date.");
            }
        } else {
            System.out.println("Value is not a string.");
        }
    }
}