package model;

public class Move {

    private Square startSquare;
    private Square destinationSquare;

    public Move(Square startSquare, Square destinationSquare) {
        this.startSquare = startSquare;
        this.destinationSquare = destinationSquare;

    }

    public Square getDestinationSquare() {
        return this.destinationSquare;
    }

    public void setDestinationSquare(Square destinationSquare) {
        this.destinationSquare = destinationSquare;
    }

    public Square getStartSquare() {
        return this.startSquare;
    }

    public void setStartSquare(Square startSquare) {
        this.startSquare = startSquare;
    }

}
