# Chess Implementation Debug Exercise - Answer Key

This document contains solutions to the debugging exercise in the chess implementation. The bugs are intentionally subtle and representative of common programming mistakes.

## Position Class Bugs

1. Input Validation Missing
   - Location: `Position` constructor
   - Bug: No validation of input parameters
   - Impact: Invalid positions can be created
   - Fix: Add bounds checking for row and column values

2. Incorrect Bounds Checking
   - Location: `isValid()` method
   - Bug: Uses <= 8 instead of < 8 for bounds check
   - Impact: Allows invalid position (8,8)
   - Fix: Change to `row >= 0 && row < 8 && column >= 0 && column < 8`

3. Incomplete Normalization
   - Location: `normalize()` method
   - Bug: Only handles minimum bounds, not maximum
   - Impact: Values can still be too large
   - Fix: Add `Math.min(row, 7)` and `Math.min(column, 7)`

4. Broken equals/hashCode Contract
   - Location: `equals()` and `hashCode()` methods
   - Bug: hashCode only uses row while equals uses both row and column
   - Impact: HashMap/HashSet operations will behave incorrectly
   - Fix: Make hashCode use both row and column

5. Missing Null Check
   - Location: `equals()` method
   - Bug: No null check before casting
   - Impact: NullPointerException on null input
   - Fix: Add null check before casting

## Piece Class Bugs

1. Mutable State Exposure
   - Location: `getPosition()` method
   - Bug: Returns internal position object directly
   - Impact: Position can be modified externally
   - Fix: Return new Position object

2. Incomplete Position Validation
   - Location: `setPosition()` method
   - Bug: Only checks for negative values
   - Impact: Can set invalid positions > 7
   - Fix: Add upper bound check

3. Missing Validation
   - Location: `updatePosition()` method
   - Bug: No validation of new position
   - Impact: Can set invalid positions
   - Fix: Add position validation

4. Incomplete equals Implementation
   - Location: `equals()` method
   - Bug: Only compares color, ignores position
   - Impact: Different pieces considered equal
   - Fix: Compare both color and position

## Pawn Class Bugs

1. Unupdated State
   - Location: `hasMoved` field
   - Bug: Flag never gets updated
   - Impact: Pawn can always make two-square moves
   - Fix: Update flag in updatePosition method

2. Incorrect Capture Logic
   - Location: `isValidMove()` method
   - Bug: Only allows capture to the right (forgot abs())
   - Impact: Can't capture to the left
   - Fix: Use Math.abs() for column difference

3. Missing Check
   - Location: First move validation
   - Bug: Doesn't check intermediate square
   - Impact: Can jump over pieces on first move
   - Fix: Add check for intermediate square occupation

## King Class Bugs

1. Invalid Movement
   - Location: `isValidMove()` method
   - Bug: Allows diagonal movement of 2 squares
   - Impact: King can move like a bishop
   - Fix: Change condition to `rowDiff <= 1 && colDiff <= 1`

2. Oversimplified Castling
   - Location: `isValidMove()` method
   - Bug: Missing multiple castling checks
   - Impact: Invalid castling moves allowed
   - Fix: Add all required castling validations

3. Unupdated State
   - Location: `hasMoved` field
   - Bug: Flag never gets updated
   - Impact: Can castle multiple times
   - Fix: Update flag in updatePosition

## Board Class Bugs

1. Incomplete Initialization
   - Location: `initializeBoard()` method
   - Bug: Only initializes pawns
   - Impact: Other pieces missing
   - Fix: Add initialization for all pieces

2. Missing Validation
   - Location: `getPiece()` method
   - Bug: No bounds checking
   - Impact: ArrayIndexOutOfBoundsException possible
   - Fix: Add position validation

3. Position Update Order
   - Location: `movePiece()` method
   - Bug: Updates piece position after board state
   - Impact: Inconsistent state during move
   - Fix: Update piece position before board state

4. Incorrect King Finding
   - Location: `findKing()` method
   - Bug: Returns first piece of color
   - Impact: Wrong piece identified as king
   - Fix: Add instanceof King check

5. Boundary Conditions
   - Location: `isValidPosition()` method
   - Bug: Uses <= 7 instead of < 8
   - Impact: Subtle off-by-one errors
   - Fix: Use < 8 for bounds checking

## Queen Class (Reference Implementation)
The Queen class serves as a reference implementation with no bugs. Students can use this as an example of:
- Proper path checking
- Correct movement validation
- Complete obstacle detection
- Proper capture validation

## Bishop Class Bugs

1. Missing Path Validation
   - Location: `isValidMove()` method
   - Bug: Only checks if movement is diagonal but not if path is clear
   - Impact: Can jump over other pieces
   - Fix: Add path checking similar to Queen implementation

## Knight Class (Reference Implementation)
The Knight class is also correctly implemented, demonstrating:
- Proper L-shaped movement validation
- Correct capture validation
- No need for path checking (knights can jump)

## Rook Class Bugs

1. Movement Validation Bug
   - Location: `isValidMove()` method
   - Bug: Uses `rowDiff == 0 || colDiff == 0` instead of ensuring exactly one is zero
   - Impact: Can move diagonally if either difference is zero
   - Fix: Change to `(rowDiff == 0) != (colDiff == 0)`

2. Unupdated State
   - Location: `updatePosition()` method
   - Bug: hasMoved flag never gets updated
   - Impact: Castling validation will be incorrect
   - Fix: Set hasMoved to true in updatePosition

## Testing Strategies

1. Position Testing
   - Test constructor with invalid inputs
   - Verify equals/hashCode behavior
   - Check boundary conditions
   - Test normalization edge cases

2. Piece Movement
   - Test all boundary positions
   - Verify position updates
   - Check color-specific rules
   - Test invalid moves

3. Pawn Specific
   - Test first move conditions
   - Verify capture mechanics
   - Check diagonal captures
   - Test en passant rules

4. King Specific
   - Test basic movement
   - Verify castling conditions
   - Check illegal moves
   - Test check detection

5. Board State
   - Test piece placement
   - Verify check detection
   - Test turn order
   - Check boundary moves

6. Integration Tests
   - Complete game scenarios
   - Edge case movements
   - Check/checkmate situations
   - Piece capture scenarios

7. Piece-Specific Tests
   - Queen: Test all movement directions and obstacles
   - Bishop: Test diagonal movement and blocked paths
   - Knight: Test L-shaped movement and jumping
   - Rook: Test straight movement and castling conditions

## Common Student Mistakes to Watch For

1. Array indexing issues
   - Off-by-one errors in array access
   - Confusion between 0-based and 1-based indexing
   - Incorrect boundary checks

2. Object-oriented design issues
   - Missing class implementations
   - Improper type checking
   - Incomplete class hierarchies

3. Logic errors
   - Incorrect move validation
   - Turn order bugs
   - Position tracking errors

## Common Implementation Mistakes

1. Path Checking
   - Forgetting to check intermediate squares
   - Incorrect step calculation
   - Missing obstacle detection

2. Movement Validation
   - Incomplete direction checks
   - Incorrect diagonal validation
   - Missing boundary validation

3. State Management
   - Unupdated movement flags
   - Incorrect castling state
   - Position synchronization issues

4. Capture Logic
   - Missing same-color checks
   - Incorrect capture validation
   - Incomplete target square checks
