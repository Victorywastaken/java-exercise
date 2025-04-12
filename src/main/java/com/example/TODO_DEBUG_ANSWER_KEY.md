# Todo Application Debug Guide

## Service Layer Issues (TodoService.java)

### 1. Caching Issues
**Bug**: Static cache without invalidation
```java
private static List<Todo> todoCache = new ArrayList<>();
```
- **Problem**: Using static cache means all service instances share the same cache
- **Impact**: Different users/requests see stale data
- **Fix**: Use a proper caching solution like Spring Cache or remove caching entirely
- **Learning Point**: Static fields in Spring beans are dangerous

### 2. Input Validation
**Bug**: Missing input validation in createTodo
```java
public Todo createTodo(Todo todo) {
    return todoRepository.save(todo);
}
```
- **Problem**: No validation before saving to database
- **Impact**: Could throw DB exceptions or save invalid data
- **Fix**: Add @Valid annotation and validation logic
- **Learning Point**: Always validate input at service boundaries

### 3. Negative ID Handling
**Bug**: No validation of ID parameter
```java
public Optional<Todo> getTodoById(Long id) {
    return todoRepository.findById(id);
}
```
- **Problem**: Negative IDs are accepted
- **Impact**: Unnecessary database queries
- **Fix**: Add validation for id > 0
- **Learning Point**: Validate all method parameters

### 4. Null Safety
**Bug**: No null checks in updateTodo
```java
todo.setTitle(todoDetails.getTitle());
todo.setDescription(todoDetails.getDescription());
```
- **Problem**: NullPointerException if todoDetails fields are null
- **Impact**: Runtime exceptions
- **Fix**: Add null checks or use Optional
- **Learning Point**: Defensive programming is important

### 5. Business Logic Error
**Bug**: One-way completed status
```java
if (todoDetails.isCompleted()) {
    todo.setCompleted(true);
}
```
- **Problem**: Todos can't be uncompleted once completed
- **Impact**: Users can't mark todos as incomplete
- **Fix**: Simply use todo.setCompleted(todoDetails.isCompleted());
- **Learning Point**: Test both positive and negative state changes

### 6. Transaction Management
**Bug**: Missing @Transactional
```java
public void deleteTodo(Long id) {
    Todo todo = todoRepository.findById(id)...
```
- **Problem**: No transaction boundary
- **Impact**: Potential data inconsistency
- **Fix**: Add @Transactional annotation
- **Learning Point**: Understand Spring transaction management

### 7. Error Handling
**Bug**: Silent failure in deleteTodo
```java
if (todo.isCompleted()) {
    return;
}
```
- **Problem**: Operation fails silently
- **Impact**: User doesn't know deletion failed
- **Fix**: Throw appropriate exception or return status
- **Learning Point**: Make failure states visible

## Entity Layer Issues (Todo.java)

### 1. JPA Entity Issues
**Bug**: Missing column constraints
```java
private String title;
private String description;
```
- **Problem**: No length limits or constraints
- **Impact**: Potential database errors
- **Fix**: Add @Column(length=) and @NotNull where needed
- **Learning Point**: JPA entities need proper constraints

### 2. NPE in Lifecycle Methods
**Bug**: Unsafe string operations
```java
title = title.trim();
description = description.trim();
```
- **Problem**: NullPointerException if fields are null
- **Impact**: Entity can't be saved if fields are null
- **Fix**: Add null checks before trim()
- **Learning Point**: Lifecycle methods need defensive coding

### 3. Architectural Issue
**Bug**: Business logic in entity
```java
if (description.toLowerCase().contains("done")) {
    completed = true;
}
```
- **Problem**: Business logic in JPA entity
- **Impact**: Logic can't be reused or easily modified
- **Fix**: Move to service layer
- **Learning Point**: Separation of concerns

### 4. Object Contract
**Bug**: Incomplete equals/hashCode implementation
```java
@Override
public boolean equals(Object obj) {
    if (obj instanceof Todo) {
        Todo other = (Todo) obj;
        return this.id != null && this.id.equals(other.id);
    }
    return false;
}
```
- **Problem**: Only compares IDs, missing proper null checks
- **Impact**: Potential bugs in collections/comparisons
- **Fix**: Implement complete equals/hashCode contract
- **Learning Point**: Understand Java object contract

## Common Debugging Scenarios

1. **Data Not Updating**
   - Check if cached data is being returned
   - Verify transaction boundaries
   - Look for silent failures

2. **NPEs in Production**
   - Check all null safety issues
   - Review lifecycle method assumptions
   - Verify input validation

3. **Inconsistent Todo States**
   - Review completed flag logic
   - Check transaction boundaries
   - Verify entity lifecycle methods

4. **Performance Issues**
   - Review caching implementation
   - Check unnecessary database calls
   - Look for N+1 query problems

## Testing Recommendations

1. Test cache invalidation scenarios
2. Test null inputs in all service methods
3. Test completed/uncompleted state transitions
4. Test concurrent modifications
5. Test with very long text inputs
6. Test deletion of todos in different states
