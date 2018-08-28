package com.ericribeiro.server.rmi;

import com.ericribeiro.server.model.Palavra;

import java.rmi.RemoteException;

public interface Dicionario extends java.rmi.Remote {

	Integer adicionar(Palavra palavra) throws RemoteException;

	Palavra consultar(Palavra palavra) throws RemoteException;

	Integer remover(Palavra palavra) throws RemoteException;
}
