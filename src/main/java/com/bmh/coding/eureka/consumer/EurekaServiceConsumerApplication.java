package com.bmh.coding.eureka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaServiceConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceConsumerApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();

	}

	@RestController
	@Component
	class ConsumerCommandServiceController {

		//available now with load balanced bean
		@Autowired
		private RestTemplate restTemplate;

		@RequestMapping("/service-instance-cmd")
		public String GetServiceInstancesRt() {

			String response = restTemplate.getForObject("http://boot-command-service/commandService/commands", String.class);
			return response;
		}

	}
	
	
	@RestController
	@Component
	class ConsumerCustomerServiceController {

		//available now with load balanced bean
		@Autowired
		private RestTemplate restTemplate;

		@RequestMapping("/service-instance-cust")
		public String GetServiceInstancesRt() {

			String response = restTemplate.getForObject("http://boot-customer-service/env", String.class);
			return response;
		}

	}


}
