package net.cavitos.task.docker.service;

import java.util.Objects;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.AuthConfig;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.PullResponseItem;
import com.github.dockerjava.core.command.PullImageResultCallback;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import io.vavr.control.Either;

public class DockerService {

    private static final Logger logger = LoggerFactory.getLogger(DockerService.class);

    private final DockerClient dockerClient;

    public DockerService(final DockerClient dockerClient) {

        this.dockerClient = Objects.requireNonNull(dockerClient);        
    }

    public Either<String, Info> getDockerInfo() {

        try {

            logger.info("retrieve docker information");
            var info = dockerClient.infoCmd()
                .exec();

            return Either.right(info);

        } catch(Exception ex) {

            logger.error("can't get information: ", ex);
            return Either.left("can't get information from docker");
        }
    }

    public Either<String, String> pullImage(String imageName, String tag, AuthConfig authConfig) {

        try {

            dockerClient.pullImageCmd(imageName)
                .withTag(tag)
                .withAuthConfig(authConfig)
                .exec(new PullImageResultCallback())
                .awaitCompletion();

            return Either.right("");

        } catch(InterruptedException ex) {

            return Either.left("left");
        }

    }
}
