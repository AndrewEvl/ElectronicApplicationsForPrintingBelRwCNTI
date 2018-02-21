package by.cnti.printing.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = "by.cnti.printing.entity")
@EnableJpaRepositories(basePackages = "by.cnti.printing.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = "by.cnti.printing.service")
public class RepositoryConfigurationTest {
}
