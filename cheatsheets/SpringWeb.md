# Spring Web Cheat Sheet

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

## Controller

API: `GET /tasks`

```java
@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TasksController {
	private final TaskListService taskListService;

	@GetMapping
	public List<TaskDto> getTasks() {
		return taskListService.getTasks();
	}
}
```

## Request Mapping

### GET Mapping with query params

API: `GET /tasks?status=PENDING`

```java
@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TasksController {
	private final TaskListService taskListService;

	@GetMapping
	public List<TaskDto> getTasks(@RequestParam String status) {
		return taskListService.getTasks(status);
	}
}
```

### Get Mapping with path param

API: `GET /tasks/123`

```java
@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TasksController {
	private final TaskListService taskListService;

	@GetMapping("{id}")
	public TaskDto getTaskById(@PathVariable Long id) {
		return taskListService.getTaskById(id);
	}
}
```

### POST Mapping with request body

API: `POST /tasks`

```json
{
	"name": "Prepare training",
	"status": "PENDING",
	"checked": false
}
```

```java
@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TasksController {
	private final TaskListService taskListService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TaskDto addTask(@RequestBody TaskDto taskDto) {
		return taskListService.addTask(taskDto);
	}
}
```

## DTO Validation

```xml

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

API: `POST /tasks`

```java
@Data
public class Item {
	private Long id;
	@NotNull
	private Status status;
	@NotBlank
	private String name;
	private LocalDateTime creationDate;
	private boolean checked;
}
```

```java
@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TasksController {
	private final TaskListService taskListService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TaskDto addTask(@RequestBody @Valid TaskDto taskDto) {
		return taskListService.addTask(taskDto);
	}
}
```

## Exception Handling

```java
@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
    public String taskListNotFound(TaskFoundException exception) {
		"Task not found";
    }
}

```
