# Spring-Rest-Jackson-Json-Data-Binding
Representational State Transfer is a software architectural style that defines a set of constraints to be used for creating Web services. Web services that conform to the REST architectural style, called Rest API, provide interoperability between computer systems on the Internet.We are implementing it with Sping framework with the help of jackson.

# spring-rest [![Build Status](https://travis-ci.org/lidderupk/spring-rest.svg?branch=master)](https://travis-ci.org/lidderupk/spring-rest)

## Introduction
This project shows different ways to test Spring Boot Application.

Libraries used:

1. spring boot web

2. spring boot test

[See dependency tree.](https://github.com/lidderupk/spring-rest#dependency-tree)

## Testing Notes
There are multiple ways to test spring applications. See [improvements in Spring Boot 1.4](https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4).

### Integration Tests
- will start the server and load the complete spring context and all the beans.

```
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MyTest {

    // will have whatever port tomcat starts on
    @Value("${local.server.port}")
    private int port;

    // ...

}
```

- can use rest-assured to create BDD styled tests

```
@Before
public void setup() {
    logger.info("setting RestAssured port: " + port);
    RestAssured.baseURI = "http://localhost:" + port;
}

@Test
public void getAllBooksShouldReturnListOfAllBooksWithRestAssured() throws Exception {

    final String expectedTitle = "The Lightning Thief";

    Response response = given().
                        when().
                            get(baseUrl + getAllBooksURL).
                         then().
                            statusCode(HttpServletResponse.SC_OK).
                            contentType("application/json").extract().response();

    String res = response.asString();
    jsonPath(res, hasSize(4));
}
```

- can use RestTestTemplate in Integration tests
```
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeRestControllerRestTemplateTest {

...

    @Autowired
	private TestRestTemplate restTemplate;

...

    @Test
	public void getAllBooksShouldReturnListOfAllBooks() throws Exception {
		final String expectedTitle = "The Lightning Thief";
		//another way to test
		ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity(baseUrl + getAllBooksURL
		                                                                    , Book[].class);
		Book[] books = responseEntity.getBody();
		List<Book> booksList = Arrays.asList(books);
		assertThat(booksList.isEmpty(), is(false));
		assertThat(booksList.size(), is(4));
		assertThat(booksList.get(0).getTitle(), is(expectedTitle));
	}

```

### Unit/Slice Tests
- Use this to test individual parts of the spring application. @WebMVCTest to test just the controller/service.
- mockmvc provides methods to fake the http calls. There is no server running in the background. You can check the logs and confirm tomcast is never started.

```
@RunWith(SpringRunner.class)
@WebMvcTest(HomeRestController.class)
public class HomeRestControllerMockMVCTest {
    ...

    @Autowired
    	private MockMvc mockMvc;

    @Test
    	public void getAllBooksShouldExist() throws Exception{
    		mockMvc.perform(get(baseUrl+getAllBooksURL).accept(MediaType.APPLICATION_JSON))
    		.andExpect(status().isOk());
    	}
}
```

## Notes:
- Read a resource using appcontext:

```@Autowired
private ApplicationContext appContext;
Resource resource = appContext.getResource("classpath:data.json");
```

- Use ObjectMapper to convert File/url into json object

```
ObjectMapper mapper = new ObjectMapper();
Book[] b = mapper.readValue(resource.getFile(), Book[].class);
```
# Security Issue
