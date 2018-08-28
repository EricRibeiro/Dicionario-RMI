package com.ericribeiro.server.file;

import com.ericribeiro.server.model.Palavra;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Arquivo {

    private ObjectMapper objectMapper = new ObjectMapper();
    private File arquivo;

    public Arquivo(File arquivo) {
        carregarArquivo(arquivo);
    }

    private void carregarArquivo(File arquivo) {
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();

            } catch (IOException e) {
                System.out.println("Falha ao criar novo arquivo: " + e.toString());
                e.printStackTrace();
                System.exit(-1);
            }
        }

        this.arquivo = arquivo;
    }

    public Set<Palavra> carregarDicionario() {
        Set<Palavra> dicionario = null;

        try {
            dicionario = this.objectMapper.readValue(this.arquivo, new TypeReference<Set<Palavra>>() {});

        } catch (MismatchedInputException e) {
            dicionario = new HashSet<>();

        } catch (IOException e) {
            System.out.println("Falha ao carregar o dicionário: " + e.toString());
            e.printStackTrace();
            System.exit(-1);
        }

        return dicionario;
    }

    public void gravar(Set<Palavra> dicionario) {
        try {
            objectMapper.writeValue(arquivo, dicionario);

        } catch (IOException e) {
            System.out.println("Falha ao gravar os dados no arquivo: " + e.toString());
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
