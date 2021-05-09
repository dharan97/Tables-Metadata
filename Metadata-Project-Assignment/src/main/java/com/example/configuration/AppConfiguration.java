package com.example.configuration;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("file:${config}/app.properties")
public class AppConfiguration {

	private static final String MYSQL_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

	@Autowired
	private Environment env;

	@Bean
	public HikariDataSource dataSource() {
		String host = env.getProperty("mysql.host");
		String port = env.getProperty("mysql.port");
		String db = env.getProperty("mysql.db");
		String user = env.getProperty("mysql.user");
		String pswd = env.getProperty("mysql.password");
//		int max_connections = env.getProperty("mysql.maxconnections", Integer.class);
		String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?autoReconnect=true";
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(url);
//		config.setMaximumPoolSize(max_connections);
		config.setUsername(user);
		config.setPassword(pswd);
		config.setConnectionTimeout(5000);
		config.setIdleTimeout(10 * 1000);
		config.setLeakDetectionThreshold(10 * 1000);
		config.setMaxLifetime(10 * 1000);
		config.setDriverClassName(MYSQL_DRIVER_CLASS_NAME);
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}

	@Bean(name = { "mongo_data_factory" })
	public MongoDbFactory mongo_data_factory() {
		String mongo_user = this.env.getProperty("mongo.user");
		String mongo_host = this.env.getProperty("mongo.host");
		String mongo_port = this.env.getProperty("mongo.port");
		String mongo_db = this.env.getProperty("mongo.db");
		String mongo_password = this.env.getProperty("mongo.password");
		int mongo_max_connections = Integer.parseInt(this.env.getProperty("mongo.max_connections"));
		MongoCredential credential = null;
		credential = MongoCredential.createCredential(mongo_user, mongo_db, mongo_password.toCharArray());
		MongoClientOptions option = MongoClientOptions.builder().connectionsPerHost(mongo_max_connections).build();
		ServerAddress address = new ServerAddress(mongo_host, Integer.parseInt(mongo_port));
		MongoClient mongoClient = new MongoClient(new ArrayList<>(Arrays.asList(address)), credential, option);
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, mongo_db);
		return mongoDbFactory;
	}

	@Bean("mongo_data_template")
	public MongoTemplate mongo_data_template(@Autowired MongoDbFactory mongo_data_factory) {
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongo_data_factory);
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		MongoTemplate mongoTemplate = new MongoTemplate(mongo_data_factory, converter);
		return mongoTemplate;
	}
}
