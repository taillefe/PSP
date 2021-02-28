import java.io.*;
import java.security.*;
import javax.crypto.*;
public class AlmacenaClaveSecreta {

	public static void main(String[] args) {
		try {
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			kg.init (128);
			
			//genera clave secreta
			SecretKey clave = kg.generateKey();
			
			//la almacena en un fichero Clave.ken
			ObjectOutputStream out = new ObjectOutputStream(
			new FileOutputStream("clave.ken"));
			out.writeObject(clave);
			out.close ();
			System.out.println("Se ha generado la clave clave.ken");
			
		} catch (NoSuchAlgorithmException e) {e .printStackTrace (); }
			catch (FileNotFoundException e) {e.printStackTrace();}
			catch (IOException e) {e.printStackTrace();}
	}//end main
}// end AlmacenaClaveSecreta