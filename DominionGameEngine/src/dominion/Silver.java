package dominion;

public class Silver extends TreasureCard
{
    public Silver ()
    {
        super("silver", 3, 2);
    }
    public void performAction(Game game)
    {
        System.out.println("this card has no actions");
    }
}
