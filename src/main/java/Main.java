import TextTemplateLib.Variable;
import TextTemplateLib.VariableFinder;
import TextTemplateLib.VariableReplacer;
import com.beust.jcommander.JCommander;
import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableCache;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ulanov on 17.01.17.
 */
public class Main {
    public static void main(String[] args) {

        Observable<String> input = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    String s = bfr.readLine();
                    if (s.startsWith("$$$END"))
                        break;
                    e.onNext(s);
                }
                e.onComplete();
            }
        });

        Observable<String> text = input.scan((String res, String s) -> { return res + "\n" + s; })
                .takeLast(1).cache();

        text.map(VariableFinder::findVariables)
                .flatMap((Observable<Variable> o) -> { return o; })
                .map((Variable var) -> {
                    VariableReader.print(var);
                    return VariableReader.set(var, VariableReader.read());
                })
                //TODO make an operator BufferAll or smth like that
                .buffer(99999)
                .zipWith(text.repeat(), VariableReplacer::replaceAll)
                .subscribe(System.out::println);
    }
}
