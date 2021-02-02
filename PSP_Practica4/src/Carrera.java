import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Carrera {

	public String nombreGanador;
	private JFrame frame;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	JButton btnComenzar;
	JSlider sld1;
	JProgressBar pb1;
	JSlider sld2;
	JProgressBar pb2;
	JSlider sld3;
	JProgressBar pb3;
	
	private Hilo[] hilos;
	private JProgressBar[] barras;
    private JSlider[] deslizadores;
    private JTextField[] textos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Carrera window = new Carrera();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	public Carrera() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		hilos = new Hilo[3];
		barras = new JProgressBar[3];
		deslizadores = new JSlider[3];
		textos = new JTextField[3];
		
		frame = new JFrame();
		frame.setBounds(100, 100, 591, 378);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		 btnComenzar = new JButton("Comenzar Carrera");
		btnComenzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				comenzarCarrera();
				 // cuando da al boton de aceptar el ganador se inicializan todos los valores
		          inicializarDatos();
            
			
			}
		});
		btnComenzar.setBounds(186, 11, 201, 23);
		frame.getContentPane().add(btnComenzar);
		
		sld1 = new JSlider();
		sld1.setValue(5);
		sld1.setMinorTickSpacing(1);
		sld1.setMaximum(10);
		sld1.setPaintTicks(true);
		sld1.setMajorTickSpacing(1);
		sld1.setPaintLabels(true);
		sld1.setBounds(174, 53, 228, 36);
		frame.getContentPane().add(sld1);
		
		 pb1 = new JProgressBar();
		pb1.setStringPainted(true);
		pb1.setBounds(32, 100, 506, 23);
		frame.getContentPane().add(pb1);
		
		 sld2 = new JSlider();
		 sld2.setMinorTickSpacing(1);
		 sld2.setSnapToTicks(true);
		 sld2.setValue(5);
		 sld2.setMaximum(10);
		sld2.setMajorTickSpacing(1);
		sld2.setPaintTicks(true);
		sld2.setPaintLabels(true);
		sld2.setBounds(174, 134, 228, 36);
		frame.getContentPane().add(sld2);
		
		 pb2 = new JProgressBar();
		pb2.setStringPainted(true);
		pb2.setBounds(32, 181, 506, 23);
		frame.getContentPane().add(pb2);
		
		 sld3 = new JSlider();
		 sld3.setValue(5);
		 sld3.setMaximum(10);
		sld3.setMajorTickSpacing(1);
		sld3.setPaintTicks(true);
		sld3.setPaintLabels(true);
		sld3.setBounds(174, 215, 228, 36);
		frame.getContentPane().add(sld3);
		
		 pb3 = new JProgressBar();
		pb3.setStringPainted(true);
		pb3.setBounds(32, 262, 506, 23);
		frame.getContentPane().add(pb3);
		
		txt1 = new JTextField();
		txt1.setBounds(32, 296, 86, 20);
		frame.getContentPane().add(txt1);
		txt1.setColumns(10);
		
		txt2 = new JTextField();
		txt2.setBounds(242, 296, 86, 20);
		frame.getContentPane().add(txt2);
		txt2.setColumns(10);
		
		txt3 = new JTextField();
		txt3.setBounds(452, 296, 86, 20);
		frame.getContentPane().add(txt3);
		txt3.setColumns(10);
	}
	
	public void comenzarCarrera() {
		
		long numAleatorio;
		
		btnComenzar.setEnabled(false);
		boolean noGanador = true;
		
		barras[0] = pb1;
		barras[1] = pb2;
		barras[2] = pb3;
		textos[0] = txt1;
		textos[1] = txt2;
		textos[2] = txt3;
		deslizadores[0] = sld1;
		deslizadores[1] = sld2;
		deslizadores[2] = sld3;
		
		
	//	textos[0].setText("GANADOR");
        for (int i = 0; i < hilos.length; i++) {
                
        	numAleatorio = (long) Math.floor(Math.random() *1000)+1;
            Hilo h = new Hilo((i+1)+"",barras[i], textos[i], numAleatorio);
       
            hilos[i] =h;
            // damos la prioridad al hilo
            h.setPriority(deslizadores[i].getValue());
            System.out.println("prioridad : "+ deslizadores[i].getValue());
            h.start();
           
        }
        
        // nombreGanador es una variable global
     //   JOptionPane.showMessageDialog(null, "Y EL GANADOR ES : HILO" + nombreGanador);
    /*    try {
            //Ponemos a "Dormir" el programa durante los ms que queremos
            Thread.sleep(5*10);
         } catch (Exception e) {
            System.out.println(e);
         }
      */  //comprobar si alguno de los hilos llega a 100 y parar los otros dos
    /*    while (noGanador){
        	for (int i = 0; i < hilos.length; i++) {
        	//	System.out.println("PORCENTAJE HILO : "+hilos[i].getNombre() +" "+hilos[i].getPorcentaje());
        		
        		if (hilos[i].getPorcentaje() == 100) {
        			System.out.println("AAAAAAA");
        			// el hilo i + 1 será el ganador y hay que parar los otros dos
        			for (int j = 0; j < hilos.length; j++) {
        				hilos[j].setStopHilo(true);
        			}
        			noGanador = false;
        			
        		}
        	//	System.out.println("estado hilo : "+hilos[i].getNombre() +" " + hilos[i].getState());	
        	}
        }
     */
     
	}
	
	public void inicializarDatos() {
		 
		 btnComenzar.setEnabled(true);
 
		 pb1.setValue(0);
		 pb2.setValue(0);
		 pb3.setValue(0);
		txt1.setName("Hilo1 :  ");
		txt2.setName("Hilo2 :  ");
		txt3.setName("Hilo3 :  ");
		sld1.setValue(5);
		sld2.setValue(5);
		sld3.setValue(5);
		
        }
         

	
	private void terminar(){
        
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].interrupt();
        }
         
    }
}


