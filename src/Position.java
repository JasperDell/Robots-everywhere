public class Position {
    private float x;
    private float y;

    public Position(){
        this.x = 0;
        this.y = 0;
    }

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position otherPosition) {
        return (this.x == otherPosition.x && this.y == otherPosition.y);
    }

    public Position(float[] pos){
        this.x = pos[0];
        this.y = pos[1];
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void addToX(float x) {
        this.x += x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void addToY(float y) {
        this.y += y;
    }

    @Override
    public Position clone(){
        return new Position(this.x, this.y);
    }
}
