package TextTemplateLib;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by ulanov on 17.01.17.
 */
public class VariableReplacer {
    public static String replace(Variable var, String text) {
        return text.replaceAll("\\$" + var.mName, var.getValue());
    }

    public static String replaceAll(List<Variable> vars, String text) {
        for (int i = 0; i < vars.size(); i++)
            text = replace(vars.get(i), text);

        return text;
    }
}
