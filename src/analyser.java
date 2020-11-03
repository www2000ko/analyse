public class analyser {
    String rules="E -> E '+' T | T\n" +
            "T -> T '*' F | F\n" +
            "F -> '(' E ')' | 'i'";

    public analyser() {

    }

    public static void main(String[] args) {
        analyser op=new analyser();
        System.out.println(op.rules);
    }
}
