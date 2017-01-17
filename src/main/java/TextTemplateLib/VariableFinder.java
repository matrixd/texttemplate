package TextTemplateLib;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ulanov on 17.01.17.
 */
public class VariableFinder {
    public static Observable<Variable> findVariables(final String input) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Matcher matcher = varPattern.matcher(input);
                System.out.println("matcher");
                while (matcher.find()) {
                    System.out.println("found " + matcher.group(1));
                    e.onNext(matcher.group(1));
                }

                e.onComplete();
            }
        }).distinct()
          .map(Variable::new);
    }

    static final Pattern varPattern = Pattern.compile("\\$([a-zA-Z0-9]+)");
}
