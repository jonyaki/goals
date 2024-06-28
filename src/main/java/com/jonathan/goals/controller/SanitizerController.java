package com.jonathan.goals.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.owasp.encoder.Encode;

@RestController
@RequestMapping("/sanitizer")
public class SanitizerController {

    @GetMapping("/sanitize-string")
    public ResponseEntity<String> sanitizeString(@RequestParam("inputString") String inputString) {
        String sanitizedInput = inputString.replaceAll("[^\\w]", "");
        return ResponseEntity.ok(sanitizedInput);
    }
    @GetMapping("/sanitize-html")
    public ResponseEntity<String> sanitizeHtml(@RequestParam("htmlInput") String htmlInput) {
        String sanitizedHtml = Encode.forHtml(htmlInput);
        return ResponseEntity.ok(sanitizedHtml);
    }
    @GetMapping("/sanitize-java-script")
    public ResponseEntity<String> sanitizeJavaScript(@RequestParam("jsInput") String jsInput) {
        String sanitizedJs = Encode.forJavaScript(jsInput);
        return ResponseEntity.ok(sanitizedJs);
    }
}
