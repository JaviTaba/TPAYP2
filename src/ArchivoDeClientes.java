import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ArchivoDeClientes {
	
	HashMap<String, Cliente> aliasCliente;
	ArchivoDeTarjetas cliente;
	
	
	public void lectorClientes() throws IOException {
		FileReader fr = new FileReader("archivos/clientes.txt");
		BufferedReader br = new BufferedReader(fr);
		aliasCliente = new HashMap<>();
		this.cliente = new ArchivoDeTarjetas();		
		
		
		String linea = br.readLine();
		
		while(linea != null) {
			String[] spliteado = linea.split(",");
			
			aliasCliente.put(spliteado[1], cliente.getCuitCliente().get(Long.parseLong(spliteado[1])) );
			
		}
		br.close();
		
	}


	public HashMap<String, Cliente> getAliasCliente() {
		return aliasCliente;
	}

}