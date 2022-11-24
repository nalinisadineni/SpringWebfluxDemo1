package com.howtodoinjava.demo.practice;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxErrorHandling {

	public static void main(String[] args) {
		Flux.just(1,3,6)
			//.concatWith(Flux.error(new RuntimeException("Exception occurred")))
			//.concatWith(Mono.just(12))
			/*.onErrorResume(val -> {
				System.out.println("error resumed " + val);
				return Mono.just(10);
			})*/
			/*.map(val -> {if( val == 3) {
				throw new RuntimeException("Exception occurred");
				}
					return val;})
			.onErrorContinue((ex,val)->{System.out.println("exc: " + ex + " " + val);})
			*/
			.log()
			.subscribe(System.out::println);
	}

}
