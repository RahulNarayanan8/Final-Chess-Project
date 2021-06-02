import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class AIBoard extends JFrame
{
	// One player chess (against computer) (extremely skilled computer) (ELO ≈ 3607)
	
	private JLabel piece_clicked = null;
	private JLabel[][] visual_board = new JLabel[8][8];
	private String turn = "";
	private boolean game_over= false;
	ArrayList<String> moves = new ArrayList<String>();


	public AIBoard()
	{
		setBounds(100,100,600,600);
		setLayout(null);
		//Adding in all the pieces in their appropriate starting squares
		//Use the appropriate file path given where you saved the Chess_Piece_images folder
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
		
		// Creates array of piece Jlabels and adds them to the frame
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
		int x = 0;
		int y=0;
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
		// Creating the resign button
		turn = "white";
		JButton resign_button = new JButton();
		resign_button.setIcon(new ImageIcon("/Users/rahulnarayanan/eclipse-workspace/Chess Project/Resign.png"));// change file path to what is appropriate for you
		resign_button.setBounds(200,500,98,64);
		resign_button.setVisible(true);
		this.add(resign_button);
		resign_button.addActionListener(new ActionListener()		
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (turn.equals("white"))
				{
					game_over = true;
					JOptionPane.showMessageDialog(null, "                                      Black Wins By Resignation                                      \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					System.exit(0);
				}
				if (turn.equals("black"))
				{
					game_over = true;
					JOptionPane.showMessageDialog(null, "                                      White Wins By Resignation                                      \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					System.exit(0);
				}
			}
			
		});
		
		//Creating the move table where moves are recorded for the player to view
		
		JTable moveTable = new JTable(new DefaultTableModel(new Object[]{"White", "Black"},270));
		moveTable.setBounds(400, 0, 200, 500);
		this.add(moveTable);
				
		
		makeMove("e2","e4", visual_board,"white");
		turn = "black";
		moves.add("white pawn e2e4");
		moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 0);
		
		JOptionPane.showMessageDialog(null, "Welcome to Chess (Against the computer)!");
		// The code below is the real heart of the chess program. This is the code that handles dragging and dropping.
		//Additionally, if an illegal move is dragged and dropped, the mouseListener will reset the piece to its original square
		this.addMouseListener(new MouseListener()
				{
			
					String piece_type;
					String piece_color;
					String piece_icon_str;
					int piece_clicked_x;
					int piece_clicked_y;
					String first_square;
					String second_square;
					boolean piece_captured = false;
					JLabel temp = null;

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) 
					{
						piece_clicked = findPieceAtCoords(e.getX(), e.getY()-22, visual_board); //22 is the top inset size
						if (piece_clicked != null && getPieceColorFromJLabel(piece_clicked).equals("black")) {
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
						boolean move_made = false;
						if(piece_clicked!= null && getPieceColorFromJLabel(piece_clicked).equals("black"))
						{
							piece_clicked.setLocation(e.getX(),e.getY());
							
							for (int i=0;i<visual_board.length;i++)
							{
								for (int j=0;j<visual_board[0].length;j++)
								{
									if (visual_board[i][j] != null)
									{
										if (visual_board[i][j].getX() == correctCoords(e.getX(),e.getY())[0] && visual_board[i][j].getY() == correctCoords(e.getX(),e.getY())[1])
										{
											temp = visual_board[i][j];
											temp.setVisible(false);
											visual_board[i][j].setVisible(false);
											visual_board[i][j] = null;
											piece_captured = true;
											
											
										}
									}
								}
							}
							
							piece_clicked.setLocation(correctCoords(piece_clicked.getX(),piece_clicked.getY())[0],correctCoords(piece_clicked.getX(),piece_clicked.getY())[1]);
							int[] second_indices = MoveLegality.getIndicesFromCoords(piece_clicked.getX(),piece_clicked.getY());
							second_square = MoveLegality.getSquareFromIndices(second_indices[0], second_indices[1]);							
							
							
							if (piece_type.equals("pawn"))
							{
								if ((!(MoveLegality.pawnLegalBehavior(first_square, second_square, piece_captured, piece_color)))|| (temp!=null && getPieceColorFromJLabel(temp).equals(turn))|| isAnythingObstructing(first_square,second_square, visual_board)|| isnotPawnCheck(turn,visual_board))
								{
									piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
									if (temp!=null && piece_captured) {
										temp.setVisible(true);
										piece_captured= false;
										temp = null;
										
									}
								}
								else
								{
									if (second_square.charAt(1) == '8')
									{
										if (turn.equals("white")) {
											piece_clicked.setIcon(new ImageIcon("/Users/rahulnarayanan/Desktop/Chess_Piece_images/White_queen(mm3Xl34BAaA).png"));
											repaint();
										}
									}
									if (second_square.charAt(1) == '1')
									{
										if (turn.equals("black")) {
											piece_clicked.setIcon(new ImageIcon("/Users/rahulnarayanan/Desktop/Chess_Piece_images/black_queen(WJdlIxms2Ll).png"));
											repaint();
										}
									}
									
									
									
									String combined = piece_color+ " "+piece_type + " " + first_square+second_square;
									board[second_indices[0]][second_indices[1]] = new Piece(piece_type,piece_color,piece_icon_str);
									moves.add(combined);
									temp = null;
									piece_captured = false;
									if (turn.equals("white"))
										turn = "black";
									else
										turn = "white";
									try
									{
									if (moves.get(moves.size()-1).charAt(0) == 'w')
										moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 0);
									else
										moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 1);
									}
										
									catch(Exception excep) {
									}
									move_made = true;
								}
							}
							else if (piece_type.equals("knight"))
							{
								if ((!(MoveLegality.knightLegalBehavior(first_square, second_square)))|| (temp!=null && getPieceColorFromJLabel(temp).equals(turn))|| isnotPawnCheck(turn,visual_board))
								{
									piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
									if (temp!=null && piece_captured) {
										temp.setVisible(true);
										piece_captured = false;
										temp = null;
									}
								}
								else
								{
									String combined = piece_color+ " "+piece_type + " " + first_square+second_square;
									board[second_indices[0]][second_indices[1]] = new Piece(piece_type,piece_color,piece_icon_str);
									moves.add(combined);
									temp = null;
									piece_captured = false;
									if (turn.equals("white"))
										turn = "black";
									else
										turn = "white";
									try
									{
									if (moves.get(moves.size()-1).charAt(0) == 'w')
										moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 0);
									else
										moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 1);
									}
										
									catch(Exception excep) {
									}
									move_made = true;
								}
								
							}
							
							else if (piece_type.equals("bishop"))
							{
								if ((!(MoveLegality.bishopLegalBehavior(first_square, second_square)))|| (temp!=null && getPieceColorFromJLabel(temp).equals(turn))|| isAnythingObstructing(first_square,second_square, visual_board) || isnotPawnCheck(turn,visual_board))
								{
									piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
									if (temp!=null && piece_captured) {
										temp.setVisible(true);
										piece_captured = false;
										temp = null;
									}
								}
								else
								{
									String combined = piece_color+ " "+piece_type + " " + first_square+second_square;
									board[second_indices[0]][second_indices[1]] = new Piece(piece_type,piece_color,piece_icon_str);
									moves.add(combined);
									temp = null;
									piece_captured = false;
									if (turn.equals("white"))
										turn = "black";
									else
										turn = "white";
									try
									{
									if (moves.get(moves.size()-1).charAt(0) == 'w')
										moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 0);
									else
										moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 1);
									}
										
									catch(Exception excep) {
									}
									move_made = true;
								}
								
							}
							
							else if (piece_type.equals("rook"))
							{
								if ((!(MoveLegality.rookLegalBehavior(first_square, second_square)))|| (temp!=null && getPieceColorFromJLabel(temp).equals(turn))|| isAnythingObstructing(first_square,second_square, visual_board)|| isnotPawnCheck(turn,visual_board))
								{
									piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
									if (temp!=null && piece_captured) {
										temp.setVisible(true);
										piece_captured = false;
										temp = null;
									}
								}
								else
								{
									String combined = piece_color+ " "+piece_type + " " + first_square+second_square;
									board[second_indices[0]][second_indices[1]] = new Piece(piece_type,piece_color,piece_icon_str);
									moves.add(combined);
									temp = null;
									piece_captured = false;
									if (turn.equals("white"))
										turn = "black";
									else
										turn = "white";
									try
									{
									if (moves.get(moves.size()-1).charAt(0) == 'w')
										moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 0);
									else
										moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 1);
									}
										
									catch(Exception excep) {
									}
									move_made = true;
								}
							}
							
							else if (piece_type.equals("queen"))
							{
								if ((!(MoveLegality.queenLegalBehavior(first_square, second_square)))|| (temp!=null && getPieceColorFromJLabel(temp).equals(turn))|| isAnythingObstructing(first_square,second_square, visual_board)|| isnotPawnCheck(turn,visual_board))
								{
									piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
									if (temp!=null && piece_captured) {
										temp.setVisible(true);
										piece_captured = false;
										temp = null;
									}
								}
								else
								{
									String combined = piece_color+ " "+piece_type + " " + first_square+second_square;
									board[second_indices[0]][second_indices[1]] = new Piece(piece_type,piece_color,piece_icon_str);
									moves.add(combined);
									temp = null;
									piece_captured = false;
									if (turn.equals("white"))
										turn = "black";
									else
										turn = "white";
									try
									{
									if (moves.get(moves.size()-1).charAt(0) == 'w')
										moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 0);
									else
										moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 1);
									}
										
									catch(Exception excep) {
									}
									move_made = true;
								}
							}
							else 
							{
								if ((!(MoveLegality.kingLegalBehavior(first_square, second_square)))|| (temp!=null && getPieceColorFromJLabel(temp).equals(turn))|| isAnythingObstructing(first_square,second_square, visual_board)|| isnotPawnCheck(turn,visual_board) || (first_square.equals("e1") && second_square.equals("e8") || (first_square.equals("e8") && second_square.equals("e1"))))
								{
									piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
									if (temp!=null && piece_captured) {
										temp.setVisible(true);
										piece_captured = false;
										temp = null;
									}
								}
								else if ((first_square.equals("e1") && second_square.equals("g1")) || (first_square.equals("e1") && second_square.equals("c1")) || (first_square.equals("e8") && second_square.equals("g8")) || (first_square.equals("e8") && second_square.equals("g8")))
								{
									boolean illegal = false;
									if (hasKingMoved(turn,moves)) {
										piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
										illegal = true;
									}
									else
									{
										if ((first_square.equals("e1") && second_square.equals("g1"))) {
											if (hasKingRookMoved("white",moves)) {
												piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
											illegal = true;
											}
										}
										else if ((first_square.equals("e8") && second_square.equals("g8"))) {
											if (hasKingRookMoved("black",moves)) {
												piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
												illegal = true;
											}
										}
										else if ((first_square.equals("e1") && second_square.equals("c1"))) {
											if (hasQueenRookMoved("white",moves)) {
												piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
											illegal = true;
											}
										}
										else if ((first_square.equals("e8") && second_square.equals("c8"))) {
											if (hasQueenRookMoved("black",moves)) {
												piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
											illegal = true;
											}
										}
										
										
										if (!illegal && turn.equals("white") && isSideAttackingSquare("e1","black",visual_board))
										{
											piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
											illegal = true;
										}
										if (!illegal && turn.equals("black") && isSideAttackingSquare("e8","white",visual_board))
										{
											piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
											illegal = true;
										}
										if (!illegal && (first_square.equals("e1") && second_square.equals("g1")) && isSideAttackingSquare("f1", "black",visual_board))
										{
											piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
											illegal = true;
										}
										if (!illegal && (first_square.equals("e8") && second_square.equals("g8")) && isSideAttackingSquare("f8", "white",visual_board))
										{
											piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
											illegal = true;
										}
										if (!illegal && (first_square.equals("e1") && second_square.equals("c1")) && isSideAttackingSquare("d1", "black",visual_board))
										{
											piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
											illegal = true;
										}
										if (!illegal && (first_square.equals("e8") && second_square.equals("c8")) && isSideAttackingSquare("d8", "white",visual_board))
										{
											piece_clicked.setLocation(MoveLegality.getCoordsFromSquare(first_square)[0],MoveLegality.getCoordsFromSquare(first_square)[1]);
											illegal = true;
										}
										
										if (!illegal)
										{
											if (first_square.equals("e1") && second_square.equals("g1"))
											{
												String combined = piece_color+ " "+piece_type + " " + first_square+second_square;
												board[second_indices[0]][second_indices[1]] = new Piece(piece_type,piece_color,piece_icon_str);
												moves.add(combined);
												temp = null;
												piece_captured = false;
												if (turn.equals("white"))
													turn = "black";
												else
													turn = "white";
												moves.remove(moves.size()-1);
												moves.add("white O-O");
												JLabel white_castling_rook = findPieceAtCoords(375,375,visual_board);
												white_castling_rook.setLocation(250, 350);
											}
											if (first_square.equals("e1") && second_square.equals("c1"))
											{
												String combined = piece_color+ " "+piece_type + " " + first_square+second_square;
												board[second_indices[0]][second_indices[1]] = new Piece(piece_type,piece_color,piece_icon_str);
												moves.add(combined);
												temp = null;
												piece_captured = false;
												if (turn.equals("white"))
													turn = "black";
												else
													turn = "white";
												moves.remove(moves.size()-1);
												moves.add("white 0-0-0");
												JLabel white_qs_castling_rook = findPieceAtCoords(25,375,visual_board);
												white_qs_castling_rook.setLocation(150,350);
											}
											if (first_square.equals("e8") && second_square.equals("g8"))
											{
												String combined = piece_color+ " "+piece_type + " " + first_square+second_square;
												board[second_indices[0]][second_indices[1]] = new Piece(piece_type,piece_color,piece_icon_str);
												moves.add(combined);
												temp = null;
												piece_captured = false;
												if (turn.equals("white"))
													turn = "black";
												else
													turn = "white";
												moves.remove(moves.size()-1);
												moves.add("black O-O");
												JLabel black_castling_rook = findPieceAtCoords(375,25,visual_board);
												black_castling_rook.setLocation(250, 0);
											}
											if (first_square.equals("e8") && second_square.equals("c8"))
											{
												String combined = piece_color+ " "+piece_type + " " + first_square+second_square;
												board[second_indices[0]][second_indices[1]] = new Piece(piece_type,piece_color,piece_icon_str);
												moves.add(combined);
												temp = null;
												piece_captured = false;
												if (turn.equals("white"))
													turn = "black";
												else
													turn = "white";
												moves.remove(moves.size()-1);
												moves.add("black 0-0-0");
												JLabel white_qs_castling_rook = findPieceAtCoords(25,25,visual_board);
												white_qs_castling_rook.setLocation(150,0);
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
											move_made = true;
										}
										
									}
								}
								else
								{
									String combined = piece_color+ " "+piece_type + " " + first_square+second_square;
									board[second_indices[0]][second_indices[1]] = new Piece(piece_type,piece_color,piece_icon_str);
									moves.add(combined);
									temp = null;
									piece_captured = false;
									if (turn.equals("white"))
										turn = "black";
									else
										turn = "white";
									try
									{
									if (moves.get(moves.size()-1).charAt(0) == 'w')
										moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 0);
									else
										moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 1);
									}
										
									catch(Exception excep) {
									}
									move_made = true;
								}
						}
					}	
						
						
						if(isnotPawnCheck("black", visual_board) && generateMoves("black",visual_board).size() == 0)
						{
							game_over = true;
							JOptionPane.showMessageDialog(null, "                                      White Wins By Checkmate                                      \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
							System.exit(0);
						}
						if(isnotPawnCheck("white", visual_board) && generateMoves("white",visual_board).size() == 0)
						{
							game_over = true;
							JOptionPane.showMessageDialog(null, "                                      Black Wins By Checkmate                                      \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
							System.exit(0);
						}
						if (isDrawByInsufficientMaterial(visual_board))
						{
							game_over = true;
							JOptionPane.showMessageDialog(null, "                                      Game is drawn by Insufficient Material                                      \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
						}
						if(!isnotPawnCheck("black", visual_board) && generateMoves("black",visual_board).size() == 0)
						{
							game_over = true;
							JOptionPane.showMessageDialog(null, "                                      Game is drawn by Stalemate                                      \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
							System.exit(0);
						}
						if(!isnotPawnCheck("white", visual_board) && generateMoves("white",visual_board).size() == 0)
						{
							game_over = true;
							JOptionPane.showMessageDialog(null, "                                      Game is drawn by Stalemate                                      \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
							System.exit(0);
						}
						if (move_made && turn.equals("white")) {
						System.out.println(generateFEN(visual_board, moves));
						StockfishJava thing = new StockfishJava();		
						thing.startEngine();
						thing.drawBoard(generateFEN(visual_board, moves));
						String move = thing.getBestMove(generateFEN(visual_board, moves), 100);
						int[] coords = MoveLegality.getCoordsFromSquare(move.substring(move.length()-2));
						if (findPieceAtCoords(coords[0],coords[1],visual_board)!=null)
						{
							for (JLabel[] arr:visual_board)
							{
								for (JLabel piece:arr)
								{
									if (piece!=null) {
										if (piece.getX() == coords[0] && piece.getY() == coords[1]) {
											piece.setVisible(false);
											piece = null;
										}
									}
								}
							}
							
						}
						makeMove(move.substring(move.length()-4,move.length()-2),move.substring(move.length()-2), visual_board,"white");
						if (move.equals("e1g1"))
						{
							JLabel white_castling_rook = findPieceAtCoords(375,375,visual_board);
							white_castling_rook.setLocation(250, 350);
							moves.add("O-O");
						}
						else if (move.equals("e1c1"))
						{
							moves.add("white 0-0-0");
							JLabel white_qs_castling_rook = findPieceAtCoords(25,375,visual_board);
							white_qs_castling_rook.setLocation(150,350);
						}
						else
							moves.add(move);
						try
						{
							moveTable.setValueAt(moves.get(moves.size()-1).substring(moves.get(moves.size()-1).length()-4), (moves.size()-1)/2, 0);
						}								
						catch(Exception excep) {
						}
						turn = "black";
						
						
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
							piece_clicked.setLocation(e.getX()-25,e.getY()-45);
						
					}

					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
			
				});
		
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//Finding a piece at a given absolute coordinate
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
	//Method to find a piece of a specific color at a given absolute coordinate
	public JLabel findSpecificPieceAtCoords(int x_value, int y_value, JLabel[][] visual_board, String piece_color)
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
					if (getPieceColorFromJLabel(piece).equals(piece_color)) {
						if (piece.getX() == x_coord && piece.getY() == y_coord)
						{	
							returned_piece = piece;
						}
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
			if (x>=coordinates[i])
			{
				return_arr[0] = coordinates[i];
			}
			if (y>=coordinates[i])
			{
				return_arr[1] = coordinates[i];
			}
		}
		return return_arr;
	}
	public String getPieceColorFromJLabel (JLabel piece)
	{
		if (piece.getIcon().toString().toLowerCase().contains("white"))
			return "white";
		return "black";
	}
	public String getPieceTypeFromJLabel(JLabel piece)
	{
		String icon_str = piece.getIcon().toString();
		if (icon_str.contains("pawn"))
			return "pawn";
		else if (icon_str.contains("knight"))
			return "knight";
		else if(icon_str.contains("bishop"))
			return "bishop";
		else if (icon_str.contains("rook"))
			return "rook";
		else if(icon_str.contains("queen"))
			return "queen";
		return "king";
	}
	public String getImageStringFromJLabel(JLabel piece)
	{
		return piece.getIcon().toString();
	}
	
	public boolean isAnythingObstructing(String startSquare,String endSquare,JLabel[][] visual_board)
	{
		ArrayList<String> squaresBetween = MoveLegality.squaresBetween(startSquare, endSquare);
		if (squaresBetween == null || squaresBetween.size() == 0)
			return false;
		for (String s:squaresBetween) {
			if (findPieceAtCoords(MoveLegality.getCoordsFromSquare(s)[0],MoveLegality.getCoordsFromSquare(s)[1], visual_board) !=null)
				{
					return true;
				}
		}
		return false;
			
	}
	//Finds the square on which the king of a given color is
	public String findKing(String color, JLabel[][] visual_board)
	{
		for (JLabel[] arr:visual_board)
		{
			for (JLabel piece:arr)
			{
				if(piece!=null) {
				if (getPieceTypeFromJLabel(piece).equals("king") && getPieceColorFromJLabel(piece).equals(color))
				{
					int king_x = piece.getX();
					int king_y = piece.getY();
					int[] king_indices = MoveLegality.getIndicesFromCoords(king_x,king_y);
					return MoveLegality.getSquareFromIndices(king_indices[0], king_indices[1]);
				}
				}
			}
		}
		
		return null;
	}
	//isCheck()
	public boolean isnotPawnCheck(String color, JLabel[][] visual_board)
	{
		String king_square = findKing(color,visual_board);
		for (JLabel[] arr:visual_board)
		{
			for (JLabel piece:arr)
			{
				if (piece!=null)
				{
					if (!(getPieceTypeFromJLabel(piece).equals("king")) && !getPieceColorFromJLabel(piece).equals(color))
					{
						int piece_x = piece.getX();
						int piece_y = piece.getY();
						int[] piece_indices = MoveLegality.getIndicesFromCoords(piece_x,piece_y);
						String first = MoveLegality.getSquareFromIndices(piece_indices[0], piece_indices[1]);
						
						if (getPieceTypeFromJLabel(piece).equals("pawn"))
						{
							if (MoveLegality.pawnLegalBehavior(first, king_square, true, getPieceColorFromJLabel(piece)))
								return true;
						}
						if (getPieceTypeFromJLabel(piece).equals("bishop")) {
							if(MoveLegality.bishopLegalBehavior(first, king_square) && !isAnythingObstructing(first, king_square,visual_board))
							{
								return true;
							}
						}
						if (getPieceTypeFromJLabel(piece).equals("knight")) {
							if(MoveLegality.knightLegalBehavior(first, king_square))
							{
								return true;
							}
						}
						if (getPieceTypeFromJLabel(piece).equals("rook")) {
							if(MoveLegality.rookLegalBehavior(first, king_square) && !isAnythingObstructing(first, king_square,visual_board))
							{
								return true;
							}
						}
						if (getPieceTypeFromJLabel(piece).equals("queen")) {
							if(MoveLegality.queenLegalBehavior(first, king_square) && !isAnythingObstructing(first, king_square,visual_board))
							{
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	//moves the piece at start to stop
	public void makeMove(String start, String stop, JLabel[][] visual_board, String color)
	{
		JLabel piece_moved = findSpecificPieceAtCoords(MoveLegality.getCoordsFromSquare(start)[0],MoveLegality.getCoordsFromSquare(start)[1], visual_board, color);
		if (piece_moved!=null)
			{
			piece_moved.setLocation(MoveLegality.getCoordsFromSquare(stop)[0],MoveLegality.getCoordsFromSquare(stop)[1]);
			}
			
	}
	//Generates the legal moves in a given position for a certain color
	public ArrayList<String> generateMoves(String color, JLabel[][] visual_board)
	{
		ArrayList<String> moveList = new ArrayList<String>();
		ArrayList<String> checkList;
		if (color.equals("white"))
			checkList = findCheckingPiecesSquares("black",visual_board);
		else
			checkList = findCheckingPiecesSquares("white",visual_board);
		if (checkList!=null && checkList.size() == 1)
		{
			if (isSideAttackingSquare(checkList.get(0), color,visual_board))
			{
				moveList.add("capture checking_piece_possible");
			}
		}
		for (JLabel[] arr:visual_board)
		{
			for (JLabel piece: arr)
			{
				if (piece!=null)
				{
					if (getPieceColorFromJLabel(piece).equals(color))
					{
						int piece_x = piece.getX();
						int piece_y = piece.getY();
						int[] piece_indices = MoveLegality.getIndicesFromCoords(piece_x,piece_y);
						String piece_square = MoveLegality.getSquareFromIndices(piece_indices[0], piece_indices[1]);
						String[] squares = {"a8","b8","c8","d8","e8","f8","g8","h8","a7","b7","c7","d7","e7","f7","g7","h7","a6","b6","c6","d6","e6","f6","g6","h6","a5","b5","c5","d5","e5","f5","g5","h5","a4","b4","c4","d4","e4","f4","g4","h4","a3","b3","c3","d3","e3","f3","g3","h3","a2","b2","c2","d2","e2","f2","g2","h2","a1","b1","c1","d1","e1","f1","g1","h1"};
						for (String s:squares)
						{
							if (getPieceTypeFromJLabel(piece).equals("pawn"))
							{
								if(MoveLegality.pawnLegalBehavior(piece_square, s, false, color))
								{
									if (!isAnythingObstructing(piece_square,s,visual_board))
									{
										if (!isSquareOccupied(s,visual_board))
										{
											makeMove(piece_square,s,visual_board, color);
											if (!isnotPawnCheck(color,visual_board))
											{
												makeMove(s,piece_square,visual_board, color);
												moveList.add(getPieceTypeFromJLabel(piece)+piece_square+s);
											}
											makeMove(s,piece_square,visual_board, color);
											
										}
									}
								}
								if (isSquareOccupied(s,visual_board)&& !isOwnPiece(color,s,visual_board)) {
									if(MoveLegality.pawnLegalBehavior(piece_square, s, true, color))
									{
										if (!isAnythingObstructing(piece_square,s,visual_board))
										{
											makeMove(piece_square,s,visual_board, color);
											if (!isnotPawnCheck(color,visual_board))
											{
												makeMove(s,piece_square,visual_board, color);
												moveList.add(getPieceTypeFromJLabel(piece)+piece_square+s);
											}
											makeMove(s,piece_square,visual_board, color);
										}
									}
								}
							}
							if (getPieceTypeFromJLabel(piece).equals("knight"))
							{
								if(MoveLegality.knightLegalBehavior(piece_square, s))
								{
									
										if (!isSquareOccupied(s,visual_board) || (isSquareOccupied(s,visual_board) &&!isOwnPiece(color,s,visual_board)))
										{
											makeMove(piece_square,s,visual_board, color);
											if (!isnotPawnCheck(color,visual_board))
											{
												makeMove(s,piece_square,visual_board, color);
												moveList.add(getPieceTypeFromJLabel(piece)+piece_square+s);
											}
											makeMove(s,piece_square,visual_board, color);
											
										}
									
								}
								
							}
							if (getPieceTypeFromJLabel(piece).equals("bishop"))
							{
								if(MoveLegality.bishopLegalBehavior(piece_square, s))
								{
									if (!isAnythingObstructing(piece_square,s,visual_board))
									{
										if (!isSquareOccupied(s,visual_board) || (isSquareOccupied(s,visual_board) &&!isOwnPiece(color,s,visual_board)))
										{
											makeMove(piece_square,s,visual_board, color);
											if (!isnotPawnCheck(color,visual_board))
											{
												makeMove(s,piece_square,visual_board, color);
												moveList.add(getPieceTypeFromJLabel(piece)+piece_square+s);
											}
											makeMove(s,piece_square,visual_board, color);
											
										}
									}
								}
								
							}
							if (getPieceTypeFromJLabel(piece).equals("rook"))
							{
								if(MoveLegality.rookLegalBehavior(piece_square, s))
								{
									if (!isAnythingObstructing(piece_square,s,visual_board))
									{
										if (!isSquareOccupied(s,visual_board) || (isSquareOccupied(s,visual_board) &&!isOwnPiece(color,s,visual_board)))
										{
											makeMove(piece_square,s,visual_board, color);
											if (!isnotPawnCheck(color,visual_board))
											{
												makeMove(s,piece_square,visual_board, color);
												moveList.add(getPieceTypeFromJLabel(piece)+piece_square+s);
											}
											makeMove(s,piece_square,visual_board, color);
											
										}
									}
								}	
							}
							if (getPieceTypeFromJLabel(piece).equals("queen"))
							{
								if(MoveLegality.queenLegalBehavior(piece_square, s))
								{
									if (!isAnythingObstructing(piece_square,s,visual_board))
									{
										if (!isSquareOccupied(s,visual_board) || (isSquareOccupied(s,visual_board) &&!isOwnPiece(color,s,visual_board)))
										{
											makeMove(piece_square,s,visual_board, color);
											if (!isnotPawnCheck(color,visual_board))
											{
												makeMove(s,piece_square,visual_board, color);
												moveList.add(getPieceTypeFromJLabel(piece)+piece_square+s);
											}
											makeMove(s,piece_square,visual_board, color);
											
										}
									}
								}
								
							}
							if (getPieceTypeFromJLabel(piece).equals("king"))
							{
								if(MoveLegality.kingLegalBehavior(piece_square, s))
								{
									if (!isAnythingObstructing(piece_square,s,visual_board))
									{
										if (!isSquareOccupied(s,visual_board) || (isSquareOccupied(s,visual_board) &&!isOwnPiece(color,s,visual_board)))
										{
											makeMove(piece_square,s,visual_board, color);
											if (!isnotPawnCheck(color,visual_board))
											{
												makeMove(s,piece_square,visual_board, color);
												moveList.add(getPieceTypeFromJLabel(piece)+piece_square+s);
											}
											makeMove(s,piece_square,visual_board, color);
											
										}
									}
								}
								
							}
						}
					}
				}
			}
		}
		
		if (checkList!=null && checkList.size()>0)
		{
			moveList.remove("kinge1g1");
			moveList.remove("kinge1c1");
			moveList.remove("kinge8g8");
			moveList.remove("kinge8c8");
		}
		
		return moveList;
	}
	//Checks whether a square has a piece on it
	public boolean isSquareOccupied(String square, JLabel[][] visual_board)
	{
		int[] coords = MoveLegality.getCoordsFromSquare(square);
		if (findPieceAtCoords(coords[0],coords[1],visual_board) == null)
		{
			return false;
		}
		return true;
	}
	//Checks whether a piece at a given square matches a given color
	public boolean isOwnPiece(String color, String square, JLabel[][] visual_board)
	{
		int[] coords = MoveLegality.getCoordsFromSquare(square);
		if (getPieceColorFromJLabel(findPieceAtCoords(coords[0],coords[1],visual_board)).equals(color))
		{
			return true;
		}
		return false;
	}
	//Checks whether one color is attacking a given square
	public boolean isSideAttackingSquare(String square, String color, JLabel[][] visual_board)
	{
		for (JLabel[] arr:visual_board)
		{
			for (JLabel piece:arr)
			{
				if (piece!=null)
				{
					if (getPieceColorFromJLabel(piece).equals(color))
					{
						int piece_x = piece.getX();
						int piece_y = piece.getY();
						int[] piece_indices = MoveLegality.getIndicesFromCoords(piece_x,piece_y);
						String piece_square = MoveLegality.getSquareFromIndices(piece_indices[0], piece_indices[1]);
						if (getPieceTypeFromJLabel(piece).equals("pawn"))
						{
							if (MoveLegality.pawnLegalBehavior(piece_square, square, true, color))
							{
								return true;
							}
						}
						if (getPieceTypeFromJLabel(piece).equals("knight"))
						{
							if (MoveLegality.knightLegalBehavior(piece_square, square))
							{
								return true;
							}
						}
						if (getPieceTypeFromJLabel(piece).equals("bishop"))
						{
							if (MoveLegality.bishopLegalBehavior(piece_square, square))
							{
								if (!isAnythingObstructing(piece_square,square,visual_board))
									return true;
							}
						}
						if (getPieceTypeFromJLabel(piece).equals("rook"))
						{
							if (MoveLegality.rookLegalBehavior(piece_square, square))
							{
								if (!isAnythingObstructing(piece_square,square,visual_board))
									return true;
							}
						}
						if (getPieceTypeFromJLabel(piece).equals("queen"))
						{
							if (MoveLegality.queenLegalBehavior(piece_square, square))
							{
								if (!isAnythingObstructing(piece_square,square,visual_board))
									return true;
							}
						}
						
					}
				}
			}
		}
		
		return false;
	}
	//returns an array of the squares in which pieces delivering check reside
	public ArrayList<String> findCheckingPiecesSquares(String color, JLabel[][] visual_board)
	{
		String side_checked = "";
		if (color.equals("white"))
		{
			side_checked = "black";
			if (!isnotPawnCheck("black",visual_board))
				return null;
		}
		if (color.equals("black"))
		{
			side_checked = "white";
			if (!isnotPawnCheck("white",visual_board))
				return null;
		}
		ArrayList<String> checking_pieces = new ArrayList<String>();
		String king_square = findKing(side_checked,visual_board);
		for (JLabel[] arr:visual_board)
		{
			for (JLabel piece:arr)
			{
				if (piece!=null) {
				if (getPieceColorFromJLabel(piece).equals(color))
				{
					int piece_x = piece.getX();
					int piece_y = piece.getY();
					int[] piece_indices = MoveLegality.getIndicesFromCoords(piece_x,piece_y);
					String piece_square = MoveLegality.getSquareFromIndices(piece_indices[0], piece_indices[1]);
					if (getPieceTypeFromJLabel(piece).equals("pawn"))
					{
						if (MoveLegality.pawnLegalBehavior(piece_square, king_square, true, color))
						{
							checking_pieces.add(piece_square);
						}
					}
					if (getPieceTypeFromJLabel(piece).equals("knight"))
					{
						if (MoveLegality.knightLegalBehavior(piece_square, king_square))
						{
							checking_pieces.add(piece_square);
						}
					}
					if (getPieceTypeFromJLabel(piece).equals("bishop"))
					{
						if (MoveLegality.bishopLegalBehavior(piece_square, king_square))
						{
							if (!isAnythingObstructing(piece_square,king_square,visual_board)) {
							checking_pieces.add(piece_square);
							}
						}
					}
					if (getPieceTypeFromJLabel(piece).equals("rook"))
					{
						if (MoveLegality.rookLegalBehavior(piece_square, king_square))
						{
							if (!isAnythingObstructing(piece_square,king_square,visual_board)) {
							checking_pieces.add(piece_square);
							}
						}
					}
					if (getPieceTypeFromJLabel(piece).equals("queen"))
					{
						if (MoveLegality.queenLegalBehavior(piece_square, king_square))
						{
							if (!isAnythingObstructing(piece_square,king_square,visual_board)) {
							checking_pieces.add(piece_square);
							}
						}
					}
					
				}
			}
			}
		}
		return checking_pieces;
	}
	//Used for castling to check whether the king has moved at some point
	public boolean hasKingMoved(String color, ArrayList<String> moves)
	{
		for (String s:moves)
		{
			if (s.contains(color) && s.contains("king"))
				return true;
		}
		return false;
	}
	//Same as previous method but for a rook
	public boolean hasKingRookMoved(String color, ArrayList<String> moves)
	{
		if (color.equals("white"))
		{
			for (String s:moves)
			{
				if (s.contains("rook") && s.contains("h1"))
					return true;
			}
		}
		else
		{
			for (String s:moves)
			{
				if (s.contains("rook") && s.contains("h8"))
					return true;
			}
		}
		return false;
	}
	public boolean hasQueenRookMoved(String color, ArrayList<String> moves)
	{
		if (color.equals("white"))
		{
			for (String s:moves)
			{
				if (s.contains("rook") && s.contains("a1"))
					return true;
			}
		}
		else
		{
			for (String s:moves)
			{
				if (s.contains("rook") && s.contains("a8"))
					return true;
			}
		}
		return false;
	}
	//Used for checking if a position is a draw
	public ArrayList<String> getAllPiecesBySide(String color,JLabel[][] visual_board)
	{
		ArrayList<String> list_of_pieces = new ArrayList<String>();
		for (JLabel[] arr:visual_board)
		{
			for (JLabel piece:arr)
			{
				if (piece!=null) {
					if (getPieceColorFromJLabel(piece).equals(color))
						list_of_pieces.add(getPieceTypeFromJLabel(piece));
				}
			}
			
		}
		return list_of_pieces;
	}
	//Drawing by insufficient material
	public boolean isDrawByInsufficientMaterial(JLabel[][] visual_board)
	{
		ArrayList<String> white_pieces = getAllPiecesBySide("white",visual_board);
		ArrayList<String> black_pieces = getAllPiecesBySide("black",visual_board);
		String pieces = "";
		for (String s:white_pieces)
			pieces+=s;
		for(String s:black_pieces)
			pieces+=s;
		if (pieces.equals("kingking"))
			return true;
		if (pieces.equals("kingknightking")||pieces.equals("knightkingking")||pieces.equals("kingkingknight"))
			return true;
		if (pieces.equals("kingbishopking")||pieces.equals("bishopkingking")||pieces.equals("kingkingbishop"))
			return true;
		
		return false;
	}
	public int evaluateMaterial(JLabel[][] visual_board)
	{
		int white_total = 0;
		int black_total = 0;
		for (JLabel[] arr:visual_board)
		{
			for (JLabel piece:arr)
			{
				if (piece!=null)
				{
					if (getPieceColorFromJLabel(piece).equals("white"))
					{
						if (getPieceTypeFromJLabel(piece).equals("pawn"))
							white_total++;
						if (getPieceTypeFromJLabel(piece).equals("knight"))
							white_total+=3;
						if (getPieceTypeFromJLabel(piece).equals("bishop"))
							white_total+=3;
						if (getPieceTypeFromJLabel(piece).equals("rook"))
							white_total+=5;
						if (getPieceTypeFromJLabel(piece).equals("queen"))
							white_total+=9;
					}
					if (getPieceColorFromJLabel(piece).equals("black"))
					{
						if (getPieceTypeFromJLabel(piece).equals("pawn"))
							black_total++;
						if (getPieceTypeFromJLabel(piece).equals("knight"))
							black_total+=3;
						if (getPieceTypeFromJLabel(piece).equals("bishop"))
							black_total+=3;
						if (getPieceTypeFromJLabel(piece).equals("rook"))
							black_total+=5;
						if (getPieceTypeFromJLabel(piece).equals("queen"))
							black_total+=9;
					}
				}
			}
		}
		return white_total-black_total;
	}
	public String truncateMove(String move)
	{
		String[] parts = move.split(" ");
		return parts[parts.length-1];
	}
	public String generateFEN(JLabel[][] visual_board, ArrayList<String> move_list)
	{
		String final_fen = "";
		String[][] board_squares = {{"a8","b8","c8","d8","e8","f8","g8","h8"},{"a7","b7","c7","d7","e7","f7","g7","h7"},{"a6","b6","c6","d6","e6","f6","g6","h6"},{"a5","b5","c5","d5","e5","f5","g5","h5"},{"a4","b4","c4","d4","e4","f4","g4","h4"},{"a3","b3","c3","d3","e3","f3","g3","h3"},{"a2","b2","c2","d2","e2","f2","g2","h2"},{"a1","b1","c1","d1","e1","f1","g1","h1"}};
		for (String [] row: board_squares)
		{
			int counter = 0;
			for (String square:row)
			{
				JLabel piece_on_square = findPieceAtCoords(MoveLegality.getCoordsFromSquare(square)[0],MoveLegality.getCoordsFromSquare(square)[1], visual_board);
				if (piece_on_square!=null)
				{
					if (getPieceColorFromJLabel(piece_on_square).equals("white")) {
						if (getPieceTypeFromJLabel(piece_on_square).equals("pawn"))
						{
							final_fen+="P";
						}
						if (getPieceTypeFromJLabel(piece_on_square).equals("rook"))
						{
							final_fen+="R";
						}
						if (getPieceTypeFromJLabel(piece_on_square).equals("knight"))
						{
							final_fen+="N";
						}
						if (getPieceTypeFromJLabel(piece_on_square).equals("bishop"))
						{
							final_fen+="B";
						}
						if (getPieceTypeFromJLabel(piece_on_square).equals("queen"))
						{
							final_fen+="Q";
						}
						if (getPieceTypeFromJLabel(piece_on_square).equals("king"))
						{
							final_fen+="K";
						}
						
					}
					if (getPieceColorFromJLabel(piece_on_square).equals("black")) {
						if (getPieceTypeFromJLabel(piece_on_square).equals("pawn"))
						{
							final_fen+="p";
						}
						if (getPieceTypeFromJLabel(piece_on_square).equals("rook"))
						{
							final_fen+="r";
						}
						if (getPieceTypeFromJLabel(piece_on_square).equals("knight"))
						{
							final_fen+="n";
						}
						if (getPieceTypeFromJLabel(piece_on_square).equals("bishop"))
						{
							final_fen+="b";
						}
						if (getPieceTypeFromJLabel(piece_on_square).equals("queen"))
						{
							final_fen+="q";
						}
						if (getPieceTypeFromJLabel(piece_on_square).equals("king"))
						{
							final_fen+="k";
						}
						
					}
				}
				else
				{
					final_fen+="1";
				}
				
			}
			
			counter+=1;
			if (counter<7)
				final_fen+="/";
		}
		final_fen = final_fen.substring(0,final_fen.length()-1);
		final_fen+=" w ";
		
		
		//adding the castling stuff to the FEN
		if (!hasKingMoved("white", move_list) && !hasKingRookMoved("white", move_list) && !isAnythingObstructing("e1","g1",visual_board))
		{
			final_fen+= "K";
		}
		if (!hasKingMoved("white", move_list) && !hasQueenRookMoved("white", move_list)&& !isAnythingObstructing("e1","c1",visual_board))
		{
			final_fen+= "Q";
		}
		if (!hasKingMoved("black", move_list) && !hasKingRookMoved("black", move_list)&& !isAnythingObstructing("e8","g8",visual_board))
		{
			final_fen+= "k";
		}
		if (!hasKingMoved("black", move_list) && !hasQueenRookMoved("black", move_list)&& !isAnythingObstructing("e8","c8",visual_board))
		{
			final_fen+= "q ";
		}
		final_fen += "- 2 2";
		
		final_fen = final_fen.replaceAll("11111111", "8");
		final_fen = final_fen.replaceAll("1111111", "7");
		final_fen = final_fen.replaceAll("111111", "6");
		final_fen = final_fen.replaceAll("11111", "5");
		final_fen = final_fen.replaceAll("1111", "4");
		final_fen = final_fen.replaceAll("111", "3");
		final_fen = final_fen.replaceAll("11", "2");
		final_fen = final_fen.replaceAll("1", "1");
		return final_fen;
	}
	public static void main(String[] args)
	{
		new AIBoard();
	}
}
