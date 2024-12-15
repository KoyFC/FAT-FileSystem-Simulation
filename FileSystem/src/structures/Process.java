package structures;

import java.util.Timer;
import java.util.TimerTask;

public class Process {
	int id;
	String name;
	
	/**
	 * Full Process constructor.
	 * @param id The ID of the new process.
	 * @param name The name of the new process.
	 */
	public Process(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void startProcess() {
		
		
	}
	
	public void alarmFiveSeconds(Process currentProcess)
	{
		
		// 1 - Crear un Timer:
		Timer nombreTemporizador = new Timer();

		// 2 - Definir la tarea que se tiene que ejecutar:
		TimerTask tareaEjecutar = new TimerTask() {
		    public void run() {
		        //destructor
		        nombreTemporizador.cancel(); // Puesto que ya no es necesario tener el temporizador, se borra al ejecutar la acción evitando que siga consumiendo recursos innecesariamente
		    }
		};

		// 3 - Programar la tarea:
		nombreTemporizador.schedule(tareaEjecutar, 5000); // Esto funciona con milisegundos
	}
}
