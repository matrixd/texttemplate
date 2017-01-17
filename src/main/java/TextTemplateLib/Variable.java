package TextTemplateLib;

/**
 * Created by ulanov on 17.01.17.
 */
public class Variable {
    public Variable(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        this.mValue = value;
    }

    String mValue = "";
    final String mName;
}
