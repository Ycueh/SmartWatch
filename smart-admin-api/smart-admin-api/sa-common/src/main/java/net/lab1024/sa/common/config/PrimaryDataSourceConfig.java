package net.lab1024.sa.common.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.common.common.domain.DataScopePlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@MapperScan(basePackages = {"net.lab1024.sa.admin.module.system.dao",
                            "net.lab1024.sa.common.module.dao"}, sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class PrimaryDataSourceConfig {
    @Value("${spring.datasource.user.driver-class-name}")
    String driver;

    @Value("${spring.datasource.user.url}")
    String url;

    @Value("${spring.datasource.user.username}")
    String username;

    @Value("${spring.datasource.user.password}")
    String password;

    @Value("${spring.datasource.initial-size}")
    int initialSize;

    @Value("${spring.datasource.min-idle}")
    int minIdle;

    @Value("${spring.datasource.max-active}")
    int maxActive;

    @Value("${spring.datasource.max-wait}")
    long maxWait;

    @Value("${spring.datasource.time-between-eviction-runs-millis}")
    long timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.min-evictable-idle-time-millis}")
    long minEvictableIdleTimeMillis;

    @Value("${spring.datasource.filters}")
    String filters;

    @Value("${spring.datasource.druid.username}")
    String druidUserName;

    @Value("${spring.datasource.druid.password}")
    String druidPassword;

    @Value("${spring.datasource.druid.login.enabled}")
    boolean druidLoginEnable;
    @Value("${spring.datasource.druid.method.pointcut}")
    String methodPointcut;

    @Autowired
    private MybatisPlusInterceptor paginationInterceptor;

    @Autowired(required = false)
    private DataScopePlugin dataScopePlugin;

    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSource primaryDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDbType(DbType.MYSQL.getDb());
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery("SELECT 1");
        try {
            druidDataSource.setFilters(filters);
            ArrayList<Filter> arrayList = new ArrayList<>();
            StatFilter statFilter = new StatFilter();
            statFilter.setMergeSql(true);
            statFilter.setSlowSqlMillis(500);
            statFilter.setLogSlowSql(true);
            arrayList.add(statFilter);
            druidDataSource.setProxyFilters(arrayList);
            druidDataSource.init();
        } catch (SQLException e) {

        }

        return druidDataSource;
    }


    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        return bean.getObject();
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(primaryDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:/mapper/**/*.xml");
        factoryBean.setMapperLocations(resources);

        // 设置 MyBatis-Plus 分页插件 注意此处myBatisPlugin一定要放在后面
        List<Interceptor> pluginsList = new ArrayList<>();
        pluginsList.add(paginationInterceptor);
        if (dataScopePlugin != null) {
            pluginsList.add(dataScopePlugin);
        }
        factoryBean.setPlugins(pluginsList.toArray(new Interceptor[pluginsList.size()]));

        return factoryBean.getObject();
    }

    @Bean(name = "primarySqlSessionTemplate")
    public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}


//import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import lombok.extern.slf4j.Slf4j;
//import net.lab1024.sa.common.common.domain.DataScopePlugin;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@Configuration
//@MapperScan("net.lab1024.sa.mapper") // 替换为你的Mapper包路径
//public class DataSourceConfig {
//
//    // 注入SQLite文件路径
//    @Value("${spring.datasource.url}")
//    private String sqliteFilePath;
//
//    // 其他配置参数忽略，如果不适用，可以删除
//
//    @Autowired
//    private MybatisPlusInterceptor paginationInterceptor;
//
//    @Autowired(required = false)
//    private DataScopePlugin dataScopePlugin;
//
//    // 使用SQLite数据源，不需要用户名和密码
//    @Bean
//    @Primary
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.sqlite.JDBC");
//        dataSource.setUrl(sqliteFilePath); // 设置SQLite数据库文件路径
//        return dataSource;
//    }
//
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource());
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource[] resources = resolver.getResources("classpath*:/mapper/**/*.xml");
//        factoryBean.setMapperLocations(resources);
//
//        // 设置 MyBatis-Plus 分页插件 注意此处myBatisPlugin一定要放在后面
//        List<Interceptor> pluginsList = new ArrayList<>();
//        pluginsList.add(paginationInterceptor);
//        if (dataScopePlugin != null) {
//            pluginsList.add(dataScopePlugin);
//        }
//        factoryBean.setPlugins(pluginsList.toArray(new Interceptor[pluginsList.size()]));
//
//        return factoryBean.getObject();
//    }
//
//    // 其他配置忽略
//}



