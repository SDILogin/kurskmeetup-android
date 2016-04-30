package mobi.mpk.kurskmeetup.application.presenter;

public interface BasePresenter<VIEW> {

    VIEW getView();

    void onViewAttached(VIEW view);

    void onViewDetached(VIEW view);
}
