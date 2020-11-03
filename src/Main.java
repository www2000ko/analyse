import java.io.File;
import java.io.FileReader;
public class Main {
    private String rules="E -> E '+' T | T\n" +
            "T -> T '*' F | F\n" +
            "F -> '(' E ')' | 'i'";

    void analyze(char[] args) {
        System.out.println(rules);
    }

    public static void main(String[] args) throws Exception {
        String filepath=args[0];
        File file = new File(filepath);
        FileReader reader = new FileReader(file);
        int length = (int) file.length();
        char buf[] = new char[length+1];
        reader.read(buf);
        reader.close();
        new Main().analyze(buf);
    }
}
