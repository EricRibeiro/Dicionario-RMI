package com.ericribeiro.server.rmi;

import com.ericribeiro.server.file.Arquivo;
import com.ericribeiro.server.model.Palavra;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Set;

public class DicionarioServant extends java.rmi.server.UnicastRemoteObject implements Dicionario {

    private static final long serialVersionUID = 1L;

    private Arquivo arquivo;
    private Set<Palavra> dicionario;
    private String caminhoDicionario = "dicionario.json";

    public DicionarioServant() throws java.rmi.RemoteException {
        super();
        this.arquivo = new Arquivo(new File(caminhoDicionario));
        this.dicionario = arquivo.carregarDicionario();
    }

    /**
     *
     * Método responsável por adicionar uma palavra no dicionário.
     *
     * código de retorno "1" implica adição bem sucedida.
     * código de retorno "2" implica adição bem sucedida com sobrescrita de duplicata.
     *
     * @param palavra
     * @return código responsável por notificar o estado da operação.
     * @throws RemoteException
     */
    @Override
    public synchronized Integer adicionar(Palavra palavra) throws RemoteException {
        Integer codigo;

        if (this.dicionario.contains(palavra)) {
            codigo = 2;

        } else {
            codigo = 1;
        }

        this.dicionario.add(palavra);
        this.arquivo.gravar(this.dicionario);

        return codigo;
    }

    /**
     *
     * Método responsável por retornar o significado de uma palavra do dicionário.
     *
     * @param palavra
     * @return palavra pesquisada ou null caso ela não exista no dicionário.
     * @throws RemoteException
     */
    @Override
    public synchronized Palavra consultar(Palavra palavra) throws RemoteException {
        Palavra plvrConsultada = null;

        if (this.dicionario.contains(palavra)) {
            for (Palavra p : this.dicionario) {
                if(p.equals(palavra)) {
                    plvrConsultada = p;
                }
            }
        }

        return plvrConsultada;
    }

    /**
     *
     * Método responsável por remover uma palavra do dicionário.
     *
     * código de retorno "1" implica remoção bem sucedida.
     * código de retorno "2" implica ausência da palavra no dicionário .
     *
     * @param palavra
     * @return código responsável por notificar o estado da operação.
     * @throws RemoteException
     */
    @Override
    public synchronized Integer remover(Palavra palavra) throws RemoteException {
        Integer codigo;

        if(this.dicionario.contains(palavra)) {
            this.dicionario.remove(palavra);
            this.arquivo.gravar(this.dicionario);
            codigo = 1;

        } else {
            codigo = 2;
        }

        return codigo;
    }
}
