//helper methods for the board class
import java.util.ArrayList;

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
	public static boolean pawnLegalBehavior(String startSquare, String endSquare, boolean capturing, String color)
	{
		if (startSquare.equals(endSquare))
			return false;
		if (color.equals("white")) {	
			if (startSquare.charAt(0) == endSquare.charAt(0) && capturing ==false)
			{
				if (Character.getNumericValue(startSquare.charAt(1))+1 == Character.getNumericValue(endSquare.charAt(1)))
				{
					return true;
				}
			}
			else if ((Math.abs(((int)startSquare.charAt(0))-(int)endSquare.charAt(0))==1) && capturing)
			{
				if (Character.getNumericValue(startSquare.charAt(1))+1 == Character.getNumericValue(endSquare.charAt(1)))
				{
					return true;
				}
			}
		}
		else {
			if (startSquare.charAt(0) == endSquare.charAt(0) && capturing ==false)
			{
				if (Character.getNumericValue(startSquare.charAt(1))-1 == Character.getNumericValue(endSquare.charAt(1)))
				{
					return true;
				}
			}
			else if ((Math.abs(((int)startSquare.charAt(0))-(int)endSquare.charAt(0))==1) && capturing)
			{
				if (Character.getNumericValue(startSquare.charAt(1))-1 == Character.getNumericValue(endSquare.charAt(1)))
				{
					return true;
				}
			}
		}
		if (startSquare.charAt(1) == '2' && endSquare.charAt(1) == '4' && startSquare.charAt(0) == endSquare.charAt(0) && !capturing && color.equals("white"))
			return true;
		if (startSquare.charAt(1) == '7' && endSquare.charAt(1) == '5' && startSquare.charAt(0) == endSquare.charAt(0) && !capturing && color.equals("black"))
			return true;
		return false;
	}
	public static boolean knightLegalBehavior(String startSquare, String endSquare)
	{
		if (startSquare.equals(endSquare))
			return false;
		char startFile =startSquare.charAt(0);
		char endFile =endSquare.charAt(0);
		int startRank= Character.getNumericValue(startSquare.charAt(1));
		int endRank = Character.getNumericValue(endSquare.charAt(1));
		if (Math.abs(startFile-endFile) == 2 && Math.abs(startRank-endRank) == 1)
			return true;
		if (Math.abs(startFile-endFile) == 1 && Math.abs(startRank-endRank) == 2)
			return true;
		return false;
	}
	public static boolean bishopLegalBehavior(String startSquare, String endSquare)
	{
		if (startSquare.equals(endSquare))
			return false;
		char startFile =startSquare.charAt(0);
		char endFile =endSquare.charAt(0);
		int startRank= Character.getNumericValue(startSquare.charAt(1));
		int endRank = Character.getNumericValue(endSquare.charAt(1));
		if (Math.abs(startFile-endFile) == Math.abs(startRank-endRank))
			return true;
		return false;
	}
	public static boolean rookLegalBehavior(String startSquare, String endSquare)
	{
		if (startSquare.equals(endSquare))
			return false;
		return ((startSquare.charAt(0) == endSquare.charAt(0)) || (startSquare.charAt(1) == endSquare.charAt(1)));
	}
	public static boolean queenLegalBehavior(String startSquare, String endSquare)
	{
		if (rookLegalBehavior(startSquare, endSquare) || bishopLegalBehavior(startSquare,endSquare))
			return true;
		return false;
	}
	public static boolean kingLegalBehavior(String startSquare, String endSquare)
	{
		if (startSquare.equals(endSquare))
			return false;
		if (startSquare.equals("e1"))
		{
			if (endSquare.equals("c1") || endSquare.equals("g1"))
				return true;
		}
		if (startSquare.equals("e8"))
		{
			if (endSquare.equals("c8") || endSquare.equals("g8"))
				return true;
		}
		char startFile =startSquare.charAt(0);
		char endFile =endSquare.charAt(0);
		int startRank= Character.getNumericValue(startSquare.charAt(1));
		int endRank = Character.getNumericValue(endSquare.charAt(1));
		if (Math.abs(startRank-endRank) == 1 || Math.abs((int)(startFile)-(int)(endFile)) == 1)
			return true;
		return false;
	}
	public static ArrayList<String> squaresBetween(String startSquare, String endSquare)
	{
		if (startSquare.equals(endSquare))
			return null;
		if (!queenLegalBehavior(startSquare,endSquare))
			return null;
		String[][] board = {{"a8","b8","c8","d8","e8","f8","g8","h8"},{"a7","b7","c7","d7","e7","f7","g7","h7"},{"a6","b6","c6","d6","e6","f6","g6","h6"},{"a5","b5","c5","d5","e5","f5","g5","h5"},{"a4","b4","c4","d4","e4","f4","g4","h4"},{"a3","b3","c3","d3","e3","f3","g3","h3"},{"a2","b2","c2","d2","e2","f2","g2","h2"},{"a1","b1","c1","d1","e1","f1","g1","h1"}};
		int srow =0,scolumn =0,erow =0,ecolumn =0;
		ArrayList<String> return_arr = new ArrayList<String>();
		for (int i=0;i<board.length;i++)
		{
			for (int j=0;j<board[0].length;j++)
			{
				if (board[i][j].equals(startSquare))
				{
					srow =i;
					scolumn = j;
				}
				else if (board[i][j].equals(endSquare))
				{
					erow = i;
					ecolumn = j;
				}
			}
		}
		if (srow == erow)	
		{
			if (scolumn<ecolumn)
			{
				for (int i=scolumn;i<ecolumn;i++)
				{
					return_arr.add(board[srow][i]);
				}
			}
			else
			{
				for (int i=ecolumn;i<scolumn;i++)
				{
					return_arr.add(board[srow][i]);
				}
			}
		}
		else if (scolumn == ecolumn)
		{
			if (srow<erow)
			{
				for (int i=srow;i<erow;i++)
				{
					return_arr.add(board[i][scolumn]);
				}
			}
			else
			{
				for (int i=erow;i<srow;i++)
				{
					return_arr.add(board[i][scolumn]);
				}
			}
		}
		else {
			int rowDiff = (erow-srow)/(Math.abs(erow-srow));
			int colDiff = (ecolumn-scolumn)/(Math.abs(ecolumn-scolumn));
			int val = Math.abs(erow-srow);
			for (int i=0;i<val;i++)
			{
				return_arr.add(board[srow][scolumn]);
				srow+=rowDiff;
				scolumn+=colDiff;
			}
		}
		
		return_arr.remove(startSquare);
		return_arr.remove(endSquare);
		return return_arr;
				
	}
	public static void main(String []args)
	{
		ArrayList<String> return_arr = squaresBetween("f4","c1");
		for (String s:return_arr)
			System.out.println(s);
	}
}
