import java.io.IOException;

public class Main {

	public static void main(String[] args) throws ExcepcionTarjeta, IOException, ExcepcionTransaccion, ExcepcionCuenta {
		Cajero cajero = new Cajero();
		cajero.iniciar();
	}


}
