// Return vectors as arrays, e.g.:
// return [ 1, 2, 3, 4 ];

export function negate(v) {
	// TODO implement
	const n = v.length;
	var neg_v = [];
	for (let i = 0; i < n; i++) {
		neg_v.push(-v[i]);
	}
	return neg_v;
}

export function add(v, w) {
	// TODO implement
	if (v.length != w.length) return;
	const n = v.length || w.length;
	var added_v = [];
	for (let i = 0; i < n; i++) {
		added_v.push(v[i] + w[i]);
	}
	return added_v;
}

export function subtract(v, w) {
	// TODO implement
	if (v.length != w.length) return;
	const n = v.length || w.length;
	var sub_v = [];
	for (let i = 0; i < n; i++) {
		sub_v.push(Math.abs(v[i] - w[i]));
	}
	return sub_v;
}

export function multiply(v, w) {
	// TODO implement
	if (v.length != w.length) return;
	const n = v.length || w.length;
	var mul_v = [];
	for (let i = 0; i < n; i++) {
		mul_v.push(v[i] * w[i]);
	}
	return mul_v;
}

export function divide(v, w) {
	// TODO implement
	if (v.length != w.length) return;
	const n = v.length || w.length;
	var div_v = [];
	for (let i = 0; i < n; i++) {
		div_v.push(v[i] / w[i]);
	}
	return div_v;
}

export function dot(v, w) {
	// TODO implement
	if (v.length != w.length) return;
	var phi = (angle(v, w) * Math.PI) / 180;
	var abs_v = length(v);
	var abs_w = length(w);
	return abs_v * abs_w * Math.cos(phi);
}

export function cross(v, w) {
	// TODO implement
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
	var abs_v = length(v);
	var norm_v = [];
	for (let i = 0; i < n; i++) {
		norm_v.push(v[i] / abs_v);
	}
	return norm_v;
}

export function project(v, w) {
	// TODO implement
}

export function reflect(v, w) {
	// TODO implement
}

export function angle(v, w) {
	// TODO implement
	return (
		(Math.acos(
			multiply(v, w).reduce((acc, val) => {
				return acc + val;
			}, 0) /
				(length(v) * length(w))
		) *
			180) /
		Math.PI
	);
}
