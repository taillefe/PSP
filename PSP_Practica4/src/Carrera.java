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

	 JFrame frame;
	 JTextField txt1;
	 JTextField txt2;
	 JTextField txt3;
	JButton btnComenzar;
	JSlider sld1;
	JProgressBar pb1;
	JSlider sld2;
	JProgressBar pb2;
	JSlider sld3;
	JProgressBar pb3;
	/**
	 * Apliga
	 */

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
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 591, 378);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		 	
		sld1 = new JSlider();
		sld1.setMinimum(1);
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
		 sld2.setMinimum(1);
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
		 sld3.setMinimum(1);
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
		
		btnComenzar = new JButton("Comenzar Carrera");
		btnComenzar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
							
				comenzarCarrera();
				
			}
		});
		btnComenzar.setBounds(186, 11, 201, 23);
		frame.getContentPane().add(btnComenzar);
			
	}
	

	public void comenzarCarrera() {
		
			long numAleatorio;
			this.btnComenzar.setEnabled(false);
		                
        	numAleatorio = (long) Math.floor(Math.random() *1000)+1;
        	
        	//Creamos tres hilos de tres clases diferentes
        	// cada una de ellas lleva como parámetro los otros dos hilos
        	//para poder pararlos dentro del método run si es el hilo ganador
        	
        	Hilo h = null;
        	Hilo2 h2 = null;
        	Hilo3 h3= null;
        	 h2 = new Hilo2(2 +"",pb2, txt2, 1000,h,h3,btnComenzar);
             h3 = new Hilo3(3 +"",pb3, txt3, 1000,h,h2,btnComenzar);
            h = new Hilo(1 +"",pb1, txt1, 1000, h2,h3,btnComenzar);
            h2.setHilo(h);
            h2.setHilo3(h3);
            h3.setHilo(h);
         
            // damos la prioridad a los hilos
            h.setPriority(sld1.getValue());
            h2.setPriority(sld2.getValue());
            h3.setPriority(sld3.getValue());
           
            // ejecutamos cada uno de los hilos
            h.start();
            h2.start();
            h3.start();
    
  		
	}
	

}


