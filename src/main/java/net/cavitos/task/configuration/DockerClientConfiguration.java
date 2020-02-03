package net.cavitos.task.configuration;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerClientConfiguration {

    @Value("${docker.host:unix://var/run/docker.sock}")
    private String dockerHost;

    @Bean
    public DockerClientConfig dockerClientConfig() {

        return new DefaultDockerClientConfig.Builder()
            .withDockerHost(dockerHost)
            .build();        
    } 
    
    @Bean
    public DockerClient dockerClient(DockerClientConfig dockerClientConfig) {

        return DockerClientBuilder.getInstance(dockerClientConfig)
            .build();        
    }
}
