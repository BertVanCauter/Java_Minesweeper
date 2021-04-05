public class Vak {

	protected boolean flagged;
	protected boolean isbom;
	protected boolean zichtbaar;
	protected int buren;


	public Vak() {
		flagged = false;
		zichtbaar = false;
	}


	public void Flag() {

		flagged = !flagged;

	}

	/*public int getX_coordinaat()
	{
		return x_coordinaat;
	}

	public int getY_coordinaat()
	{
		return y_coordinaat;
	}*/

	public void klik() {

	}

	/*public Vak krijgvak(int x, int y)
	{
		return .getVak(1,4);
		//werkt niet!
		// je moet methode op instantie van klasse toepassen en niet klasse zelf
	}
*/

	public void setRevealed(int x, int y) {

	}


	public String toString() {
		if (!flagged)
			if (zichtbaar)
				if (!isbom) {
					return "|" + " "+  buren + " " + "|";
				} else {
					return "| X |";
				}
			else {
				return "|   |";
			}
		else {
			return "| F |";
		}


	}

	public boolean Isbom() {

		return isbom;
	}

	public int getNeig()
	{
		return buren;
	}



	public boolean controlled = false;

	public void makeControlled() {
		controlled = true;
	}

	public boolean getControlled()
	{
		return controlled;
	}

	public boolean isFlagged(){
		return flagged;
	}

	public void makeVisual(){
		zichtbaar = true;
	}

	public void setburen(int countedburen){
		buren = countedburen;
	}

	public boolean geenburen(){
		return (buren == 0);
	}

	public boolean isZichtbaar()
	{
		return zichtbaar;
	}


}