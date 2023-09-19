package com.itau.cobranca.service;

import com.itau.cobranca.model.Cobranca;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

public class CanaisCobranca {
    private SnsClient snsClient;

    @Autowired
    public void SnsService() {
        this.snsClient = SnsClient.builder()
                .region(Region.SA_EAST_1)
                .build();
    }

    public CanaisCobranca(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    public void cobrancaEmail(Cobranca cobranca){
        PublishRequest request = PublishRequest.builder()
                .message("Pendente pagamento de"+ cobranca.getDescricao()+ " no valor de "+ cobranca.getValor()+
                        ". Vencimento em: "+ cobranca.getDataVencimento())
                .phoneNumber(cobranca.getCliente().getCelular())  // Número de telefone para enviar o SMS
                .build();

        snsClient.publish(request);
    }

    public void cobrancaSNS(Cobranca cobranca){
        PublishRequest request = PublishRequest.builder()
                .message("Pendente pagamento de"+ cobranca.getDescricao()+ " no valor de "+ cobranca.getValor()+
                        ". Vencimento em: "+ cobranca.getDataVencimento())
                .topicArn("arn:aws:sns:sa-east-1:973397151706:cobranca-topic") // cobranca-topic-email
                .build();

        snsClient.publish(request);
    }
}
