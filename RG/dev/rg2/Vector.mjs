export function length(v) {
	return Math.sqrt(v.reduce((x) => Math.pow(x, 2)));
}

export function add(a, b) {
	if (a.length != b.length) return;
	let c = [];
	for (let i = 0; i < a.length; i++) {
		c.push(a[i] + b[i]);
	}
	return c;
}

export function sub(a, b) {
	if (a.length != b.length) return;
	let c = [];
	for (let i = 0; i < a.length; i++) {
		c.push(a[i] - b[i]);
	}
	return c;
}

export function mul(a, b) {
	if (a.length != b.length) return;
	let c = [];
	for (let i = 0; i < a.length; i++) {
		c.push(a[i] * b[i]);
	}
	return c;
}

export function div(a, b) {
	if (a.length != b.length) return;
	let c = [];
	for (let i = 0; i < a.length; i++) {
		c.push(a[i] / b[i]);
	}
	return c;
}

export function mulScalar(v, s) {
	let w = [];
	for (let i = 0; i < v.length; i++) {
		w.push(v[i] * s);
	}
	return w;
}

export function divScalar(v, s) {
	return mulScalar(v, 1 / s);
}
