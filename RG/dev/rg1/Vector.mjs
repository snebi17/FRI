// Return vectors as arrays, e.g.:
// return [ 1, 2, 3, 4 ];

export function negate(v) {
	// TODO implement
	const n = v.length;
	let neg_v = [];
	for (let i = 0; i < n; i++) {
		neg_v.push(-v[i]);
	}
	return neg_v;
}

export function add(v, w) {
	// TODO implement
	if (v.length != w.length) return;
	const n = v.length || w.length;
	let added_v = [];
	for (let i = 0; i < n; i++) {
		added_v.push(v[i] + w[i]);
	}
	return added_v;
}

export function subtract(v, w) {
	// TODO implement
	if (v.length != w.length) return;
	const n = v.length || w.length;
	let sub_v = [];
	for (let i = 0; i < n; i++) {
		sub_v.push(v[i] - w[i]);
	}
	return sub_v;
}

export function multiply(v, w) {
	// TODO implement
	if (v.length != w.length) return;
	const n = v.length || w.length;
	let mul_v = [];
	for (let i = 0; i < n; i++) {
		mul_v.push(v[i] * w[i]);
	}
	return mul_v;
}

export function divide(v, w) {
	// TODO implement
	if (v.length != w.length) return;
	const n = v.length || w.length;
	let div_v = [];
	for (let i = 0; i < n; i++) {
		div_v.push(v[i] / w[i]);
	}
	return div_v;
}

export function dot(v, w) {
	// TODO implement
	if (v.length != w.length) return;
	const phi = angle(v, w);
	const abs_v = length(v);
	const abs_w = length(w);
	return abs_v * abs_w * Math.cos(phi);
}

export function cross(v, w) {
	// TODO implement
	let a = v[1] * w[2] - v[2] * w[1];
	let b = v[0] * w[2] - v[2] * w[0];
	let c = v[0] * w[1] - v[1] * w[0];
	return [a, b, c];
}

export function length(v) {
	// TODO implement
	return Math.sqrt(
		v
			.map((x) => Math.pow(x, 2))
			.reduce((acc, val) => {
				return acc + val;
			}, 0)
	);
}

export function normalize(v) {
	// TODO implement
	const n = v.length;
	const abs_v = length(v);
	let norm_v = [];
	for (let i = 0; i < n; i++) {
		norm_v.push(v[i] / abs_v);
	}
	return norm_v;
}

export function project(v, w) {
	// TODO implement
	const scale = dot(v, w) / Math.pow(length(w), 2);
	let proj_v = [];
	for (let i = 0; i < w.length; i++) {
		proj_v.push(w[i] * scale);
	}
	return proj_v;
}

export function reflect(v, w) {
	// TODO implement
	let k = 2 * dot(v, w);
	let dot_w = [];
	for (let i = 0; i < w.length; i++) {
		dot_w.push(w[i] * k);
	}
	let reflected_v = subtract(v, dot_w);
	return reflected_v;
}

export function angle(v, w) {
	// TODO implement
	return Math.acos(
		multiply(v, w).reduce((acc, val) => {
			return acc + val;
		}, 0) /
			(length(v) * length(w))
	);
	// 	180) /
	// Math.PI
}
