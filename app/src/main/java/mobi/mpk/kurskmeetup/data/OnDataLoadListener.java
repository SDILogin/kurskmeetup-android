package mobi.mpk.kurskmeetup.data;

public interface OnDataLoadListener<T> {

    public void onSuccess(T data);
    public void onFailure(Throwable throwable);

}
