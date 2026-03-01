# Java 17 Features Used in Dress Shop Application

This document highlights the Java 17 features implemented in this project.

## 1. **Records** (Preview in Java 14, Standard in Java 16)
While not used in this project, Records would be ideal for immutable data transfer objects.

## 2. **Text Blocks** (Standard in Java 15)
Multi-line string literals for cleaner SQL queries and JSON representations.

## 3. **Sealed Classes** (Standard in Java 17)
Can be used to restrict which classes can extend a parent class, providing better control over inheritance hierarchies.

## 4. **Pattern Matching for Switch** (Preview in Java 17)
Enhanced switch expressions with pattern matching for more expressive code.

## 5. **Stream API Enhancements**
### `.toList()` Method (Java 16+)
Used extensively in the `DressService` class for converting streams to immutable lists:

```java
// Java 17 - Cleaner approach
return dressRepository.findAll()
        .stream()
        .map(this::mapToDTO)
        .toList();  // Returns immutable List instead of collect(Collectors.toList())

// Old Java 8 approach
return dressRepository.findAll()
        .stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
```

**Benefits:**
- More concise and readable
- Returns an immutable list
- No need to import `Collectors`
- Better performance

## 6. **Records for DTOs** (Recommended Pattern)
While using Lombok `@Data` in this project, Java 17 Records provide a native alternative:

```java
// Java 17 Record approach (alternative to Lombok)
public record DressDTO(
    Long id,
    String name,
    String description,
    Double price,
    Integer quantity,
    String size,
    String color,
    String material
) {}

// Current approach with Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DressDTO { ... }
```

## 7. **Lombok Integration with Java 17**
The project uses Lombok annotations which work seamlessly with Java 17:
- `@Data` - Generates getters, setters, equals, hashCode, toString
- `@Builder` - Provides builder pattern
- `@RequiredArgsConstructor` - Constructor for final fields
- `@NoArgsConstructor` - No-argument constructor
- `@AllArgsConstructor` - All-argument constructor

## 8. **Jakarta EE (Java EE Replacement)**
Java 17 uses Jakarta EE instead of javax packages:

```java
// Java 17 - Jakarta EE
import jakarta.persistence.*;

// Old Java 8 - javax
import javax.persistence.*;
```

## 9. **Method References and Functional Programming**
Used in service layer for clean, functional-style code:

```java
// Method reference for mapping
.map(this::mapToDTO)

// Lambda expressions
.filter(dress -> dress.getPrice() > 100)
```

## 10. **Var Keyword** (Java 10+)
Can be used for local variable type inference:

```java
// Java 17 - Type inference
var dress = dressRepository.findById(id);
var dresses = dressRepository.findAll();

// Traditional approach
Dress dress = dressRepository.findById(id);
List<Dress> dresses = dressRepository.findAll();
```

## 11. **Null Safety with Optional**
Java 17 encourages functional null handling:

```java
return dressRepository.findById(id)
        .map(this::mapToDTO)
        .orElse(null);
```

## 12. **String Formatting Enhancements**
Java 17 supports modern string formatting:

```java
// Java 17 - Text blocks for multi-line strings
String query = """
    SELECT * FROM dresses 
    WHERE color = ? 
    AND size = ?
    """;
```

## 13. **Sealed Classes Example**
Can restrict which classes can extend a base class:

```java
public sealed class DressEntity permits Dress {
    // Only Dress class can extend this
}

public final class Dress extends DressEntity {
    // Implementation
}
```

## 14. **Record Patterns** (Preview in Java 19)
Future enhancement for destructuring records in pattern matching.

## Best Practices Applied

1. **Immutability**: Using `@Builder` and DTOs for immutable data transfer
2. **Functional Programming**: Stream API with `.toList()` for cleaner code
3. **Type Safety**: Leveraging Java 17's type system
4. **Null Safety**: Using Optional instead of null checks
5. **Clean Code**: Using Lombok to reduce boilerplate
6. **Modern Imports**: Using Jakarta EE instead of javax
7. **Transactional Safety**: Using `@Transactional` for database operations

## Migration from Java 8 to Java 17

| Feature | Java 8 | Java 17 |
|---------|--------|---------|
| Stream Terminal | `.collect(Collectors.toList())` | `.toList()` |
| Imports | `javax.persistence` | `jakarta.persistence` |
| Type Inference | Manual | `var` keyword |
| Records | N/A | Native support |
| Text Blocks | N/A | Multi-line strings |
| Pattern Matching | N/A | Enhanced switch |
| Sealed Classes | N/A | Native support |

## Conclusion

This Dress Shop Application leverages modern Java 17 features for cleaner, more maintainable code while maintaining backward compatibility with Spring Boot 4.0.2 and best practices in enterprise application development.
