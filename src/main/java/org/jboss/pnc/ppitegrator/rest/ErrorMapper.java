/*
 * Copyright (C) 2020 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.ppitegrator.rest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ErrorMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        int code;

        if (exception instanceof WebApplicationException) {
            code = ((WebApplicationException) exception).getResponse().getStatus();
        } else {
            code = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        }

        ErrorMessage errorMessage = new ErrorMessage(code, exception);

        return Response.status(code).type(MediaType.APPLICATION_JSON).entity(errorMessage).build();
    }

    class ErrorMessage {
        private int code;

        private String message;

        private List<String> stackTrace;

        ErrorMessage(int code, Exception exception) {
            this.code = code;
            this.message = exception.getMessage() != null ? exception.getMessage() : "";
            this.stackTrace = Arrays.asList(exception.getStackTrace())
                    .stream()
                    .map(StackTraceElement::toString)
                    .collect(Collectors.toList());
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<String> getStackTrace() {
            return stackTrace;
        }

        public void setStackTrace(List<String> stackTrace) {
            this.stackTrace = stackTrace;
        }
    }
}
