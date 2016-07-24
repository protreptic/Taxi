package name.peterbukhal.android.taxi.partner.taximeter;

import android.support.annotation.NonNull;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public interface Taximeter {

    /**
     * TODO Доработать документацию
     */
    interface TaximeterStateListener {

        /**
         * TODO Доработать документацию
         */
        void onStarted();

        /**
         * TODO Доработать документацию
         */
        void onSuspended();

        /**
         * TODO Доработать документацию
         */
        void onResumed();

        /**
         * TODO Доработать документацию
         */
        void onStopped();

    }

    /**
     * Запускает таксометр.
     *
     * @throws TaximeterException
     */
    void start() throws TaximeterException;

    /**
     * Приостанавливает таксометр.
     */
    void suspend() throws TaximeterException;

    /**
     * Продолжает работу таксометра.
     */
    void resume() throws TaximeterException;

    /**
     * Завершает работу таксометра.
     */
    void stop() throws TaximeterException;

    /**
     *
     */
    enum State {
        STARTED, SUSPENDED, STOPPED
    }

    /**
     * Возвращает текущее состояние таксометра.
     * Состояния таксометра определены в {@link State}.
     *
     * @return состояние таксометра
     */
    @NonNull State getState();

    /**
     * TODO Доработать документацию
     *
     * @return
     */
    boolean isActive();

    /**
     * TODO Доработать документацию
     */
    interface TaximeterPersistListener {

        /**
         * TODO Доработать документацию
         */
        void onPersisted();

        /**
         * TODO Доработать документацию
         */
        void onRestored();
    }

    /**
     * TODO Доработать документацию
     */
    void persist() throws TaximeterException;

    /**
     * TODO Доработать документацию
     */
    void restore() throws TaximeterException;

    /**
     * TODO Доработать документацию
     *
     * @return
     */
    @NonNull TaximeterContext getTaximeterContext();

}
