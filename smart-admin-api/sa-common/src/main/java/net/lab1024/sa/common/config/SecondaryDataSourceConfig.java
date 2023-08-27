package net.lab1024.sa.common.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import net.lab1024.sa.common.common.domain.DataScopePlugin;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;


@Configuration
@MapperScan(basePackages = "net.lab1024.sa.admin.module.smartWatch.dao", sqlSessionTemplateRef = "secondarySqlSessionTemplate")
public class SecondaryDataSourceConfig {
    @Value("${spring.datasource.ema.driver-class-name}")
    String driver;

    @Value("${spring.datasource.ema.url}")
    String url;

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

    private DruidDataSource druidDataSource;

    private static final String DBWALPATH = "."+ File.separator +"database" + File.separator +"smart_admin_v2.db-wal";
    private static final String DBSHMPATH = "."+ File.separator +"database" + File.separator +"smart_admin_v2.db-shm";

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ema")
    public DataSource secondaryDataSource() {
        if (druidDataSource == null) {
            druidDataSource = new DruidDataSource();
        }
        druidDataSource.setDbType(DbType.SQLITE.getDb());
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery("SELECT 1");
        druidDataSource.setConnectionInitSqls(Arrays.asList("PRAGMA journal_mode=DELETE;"));
        druidDataSource.setConnectionInitSqls(Arrays.asList("PRAGMA locking_mode=NORMAL;"));
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

    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(secondaryDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:/mapper/business/smartwatch/*.xml");
        factoryBean.setMapperLocations(resources);

        List<Interceptor> pluginsList = new ArrayList<>();
        pluginsList.add(paginationInterceptor);
        if (dataScopePlugin != null) {
            pluginsList.add(dataScopePlugin);
        }
        factoryBean.setPlugins(pluginsList.toArray(new Interceptor[pluginsList.size()]));

        return factoryBean.getObject();
    }

    @Bean(name = "secondarySqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    public void resetConnection() {
        if (secondaryDataSource() != null) {
            druidDataSource.close();  // Close all current connections
            deleteCurrentFile();
            try {
                druidDataSource.restart();  // Reinitialize the datasource
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void deleteCurrentFile() {
            try {
                Files.deleteIfExists(Paths.get(DBWALPATH));
                Files.deleteIfExists(Paths.get(DBSHMPATH));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//    @Bean
//    @Scope("prototype")
//    public DatabaseConnectionManager databaseConnectionManager(@Qualifier("secondaryDataSource") DataSource dataSource) {
//        return new DatabaseConnectionManager(dataSource);
//    }
//
//    public static class DatabaseConnectionManager {
//
//        private final DataSource dataSource;
//
//        private Connection connection;
//        private static final String DBPATH = "."+ File.separator +"database" + File.separator +"smart_admin_v2.db";
//        private static final String DBWALPATH = "."+ File.separator +"database" + File.separator +"smart_admin_v2.db-wal";
//        private static final String DBSHMPATH = "."+ File.separator +"database" + File.separator +"smart_admin_v2.db-shm";
//
//        public DatabaseConnectionManager(DataSource dataSource) {
//            this.dataSource = dataSource;
//        }
//
//        public synchronized void resetConnection() throws SQLException {
//            if (this.connection != null && !this.connection.isClosed()) {
//                this.connection.close();
//                deleteCurrentFile();
//            }
//            this.connection = dataSource.getConnection();
//        }
//
//        public Connection getConnection() {
//            return connection;
//        }
//
//        private void deleteCurrentFile() {
//            try {
//                Files.deleteIfExists(Paths.get(DBWALPATH));
//                Files.deleteIfExists(Paths.get(DBSHMPATH));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
