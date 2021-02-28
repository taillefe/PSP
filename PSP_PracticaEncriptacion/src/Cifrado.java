import java.io.*;
import java.security.*;
import javax.crypto.*;
public class Cifrado {
	public static void main(String[] args) {
		try {
			//se recupera la clave secreta del fichero clave.ken
			ObjectInputStream oin = new ObjectInputStream(
			new FileInputStream("clave.ken"));
			Key clavesecreta = (Key) oin.readObject();
			oin.close();
			
			//se obtiene el objeto Cipher para encriptar
			Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, clavesecreta);
			
			//fichero para cifrar
			FileInputStream filein = new FileInputStream("fichero.pdf");
			
			//objeto CipherOutputStream que encripta el fichero
			CipherOutputStream out = new CipherOutputStream(
			new FileOutputStream("FicheroPDF.Cifrado"), c);
			int tambloque = c.getBlockSize();//tamaño de bloque objeto Cipher
			byte[] bytes = new byte[tambloque];//bloque de bytes
		
			//se leen los bloques de bytes del fichero PDF
			//y se escribe al CipherOutputStream
			int i = filein.read(bytes);
			while (i != -1) {
			out.write(bytes, 0, i);
			i = filein.read(bytes);
		}
			out.flush();
			out.close ();
			filein.close () ;
			System.out.println("Fichero cifrado con clave secreta.");
		} catch (Exception e) {e .printStackTrace ();}
	}//main .
}// end  Cifrado