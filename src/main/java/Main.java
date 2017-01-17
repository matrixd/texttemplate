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

/**
 * Created by ulanov on 17.01.17.
 */
public class Main {
    public static void main(String[] args) {

        Observable<String> input = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
                String s = "";
                while (!s.startsWith("superend")) {
                    s = bfr.readLine();
                    e.onNext(s);
                }

                e.onComplete();
            }
        });

        //Observable<String> text = input.takeWhile((String s) -> s.contains("END")).cache();
        Observable<String> text = Observable.just("hi $name");

        //text.subscribe(System.out::println, (Throwable e) -> {}, () -> { System.out.println("finish"); } );

        text.map(VariableFinder::findVariables)
                .flatMap((Observable<Variable> o) -> { return o; })
                .map(VariableReader::print)
                .zipWith(input.take(1), VariableReader::read)
                .zipWith(text.repeat(), VariableReplacer::replace)
                .subscribe(System.out::println);
    }
}
