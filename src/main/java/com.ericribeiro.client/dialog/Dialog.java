package com.ericribeiro.client.dialog;

import com.ericribeiro.server.model.Palavra;

import javax.swing.*;

public class Dialog {

    public static void exibirPlvrConsultada(Palavra palavra) {
        JTextField jTextField = new JTextField();
        jTextField.setEditable(false);
        jTextField.setText(palavra.getPalavra());

        JTextArea jTextArea = new JTextArea(6, 20);
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);
        jTextArea.setText(palavra.getSignificado());
        jTextArea.setWrapStyleWord(true);

        Object[] message = {
                "Palavra:", jTextField,
                "Significado:", jTextArea,
        };

        JOptionPane.showMessageDialog(null, message, "Dicionário", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void exibirMsgErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void exibirMsgInfo(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Informação", JOptionPane.INFORMATION_MESSAGE);
    }

    public static String exibirOpcoes() {
        Object[] opcoes = {"Adicionar", "Consultar", "Remover"};

        Object opcao = JOptionPane.showInputDialog(null,
                "Escolha uma operação:", "Dicionário",
                JOptionPane.INFORMATION_MESSAGE, null,
                opcoes, opcoes[0]);

        return opcao.toString();
    }

    public static String inserirEndrServidor() {
        String mensagem = "<html>Insira o caminho do inserirEndrServidor." + "<br/>" + "<h5><i>e.g</i> rmi://localhost/DicionarioService<h5></html>";

        String servidor = JOptionPane.showInputDialog(null,
                mensagem, "Dicionário",
                JOptionPane.INFORMATION_MESSAGE);

        return servidor;
    }

    public static Palavra inserirNovaEntrada() {
        JTextField jTextField = new JTextField();
        JTextArea jTextArea = new JTextArea(6, 20);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);

        Palavra novaEntrada = null;

        Object[] message = {
                "Palavra:", jTextField,
                "Significado:", jTextArea,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Adicionar Palavra", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String palavra = jTextField.getText();
            String significado = jTextArea.getText();
            novaEntrada = new Palavra(palavra, significado);
        }

        return novaEntrada;
    }

    public static String inserirPalavra(String mensagem) {
        String palavra = JOptionPane.showInputDialog(null,
                mensagem, "Dicionário",
                JOptionPane.INFORMATION_MESSAGE);

        return palavra;
    }
}
