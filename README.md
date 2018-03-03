
### Overview 

Built using in memory h2 database on springboot. 
JPA/Hibernate as the data access layer. 

### prerequisites 

1. maven 3
2. java 8

### APIs

`POST` `/blog` 

Use the above api to post a blog with the sample body as below 

```


{
   "title": "First title",
   "paragraphs": "hello world how are \n\n to \n\n you"
}


```
  
`POST` `/comment`

Use the above api to post a comment to a paragraph with sample body as below 

```
 	{
	"paragraphId":1,
	"comment":"Iam the commenter"
	}
```

To get a brief list of blogs summary with out comments use the below apis, 
Pagination details : 
use query parameters `page` and `size` to configure the page. 

Note: `page` starts from `0`
 
`GET` `/blog`

`GET` `/blog/{id}` 

### Steps to run

1. Build the project using `mvn clean package` skip the tests using `-DskipTests`
2. Run the Project using command `mvn spring-boot:run`.

