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


    @Override
    public Integer adicionar(Palavra palavra) throws RemoteException {
        Integer codigo;

        if (this.dicionario.contains(palavra)) {
            this.dicionario.add(palavra);
            codigo = 2;

        } else {
            this.dicionario.add(palavra);
            codigo = 1;
        }

        arquivo.gravar(this.dicionario);

        return codigo;
    }

    @Override
    public Palavra consultar(Palavra palavra) throws RemoteException {
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
     * código de retorno "1" implica remoção bem sucedida.
     * código de retorno "2" implica ausência da palavra no dicionário .
     *
     * @param palavra
     * @return código responsável por notificar o estado da operação.
     * @throws RemoteException
     */
    @Override
    public Integer remover(Palavra palavra) throws RemoteException {
        Integer codigo;

        if(this.dicionario.contains(palavra)) {
            this.dicionario.remove(palavra);
            codigo = 1;

        } else {
            codigo = 2;
        }

        return codigo;
    }
}
