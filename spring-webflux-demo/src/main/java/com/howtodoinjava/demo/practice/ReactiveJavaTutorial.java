package com.howtodoinjava.demo.practice;

import java.time.Duration;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ReactiveJavaTutorial {
	public static void main(String[] args) throws InterruptedException {

		//Flux.just("New York", "London", "Paris", "Amsterdam").map(ReactiveJavaTutorial::stringToUpperCase)
			//	.publishOn(Schedulers.elastic()).map(ReactiveJavaTutorial::concat).subscribe();
		
		//Hot publisher and cold publisher example
		 Flux<String> netFlux = Flux.fromStream(ReactiveJavaTutorial::getVideo)
		            .delayElements(Duration.ofSeconds(2))
		            .publish()
		            .refCount(2);
		            //.share(); // each part will play for 2 seconds

		    // First Subscriber
		    netFlux.subscribe(part -> System.out.println("Subscriber 1: " + part));

		    // wait 5 seconds before next Subscriber joins
		    Thread.sleep(5000);

		    // Seconds Subscriber
		    netFlux.subscribe(part -> System.out.println("Subscriber 2: " + part));

		    Thread.sleep(60000);
		  }

		  private static Stream<String> getVideo() {
		    System.out.println("Request for the video streaming received.");
		    return Stream.of("part 1", "part 2", "part 3", "part 4", "part 5");
		  }
	

	private static String stringToUpperCase(String name) {
		System.out.println("stringToUpperCase: " + Thread.currentThread().getName());
		return name.toUpperCase();
	}

	private static String concat(String name) {
		System.out.println("concat: " + Thread.currentThread().getName());
		return name.concat(" City");
	}
}
