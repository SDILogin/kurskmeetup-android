package mobi.mpk.kurskmeetup.presentation;

import java.util.List;

public interface ListFragment<T> {

    public void showLoading();
    public void showList(List<T> list);
    public void showEmpty();
    public void showError(String title, String msg);

}
