package hugoaguirre.git.art;

public class Handler {

	public static void main(String[] args) 
	{
		// Añadirle al constructor de ART una GUI, para llamar dentro de ART gui.paint_Image(image, content[][]);
		
		// Modificar consructores para que no sean reduntantes.
		ART art_network = ART.getInstance(5,9,7, new GUI(5,9,7));
	}

}
