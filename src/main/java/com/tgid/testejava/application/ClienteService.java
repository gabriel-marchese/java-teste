package com.tgid.testejava.application;

import com.tgid.testejava.domain.cliente.Cliente;
import com.tgid.testejava.domain.empresa.Empresa;
import com.tgid.testejava.dtos.ClienteDto;
import com.tgid.testejava.repositories.ClienteRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tgid.testejava.core.TransactionInformation;
import com.tgid.testejava.core.exceptions.CallbackException;

@Service
public class ClienteService {

    private final EmailSenderService emailSenderService;

    @Autowired
    public ClienteService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }
    
    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ClienteRepository repository;

    public Cliente findClienteByCpf(String cpf) throws Exception {
        return this.repository.findUserByCpf(cpf).orElseThrow(
            () -> new Exception("Cliente não encontrado"));
    }

    public void newDeposit(TransactionInformation transaction) throws Exception {
        Cliente cliente = this.findClienteByCpf(transaction.cpf());
        Empresa empresa = this.empresaService.findEmpresaByCnpj(transaction.cnpj());
        if (empresa.getSaldo().compareTo(transaction.value()) < 0) {
            throw new Error("Saldo insuficiente");
        }
        String body = String.format("Depósito no valor de %s para CNPJ: %s do CPF: %s", transaction.value(), transaction.cnpj(), transaction.cpf());
        empresa.setSaldo(empresa.getSaldo().add(transaction.value()));
        TransactionInformation teste = this.createTransactionInformation(transaction.value(), "Deposit", transaction.cpf(), transaction.cnpj());
        sendCallback(teste);
        emailSenderService.sendEmail(cliente.getEmail(), "Deposito", body);
    }
    

    public void saveCliente(Cliente cliente) {
        this.repository.save(cliente);
    }

    public Cliente createCliente(ClienteDto data) {
        Cliente newCliente = new Cliente(data);
        this.saveCliente(newCliente);
        return newCliente;
    }

    public List<Cliente> getAllClientes() {
       return this.repository.findAll();
    }
    

    @Value("${url}")
    private String empresaCallbackUrl;


    public void sendCallback(TransactionInformation transactionInformation) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Random random = new Random();
        int transactionId = random.nextInt(100000);

        String callbackBody = String.format(
            "{ \"transactionId\": %d, \"value\": %s, \"type\": \"%s\", \"cpf\": \"%s\", \"cnpj\": \"%s\" }",
            transactionId,
            transactionInformation.value(),
            transactionInformation.type(),
            transactionInformation.cpf(),
            transactionInformation.cnpj()
    );

        HttpEntity<String> requestEntity = new HttpEntity<>(callbackBody, headers);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(empresaCallbackUrl, HttpMethod.POST, requestEntity, String.class);
            System.out.println("Resposta do Callback: " + responseEntity.getBody());
        } catch (Exception exception) {
            throw new CallbackException("Erro durante a execução do callback", exception);
        }
    }

    private TransactionInformation createTransactionInformation(BigDecimal value, String type, String cpf, String cnpj) {
        return new TransactionInformation(value, type, cpf, cnpj);
    }
}
