package main;
import java.io.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonParseException;
import data.Ticket;


public class FileHelper {
    private String fileName;
    private Gson gson = new Gson();

    public FileHelper(String fileName) {
        this.fileName = fileName;
    }

    public static String read(BufferedInputStream bufferedInputStream) throws IOException {
        String string;
        int i;
        ArrayList<String> a = new ArrayList<String>();
        while ((i = bufferedInputStream.read()) != -1) {
            char j = (char) i;
            String s = String.valueOf(new char[]{j});
            a.add(s);
        }
        System.out.println("a");
        return a.toString();
    }

    public HashSet<Ticket> readCollectionFromFile() throws IOException {
        System.out.println("a");
        if (System.getenv().get(fileName) != null) {
            System.out.println("a");
            try (FileInputStream fileInputStream = new FileInputStream(System.getenv().get(fileName))) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                Type collectionType = new TypeToken<HashSet<Ticket>>() {}.getType();
                HashSet<Ticket> collection = gson.fromJson(read(bufferedInputStream), collectionType);
                return collection;
            }
        } else return new HashSet<Ticket>();
    }
}
