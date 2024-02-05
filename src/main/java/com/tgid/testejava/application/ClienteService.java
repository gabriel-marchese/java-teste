package com.tgid.testejava.application;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tgid.testejava.adapters.ClienteGateway;
import com.tgid.testejava.core.TransactionInformation;
import com.tgid.testejava.core.exceptions.CallbackException;

@Service
public class ClienteService {

    private final ClienteGateway clienteGateway;

    @Autowired
    public ClienteService(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public void doDeposit(TransactionInformation transactionInformation) {
        clienteGateway.doDeposit(transactionInformation.cnpj(), transactionInformation.valueInCents());
        sendCallback(createTransactionInformation(transactionInformation.valueInCents(), "Deposito", transactionInformation.cnpj(), transactionInformation.cpf()));
    }

    public void doWithdraw(TransactionInformation transactionInformation) {
        clienteGateway.doWithdraw(transactionInformation.cnpj(), transactionInformation.valueInCents());
        sendCallback(createTransactionInformation(transactionInformation.valueInCents(), "Saque", transactionInformation.cnpj(), transactionInformation.cpf()));
    }

    @Value("${url}")
    private String empresaCallbackUrl;


    public void sendCallback(TransactionInformation transactionInformation) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Random random = new Random();
        int transactionId = random.nextInt(100000);

        String callbackBody = String.format(
            "{ \"transactionId\": %d, \"valueInCents\": %d, \"type\": \"%s\", \"cpf\": \"%s\", \"cnpj\": \"%s\" }",
            transactionId,
            transactionInformation.valueInCents(),
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

    private TransactionInformation createTransactionInformation(Integer valueInCents, String type, String cpf, String cnpj) {
        return new TransactionInformation(valueInCents, type, cpf, cnpj);
    }
}
