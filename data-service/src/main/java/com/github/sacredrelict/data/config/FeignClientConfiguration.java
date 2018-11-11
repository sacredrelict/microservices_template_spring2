package com.github.sacredrelict.data.config;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Feign Client.
 * @Configuration - Flag for Spring Boot that this is configuration.
 */
@Configuration
public class FeignClientConfiguration {

    /**
     * Bean for ErrorDecoder.
     * @return Error decoder object.
     */
    @Bean
    public ErrorDecoder getErrorDecoder() {
        return new ErrorResponseDecoder(new ErrorDecoder.Default());
    }

    /**
     * Validate all responses between services.
     */
    private static class ErrorResponseDecoder implements ErrorDecoder {

        private final ErrorDecoder errorDecoder;

        public ErrorResponseDecoder(ErrorDecoder errorDecoder) {
            this.errorDecoder = errorDecoder;
        }

        @Override
        public Exception decode(String methodKey, Response response) {
            if (response.status() == 400 || response.status() == 404) {
                Exception exception = FeignException.errorStatus(methodKey, response);
                return new HystrixBadRequestException(exception.getMessage(), exception);
            }
            return errorDecoder.decode(methodKey, response);
        }
    }

}
