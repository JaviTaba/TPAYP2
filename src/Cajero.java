import java.io.IOException;
import java.util.Scanner;

public class Cajero {
	private int billetesDeCien = 500;
	private int billetesDeQuinientos = 500;
	private int billetesDeMil = 500;
	private ArchivoDeCuentas lector;
	private MensajesATM mensaje;
	private Scanner sc;
	private Long cuit;
	private Tarjeta tarjeta;
	private Ticket ticket;

	public Cajero() throws IOException {
		lector = new ArchivoDeCuentas();
		mensaje = new MensajesATM();
		sc = new Scanner(System.in);
		ticket = new Ticket();
		
	}
	public void iniciar() throws ExcepcionTarjeta, ExcepcionTransaccion, IOException {
		
		
		mensaje.bienvenidaYTarjeta();
		int numeroTarjeta = sc.nextInt();
		mensaje.ingresePin();
		int pin = sc.nextInt();
		
		tarjeta = new Tarjeta(numeroTarjeta, pin);
		
		
		cuit = lector.getTarjetas().getCuitTarjeta().get(tarjeta);
		
		if(cuit != null) {
			mensaje.queOperacionDeseaHacer();
			int operacion = sc.nextInt();
			
			switch(operacion) {
			
			case 1:
				extraerEfectivo();
				break;
			case 2:
				comprarUSD();
				break;
			case 3:
				depositarEfectivo();
				break;
			case 4:
				transferenciaEntreCuentas();
				break;
			}
		}
		else {
			mensaje.numeroOPinIncorrectos();
			mensaje.reintentar();
			int reintentar = sc.nextInt();
			if(reintentar==1) {
				iniciar();
			}else {
				mensaje.adios();
			}
		}

	}
	
	private void extraerEfectivo() throws ExcepcionTransaccion, IOException {

		mensaje.extraerEfectivo();
		int cuenta = sc.nextInt();
		mensaje.extraerEfectivoMonto();
		int monto = sc.nextInt();
		
		switch(cuenta) {
		
		case 1:
			lector.getTarjetas().getCuitCliente().get(cuit).getArs().extraer(monto);
			ticket.extraer(lector.getTarjetas().getCuitCliente().get(cuit).getArs().getSaldo(), monto, lector.getTarjetas().getCuitCliente().get(cuit).getArs());
			dispensar(monto);
			break;
			
		case 2:
			lector.getTarjetas().getCuitCliente().get(cuit).getCC().extraer(monto);
			ticket.extraer(lector.getTarjetas().getCuitCliente().get(cuit).getCC().getSaldo(), monto, lector.getTarjetas().getCuitCliente().get(cuit).getCC());
			dispensar(monto);
			break;
		}
		
	}
	
	
	private void comprarUSD() throws ExcepcionTransaccion, IOException {
		mensaje.comprarUSD();
		int cuenta = sc.nextInt();
		mensaje.comprarUSDMonto();
		int monto = sc.nextInt();
		
		switch(cuenta) {
		
		case 1:
			lector.getTarjetas().getCuitCliente().get(cuit).getArs().comprarUSD(lector.getTarjetas().getCuitCliente().get(cuit), monto);
			ticket.comprarUSD(lector.getTarjetas().getCuitCliente().get(cuit).getArs().getSaldo(), monto, 	lector.getTarjetas().getCuitCliente().get(cuit).getArs());
			break;
			
		case 2:
			lector.getTarjetas().getCuitCliente().get(cuit).getCC().comprarUSD(lector.getTarjetas().getCuitCliente().get(cuit), monto);
			ticket.comprarUSD(lector.getTarjetas().getCuitCliente().get(cuit).getCC().getSaldo(), monto, lector.getTarjetas().getCuitCliente().get(cuit).getCC());
			break;
			
		}
	}
	
	private void depositarEfectivo() throws ExcepcionTransaccion,IOException {
		mensaje.depositarEfectivo();
		int cuenta = sc.nextInt();
		mensaje.depositarEfectivoMonto();
		int monto = sc.nextInt();
		
		switch(cuenta) {
		
		case 1:
			lector.getTarjetas().getCuitCliente().get(cuit).getArs().depositar(monto);
			ticket.depositar(lector.getTarjetas().getCuitCliente().get(cuit).getArs().getSaldo(), monto,lector.getTarjetas().getCuitCliente().get(cuit).getArs() );
			break;
		
		case 2:
			lector.getTarjetas().getCuitCliente().get(cuit).getCC().depositar(monto);
			ticket.depositar(lector.getTarjetas().getCuitCliente().get(cuit).getCC().getSaldo(), monto, lector.getTarjetas().getCuitCliente().get(cuit).getCC());
			break;
			
		case 3:
			lector.getTarjetas().getCuitCliente().get(cuit).getUSD().depositar(monto);
			ticket.depositar(lector.getTarjetas().getCuitCliente().get(cuit).getUSD().getSaldo(), monto, lector.getTarjetas().getCuitCliente().get(cuit).getUSD());
			break;
		}
		
		
	}


	private void transferenciaEntreCuentas() throws ExcepcionTransaccion, IOException {
		mensaje.transferenciaEntreCuentas();
		int cuenta = sc.nextInt();
		mensaje.transferenciaEntreCuentasMonto();
		int monto = sc.nextInt();
		
		switch(cuenta) {
		
		case 1:
			lector.getTarjetas().getCuitCliente().get(cuit).getCC().transferir(lector.getTarjetas().getCuitCliente().get(cuit), monto);
			ticket.transferir(lector.getTarjetas().getCuitCliente().get(cuit).getCC().getSaldo(), monto, lector.getTarjetas().getCuitCliente().get(cuit).getCC());
			break;
			
		case 2:
			lector.getTarjetas().getCuitCliente().get(cuit).getArs().transferir(lector.getTarjetas().getCuitCliente().get(cuit), monto);
			ticket.transferir(lector.getTarjetas().getCuitCliente().get(cuit).getArs().getSaldo(), monto, lector.getTarjetas().getCuitCliente().get(cuit).getArs());
			break;
		}
	}
	
		
	
	private void dispensar(int monto) {
		if (monto>100 && monto%100 == 0) {
			if(monto < 500) {
				billetesDeCien -= monto/100;
			} else if(monto == 500 || monto < 1000) {
				billetesDeQuinientos--;
				billetesDeCien -= (monto-500)/100;
			} else {
				billetesDeMil -= monto/1000;
			}
	}
	

		
	}
	
		
		
		
		
		
		
		
		
	


}
