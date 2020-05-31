public class CajaAhorroPesos extends Pesos{
    
    public CajaAhorroPesos( String alias, double saldo) throws ExcepcionCuenta {
    	
    	super(alias, saldo);
    	
    }

	@Override
	public void extraer(int monto) {
		if(verificarMonto(monto) && verificarSaldo(monto)) {
			saldo-= monto;
		
			//hay que agregar la parte de ticket y modificar el txt

		}
	}

	@Override
	public void transferir(Cliente cliente, int monto) {
		if(verificarMonto(monto) && verificarSaldo(monto)) {
			saldo -= monto;
			cliente.getCC().depositar(monto);
		}
		//hay que agregar la parte de ticket y modificar el txt
	}


	@Override
	public void comprarUSD(Cliente cliente,int monto) {
		int sinImpuestos = monto*70;
		int conImpuestos = sinImpuestos+= sinImpuestos*0.3;
		if(verificarMonto(monto) && verificarSaldo(monto)) {
			if(cliente.getUSD()!= null) {
				saldo-= conImpuestos;
				cliente.getUSD().depositar(monto);
				
			}
			
			//hay que agregar la parte de ticket y modificar el txt
		
		}
	}

	@Override
	public void depositar(int monto) {
		if(verificarMonto(monto)) {
			saldo+= monto;
			
			//hay que agregar la parte de ticket y modificar el txt

		}
	}

    
}