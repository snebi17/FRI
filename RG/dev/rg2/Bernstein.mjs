export class Bernstein {
	constructor(n, k) {
		this.n = n;
		this.k = k;
	}
	value(x) {
		const c = this.combination(this.k, this.n);
		return c * Math.pow(x, this.n) * Math.pow(1 - x, this.k - this.n);
	}
	derivative(x) {
		const firstVal = new Bernstein(this.n - 1, this.k - 1).value(x);
		const secondVal = new Bernstein(this.n, this.k - 1).value(x);
		return this.k * (firstVal - secondVal);
	}

	combination(n, r) {
		return this.factorial(n) / (this.factorial(r) * this.factorial(n - r));
	}
	factorial(n) {
		if (n < 0) return -1;
		if (n == 0) return 1;
		return n * this.factorial(n - 1);
	}
}
