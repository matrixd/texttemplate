import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ulanov on 09.12.16.
 */
public class Printer implements Observer<String> {
    public Printer(String mName) {
        this.mName = mName;
    }

    public void onComplete() {
        System.out.println(mName + " done");
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    public void onNext(String s) {
        System.out.println(mName + ": " + s);
    }

    @Override
    public void onError(Throwable e) {

    }

    final String mName;
}
