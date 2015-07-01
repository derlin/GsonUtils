# GsonUtils
A java .jar easing the use of the Gson library. The class `GsonUtils` offers static methods to quickly:
* load an object from a json file
* serialize an object into a string
* serialize an object into a file
* exclude fields from the serialization with the `@DoNotSerialize` annotation.

# How to use

Add the jar to the classpath.

To serialize __an object into a file__, you two methods, taking either a path to a file or the file itself. For example, 
if you have an object, you can do:

```java
MyObject myObject = new MyObject();
GsonUtils.writeToFile("/path/to/a/file.json", myObject, true);
```

The boolean parameter determines if the output should be prettyprinted or not.

If your object is a collection or another generic object, you can use the `TypeToken` class, like that:

```java
Map<String,MyObject> map = new HashMap<>();
GsonUtils.writeToFile("/path/to/a/file.json", new TypeToken<HashMap<String,MyObject>>(){}, true);
```

To deserialize a __json file to an object__, the principle is the same:

```java
MyObject myObject =v (MyObject) GsonUtils.getFromFile("/path/to/a/file.json", new MyObject());
Map<String,MyObject> map = (HashMap<String,MyObject>)
    GsonUtils.getFromFile("/path/to/a/file.json", new TypeToken<HashMap<String,MyObject>>(){});
```

Finally, you can serialize __an object into a string__. The method `dump` serialize every field, the method `toJson` will 
exclude any field annotated with `@DoNotSerialize`.
