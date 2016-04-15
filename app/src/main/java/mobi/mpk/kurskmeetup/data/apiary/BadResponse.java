package mobi.mpk.kurskmeetup.data.apiary;

public class BadResponse extends Exception {

    public BadResponse() {
        super();
    }

    public BadResponse(String detailMessage) {
        super(detailMessage);
    }

    public BadResponse(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BadResponse(Throwable throwable) {
        super(throwable);
    }
}
