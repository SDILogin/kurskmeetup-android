package mobi.mpk.kurskmeetup.application.presenter;

public class MeetupsPresenterFactory {
    private static MeetupsPresenterFactory instance;

    private MeetupsPresenterFactory() {}

    public static MeetupsPresenterFactory getInstance() {
        if (instance == null) {
            instance = new MeetupsPresenterFactory();
        }
        return instance;
    }

    public MeetupsPresenter create() {
        return new MyMeetupsPresenter();
    }

}
