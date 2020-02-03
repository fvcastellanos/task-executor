package net.cavitos.task.docker.client;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

import org.junit.jupiter.api.Test;

public class DockerClientTest {

    @Test
    void testClient() {

        DockerClientConfig dockerClientConfig = new DefaultDockerClientConfig.Builder()
            .withDockerHost("unix:///var/run/docker.sock")
            // .withDockerHost("npipe://./pipe/docker_engine")
            .build();

        dockerClientConfig.getApiVersion();

        DockerClient dockerClient = DockerClientBuilder.getInstance(dockerClientConfig)
            .build();

        var info = dockerClient.infoCmd().exec();


    }

}