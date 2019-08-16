package com.novaeslucas.modelo.security;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectJdbcRealm extends JdbcRealm {

    private String jndiDataSourceName;
    
    private static final Logger log = LoggerFactory.getLogger(ProjectJdbcRealm.class);
       
    public String getJndiDataSourceName() {
        return jndiDataSourceName;
    }

    public void setJndiDataSourceName(String jndiDataSourceName) {
        this.jndiDataSourceName = jndiDataSourceName;
        DataSource dataSource = createDataSource(jndiDataSourceName);
        setDataSource(dataSource);
    }
    
    
    private DataSource createDataSource(String jndiDataSourceName){
        try {  
            InitialContext ic = new InitialContext();  
            return (DataSource) ic.lookup(jndiDataSourceName);  
        } catch (NamingException e) {  
            log.error("JNDI error while retrieving " + jndiDataSourceName, e);  
            throw new AuthorizationException(e);  
        } 
    }

    
    
    
}
