package chessgame.domain.model;

import java.util.Arrays;

import static java.util.Optional.of;

/**
 * Positions of Chessboard.
 *
 * @author Prestes, E. M.
 * @since September 2016
 */
public enum BoardPosition {
    A1(1, 1), A2(1, 2), A3(1, 3), A4(1, 4),
    A5(1, 5), A6(1, 6), A7(1, 7), A8(1, 8),
    B1(2, 1), B2(2, 2), B3(2, 3), B4(2, 4),
    B5(2, 5), B6(2, 6), B7(2, 7), B8(2, 8),
    C1(3, 1), C2(3, 2), C3(3, 3), C4(3, 4),
    C5(3, 5), C6(3, 6), C7(3, 7), C8(3, 8),
    D1(4, 1), D2(4, 2), D3(4, 3), D4(4, 4),
    D5(4, 5), D6(4, 6), D7(4, 7), D8(4, 8),
    E1(5, 1), E2(5, 2), E3(5, 3), E4(5, 4),
    E5(5, 5), E6(5, 6), E7(5, 7), E8(5, 8),
    F1(6, 1), F2(6, 2), F3(6, 3), F4(6, 4),
    F5(6, 5), F6(6, 6), F7(6, 7), F8(6, 8),
    G1(7, 1), G2(7, 2), G3(7, 3), G4(7, 4),
    G5(7, 5), G6(7, 6), G7(7, 7), G8(7, 8),
    H1(8, 1), H2(8, 2), H3(8, 3), H4(8, 4),
    H5(8, 5), H6(8, 6), H7(8, 7), H8(8, 8);

    private final int column;
    private final int row;

    BoardPosition(int column, int row) {
        this.column = column;
        this.row = row;
    }

    /**
     * Find next column of the Chessboard.
     *
     * @return {@link BoardPosition}
     */
    public BoardPosition nextColumn() {
        return of(this)
                .filter(BoardPosition::isLastColumn)
                .orElse(valueOf(this.column + 1, this.row));
    }

    /**
     * Find next row of the Chessboard.
     *
     * @return {@link BoardPosition}
     */
    public BoardPosition nextRow() {
        return of(this)
                .filter(BoardPosition::isLastRow)
                .orElse(valueOf(this.column, this.row + 1));
    }

    /**
     * Find previous row of the Chessboard.
     *
     * @return {@link BoardPosition}
     */
    public BoardPosition previousRow() {
        return of(this)
                .filter(BoardPosition::isFirstRow)
                .orElse(valueOf(this.column, this.row - 1));
    }

    /**
     * Find previous column of the Chessboard.
     *
     * @return {@link BoardPosition}
     */
    public BoardPosition previousColumn() {
        return of(this)
                .filter(BoardPosition::isFirstColumn)
                .orElse(valueOf(this.column - 1, this.row));
    }

    /**
     * Find diagonal right up of the Chessboard.
     *
     * @return {@link BoardPosition}
     */
    public BoardPosition diagonalRightUp() {
        return of(this)
                .filter(BoardPosition::nonLastColumn)
                .map(BoardPosition::nextColumn)
                .filter(this::nonEquals)
                .filter(BoardPosition::nonLastRow)
                .map(BoardPosition::nextRow)
                .orElse(this);
    }

    /**
     * Find diagonal right down of the Chessboard.
     *
     * @return {@link BoardPosition}
     */
    public BoardPosition diagonalRightDown() {
        return of(this)
                .filter(BoardPosition::nonLastColumn)
                .map(BoardPosition::nextColumn)
                .filter(this::nonEquals)
                .filter(BoardPosition::nonFirstRow)
                .map(BoardPosition::previousRow)
                .orElse(this);
    }

    /**
     * Find diagonal left up of the Chessboard.
     *
     * @return {@link BoardPosition}
     */
    public BoardPosition diagonalLeftUp() {
        return of(this)
                .filter(BoardPosition::nonFirstColumn)
                .map(BoardPosition::previousColumn)
                .filter(this::nonEquals)
                .filter(BoardPosition::nonLastRow)
                .map(BoardPosition::nextRow)
                .orElse(this);
    }

    /**
     * Find diagonal left down of the Chessboard.
     *
     * @return {@link BoardPosition}
     */
    public BoardPosition diagonalLeftDown() {
        return of(this)
                .filter(BoardPosition::nonFirstColumn)
                .map(BoardPosition::previousColumn)
                .filter(this::nonEquals)
                .filter(BoardPosition::nonFirstRow)
                .map(BoardPosition::previousRow)
                .orElse(this);
    }

    /**
     * Checks if this position is the first column.
     *
     * @return {@link Boolean}
     */
    public Boolean isFirstColumn() {
        return this.column == 1;
    }

    /**
     * Checks if this position is not the first column.
     *
     * @return {@link Boolean}
     */
    public Boolean nonFirstColumn() {
        return !isFirstColumn();
    }

    /**
     * Checks if this position is the last column.
     *
     * @return {@link Boolean}
     */
    public Boolean isLastColumn() {
        return this.column == 8;
    }

    /**
     * Checks if this position is not the last column.
     *
     * @return {@link Boolean}
     */
    public Boolean nonLastColumn() {
        return !isLastColumn();
    }

    /**
     * Checks if this position is the first row.
     *
     * @return {@link Boolean}
     */
    public Boolean isFirstRow() {
        return this.row == 1;
    }

    /**
     * Checks if this position is not the first row.
     *
     * @return {@link Boolean}
     */
    public Boolean nonFirstRow() {
        return !isFirstRow();
    }

    /**
     * Checks if this position is the last row.
     *
     * @return {@link Boolean}
     */
    public Boolean isLastRow() {
        return this.row == 8;
    }

    /**
     * Checks if this position is not the last row.
     *
     * @return {@link Boolean}
     */
    public Boolean nonLastRow() {
        return !isLastRow();
    }

    /**
     * Checks if this position is not equalled by position informed.
     *
     * @param position Position informed.
     * @return {@link Boolean}
     */
    public Boolean nonEquals(BoardPosition position) {
        return !this.equals(position);
    }

    private BoardPosition valueOf(int column, int row) {
        return Arrays.stream(values())
                .filter(position -> position.column == column && position.row == row)
                .findFirst()
                .orElse(this);
    }
}
