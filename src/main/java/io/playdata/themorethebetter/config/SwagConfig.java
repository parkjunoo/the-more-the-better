package io.playdata.themorethebetter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwagConfig 
{
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any()) // 현재 RequestMapping으로 할당된 모든 URL을 리스트로 추춣마
				.paths(PathSelectors.any()) // 경로에 잡히는 모든 URL 값들을 필터링 함
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder()
				.title("다다익선 API 문서")
				.contact(new Contact("담당자 문의하기", null, "chaeyeon@encore-dada.com"))
				.description("다다익선 API 문서 자료입니다 \n <a href=\"http://github.com/benz-e-class/the-more-the-better\">저장소 사이트 이동하기</a>")
				.build();
	}
}