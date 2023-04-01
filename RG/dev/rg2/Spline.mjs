import * as Vector from "./Vector.mjs";

export class Spline {
	constructor(curves) {
		this.c = curves;
		this.n = curves.length;
	}
	value(t) {
		const { curve, index } = this.getCurveAndIndex(t);
		if (t == this.c.length) {
			return curve.value(index);
		}
		return curve.value(t - index);
	}
	derivative(t) {
		const { curve, index } = this.getCurveAndIndex(t);
		if (t == this.c.length) {
			return curve.derivative(index);
		}
		return curve.derivative(t - index);
	}
	makeContinuous() {
		for (let i = 1; i < this.n; i++) {
			let currCurve = this.c[i - 1].p;
			let nextCurve = this.c[i].p;
			currCurve[this.n - 1] = nextCurve[0] = Vector.divScalar(
				Vector.add(currCurve[this.n - 1], nextCurve[0]),
				2
			);
			this.c[i - 1][this.n - 1] = currCurve[this.n - 1];
			this.c[i][0] = nextCurve[0];
		}
	}
	makeSmooth() {
		this.makeContinuous();
		for (let t = 0.01; t < 0.99; t += 0.01) {
			let beforeDer = this.curves.derivative(t - 0.01);
			let afterDer = this.curves.derivative(t + 0.01);
			if (!(beforeDer > 0 && afterDer < 0) && !(beforeDer < 0 && afterDer > 0))
				continue;
			let currCurve = this.getCurveAndIndex(t).curve;
			while (currCurve.derivative(t) < 0.1 && currCurve.derivative(t) > 0.1) {
				// ...
			}
		}
	}

	getCurveAndIndex(t) {
		let i = Math.floor(t);
		if (i == this.c.length) {
			return { curve: this.c[i - 1], index: 1 };
		}
		return { curve: this.c[i], index: i };
	}

	getCurves() {
		return this.c;
	}
}
