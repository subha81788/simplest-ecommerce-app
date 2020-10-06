package org.subhashis.simplestecommerceapp.configs;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Slf4j
@Configuration
@EnableReactiveMongoRepositories(basePackages = "org.subhashis.simplestecommerceapp.repositories")
public class DataSourceReactiveMongoConfig extends AbstractReactiveMongoConfiguration {

    //@Value("${spring.data.mongodb.uri}")
    //private String mongoDbUri;

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Value("${spring.data.mongodb.db}")
    private String mongoDbName;

    @Value("${spring.data.mongodb.username}")
    private String mongoUserName;

    @Value("${spring.data.mongodb.password}")
    private String mongoPassword;

    @Override
    protected String getDatabaseName() {
        return connectionString().getDatabase();
    }

    @Bean
    public MongoClient reactiveMongoClient() {
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString())
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }

    private ConnectionString connectionString() {
        String mongoDbUri = "mongodb://" + mongoUserName + ":" + mongoPassword + "@" +
                mongoHost + ":" + mongoPort + "/" + mongoDbName +
                "?authSource=admin&authMechanism=SCRAM-SHA-1";
        log.info("DB URI = " + mongoDbUri);
        return new ConnectionString(mongoDbUri);
    }
}
