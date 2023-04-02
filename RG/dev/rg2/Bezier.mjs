import { Bernstein } from "./Bernstein.mjs";
import * as Vector from "./Vector.mjs";

export class Bezier {
	constructor(points) {
		this.p = points;
		this.n = points.length - 1;
		this.dimension = points[0].length;
	}
	value(t) {
		let sum = new Array(this.dimension).fill(0);
		for (let i = 0; i < this.n + 1; i++) {
			let b = new Bernstein(i, this.n).value(t);
			sum = Vector.add(sum, Vector.mulScalar(this.p[i], b));
		}
		return sum;
	}
	derivative(t) {
		let sum = new Array(this.dimension).fill(0);
		let sub = new Array(this.dimension).fill(0);
		for (let i = 0; i < this.n; i++) {
			let b = new Bernstein(i, this.n - 1).value(t);
			sub = Vector.sub(this.p[i + 1], this.p[i]);
			sum = Vector.add(sum, Vector.mulScalar(sub, b));
		}
		return Vector.mulScalar(sum, this.n);
	}
}
