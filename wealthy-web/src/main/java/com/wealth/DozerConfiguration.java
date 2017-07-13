package com.wealth;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfiguration {

    @Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper dozerBean(){
//        List<String> mappingFiles = Arrays.asList(
//                "dozer-configration-mapping.xml"
//        );
    	DozerBeanMapper dozerBean = new DozerBeanMapper();
        return dozerBean;		
	}
	
	
}
