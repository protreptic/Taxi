package name.peterbukhal.android.taxi.partner.taximeter;

import android.support.annotation.NonNull;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class DefaultTaximeter extends AbsTaximeter {

    @NonNull
    private final DefaultTaximeterContext mContext;

    public DefaultTaximeter(@NonNull final DefaultTaximeterContext context) {
        super(context);

        mContext = context;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void suspend() {
        super.suspend();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void persist() {
        super.persist();
    }

    @Override
    public void restore() {
        super.restore();
    }

    @Override
    public @NonNull DefaultTaximeterContext getTaximeterContext() {
        return mContext;
    }

}
