package mobi.mpk.kurskmeetup.domain;

public interface OnDataLoadListener<T> {

    public void onSuccess(T data);
    public void onFailure(Throwable throwable);

}
