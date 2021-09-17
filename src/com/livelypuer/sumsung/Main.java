package com.livelypuer.sumsung;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, IOException {
        JSONAuto json = new JSONAuto();
        XMLAuto xml = new XMLAuto();
        CSVAuto csv = new CSVAuto();
        Auto auto = new Auto();
        System.out.println(json.transform(auto));
        System.out.println(xml.transform(auto));
        System.out.println(csv.transform(auto));
    }
}

class Auto {
    public int wheels = 4;
    public int doors = 5;
    public int RPM = 8000;
    public Float maxSpeed = 120.7f;
    public int sitPlace = 7;

    public float nowAngleWheel = 0;
    public float stopWheel = 50;

    public Float nowSpeed = 0f;
    private static final float a = 9f;

    public void Forward(float go) {
        nowSpeed += go * RPM * a;
    }

    public Float GetSpeed() {
        return nowSpeed;
    }

    public void Rotate(float turn) {
        nowAngleWheel += turn * a;
    }

}

class JSONAuto {
    public String transform(Auto auto) throws IllegalAccessException, IOException {
        HashMap<String, Object> json = new HashMap<>();
        for (Field field : auto.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            Object value = field.get(auto);
            if (value != null) {
                json.put(field.getName(), value);
            }
        }
        String s = "{";
        ArrayList<String> lst = new ArrayList<String>();
        for (Map.Entry entry : json.entrySet()) {
            lst.add("\"" + entry.getKey() + "\"" + ":" + entry.getValue());
        }

        s += String.join(",", lst) + "}";
        return s;
    }
}
class CSVAuto {
    public String transform(Auto auto) throws IllegalAccessException, IOException {
        HashMap<String, Object> json = new HashMap<>();
        for (Field field : auto.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            Object value = field.get(auto);
            if (value != null) {
                json.put(field.getName(), value);
            }
        }
        StringBuilder s = new StringBuilder();
        ArrayList<String> lstKeys = new ArrayList<String>();
        ArrayList<Object> lstValue = new ArrayList<Object>();
        for (Map.Entry entry : json.entrySet()) {
            s.append("\"").append(entry.getKey().toString()).append("\";");
            lstValue.add(entry.getValue());
        }
        s.append("\n");
        StringBuilder valStr = new StringBuilder();
        for (Object val : lstValue){
            valStr.append(val.toString()).append(';');
        }
        s.append(valStr);
        return s.toString();
    }
}

class XMLAuto {
    public String transform(Auto auto) throws IllegalAccessException, IOException {
        HashMap<String, Object> json = new HashMap<>();
        for (Field field : auto.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            Object value = field.get(auto);
            if (value != null) {
                json.put(field.getName(), value);
            }
        }
        String s = "<xml>";
        ArrayList<String> lst = new ArrayList<String>();
        for (Map.Entry entry : json.entrySet()) {
            lst.add("<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">");
        }

        s += String.join("", lst) + "</xml>";
        return s;
    }
}