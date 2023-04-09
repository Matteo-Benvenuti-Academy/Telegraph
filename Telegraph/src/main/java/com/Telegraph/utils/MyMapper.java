package com.Telegraph.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyMapper {

	@Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // configure ModelMapper
        modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}	
