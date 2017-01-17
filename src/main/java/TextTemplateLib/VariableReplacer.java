package TextTemplateLib;

import java.util.regex.Pattern;

/**
 * Created by ulanov on 17.01.17.
 */
public class VariableReplacer {
    public static String replace(Variable var, String text) {
        return text.replaceAll("$" + var.mName, var.getValue());
    }
}
