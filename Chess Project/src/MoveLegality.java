
import javax.swing.ImageIcon;

import javax.swing.JLabel;

public class MoveLegality 
{
	public static int[] getCoordsFromSquare(String input)
	{
		input = input.toLowerCase();
		int[] rank = {1,2,3,4,5,6,7,8};
		char[] file = {'a','b','c','d','e','f','g','h'};
		int[] coords = {0,50,100,150,200,250,300,350};
		int[] return_arr = new int[2];
		int counter1 = 0;
		int counter2=0;
		char file_value = input.charAt(0);
		char rank_value_char = input.charAt(1);
		int rank_value = rank_value_char;
		rank_value-=48;
		for (char c: file)
		{
			if (c == file_value)
			{
				return_arr[0] = coords[counter1];
			}
			counter1++;
		}
		for (int i:rank)
		{
			if (i == rank_value)
			{
				return_arr[1] = 350-coords[counter2];
			}
			counter2++;
		}
		return return_arr;
	}
	public static int[] getCoordsFromIndices(int first_index, int second_index)
	{
		int[] return_arr = new int[2];
		return_arr[1] = first_index*50;
		return_arr[0] = second_index*50;
		return return_arr;
	}
	public static int[] getIndicesFromCoords(int x_coord, int y_coord)
	{
		int[] coords = {0,50,100,150,200,250,300,350};
		int[] return_arr = new int[2];
		for (int i = 0;i<coords.length;i++)
		{
			if (coords[i] == x_coord)
			{
				return_arr[1] = i;
			}
			if (coords[i] == y_coord)
			{
				return_arr[0] = i;
			}
		}
		return return_arr;
	}
	public static String getSquareFromIndices(int first_index, int second_index)
	{
		String return_str = "";
		char[] letters = {'a','b','c','d','e','f','g','h'};
		return_str+= letters[second_index];
		return_str+= "" + (8-first_index);
		return return_str;
		
	}
	public static JLabel[][] convertArraytoVisual(Piece[][] board)
	{
		JLabel[][] visual_arr = new JLabel[8][8];
		int counter1 = 0;
		int counter2 = 0;
		for (Piece[] array : board)
		{
			for (Piece p: array)
			{
				if (p!=null)
				{
					visual_arr[counter1][counter2] = new JLabel(new ImageIcon(p.getFileDirectory()));
					visual_arr[counter1][counter2].setBounds(getCoordsFromIndices(counter1,counter2)[0],getCoordsFromIndices(counter1,counter2)[1],50,50);
				}
				else
					visual_arr[counter1][counter2] = null;
				counter2++;
			}
			counter2 = 0;
			counter1 ++;
			
		}
		return visual_arr;
	}
	
	public static void main(String []args)
	{
	}
}