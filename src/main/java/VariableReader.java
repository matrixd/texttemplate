import TextTemplateLib.Variable;

/**
 * Created by ulanov on 17.01.17.
 */
public class VariableReader {
    public static Variable read(Variable v, String value) {
        v.setValue(value);
        return v;
    }

    public static Variable print(Variable v) {
        System.out.print(v.getName() + " = ");
        return v;
    }
}
