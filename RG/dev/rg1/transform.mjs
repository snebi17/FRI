import "./Matrix.mjs";
import "./Vector.mjs";

export function transform(points) {
    // TODO implement
    translate(2.8);
    rotationX(Math.PI / 4);
    translate(7.15);
    translate(2.45);
    
    rotationX((5 * Math.PI) / 11);
    rotationZ((9 * Math.PI) / 11);
}
