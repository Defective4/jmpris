package io.github.defective4.linux.jmpris.model;

public class DBusNumber extends DBusObject {
    private final Number number;

    public DBusNumber(Number number) {
        this.number = number;
    }

    public byte byteValue() {
        return number.byteValue();
    }

    public double doubleValue() {
        return number.doubleValue();
    }

    public float floatValue() {
        return number.floatValue();
    }

    public int intValue() {
        return number.intValue();
    }

    public long longValue() {
        return number.longValue();
    }

    public short shortValue() {
        return number.shortValue();
    }

    @Override
    public String toString() {
        return number.toString();
    }

}
