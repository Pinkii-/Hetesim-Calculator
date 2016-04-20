package Persistencia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Propjecto {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, CloneNotSupportedException  {
		String s = "r1.Result";
		
		if (s.indexOf("Resuslt") != -1)
			System.out.println("yes");
		
		try {
			Path p = Paths.get("/home/albert/lel/lel");
			FileInputStream fi = new FileInputStream(p.toString());
		} catch (FileNotFoundException e) {
			System.out.println("El directorio no existe");
			e.printStackTrace();
		}
		
	}
}
