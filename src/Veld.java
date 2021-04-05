
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Veld {


    private Vak[][] veld;
    private int mines;
    private int size;


    public Veld(int size, int mijnen) {
        veld = new Vak[size][size];
        mines = mijnen;
        this.size = size;
        setBoms();
        vulVakken();
        setBuren();
    }


    public void setBoms() {
        int geplaatst = 0;
        while (geplaatst < mines) {
            int x = ThreadLocalRandom.current().nextInt(0, size);
            int y = ThreadLocalRandom.current().nextInt(0, size);
            if (!(veld[x][y]instanceof Bom)) {
                veld[x][y] = new Bom();
                geplaatst++;
            }
        }
    }


    public void vulVakken() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (veld[row][col] == null) {

                    veld[row][col] = new Leeg();
                }
            }
        }
    }

    public Vak getVak(int x, int y)
    {
        return veld[x][y];
    }



        public int countNeigbours ( int row, int col)
        {
            int neigbours = 0;
            try {
                if (CheckIfMine(row - 1, col - 1)) {
                    neigbours++;
                }
            } catch (ArrayIndexOutOfBoundsException p) {
            }
            try {
                if (CheckIfMine(row - 1, col)) {
                    neigbours++;
                }
            } catch (ArrayIndexOutOfBoundsException p) {
            }
            try {
                if (CheckIfMine(row - 1, col + 1)) {
                    neigbours++;
                }
            } catch (ArrayIndexOutOfBoundsException p) {
            }
            try {
                if (CheckIfMine(row, col - 1)) {
                    neigbours++;
                }
            } catch (ArrayIndexOutOfBoundsException p) {
            }
            try {
                if (CheckIfMine(row, col + 1)) {
                    neigbours++;
                }
            } catch (ArrayIndexOutOfBoundsException p) {
            }
            try {
                if (CheckIfMine(row + 1, col - 1)) {
                    neigbours++;
                }
            } catch (ArrayIndexOutOfBoundsException p) {
            }
            try {
                if (CheckIfMine(row + 1, col)) {
                    neigbours++;
                }
            } catch (ArrayIndexOutOfBoundsException p) {
            }
            try {
                if (CheckIfMine(row + 1, col + 1)) {
                    neigbours++;
                }
            } catch (ArrayIndexOutOfBoundsException p) {
            }
            return neigbours;
        }


    public boolean CheckIfMine(int rij, int kol) {
        try {
            if (veld[rij][kol] instanceof Bom) {
                return true;
            } else {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            return false;
        }
    }


    public boolean makeVis(int row, int col) {

        Vak vakje = veld[row][col];
        if(!vakje.isbom) {
            vakje.makeVisual();
            vakje.toString();
            vakje.makeControlled();

            if (vakje.geenburen()) {
                try {
                    vakje = veld[row - 1][col - 1];
                    if (!vakje.getControlled())
                        makeVis(row - 1, col - 1);
                    vakje.makeVisual();
                    vakje.toString();

                } catch (ArrayIndexOutOfBoundsException p) {

                }
                try {
                    vakje = veld[row - 1][col];
                    if (!vakje.getControlled())
                        makeVis(row - 1, col);
                    vakje.makeVisual();
                    vakje.toString();
                } catch (ArrayIndexOutOfBoundsException p) {
                }
                try {
                    vakje = veld[row - 1][col + 1];
                    makeVis(row - 1, col + 1);
                    vakje.makeVisual();
                    vakje.toString();
                } catch (ArrayIndexOutOfBoundsException p) {
                }
                try {
                    vakje = veld[row][col - 1];
                    if (!vakje.getControlled())
                        makeVis(row, col - 1);
                    vakje.makeVisual();
                    vakje.toString();
                } catch (ArrayIndexOutOfBoundsException p) {
                }
                try {
                    vakje = veld[row][col + 1];
                    if (!vakje.getControlled())
                        makeVis(row, col + 1);
                    vakje.makeVisual();
                    vakje.toString();
                } catch (ArrayIndexOutOfBoundsException p) {
                }
                try {
                    vakje = veld[row + 1][col - 1];
                    if (!vakje.getControlled())
                        makeVis(row + 1, col - 1);
                    vakje.makeVisual();
                    vakje.toString();
                } catch (ArrayIndexOutOfBoundsException p) {
                }
                try {
                    vakje = veld[row + 1][col];
                    if (!vakje.getControlled())
                        makeVis(row + 1, col);
                    vakje.makeVisual();
                    vakje.toString();
                } catch (ArrayIndexOutOfBoundsException p) {
                }
                try {
                    vakje = veld[row + 1][col + 1];
                    if (!vakje.getControlled())
                        makeVis(row + 1, col + 1);
                    vakje.makeVisual();
                    vakje.toString();
                } catch (ArrayIndexOutOfBoundsException p) {
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    public void showtotalVeld()
    {
        for(int row=0; row<size; row++) {
        for (int col = 0; col < size; col++) {
            veld[row][col].makeVisual();
        }
    }



    }


    public boolean ontploft()
    {
        for(int row=0; row<size; row++) {
        for (int col = 0; col < size; col++) {
            if(veld[row][col].isZichtbaar()&& veld[row][col].Isbom())
            {return true;}
        }
    }
        return false;
    }



    public void setBuren()
    {
        for(int x=0; x<size; x++)
        {
            for(int y=0; y<size; y++)
            {
               if(!veld[x][y].Isbom())
               {
                   veld[x][y].setburen(countNeigbours(x,y));
               }
            }
        }
    }


    public String veldString()
    {
        String stringVeld= "|";
        for(int i=0; i<size; i++)
        {
            if(i<10)
            {
                stringVeld+="0"+(i)+" ||";
            }
            else {
                stringVeld += (i) + " ||";
            }
        }
        stringVeld=stringVeld.substring(0,stringVeld.length()-1);
        stringVeld+="\n";

        for(int row=0; row<size; row++) {
            for (int col = 0; col <size; col++) {
                stringVeld += veld[row][col].toString();
            }
            stringVeld+="\n";
        }
        return stringVeld;
    }







}








