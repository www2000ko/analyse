import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Main {
    private enum Result{HIGHER,LOWER,EQUAL}

    /*private String rules="E -> E '+' T | T\n" +
            "T -> T '*' F | F\n" +
            "F -> '(' E ')' | 'i'";*/
    HashMap<Character, Integer> map = new HashMap<Character, Integer>() {
        {
            put('+', 0);
            put('*', 1);
            put('i', 2);
            put('(', 3);
            put(')', 4);
            put('#', 5);
        }
    };

     Result matrix[][]=
            {
                    {Result.HIGHER,Result.LOWER,Result.LOWER,Result.LOWER,Result.HIGHER,Result.HIGHER},
                    {Result.HIGHER,Result.HIGHER,Result.LOWER,Result.LOWER,Result.HIGHER,Result.HIGHER},
                    {Result.HIGHER,Result.HIGHER,null,        null,      Result.HIGHER,Result.HIGHER},
                    {Result.LOWER ,Result.LOWER,Result.LOWER ,Result.LOWER,Result.EQUAL,Result.HIGHER},
                    {Result.HIGHER,Result.HIGHER,null       ,null          , Result.HIGHER,Result.HIGHER},
                    {Result.LOWER,Result.LOWER,Result.LOWER,Result.LOWER,Result.LOWER,Result.EQUAL},
            };
    private String stk;
    private String ch;
    private char stkTop,chTop;
    void analyze(String ch) {
        System.out.println(ch);
        stk="#";
        ch=ch+'#';
        stkTop=getTop(stk);
        chTop=getTop(ch);
        while(!(chTop=='#'&&stkTop=='#')){
            Result result=checkOP(stkTop,chTop);
            if(result==Result.HIGHER){
                reduction();
                stkTop=getTop(stk);
            }
            else if(result==Result.EQUAL){
                if(stkTop=='#'){
                    System.exit(0);
                }
                System.out.println("I"+chTop);
                stk=chTop+stk;
                ch=ch.substring(1);
                chTop=getTop(ch);
            }
            else if(result==Result.LOWER){
                System.out.println("I"+chTop);
                stk=chTop+stk;
                ch=ch.substring(1);
                stkTop=getTop(stk);
                chTop=getTop(ch);
            }
            else{
                System.out.println("E");
                System.exit(0);
            }
        }

    }

    private void reduction() {
        int i;
        char item = stk.charAt(0);
        if(item=='i'){
            stk=stk.substring(1);
            stk='N'+stk;
            System.out.println("R");
        }
        else if(stk.length()>=3&&item==')'&&stk.charAt(1)=='N'&&stk.charAt(2)=='('){
            stk=stk.substring(3);
            stk='N'+stk;
            System.out.println("R");
        }
        else if(stk.length()>=3&&item=='N'&&stk.charAt(1)=='*'&&stk.charAt(2)=='N') {
            stk=stk.substring(3);
            stk='N'+stk;
            System.out.println("R");
        }
        else if(stk.length()>=3&&item=='N'&&stk.charAt(1)=='+'&&stk.charAt(2)=='N'){
            stk=stk.substring(3);
            stk='N'+stk;
            System.out.println("R");
        }
        else{
            System.out.println("RE");
            System.exit(0);
        }
        /*for (i = 0; i <stk.length()-1; i++) {
            char item = stk.charAt(i);
            char last=stk.charAt(i+1);
            if(checkOP(last,item)!=Result.EQUAL){
                   break;
            }
        }
        stk=stk.substring(i+1);*/

    }
    private char getTop(String str){
        int i=0;
        while(str.charAt(i)=='N'){
            i++;
        }
        return str.charAt(i);
    }
    private Result checkOP(char stk,char ch) {
        int vs=0,vc=0;
        if(map.containsKey(stk)){
            vs=map.get(stk);
        }

        if(map.containsKey(ch)){
            vc=map.get(ch);
        }

        return matrix[vs][vc];
    }

    public static void main(String[] args) throws Exception {
        String filepath=args[0];
        File file = new File(filepath);
        FileReader reader = new FileReader(file);
        int length = (int) file.length();
        char buf[] = new char[length+1];
        reader.read(buf);
        reader.close();
        new Main().analyze(new String(buf).substring(0,length-2));
    }
}
