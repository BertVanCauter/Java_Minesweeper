
import java.sql.SQLOutput;
import java.util.Scanner;


public class Gameplay  {


	private static String move;
	private static Veld speelVeld;
	private static Scanner scan;
	private static boolean firstclick;
	private static int aantalMijnen;
	private static Size veldSize;
	private static int maxclicks;
	private static int clikcs;
	private static boolean bomgeraakt=false;


	private static Size sizeInput()
	{
		System.out.println("hello!!! \n" +
				"choose Size by typing : 'm' for medium 'h' for hard or 'e' for expert");
		scan = new Scanner(System.in);
		String input=scan.next().toLowerCase().trim();
		if(input.equals("m"))
		{
		    veldSize=Size.MEDIUM;
			return Size.MEDIUM;
		}
		if(input.equals("h"))
		{
            veldSize=Size.HARD;
			return Size.HARD;
		}
		if(input.equals("e"))
		{
            veldSize=Size.EXPERT;
			return Size.EXPERT;
		}
		else
		{
			System.out.println("give valid input pls ");
			return sizeInput();

		}
	}

	public static void makeNewVeld()
    {
        switch (veldSize)
        {
            case MEDIUM:
                aantalMijnen=10;
                speelVeld=new Veld(8, 10);
                maxclicks=54;
                break;

            case HARD:
                aantalMijnen=40;
                speelVeld= new Veld(16, 40);
                maxclicks=216;
                break;
            case EXPERT:
                aantalMijnen=99;
                speelVeld= new Veld(30, 99);
                maxclicks=801;
                break;
        }
    }

	private static boolean inputUser()
	{
		System.out.println("geef input\n"+
						/*"vorm : xcor/ycor: actie\n"+
						" x en ycor zijn ints van 2 digits\n"+
						" actie is f voor vlag, c voor klik \n"+
						"vergeet de / en : niet !! \n"+*/
						" voorbeeld; '01/12:f'\n");
		scan= new Scanner(System.in);
		String inputString=scan.next().toLowerCase().trim();

		if(validInput(inputString))
		{
			return gameAlg(inputString);
		}
		else
			{
				System.out.println("\n\n invalid input");
				return false;}
	}


	public static boolean validInput(String input)
	{
		boolean valid= false;


		valid= input.length()==7;
		if(!valid)
		{return false;}

		valid= input.charAt(6)=='c'|| input.charAt(6)=='f'&& Character.isDigit(input.charAt(0)) &&  Character.isDigit(input.charAt(1)) &&  Character.isDigit(input.charAt(3)) &&  Character.isDigit(input.charAt(4));
		if(!valid)
		{return false;}

		valid=  input.charAt(2)=='/' && input.charAt(5)==':';

		if(!valid)
		{return false;}

		//probeer ook te zien of het in veld valt !

		return valid;
	}


	public static boolean gameAlg(String input)
	{
		Vak vakje= speelVeld.getVak(Integer.parseInt(input.substring(0,2)),Integer.parseInt(input.substring(3,5)));

		if(vakje.isZichtbaar())
        {
            System.out.println("already clicked");
            return false;
        }

		if(input.substring(6).equals("f"))
        {
            vakje.Flag();
            return true;
        }

		if(input.substring(6).equals("c")&&!vakje.isFlagged())
        {
            while(firstclick && vakje.Isbom()|| firstclick && vakje.getNeig()!=0)
            {
                makeNewVeld();

            }

            firstclick=false;
            if(
            speelVeld.makeVis(Integer.parseInt(input.substring(0,2)),Integer.parseInt(input.substring(3,5))))
			{
				speelVeld.makeVis(Integer.parseInt(input.substring(0,2)),Integer.parseInt(input.substring(3,5)));
				clikcs++;
				return true;
			}
            if(vakje.isbom)
			{
				bomgeraakt=true;
				return true;
			}
        }


		else
        {
            System.out.println("don't click on flags");
            return false;
        }
		return false;
	}


	public static void main(String[] args)
	{

	    boolean running=true;
	    firstclick=true;

	    while (running)
        {
            veldSize=sizeInput();
            makeNewVeld();

            while(clikcs<maxclicks&&!bomgeraakt)
            {
                System.out.println(speelVeld.veldString());
                while(!inputUser())
                {
                    System.out.println("\n error 404\n");
                }
            }

            if(bomgeraakt)
            {
                System.out.println("you lose!!");
            }

            else
            {
                System.out.println("you win!!");
            }
            firstclick=true;
            speelVeld.showtotalVeld();
            System.out.println(speelVeld.veldString());
        }




	}
}
