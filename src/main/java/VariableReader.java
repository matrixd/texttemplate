import TextTemplateLib.Variable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ulanov on 17.01.17.
 */
public class VariableReader {
    public static Variable set(Variable v, String value) {
        System.out.println("read");
        v.setValue(value);
        return v;
    }

    public static String read() throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        return bfr.readLine();
    }

    public static Variable print(Variable v) {
        System.out.print(v.getName() + " = ");
        return v;
    }
}
