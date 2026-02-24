package jpabook.jpashop.exception;

public class NotEnoughStockExcpetion extends RuntimeException {

    public NotEnoughStockExcpetion() {
        super();
    }

    public NotEnoughStockExcpetion(Throwable cause) {
        super(cause);
    }

    public NotEnoughStockExcpetion(String message) {
        super(message);
    }

    public NotEnoughStockExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

}
