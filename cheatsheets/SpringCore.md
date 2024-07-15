# Spring Core Cheat Sheet

## Dependency injection

### Spring Native

Injection by constructor.

```java
@Service
public class TaskManagerService {

	private final PersistenceGateway repository;
	
	public TaskManagerService(PersistenceGateway repository) {
		this.repository = repository;
	}

	public Item addItem(Long id, Item item) {
		item.setCreationDate(LocalDateTime.now());
		return repository.saveItem(id, item);
	}
}

```
### Using Lombok

Using `@RequiredArgsConstructor` to automatically create the constructor.

```java
@Service
@RequiredArgsConstructor
public class TaskManagerService {

	private final PersistenceGateway repository;

	public Item addItem(Long id, Item item) {
		item.setCreationDate(LocalDateTime.now());
		return repository.saveItem(id, item);
	}
}
```

## Bean Creation

### At class level

Using `@Service`, `@Component` or `@Repository`

```java
@Service
public class TaskManagerService {
	//...
}
```

### Using a configuration class

```java
@Configuration
public class SomeConfiguration {

	@Bean
	public SomeService myService() {
		return new SomeService();
	}
}
```
If the class needs other dependencies:
```java
@Configuration
public class SomeConfiguration {

	@Bean
	public TaskManagerService taskManagerService(Repository repository) {
		return new TaskManagerService(repository);
	}
}
```

