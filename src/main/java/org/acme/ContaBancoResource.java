package org.acme;

import GlobalExceptionHandler.ContaInvalidaException;
import GlobalExceptionHandler.SaldoInsuficienteException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import models.ContaCorrente;
import services.ContaCorrenteService;
import services.ContaCorrenteServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Path("/contacorrente")
public class ContaBancoResource {
    List<ContaCorrente> contaCorrentes = new ArrayList<>();

    ContaCorrenteService correnteService = new ContaCorrenteServiceImpl(contaCorrentes);
    ContaCorrente contaCorrente;

    //lista contas cadastradas
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public List<String> listaContas() {
        List<ContaCorrente> contas = correnteService.listaContas();
        List<String> dadosContas = new ArrayList<>();

        for (ContaCorrente contaCorrente : contas) {
            dadosContas.add(String.format("Nº conta: %s; Saldo: %s", contaCorrente.getNumeroConta(),
                    contaCorrente.getSaldo()));

        }

        return dadosContas;
    }
//cadastra contas
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String criarConta(@FormParam("nome") String nome, @FormParam("cpf") String cpf) {

        try {
             contaCorrente = correnteService.criarConta(nome, cpf);

            return String.format("Você criou uma nova conta!" +
                    "\nNúmero da Conta: " + contaCorrente.getNumeroConta() +
                    "\nNome: " + nome +
                    "\nCPF: " +  cpf +
                    "\nSaldo: " + contaCorrente.getSaldo()
            );

        } catch (ContaInvalidaException e) {
            return e.getMessage();
        }

    }

    @POST
    @Path("/sacar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String sacar(@FormParam("numeroConta") String numeroConta, @FormParam("valor") double valor) {
        try {
            correnteService.sacar(numeroConta, valor);
            return "Nº da Conta: " + numeroConta + " \nValor sacado: " + valor;
        } catch (SaldoInsuficienteException e) {
            return  e.getMessage();
        } catch (ContaInvalidaException e) {
            return "Saque não realizado " + e.getMessage();
        }
    }

    @PATCH
    @Path("/transferir")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String transferir(@FormParam("contaOrigem") String contaOrigem, @FormParam("contaDestino") String contaDestino,
                             @FormParam("valor") double valor) {
        try {
            correnteService.transferir(contaOrigem, contaDestino, valor);

            return "Valor transferido: R$ " + valor +
                    "\nConta de origem: " + contaOrigem + "\nConta destino: " + contaDestino;
        } catch (ContaInvalidaException e) {
            return "Transferência não realizada " + e.getMessage();
        } catch (SaldoInsuficienteException e) {
            return  e.getMessage();
        }
    }

    @POST
    @Path("/depositar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String depositar(@FormParam("numeroConta") String numeroConta, @FormParam("valor") double valor) {

        try {
            correnteService.depositar(numeroConta, valor);
            return "Valor depositado: " + valor + "\nNº da conta: " + numeroConta;

        } catch (ContaInvalidaException e) {
            return e.getMessage();
        }
    }
    @Path("/deletar")
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String excluir(@FormParam("numeroConta") String numeroConta) {
        try {
            correnteService.excluir(numeroConta);
            return "Nº conta: " + numeroConta + "\nExcluida com sucesso!";

        } catch (ContaInvalidaException e) {
            return e.getMessage();
        }
    }
}