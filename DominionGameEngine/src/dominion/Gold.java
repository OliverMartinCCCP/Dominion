package dominion;

public class Gold extends TreasureCard
{
    public Gold ()
    {
        super("gold", 6, 3);
    }
    public void performAction(Game game)
    {
        System.out.println("this card has no action");
    }
}
