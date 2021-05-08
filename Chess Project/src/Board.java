import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;



public class Board extends JFrame
{
	private JLabel piece_clicked = null;
	private JLabel[][] visual_board = new JLabel[8][8];
	private String turn = "";

	public Board()
	{
		setBounds(100,100,600,600);
		setLayout(null);
		
		Piece[][] board = new Piece[8][8];
		board[0][0] = new Piece("rook","black","/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_rook(tOoQuOJl6n0).png");
		board[0][1] = new Piece("knight","black","/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_knight(wAao0gwitpC).png");
		board[0][2] = new Piece("bishop","black","/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_bishop(JLtbtpL3AZt).png");
		board[0][3] = new Piece("queen","black","/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_queen(WJdlIxms2Ll).png");
		board[0][4] = new Piece("king", "black", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_king(w0lDYttQeAB).png");
		board[0][5] = new Piece("bishop","black","/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_bishop(JLtbtpL3AZt).png");
		board[0][6] = new Piece("knight","black","/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_knight(wAao0gwitpC).png");
		board[0][7] = new Piece("rook","black","/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_rook(tOoQuOJl6n0).png");
		
		board[1][0] = new Piece ("pawn", "black", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_pawn(OTQVDfsf92u).png");
		board[1][1] = new Piece ("pawn", "black", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_pawn(OTQVDfsf92u).png");
		board[1][2] = new Piece ("pawn", "black", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_pawn(OTQVDfsf92u).png");
		board[1][3] = new Piece ("pawn", "black", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_pawn(OTQVDfsf92u).png");
		board[1][4] = new Piece ("pawn", "black", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_pawn(OTQVDfsf92u).png");
		board[1][5] = new Piece ("pawn", "black", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_pawn(OTQVDfsf92u).png");
		board[1][6] = new Piece ("pawn", "black", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_pawn(OTQVDfsf92u).png");
		board[1][7] = new Piece ("pawn", "black", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_pawn(OTQVDfsf92u).png");
		
		board[6][0] = new Piece ("pawn", "white", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_pawn(6DaUXPer6tK).png");
		board[6][1] = new Piece ("pawn", "white", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_pawn(6DaUXPer6tK).png");
		board[6][2] = new Piece ("pawn", "white", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_pawn(6DaUXPer6tK).png");
		board[6][3] = new Piece ("pawn", "white", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_pawn(6DaUXPer6tK).png");
		board[6][4] = new Piece ("pawn", "white", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_pawn(6DaUXPer6tK).png");
		board[6][5] = new Piece ("pawn", "white", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_pawn(6DaUXPer6tK).png");
		board[6][6] = new Piece ("pawn", "white", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_pawn(6DaUXPer6tK).png");
		board[6][7] = new Piece ("pawn", "white", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_pawn(6DaUXPer6tK).png");
		
		board[7][0] = new Piece("rook","white","/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_rook(ttamcrm8fvD).png");
		board[7][1] = new Piece("knight","white","/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_knight(2r1HceSdkD0).png");
		board[7][2] = new Piece("bishop","white","/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_bishop(HJH1Dnd1hFf).png");
		board[7][3] = new Piece("queen","white","/Users/rahulnarayanan/Desktop/Chess_Piece_images/White_queen(mm3Xl34BAaA).png");
		board[7][4] = new Piece("king", "white", "/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_king(WHFLwHSPwsd).png");
		board[7][5] = new Piece("bishop","white","/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_bishop(HJH1Dnd1hFf).png");
		board[7][6] = new Piece("knight","white","/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_knight(2r1HceSdkD0).png");
		board[7][7] = new Piece("rook","white","/Users/rahulnarayanan/Desktop/Chess_Piece_images/white_rook(ttamcrm8fvD).png");
		
		
		visual_board = MoveLegality.convertArraytoVisual(board);
		
		for (JLabel[] j: visual_board)
		{
			for (JLabel piece: j)
			{
				if(piece!= null)
				{	
					add(piece);
				}
			}
		}

		
		//Creating the 64 squares
		
		
		int counter = 0;
		int x,y;
		x = 0;
		y=0;
		boolean color = false;
		Square[][] board_coordinates = new Square[8][8];
		for (int file= 0;file<=7;file++)
		{
			y=0;
			for (int rank = 0;rank<=7;rank++)
			{
				counter++;
				if (counter%2==0)
				{
					color=false;
				}
				else
					color = true;
				board_coordinates[file][rank] = new Square (x,y);
				board_coordinates[file][rank].setColor(color);
				y+=50;
			}
			x+=50;
			counter++;
		}
		
		for (Square[] files:board_coordinates)
		{
			for (Square s : files)
			{
				this.add(s);
			}
		}
		
		turn = "white";
		
		JTable moveTable = new JTable(new DefaultTableModel(new Object[]{"White", "Black"},270));
		moveTable.setBounds(400, 0, 200, 500);
		this.add(moveTable);
		
		this.addMouseListener(new MouseListener()
				{
			
					String piece_type;
					String piece_color;
					String piece_icon_str;
					int piece_clicked_x;
					int piece_clicked_y;
					ArrayList<String> moves = new ArrayList<String>();
					String first_square;
					String second_square;

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) 
					{
						piece_clicked = findPieceAtCoords(e.getX(), e.getY(), visual_board);
						if (piece_clicked != null) {
						piece_type = getPieceTypeFromJLabel(piece_clicked);
						piece_color = getPieceColorFromJLabel(piece_clicked);
						if (!(piece_color.equals(turn)))
							piece_clicked = null;
						else{
							piece_icon_str = getImageStringFromJLabel(piece_clicked);
							piece_clicked_x = piece_clicked.getX();
							piece_clicked_y = piece_clicked.getY();
							int[] indices = MoveLegality.getIndicesFromCoords(piece_clicked_x,piece_clicked_y);
							first_square = MoveLegality.getSquareFromIndices(indices[0], indices[1]);
							board[indices[0]][indices[1]] = null;
							}
						}
					}

					@Override
					public void mouseReleased(MouseEvent e) 
					{
						if(piece_clicked!= null)
						{
							piece_clicked.setLocation(e.getX(),e.getY());
							
							for (JLabel[] arr:visual_board)
							{
								for (JLabel piece_there:arr)
								{
									if (piece_there != null)
									{
										if (piece_there.getX() == correctCoords(e.getX(),e.getY())[0] && piece_there.getY() == correctCoords(e.getX(),e.getY())[1])
										{
											piece_there.setVisible(false);
											piece_there = null;
										}
									}
								}
							}
							
							piece_clicked.setLocation(correctCoords(piece_clicked.getX(),piece_clicked.getY())[0],correctCoords(piece_clicked.getX(),piece_clicked.getY())[1]);
							if (turn.equals("white"))
								turn = "black";
							else
								turn = "white";
							int[] second_indices = MoveLegality.getIndicesFromCoords(piece_clicked.getX(),piece_clicked.getY());
							second_square = MoveLegality.getSquareFromIndices(second_indices[0], second_indices[1]);
							String combined = piece_color+ " "+piece_type + " " + first_square+second_square;
							board[second_indices[0]][second_indices[1]] = new Piece(piece_type,piece_color,piece_icon_str);
							moves.add(combined);
							if (moves.size()>8) {
							if (moves.get(moves.size()-1).contains("white")&& moves.get(moves.size()-2).contains("white")) 
							{
								moves.remove(moves.size()-1);
								moves.remove(moves.size()-1);
								moves.add("white O-O");
							}
							if (moves.get(moves.size()-1).contains("black")&& moves.get(moves.size()-2).contains("black")) 
							{
								moves.remove(moves.size()-1);
								moves.remove(moves.size()-1);
								moves.add("black O-O");
							}
							}
							if (first_square.equals(second_square))
							{
								moves.remove(moves.size()-1);
							}
							try
							{
							if (moves.get(moves.size()-1).charAt(0) == 'w')
								moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 0);
							else
								moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 1);
							}
								
							catch(Exception excep) {
							}
							
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
	
				});
		this.addMouseMotionListener(new MouseMotionListener()
				{

					@Override
					public void mouseDragged(MouseEvent e) 
					{
						if (piece_clicked!= null)
							piece_clicked.setLocation(e.getX(),e.getY());
						
					}

					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
			
				});
		
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JLabel findPieceAtCoords(int x_value, int y_value, JLabel[][] visual_board)
	{
		int[] corrected_coords = correctCoords(x_value,y_value);
		int x_coord = corrected_coords[0];
		int y_coord = corrected_coords[1];
		JLabel returned_piece = null;
		for (JLabel[] arr: visual_board)
		{
			for (JLabel piece: arr)
			{
				if (piece!= null)
				{
					if (piece.getX() == x_coord && piece.getY() == y_coord)
					{	
						returned_piece = piece;
					}
				}
			}
		}
		return returned_piece;
	}
	public static int[] correctCoords(int x, int y)
	{
		int[] coordinates = {0,50,100,150,200,250,300,350};
		int [] return_arr = new int[2];
		for (int i=0;i<coordinates.length;i++)
		{
			if (x>coordinates[i])
			{
				return_arr[0] = coordinates[i];
			}
			if (y>coordinates[i])
			{
				return_arr[1] = coordinates[i];
			}
		}
		return return_arr;
	}
	public String getPieceColorFromJLabel (JLabel piece)
	{
		return (piece.getIcon().toString().substring(49,54));
	}
	public String getPieceTypeFromJLabel(JLabel piece)
	{
		String icon_str = piece.getIcon().toString();
		return icon_str.substring(55,icon_str.length()-17);
	}
	public String getImageStringFromJLabel(JLabel piece)
	{
		return piece.getIcon().toString();
	}
	public static void main(String[] args)
	{
		new Board();
	}
}
