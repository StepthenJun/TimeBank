package com.example.client.config;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Gateway.Builder;
import org.hyperledger.fabric.gateway.Identities;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
@AllArgsConstructor
@Configuration
@EnableConfigurationProperties(HyperLedgerFabricProperties.class)
@Slf4j
public class HyperLedgerConfig {
        final HyperLedgerFabricProperties hyperLedgerFabricProperties;
        @Bean
        public Gateway gateway() throws Exception {
            BufferedReader certificateReader = Files.newBufferedReader(Paths.get(hyperLedgerFabricProperties.getCertificatePath()), StandardCharsets.UTF_8);
            X509Certificate certificate = Identities.readX509Certificate(certificateReader);
            BufferedReader privateKeyReader = Files.newBufferedReader(Paths.get(hyperLedgerFabricProperties.getPrivateKeyPath()), StandardCharsets.UTF_8);
            PrivateKey privateKey = Identities.readPrivateKey(privateKeyReader);
            Wallet wallet = Wallets.newInMemoryWallet();
            wallet.put("user1" , Identities.newX509Identity("Org1MSP", certificate , privateKey));
             Builder builder = Gateway.createBuilder()
                   .identity(wallet , "user1")
                   .networkConfig(Paths.get("admin/src/main/resources/connection.json"));
            Gateway gateway = builder.connect();
            log.info("=========================================== connected fabric gateway {} " , gateway);
            return gateway;
        }
}