## VERY HARD TASK IF YOU DONT KNOW LANG!
Class Car to XML, JSON and CSV by zero without libs!
> but I dev it!

Class AUTO
```java
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
 ```
  Class JSONAuto
  ```java
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
```
