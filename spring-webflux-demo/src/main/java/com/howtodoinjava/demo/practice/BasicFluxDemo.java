package com.howtodoinjava.demo.practice;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BasicFluxDemo {
 
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("apple","bamna","rabit","gun");
        flux.filter(val -> val.length()>3)
        .doOnNext(val -> {
        	if(val.contains("g")) {
        		System.out.println("throiwng error");
        		throw new ArithmeticException("Error occurs as it contains char g ");
        }})
        .doOnError(val -> printError(val))
        .flatMap(val -> convertToUpper(val))
       // .delayElements(Duration.ofMillis(500))
        //.log()
        .subscribe(System.out::println);
    }
    
    public static void printValue(String value) {
    	System.out.println(value);
    }
    
    public static Mono<String> convertToUpper(String value) {
    	return Mono.just(value.toUpperCase());
    }
    public static void printError(Throwable error) {
    	System.out.println("exception: " + error);
    }
}