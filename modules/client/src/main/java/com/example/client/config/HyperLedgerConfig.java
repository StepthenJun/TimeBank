package com.example.client.config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Identities;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

@Configuration
@EnableConfigurationProperties(HyperLedgerFabricProperties.class)
@Slf4j
public class HyperLedgerConfig {
    private final HyperLedgerFabricProperties hyperLedgerFabricProperties;
    private final ResourceLoader resourceLoader;

    public HyperLedgerConfig(HyperLedgerFabricProperties hyperLedgerFabricProperties, ResourceLoader resourceLoader) {
        this.hyperLedgerFabricProperties = hyperLedgerFabricProperties;
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public Gateway gateway() throws Exception {
        // 证书
        Resource certificateResource = resourceLoader.getResource("classpath:" + hyperLedgerFabricProperties.getCertificatePath());
        String certificatePem = IOUtils.toString(certificateResource.getInputStream(), StandardCharsets.UTF_8);
        X509Certificate certificate = Identities.readX509Certificate(certificatePem);

        // 私钥
        Resource privateKeyResource = resourceLoader.getResource("classpath:" + hyperLedgerFabricProperties.getPrivateKeyPath());
        String privateKeyPem = IOUtils.toString(privateKeyResource.getInputStream(), StandardCharsets.UTF_8);
        PrivateKey privateKey = Identities.readPrivateKey(privateKeyPem);

        Wallet wallet = Wallets.newInMemoryWallet();
        wallet.put("user1", Identities.newX509Identity("Org1MSP", certificate, privateKey));

        // 网络配置
        Resource networkConfigResource = resourceLoader.getResource("classpath:" + hyperLedgerFabricProperties.getNetworkConnectionConfigPath());
        Path networkConfigPath = convertResourceToPath(networkConfigResource); // 这部分保持不变
        Gateway.Builder builder = Gateway.createBuilder()
                .identity(wallet, "user1")
                .networkConfig(networkConfigPath);

        return builder.connect();
    }

    private Path convertResourceToPath(Resource resource) throws Exception {
        Path tempFile = Files.createTempFile(null, null);
        try (InputStream inputStream = resource.getInputStream()) {
            Files.copy(inputStream, tempFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }
        return tempFile;
    }
}
