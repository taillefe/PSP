import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;


public class Hilo extends Thread{
	

	private boolean stopHilo = false;
	private String nombre;
	private int porcentaje;
	private JProgressBar pb;
	private JTextField txt;
	
	
	private float numSleep;
	
	public Hilo (String nombre, JProgressBar pb, JTextField txt,  float numSleep) {
		this.nombre = nombre;
		this.pb = pb;
		this.porcentaje = 0;
		this.txt = txt;
		
		this.numSleep = numSleep;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getPorcentaje() {
		return porcentaje;
	}
	
	public JProgressBar getJProgressBar() {
		return pb;
	}
	
	public void setJProgressBar(JProgressBar pb) {
		this.pb = pb;
	}
	
	public void setStopHilo(boolean fin) {
		porcentaje = 101;
	}
	

	
	@Override
	public void run() {
		
		porcentaje = 0;
		int numAleatorio;
		boolean hayGanador= false;
		while (!stopHilo) {
			try {
	            while (porcentaje < 100) {
	                numAleatorio = generaNumeroAleatorio(1, 10);
	              //  System.out.println("Hilo " + nombre + " ha aumentado en " + numAleatorio);
	                porcentaje = porcentaje + numAleatorio;
	          //      System.out.println("Porcentaje en el hilo : " + nombre +" " + porcentaje);
	                // aqui compruebo si el hilo es el ganador
	                if (porcentaje <100) {  // si aun no ha ganado actualiza la vista
		                // aqui debe actualizar la barra de progresión y el texto 
		                pb.setValue(porcentaje);
		                // refrescar progress bar
		              //  pb.repaint();
		                txt.setText("Hilo"+ nombre + "  "+ String.valueOf(porcentaje));
		                
		           //    System.out.println("Hilo"+ nombre + "  "+ String.valueOf(porcentaje));
		            	 // el hilo duerme el tiempo que se le pasa al constructor
		              //  sleep((long) numSleep);
		                sleep (100);
	                }else { 
	                	//la primera vez que se entra por aquí lo hace el hilo ganador
	                	// se llama al jdialog y se muestra al ganador.
	                	porcentaje = 100;
	               // 	 System.out.println("HILO GANADOR"+ nombre + "  "+ String.valueOf(porcentaje));
	                	pb.setValue(100);
	                	
		                txt.setText("Hilo"+ nombre + "  "+ "100");
	                	JOptionPane.showMessageDialog(null, "Y EL GANADOR ES : HILO" + nombre);
	                	// interrupt(); 
		              //  nombreGanador = nombre;
	                //	System.out.println("pot");
	                	// si ha ganado sale de la ejecución e interrumpe la ejecución del resto de hilos
	                	// manda mensaje de interrumpir los hilos y salir
	                }	
				}
	            System.out.println("HILO GANADOR"+ nombre + "  "+ String.valueOf(porcentaje));
	         //   pb.setValue(porcentaje);
	            stopHilo = true;
	            System.out.println("fin");
	           // terminar();
	         
	           
	           
	        } catch (InterruptedException ex) {
	            System.out.println("Hilo interrumpido");
	        }
		}
		//System.out.println(stopHilo);
    }end run
 
    public static int generaNumeroAleatorio(int minimo, int maximo) {
        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }
}
