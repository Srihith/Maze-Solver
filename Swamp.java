import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//j text that prints the path
//highlight each loction it goes to
// DO NOT!! IMPORT JAVA.LANG
//Use for loop to check the neighbors around the 1 and check to see if there are other 1's around it. If there is a one, use a recursize method to check around that.
//. Then set the pervious on to -1 so you don't go back. Check in a 
//counter clock wise format. If there are many 1's around the one use a recursive method. After the recursive method, chanege the -1 to a 1
public class Swamp
{
	static int[][] swamp;  // NOW YOU DON'T HAVE PASS THE REF IN/OUT METHODS
	static int[][] pathArray;
	static int swampSize=0;
	static Color[][] swampColor;
	static JLabel[][] labelArray;
	static String path;
	static String printPath;
	static int count;
	static JLabel[][] swampLabels;
	static JLabel pathLabel;
	static JFrame window;
	static int startRow=0;
	static int startCol=0;
	static JPanel rightPanel;
	static ArrayList<String> pathList = new ArrayList<String>();
	static boolean click;

 	public static void main(String[] args) throws Exception
	{
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		click=false;
		String swampfilename = args[0];
		Scanner swampFile = new Scanner( new File( swampfilename ) );
		pathArray=swamp;
		path = ""; 
		printPath="Paths:";
		swampSize = swampFile.nextInt(); 
		 startRow =swampFile.nextInt(); 
		 startCol = swampFile.nextInt();
		swampLabels = new JLabel[swampSize][swampSize];
		swamp= new int[swampSize][swampSize];

		JFrame window = new JFrame ("Swamp");
		Container content=window.getContentPane();
		content.setLayout(new GridLayout(1,2) );
		JPanel leftPanel = new JPanel();
		rightPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(2,1)); // array of 0s and 1s on top. SOLVE button on bottom
		JPanel leftPanelUp = new JPanel();
		leftPanelUp.setLayout(new GridLayout(swampSize,swampSize));
		JPanel leftPanelDown = new JPanel();
		leftPanelDown.setLayout(new GridLayout(8,1));
		window.setVisible(true);
		window.setSize(1100,1100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while ( swampFile.hasNextInt() )
		{	
			for(int i=0;i<swampSize;i++)
			{
				for(int j=0; j<swampSize;j++)
				{
					int next=swampFile.nextInt();
					swamp[i][j]=next;
					swampLabels[i][j] = new JLabel( ""+next );
					leftPanelUp.add( swampLabels[i][j] );
				
				}
			}
		} 
		
		JButton button = new JButton("Solve");
		///button.setPreferredSize(new setBounds(1000, 1000));
		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
		leftPanelDown.add(button);
		swampFile.close();
		leftPanel.add(leftPanelUp);
		leftPanel.add(leftPanelDown);
		content.add( leftPanel );

		
		pathLabel = new JLabel("<html>Paths:<br></html>");
		rightPanel.add(pathLabel);
		rightPanel.add(new JLabel("<html><br></html>"));
		content.add( rightPanel );	

		while(click==false){try{Thread.sleep(0);}catch(InterruptedException e){System.out.println(e);}}
		dfs( startRow, startCol, path );
		//window();
	} // END MAIN

 	
	static void dfs( int row, int col, String path ) // dfs = DEPTH FIRST SEARCH
	{
		try{Thread.sleep(250);}catch(InterruptedException e){System.out.println(e);}
		path+=("[" + row +","+ col +"]");

		if(row==0 || col==0 || row==swamp.length-1 || col==swamp.length-1)
		{
			swampLabels[row][col].setForeground( Color.BLUE);
			rightPanel.add(new JLabel(( "<html>"+path+"<br></html>")));
			return;
		}

		//north
		//swampColor[row-1][col]=new Color(0,0,255);
		if(swamp[row-1][col]==1)
		{
			swamp[row][col]=-1;
			//swampLabels[row][col]=new JLabel( "-1" );
			swampLabels[row][col].setForeground( Color.RED);
			dfs(row-1,col,path);
			swamp[row][col]=1;
			//swampLabels[row][col]="1";
			swampLabels[row][col].setForeground( Color.BLUE);
		}
		
		//north east
		//swampColor[row-1][col+1]=new Color(0,0,255);
		if(swamp[row-1][col+1]==1)
		{
			swamp[row][col]=-1;
			//swampLabels[row][col]=new JLabel( "-1" );
			swampLabels[row][col].setForeground( Color.RED);
			dfs(row-1,col+1,path);
			swamp[row][col]=1;
			//swampLabels[row][col]=1;
			swampLabels[row][col].setForeground( Color.BLUE);
		}

		//east
		//swampColor[row][col+1]=new Color(0,0,255);
		if(swamp[row][col+1]==1)
		{
			swamp[row][col]=-1;
			//swampLabels[row][col]=new JLabel( "-1" );
			swampLabels[row][col].setForeground( Color.RED);
			dfs(row,col+1,path);
			swamp[row][col]=1;
			//swampLabels[row][col]=1;
			swampLabels[row][col].setForeground( Color.BLUE);
		}

		//south east
		//swampColor[row+1][col+1]=new Color(0,0,255);
		if(swamp[row+1][col+1]==1)
		{
			swamp[row][col]=-1;
			//swampLabels[row][col]=new JLabel( "-1" );
			swampLabels[row][col].setForeground( Color.RED);
			dfs(row+1,col+1,path);
			swamp[row][col]=1;
			//swampLabels[row][col]=1;
			swampLabels[row][col].setForeground( Color.BLUE);
		}

		//south
		//swampColor[row+1][col-1]=new Color(0,0,255);
		if(swamp[row+1][col]==1)
		{
			swamp[row][col]=-1;
			//swampLabels[row][col]=new JLabel( "-1" );
			swampLabels[row][col].setForeground( Color.RED);
			dfs(row+1,col,path);
			swamp[row][col]=1;
			//swampLabels[row][col]=1;
			swampLabels[row][col].setForeground( Color.BLUE);
		}

		//south west
		//swampColor[row+1][col-1]=new Color(0,0,255);
		if(swamp[row+1][col-1]==1)
		{
			swamp[row][col]=-1;
			//swampLabels[row][col]=new JLabel( "-1" );
			swampLabels[row][col].setForeground( Color.RED);
			dfs(row+1,col-1,path);
			swamp[row][col]=1;
			//swampLabels[row][col]=1;
			swampLabels[row][col].setForeground( Color.BLUE);
		}

		//west
		//swampColor[row][col-1]=new Color(0,0,255);
		if(swamp[row][col-1]==1)
		{
			swamp[row][col]=-1;
			//swampLabels[row][col]=new JLabel( "-1" );;
			swampLabels[row][col].setForeground( Color.RED);
			dfs(row,col-1,path);
			swamp[row][col]=1;
			//swampLabels[row][col]=1;
			swampLabels[row][col].setForeground( Color.BLUE);
		}

		//north west
		//swampColor[row-1][col-1]=new Color(0,0,255);
		if(swamp[row-1][col-1]==1)
		{
			swamp[row][col]=-1;
			//swampLabels[row][col]=new JLabel( "-1" );
			swampLabels[row][col].setForeground( Color.RED);
			dfs(row-1,col-1,path);
			swamp[row][col]=1;
			//swampLabels[row][col]=1;
			swampLabels[row][col].setForeground( Color.BLUE);
		}

		return;
	}	


	static class ButtonListener implements ActionListener // MyListener is JUST THE NAME WE MAKE UP FOR OUR CLASS
	{
		public void actionPerformed(ActionEvent e)
		{
			click=true;
			//dfs( startRow, startCol, path );
		}
	}

}
