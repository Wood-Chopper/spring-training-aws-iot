# Spring Data Cheat Sheet

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

## JPA ORM
JPA = Java Persistence API

ORM = Object Relational Mapping

### Table to Java Object

```java
@Data
@Entity
@Table(name = "ITEM")
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TaskListEntity taskList;

    private String status;
	@Column(name = "task_name", length = 128)
    private String name;
    private String creationDate;
    private boolean checked;
}
```

```java
@Data
@Entity
@Table(name = "TASKLIST")
@AllArgsConstructor
@NoArgsConstructor
public class TaskListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "taskList")
    @OrderBy("creationDate")
    List<ItemEntity> items;

    private LocalDateTime creationDate;
}
```

| Annotation      | Description                                   |
|-----------------|-----------------------------------------------|
| @Id             | Indicates the Id                              |
| @GeneratedValue | Indicates that way the attribute is generated |
| @Column         | Adds instructions to the field definition     |
| @OneToMany      | Define a relation "one to many"               |
| @OrderBy        | Specify the order of the children             |
| @ManyToOne      | "one to many" at the child level              |


## Queries

### Repository

```java
@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
```

### Query methods

#### Built-in Operations

```java

public interface JpaRepository<ItemEntity, Long> {
	ItemEntity save(ItemEntity entity);

	List<ItemEntity> saveAll(List<ItemEntity> entities);

	Optional<ItemEntity> findById(Long id);

	boolean existsById(Long id);

	List<ItemEntity> findAll();

	List<ItemEntity> findAllById(List<Long> ids);

	long count();

	void deleteById(Long id);

	void delete(ItemEntity entity);

	void deleteAllById(List<Long> ids);

	void deleteAll(List<Long> entities);

	void deleteAll();
	
	// etc...
}
```

**Usage:**
```java
@Service
@RequiredArgsConstructor
public class TaskService {

    private final ItemRepository itemRepository;
    private final EntityMapper entityMapper = EntityMapper.INSTANCE;

    @Override
    public Item getItem(Long itemId) {
        ItemEntity itemEntity = itemRepository.findById(itemId)
				.orElseThrow(() -> new ItemNotFoundException());
        return entityMapper.toModel(itemEntity);
    }
}
```

#### Custom query methods

```java
public interface ItemRepository<ItemEntity, Long> {
	List<ItemEntity> findAllByNameLike(String name);
}
```

### JPQL

## Transactions