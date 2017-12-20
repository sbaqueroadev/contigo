package co.com.sbaqueroadev.contigo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "co.com.sbaqueroadev.contigo.dao")
public class MongoConfig extends AbstractMongoConfiguration {
  
    @Override
    protected String getDatabaseName() {
        return "contigo";
    }
  
    @Override
    protected String getMappingBasePackage() {
    	return "co.com.sbaqueroadev.contigo";
    }

		@Override
		public MongoClient mongoClient() {
			return new MongoClient("127.0.0.1", 27017);
		}
		
		@Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(),getDatabaseName());
    }
}