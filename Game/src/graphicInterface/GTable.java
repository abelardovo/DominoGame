package graphicInterface;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JPanel;

 class GTable<T> extends JPanel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7000895358550776766L;
	Dimension dimension = new Dimension(450,330);
    ImageDomino imageLeftDomino;
    ImageDomino imageRightDomino;
    T valueLeft=null, valueRight=null;
    Vector <ImageDomino> dominosList= new Vector <ImageDomino>();
    int indexLastElement= -1;
    public GTable ()
    {        
        setSize(dimension);
        setBackground(Color.yellow);
     
        setLayout(new GridLayout(4,7));
       
        initializeArea();
        update();
        validate();
    }
    public Dimension getMinimumSize()
	{
		return dimension;
	}
	
	public Dimension getMaximumSize()
	{
		return dimension;
	}
	
	public Dimension getPreferedSize()
	{
		return dimension;
	}
	
	/**
	 * Add the first domino on the table.
	 * @param d the domino'image to added
	 * @param left the left value of the domino
	 * @param right the right value of the domino
	 */
	 void addFirstDomino(ImageDomino image, T left, T right) 
	{
		valueLeft = left;
		valueRight = right;
		indexLastElement++;
		
		dominosList.add(0, image);
		
		dominosList.remove(dominosList.size()-1);
		
		update();
	}
	
	/**
	 * Add a  domino on the left of the table.
	 * @param d the domino'image to added
	 * @param val the new value of the left table. eg the right value of the domino.
	 */
	 void addDominoLeft(ImageDomino image,T val)
	{
		valueLeft = val;
		indexLastElement++;
		
		dominosList.add(0, image);
		
		dominosList.remove(dominosList.size()-1);
		
		update();
	}
	
	/**
	 * Add a  domino on the right of the table.
	 * @param d the domino'image to added
	 * @param val the new value of the right table. eg the left value of the domino.
	 */
	 void addDominoRight(ImageDomino image,T val)
	{
		valueRight = val;
		indexLastElement++;
		dominosList.add(indexLastElement, image);
		dominosList.remove(dominosList.size()-1);
		update();
	}
	
	/**
	 * Update all the graphical game zone.
	 */
	 void update()
	{
		removeAll();
		for (int i=0; i<dominosList.size();i++)
		{
			add(dominosList.elementAt(i));
		}
        
        validate();
	}
	
	 void initializeArea()
	{
		for (int i=0;i<28;i++)
		{
			ImageDomino vide = new ImageDomino( "imagesDominos/videGris.jpg"); 
			dominosList.add(vide);
		}
	}

	 boolean onlyOneDomino()
	{
		if (indexLastElement==0)
		//if (extremite2.nom.equals("imagesDominos/vide.jpg"))
		return true;
		else return false;
	}
	
	public boolean noDomino()
	{
		if (indexLastElement==-1)
		//if (extremite1.nom.equals("imagesDominos/videGris.jpg"))
			return true;
			else return false;
	}
	public T getValueLeftTable() 
	{
		
		return valueLeft;
		
	}
	public T getValueRightTable() 
	{
		
		return valueRight;
		
	}
	
}
    

