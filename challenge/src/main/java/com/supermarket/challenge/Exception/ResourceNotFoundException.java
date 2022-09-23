package com.supermarket.challenge.Exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {

        private static final long serialVersionUID = 5861310537366287163L;

        public ResourceNotFoundException() {
            super();
        }

        public ResourceNotFoundException(final String message, final Throwable cause) {
            super(message, cause);
        }

        public ResourceNotFoundException(final String message) {
            super(message);
        }

        public ResourceNotFoundException(final Throwable cause) {
            super(cause);
        }
    private HttpStatus status;

    public ResourceNotFoundException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }
    }

