package dominion;

public class Province extends VictoryCard
{
    public Province ()
    {
        super("province", 8, 5);
    }
    
    public void performAction(Game game)
    {
        System.out.println("this card has no actions");
    }
        public boolean hasAction()
    {
        return false;
    }
}

