package services;

import GlobalExceptionHandler.ContaInvalidaException;
import GlobalExceptionHandler.SaldoInsuficienteException;
import jakarta.enterprise.context.ApplicationScoped;
import models.ContaCorrente;

import java.util.List;


public interface ContaCorrenteService {
    ContaCorrente getContaPorNumero(String numeroConta);
    void depositar(String numeroConta, double valor)
            throws ContaInvalidaException;
    void sacar(String numeroConta, double valor)
            throws ContaInvalidaException, SaldoInsuficienteException;
    void transferir(String contaOrigem, String contaDestino, double valor)
            throws ContaInvalidaException, SaldoInsuficienteException;
    ContaCorrente criarConta(String nome, String cpf)
            throws ContaInvalidaException;


    void excluir(String numeroConta) throws ContaInvalidaException;

    List<ContaCorrente> listaContas();
}


