import java.io.*;
import java.security.*;
import javax.crypto.*;
public class Descifrado {
	public static void main(String[] args) {
		try {
			//Se recupera la clave secreta del fichero clave.ken
			ObjectInputStream oin = new ObjectInputStream(
			new FileInputStream("clave.ken"));
			Key clavesecreta = (Key) oin.readObject();
			oin.close ();
			
			//Se define el objeto Cipher para desencriptar
			Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, clavesecreta);
		
			//Objeto CipherlnputStream que se va a descifrar
			CipherInputStream in = new CipherInputStream(
			new FileInputStream("FicheroPDF.Cifrado"), c);
			int tambloque = c.getBlockSize();
			byte[] bytes = new byte[tambloque];
		
			//Fichero descifrado 
			FileOutputStream fileout = new
			FileOutputStream("FICHEROdescifrado.pdf");
		
			//Se leen bloques de bytes del fichero cifrado
			//y se escriben desencriptados al FileOutputStream
			int i = in.read(bytes);
			while (i !=-1){
			fileout.write(bytes, 0, i);
			i = in.read(bytes);
		}
			fileout.close ();
			in.close();
			System.out.println("Fichero descifrado con clave secreta.");
		} catch (Exception e) {e.printStackTrace();}
	}//main
}//end Descifrado