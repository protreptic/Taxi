package name.peterbukhal.android.taxi.partner.taximeter;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public abstract class AbsTaximeter implements Taximeter {

    @NonNull
    private final TaximeterContext mContext;

    public AbsTaximeter(@NonNull final TaximeterContext context) {
        mContext = context;
    }

    private final List<TaximeterStateListener> mTaximeterStateListeners = new ArrayList<>();

    /**
     * TODO Доработать документацию
     *
     * @param listener
     */
    public final void addTaximeterStateListener(TaximeterStateListener listener) {
        mTaximeterStateListeners.add(listener);
    }

    /**
     * TODO Доработать документацию
     *
     * @param listener
     */
    public final void removeTaximeterStateListener(TaximeterStateListener listener) {
        mTaximeterStateListeners.remove(listener);
    }

    void notifyStarted() {
        for (TaximeterStateListener listener : mTaximeterStateListeners) {
            listener.onStarted();
        }
    }

    void notifySuspended() {
        for (TaximeterStateListener listener : mTaximeterStateListeners) {
            listener.onSuspended();
        }
    }

    void notifyResumed() {
        for (TaximeterStateListener listener : mTaximeterStateListeners) {
            listener.onResumed();
        }
    }

    void notifyStopped() {
        for (TaximeterStateListener listener : mTaximeterStateListeners) {
            listener.onStopped();
        }
    }

    private final List<TaximeterPersistListener> mTaximeterPersistListeners = new ArrayList<>();

    /**
     * TODO Доработать документацию
     *
     * @param listener
     */
    public final void addTaximeterPersistListener(TaximeterPersistListener listener) {
        mTaximeterPersistListeners.add(listener);
    }

    /**
     * TODO Доработать документацию
     *
     * @param listener
     */
    public final void removeTaximeterPersistListener(TaximeterPersistListener listener) {
        mTaximeterPersistListeners.remove(listener);
    }

    void notifyPersisted() {
        for (TaximeterPersistListener listener : mTaximeterPersistListeners) {
            listener.onPersisted();
        }
    }

    void notifyRestored() {
        for (TaximeterPersistListener listener : mTaximeterPersistListeners) {
            listener.onRestored();
        }
    }

    @Override
    @CallSuper
    public void start() {
        mState = State.STARTED;

        notifyStarted();
    }

    @Override
    @CallSuper
    public void suspend() {
        mState = State.SUSPENDED;

        notifySuspended();
    }

    @Override
    @CallSuper
    public void resume() {
        mState = State.STARTED;

        notifyResumed();
    }

    @Override
    @CallSuper
    public void stop() {
        mState = State.STOPPED;

        notifyStopped();
    }

    @Override
    @CallSuper
    public void persist() {
        notifyPersisted();
    }

    @Override
    @CallSuper
    public void restore() {
        notifyRestored();
    }

    private Taximeter.State mState;

    @Override
    public final @NonNull State getState() {
        return mState;
    }

    @Override
    public final boolean isActive() {
        return (mState == State.STARTED || mState == State.SUSPENDED);
    }

    @Override
    public @NonNull TaximeterContext getTaximeterContext() {
        return mContext;
    }

}
