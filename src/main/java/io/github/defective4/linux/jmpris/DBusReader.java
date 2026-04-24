package io.github.defective4.linux.jmpris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import io.github.defective4.linux.jmpris.model.DBusArray;
import io.github.defective4.linux.jmpris.model.DBusDictionary;
import io.github.defective4.linux.jmpris.model.DBusNumber;
import io.github.defective4.linux.jmpris.model.DBusObject;
import io.github.defective4.linux.jmpris.model.DBusString;

public class DBusReader extends BufferedReader {

    private boolean signalReceived = false;

    public DBusReader(Reader in) {
        super(in);
    }

    public DBusObject readObject() throws IOException {
        Stack<List<Object>> arrays = new Stack<>();
        List<Object> root = new ArrayList<>();
        root.add(false);
        while (true) {
            String line = readLine();
            if (line == null) return null;
            line = line.trim();

            if (line.startsWith("signal")) {
                if(signalReceived) {
                    return map(root);
                }
                signalReceived = true;
                continue;
            }
            if (!signalReceived) {
                continue;
            }

            if (line.startsWith("variant")) {
                line = line.substring("variant".length()).trim();
            }

            if (line.startsWith("string") && countCharacters(line, '"') >= 2) {
                String value = line.substring(line.indexOf('"') + 1, line.lastIndexOf('"'));
                if (!arrays.isEmpty())
                    arrays.peek().add(value);
                else
                    root.add(value);
            }

            if (line.startsWith("int64 ")) {
                long value = Long.parseLong(line.substring("int64 ".length(), line.length()));
                if (!arrays.isEmpty())
                    arrays.peek().add(value);
                else
                    root.add(value);
            }

            if (line.startsWith("double ")) {
                double value = Double.parseDouble(line.substring("int64 ".length(), line.length()));
                if (!arrays.isEmpty())
                    arrays.peek().add(value);
                else
                    root.add(value);
            }

            boolean isArray = line.startsWith("array");
            boolean isDict = line.startsWith("dict entry");
            if (isArray || isDict) {
                ArrayList<Object> list = new ArrayList<>();
                arrays.push(list);
                list.add(isDict);
            }

            if ((line.startsWith("]") || line.startsWith(")")) && !arrays.isEmpty()) {
                List<Object> array = arrays.pop();
                if (!arrays.isEmpty())
                    arrays.peek().add(array);
                else
                    root.add(array);

                if (arrays.isEmpty()) {
                    signalReceived = false;
                    return map(root);
                }
            }
        }

    }

    private static int countCharacters(String str, char c) {
        return (int) str.chars().filter(c2 -> c2 == c).count();
    }

    private static DBusObject map(List<Object> list) {
        if (!list.isEmpty() && list.get(0) instanceof Boolean isDict) {
            if (isDict) {
                String key = null;
                DBusObject value = null;
                if (list.size() > 1 && list.get(1) instanceof String k) key = k;
                if (list.size() > 2) value = map(list.get(2));
                return new DBusDictionary(key, value);
            }
            List<DBusObject> array = new ArrayList<>();
            for (int i = 1; i < list.size(); i++) {
                array.add(map(list.get(i)));
            }
            return new DBusArray(array);
        }
        return new DBusArray();
    }

    private static DBusObject map(Object obj) {
        if (obj instanceof String str) return new DBusString(str);
        if (obj instanceof List<?> ls) return map((List<Object>) ls);
        if (obj instanceof Number num) return new DBusNumber(num);
        return new DBusArray();
    }
}
