import java.util.Date;

public class Lot {
    public volatile int current_price;
    public volatile String username;
    public Date end_of_bid;

    public void bet(int new_price, String new_username){
        if((new Date()).before(end_of_bid)  && new_price > current_price){
            synchronized (this) {
                if ((new Date()).before(end_of_bid) && new_price > current_price) {
                    current_price = new_price;
                    username = new_username;
                }
            }
        }
    }
    public synchronized String getWinnerUsername() throws NoWinnerException {
        if((new Date()).after(end_of_bid)){
            return username;
        }
        throw new NoWinnerException("Bidding is live");
    }
}
