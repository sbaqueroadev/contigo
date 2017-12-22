package co.com.sbaqueroadev.contigo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@EnableMongoRepositories(basePackages = "co.com.sbaqueroadev.contigo.dao")
public class MongoConfig extends AbstractMongoConfiguration {
  
		@Autowired
		OwnProperties properties;
	
    @Override
    protected String getDatabaseName() {
    	if(properties.isHeroku()){        
    		return "heroku_0gfrmdn7";
    	}else{
    		return "contigo";
    	}
    }
  
    @Override
    protected String getMappingBasePackage() {
    	return "co.com.sbaqueroadev.contigo";
    }

		@Override
		public MongoClient mongoClient() {
			//mongodb://heroku_0gfrmdn7:or0uhb6nr33ig0e1o8l1klvd8l@ds163826.mlab.com:63826/heroku_0gfrmdn7
			//return new MongoClient("127.0.0.1", 27017);
			//return new MongoClient("mongodb://heroku_0gfrmdn7:or0uhb6nr33ig0e1o8l1klvd8l@ds163826.mlab.com:63826/heroku_0gfrmdn7");
			if(properties.isHeroku()){
				return new MongoClient(new MongoClientURI(System.getenv("MONGODB_URI")));
			}else{
				return new MongoClient("127.0.0.1", 27017);
			}
		}
		
		@Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(),getDatabaseName());
    }
}