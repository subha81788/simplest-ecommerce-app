package org.subhashis.simplestecommerceapp.configs;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Slf4j
@Configuration
@EnableMongoRepositories(basePackages = "org.subhashis.simplestecommerceapp.repositories")
public class DataSourceMongoConfig extends AbstractMongoClientConfiguration {

    //@Value("${spring.data.mongodb.uri}")
    //private String mongoDbUri;

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDbName;

    @Value("${spring.data.mongodb.username}")
    private String mongoUserName;

    @Value("${spring.data.mongodb.password}")
    private String mongoPassword;

    @Override
    protected String getDatabaseName() {
        return connectionString().getDatabase();
    }

    @Override
    public MongoClient mongoClient() {
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString())
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

    private ConnectionString connectionString() {
        String mongoDbUri = "mongodb://" + mongoUserName + ":" + mongoPassword + "@" +
                mongoHost + ":" + mongoPort + "/" + mongoDbName +
                "?authSource=admin&authMechanism=SCRAM-SHA-1";
        log.info("DB URI = " + mongoDbUri);
        return new ConnectionString(mongoDbUri);
    }
}
