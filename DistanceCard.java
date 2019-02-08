package CardStuff;

// this class will represent the distance card where the distance and the card name should
// ... ideally be the same
public class DistanceCard implements CardType {
    private int distance;
    private String cardName;       // should be along the lines of 25 km or 100 km...

    public DistanceCard(int distance, String cardName){
        this.distance = distance;
        this.cardName = cardName;
    }

    // will return the card's name
    public String getCardName(){ return cardName; }

    // will return the distance of the card
    public int getDistance(){ return distance; }

    @Override
    public void getCard() {
        System.out.print(this.getCardName() + " ");
    }

    @Override
    public int getCardValue() {
        return this.getDistance();
    }
}
