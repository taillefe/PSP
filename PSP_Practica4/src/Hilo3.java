import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;


public class Hilo3 extends Thread{
	

	private boolean stopHilo = false;
	private String nombre;
	private int porcentaje;
	private JProgressBar pb;
	private JTextField txt;
	private float numSleep;
	private Hilo hilo;
	private Hilo2 hilo2;
	private JButton boton;
	
	public Hilo3 (String nombre, JProgressBar pb, JTextField txt,  float numSleep, Hilo hilo, Hilo2 hilo2,JButton boton) {
		this.nombre = nombre;
		this.pb = pb;
		this.porcentaje = 0;
		this.txt = txt;
		this.numSleep = numSleep;
		this.hilo = hilo;
		this.hilo2 = hilo2;
		this.boton = boton;
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
		this.stopHilo = fin;
		}
	public void setHilo(Hilo hilo) {
			this.hilo = hilo;
		}
	public void setHilo2(Hilo2 hilo2) {
			this.hilo2 = hilo2;
		}
	

	
	@Override
	public void run() {
		
		porcentaje = 0;
		int numAleatorio;
		
		// poner todos los datos a 0 y duermo durante 1000 ms para que se vea que empiezan todos a 0
		pb.setValue(0);
		txt.setText("Hilo3  0");
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
	            while ((porcentaje < 100) && !stopHilo) {
	                numAleatorio = generaNumeroAleatorio(1, 15);
	              //  System.out.println("Hilo " + nombre + " ha aumentado en " + numAleatorio);
	                porcentaje = porcentaje + numAleatorio;
	            //    porcentaje = porcentaje + (int)numSleep;
	                System.out.println("Porcentaje en el hilo : " + nombre +" " + porcentaje);
	                // aqui compruebo si el hilo es el ganador
	                if (porcentaje <100) {  // si aun no ha ganado actualiza la vista
		                // aqui debe actualizar la barra de progresión y el texto 
		                pb.setValue(porcentaje);
		                txt.setText("Hilo"+ nombre + "  "+ String.valueOf(porcentaje));
		                
		                System.out.println("Hilo"+ nombre + "  "+ String.valueOf(porcentaje));
		            	 // el hilo duerme el tiempo que se le pasa al constructor
		                sleep((long) numSleep);
		              //  sleep (1000);
	                }else { 
	                	//la primera vez que se entra por aquí lo hace el hilo ganador
	                	// se llama al jdialog y se muestra al ganador.
	                	porcentaje = 100;
	                	 System.out.println("HILO GANADOR"+ nombre + "  "+ String.valueOf(porcentaje));
	                	pb.setValue(100);
		                txt.setText("Hilo"+ nombre + "  "+ "100");
		                hilo2.setStopHilo(true);
	                	hilo.setStopHilo(true);
	                	JOptionPane.showMessageDialog(null, "Y EL GANADOR ES : HILO" + nombre);
	                	this.boton.setEnabled(true); // no se activa el boton de comenzar hasta que uno de los hilos haya ganado
	                	System.out.println("GANADOR");
	                	// si ha ganado sale de la ejecución e interrumpe la ejecución del resto de hilos
	                	// manda mensaje de interrumpir los hilos y salir
	                	 stopHilo = true;   
	                }
	            
				} // end while
	            
	            System.out.println("fin");
	           // terminar();
         
	        } catch (InterruptedException ex) {
	            System.out.println("Hilo interrumpido");
	        }

		System.out.println(stopHilo);
    }
 
    public static int generaNumeroAleatorio(int minimo, int maximo) {
        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }
}
