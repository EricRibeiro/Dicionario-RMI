package com.ericribeiro.client.main;

import com.ericribeiro.client.dialog.Dialog;
import com.ericribeiro.server.model.Palavra;
import com.ericribeiro.server.rmi.Dicionario;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class DicionarioClient {

    /**
     *
     * Adiciona uma nova palavra no dicionário
     *
     * @param dicionario
     * @throws RemoteException
     */
    private static void adicionarPlvr(Dicionario dicionario) throws RemoteException {
        Palavra novaPlvr = Dialog.inserirNovaEntrada();
        Integer codOpAdicao = dicionario.adicionar(novaPlvr);

        if (codOpAdicao.equals(1)) {
            Dialog.exibirMsgInfo("A palavra '" + novaPlvr.getPalavra() + "' foi adicionada.");

        } else {
            Dialog.exibirMsgInfo("A palavra '" + novaPlvr.getPalavra() + "' foi adicionada, sobrescrevendo uma entrada anterior.");
        }
    }

    /**
     *
     * Consulta o significado de uma palavra existente no dicionário.
     *
     * @param dicionario
     * @throws RemoteException
     */
    private static void consultarPlvr(Dicionario dicionario) throws RemoteException {
        String plvrInserida = Dialog.inserirPalavra("<html>Insira a palavra que deseja consultar.</html>");
        Palavra plvrConsultada = dicionario.consultar(new Palavra(plvrInserida));

        if (plvrConsultada != null) {
            Dialog.exibirPlvrConsultada(plvrConsultada);

        } else {
            Dialog.exibirMsgErro("A palavra '" + plvrInserida + "' não está cadastrada no dicionário.");
        }
    }

    /**
     *
     * Remove uma palavra do dicionário.
     *
     * @param dicionario
     * @throws RemoteException
     */
    private static void removerPlvr(Dicionario dicionario) throws RemoteException {
        String plvrRemovida = Dialog.inserirPalavra("<html>Insira a palavra que deseja remover.</html>");
        Integer codOpRemocao = dicionario.remover(new Palavra(plvrRemovida));

        if (codOpRemocao.equals(1)) {
            Dialog.exibirMsgInfo("A palavra '" + plvrRemovida + "' foi removida.");

        } else {
            Dialog.exibirMsgErro("A palavra '" + plvrRemovida + "' não está cadastrada no dicionário.");
        }
    }

    public static void main(String[] args) {

		String servidor = Dialog.inserirEndrServidor();
        String nome = servidor.substring(servidor.lastIndexOf("/") + 1);

        try {
            Dicionario dicionario = (Dicionario) Naming.lookup(servidor);
            Dialog.exibirMsgInfo("Objeto remoto \'" + nome + "\' encontrado no servidor.");

            while (true) {
                String opcao = Dialog.exibirOpcoes();

                switch (opcao) {
                    case "Adicionar":
                        adicionarPlvr(dicionario);
                        break;

                    case "Consultar":
                        consultarPlvr(dicionario);
                        break;

                    case "Remover":
                        removerPlvr(dicionario);
                        break;
                }
            }

        } catch (MalformedURLException e) {
            String mensagem = "URL \'" + servidor + nome + "\' mal formatada.";
            Dialog.exibirMsgErro(mensagem);
            e.printStackTrace();

        } catch (RemoteException e) {
            String mensagem = "Erro na invocação remota.";
            Dialog.exibirMsgErro(mensagem);
            e.printStackTrace();

        } catch (NotBoundException e) {
            String mensagem = "Objeto remoto \'" + nome + "\' não está disponível.";
            Dialog.exibirMsgErro(mensagem);
            e.printStackTrace();

        } catch (NullPointerException e) {
            String mensagem = "O programa será encerrado.";
            Dialog.exibirMsgInfo(mensagem);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }
}
