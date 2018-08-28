package com.ericribeiro.server.rmi;

import java.rmi.Naming;

public class DicionarioServer {

	public DicionarioServer() {
		try {
			Dicionario c = new DicionarioServant();
			Naming.rebind("rmi://localhost/DicionarioService", c);
			System.out.println("Servidor Dicionario em execução.");
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a conexão com o servidor: " + e.toString());
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static void main(String args[]) {
		new DicionarioServer();
	}
}
