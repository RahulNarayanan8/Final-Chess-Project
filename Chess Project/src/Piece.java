
public class Piece 
{
	private String type;
	private String color;
	private String image_string;
	
	public Piece(String piece_type,String piece_color,String imageString)
	{
		type = piece_type;
		color = piece_color;
		image_string = imageString;
	}
	public String getPieceType()
	{
		return type;
	}
	public String getPieceColor()
	{
		return color;
	}
	public String getFileDirectory()
	{
		return image_string;
	}
	
		

}
