import java.util.Date;

public class Lot {
    public int current_price;
    public String username;
    public Date end_of_bid;
    public Date curr_date;

    public synchronized void bet(int new_price, String new_username){
        if((curr_date = new Date()).before(end_of_bid)  && new_price > current_price){
            current_price = new_price;
            username = new_username;
        }
    }
    public synchronized String getWinnerUsername() throws NoWinnerException {
        if((curr_date = new Date()).after(end_of_bid)){
            return username;
        }
        throw new NoWinnerException("Bidding is live");
    }
}
