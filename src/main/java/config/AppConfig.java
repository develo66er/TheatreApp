package config;

import java.util.HashSet;
import java.util.Set;

//import javax.annotation.PostConstruct;
import javax.sql.DataSource;

//import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import com.theatre.converters.PerformanceConverter;
import com.theatre.interceptors.SessionCartInterceptor;
import com.theatre.interceptors.UserCheckInterceptor;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.theatre")
public class AppConfig implements WebMvcConfigurer,ApplicationContextAware {

	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	DataSource dataSource;

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("static/css/**").addResourceLocations("static/css/");
		registry.addResourceHandler("static/js/**").addResourceLocations("static/js/");
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionCartInterceptor())
			.addPathPatterns("/tickets")
			.excludePathPatterns("/rest/**");
		registry.addInterceptor(new SessionCartInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/admin/**");
		registry.addInterceptor(new UserCheckInterceptor())
			.addPathPatterns("/rest/cart**")
			.addPathPatterns("/rest/customer**");
	}
	@Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addFormatter(performanceConverter());
    }

    @Bean
    public PerformanceConverter performanceConverter() {
        return new PerformanceConverter();
    }
	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/*
	 * STEP 1 - Create SpringResourceTemplateResolver
	 */
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(false);
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	/*
	 * STEP 2 - Create SpringTemplateEngine
	 */
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.setAdditionalDialects(dialects());
		templateEngine.addDialect(new Java8TimeDialect());
		return templateEngine;
	}
	private Set<IDialect> dialects() {
        final Set<IDialect> set = new HashSet<IDialect>();
        set.add(new SpringSecurityDialect());
        return set;
    }
	/*
	 * STEP 3 - Register ThymeleafViewResolver
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding("UTF-8");
		registry.viewResolver(resolver);
	}

	@Bean
	public DataSource h2DataSource() {
		EmbeddedDatabase db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("db/create-data.sql").addScript("db/insert-data.sql").build();
		return db;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}
/*
 * Это нужно в процессе разработки, не удалять !
	@PostConstruct
	public void startDBManager() {
		// h2
		DatabaseManagerSwing.main(new String[] { "--url", "jdbc:h2:mem:testdb", "--user", "sa", "--password", "" });

	}
*/
}