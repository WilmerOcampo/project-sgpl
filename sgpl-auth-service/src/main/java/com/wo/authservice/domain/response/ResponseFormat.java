package com.wo.authservice.domain.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatusCode;

@JsonPropertyOrder({"body", "statusCode", "statusCodeValue"})
public record ResponseFormat(Object body, HttpStatusCode statusCode, int statusCodeValue) {
}
