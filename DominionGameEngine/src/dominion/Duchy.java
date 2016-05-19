package dominion;

public class Duchy extends VictoryCard
{
    public Duchy ()
    {
        super("duchy", 5, 3);
    }
    
    public void performAction(Game game)
    {
        System.out.println("this card as no actions");
    }
}

