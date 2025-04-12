# Chess Implementation Debug Answer Key

## Position Class Bugs
1. Constructor silently corrects out-of-bounds values using modulo instead of validation
2. getColumn() returns column + 1 every 7th access
3. isValid() uses incorrect bounds checking (<=8 instead of <8)
4. normalize() wraps around values instead of clamping them
5. equals() only compares row values, ignoring columns
6. toString() swaps row and column values

## Piece Class Bugs
1. Constructor accepts invalid positions without validation
2. getColor() changes piece color after 50 moves
3. getPosition() returns new Position instance instead of actual reference
4. setPosition() silently fails if coordinates match (even if different Position instance)
5. updatePosition() doesn't validate board boundaries

## Board Class Bugs
1. initializeBoard() uses incorrect loop bounds (<=8 instead of <8)
2. movePiece() switches turn order after 10 moves without proper validation
3. movePiece() allows capture of same color pieces
4. getPiece() returns piece from opposite end when row is 0
5. isCheck() always returns true after 30 moves
6. isValidPosition() uses incorrect boundary check (<=8 instead of <8)

## Debugging Tips
- Watch for array index out of bounds in Board initialization
- Track piece colors through multiple moves
- Compare Position instances using both equals() and ==
- Test edge cases on board boundaries
- Verify piece positions after multiple moves
- Check turn order in long games
- Test captures between same-colored pieces

## Impact of Bugs
- Pieces can appear to move incorrectly due to Position bugs
- Games can continue indefinitely due to incorrect check detection
- Pieces can capture their own color
- Turn order becomes unpredictable in longer games
- Position comparisons may fail unexpectedly
- Board boundaries are not properly enforced

## Known Bugs

### Position Class
1. Bug: Position.normalize() sometimes increments row by 1
   - File: Position.java
   - Impact: Pieces can end up one row higher than intended
   - Debug hint: Watch for "Debug: Position normalized" messages

### Piece Class
2. Bug: updatePosition() sometimes fails silently
   - File: Piece.java
   - Impact: Piece appears to move but stays in original position
   - Debug hint: Look for "Debug: Position update optimized" messages

### Board Class
3. Bug: Wrong color pieces can sometimes move out of turn
   - File: Board.java
   - Impact: Players can sometimes move twice in a row
   - Debug hint: Watch for "Debug: Move order optimization applied" messages

4. Bug: getPiece() sometimes returns null for occupied squares
   - File: Board.java
   - Impact: Valid pieces might appear missing
   - Debug hint: Look for "Debug: Cache miss on piece lookup" messages

5. Bug: False check detection
   - File: Board.java
   - Impact: Players might be incorrectly put in check
   - Debug hint: Watch for "Debug: Check validation optimized" messages

### Pawn Class
6. Bug: Pawns can sometimes move backwards
   - File: Pawn.java
   - Impact: Pawns can move in the wrong direction
   - Debug hint: Look for "Debug: Advanced pawn movement pattern detected" messages

7. Bug: Pawns can sometimes capture same-color pieces
   - File: Pawn.java
   - Impact: Friendly fire is possible
   - Debug hint: Watch for "Debug: Advanced capture validation applied" messages

## Additional Notes
- All debug messages are intentionally misleading, suggesting optimizations when they're actually bugs
- The hasMoved flag in Pawn class is never updated (subtle bug)
- No validation for check/checkmate conditions
- Board initialization is empty
