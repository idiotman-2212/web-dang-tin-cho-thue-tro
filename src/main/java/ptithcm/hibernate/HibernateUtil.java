package ptithcm.hibernate;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import ptithcm.entity.*;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
                settings.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=CNPM");
                settings.put(Environment.USER, "ducmanh");
                settings.put(Environment.PASS, "1234");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                settings.put(Environment.IMPLICIT_NAMING_STRATEGY,"org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
                settings.put(Environment.PHYSICAL_NAMING_STRATEGY,"org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(AnhEntity.class);
                configuration.addAnnotatedClass(BaiVietEntity.class);
                configuration.addAnnotatedClass(ChiTietBaiVietEntity.class);
                configuration.addAnnotatedClass(GopYEntity.class);
                configuration.addAnnotatedClass(NguoiDungEntity.class);
                configuration.addAnnotatedClass(QuangCaoEntity.class);
                configuration.addAnnotatedClass(TaiKhoanEntity.class);
                configuration.addAnnotatedClass(TenQuyenEntity.class);
                configuration.addAnnotatedClass(ThongBaoEntity.class);
                configuration.addAnnotatedClass(PasswordResetTokenEntity.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}