// Return vectors as arrays, e.g.:
// return [ 1, 2, 3, 4 ];

export function negate(v) {
	// TODO implement
	const n = v.length;
<<<<<<< HEAD
	var neg_v = [];
=======
	let neg_v = [];
>>>>>>> b67cc0623e37f64e5a0cf96156d87212273cf4cc
	for (let i = 0; i < n; i++) {
		neg_v.push(-v[i]);
	}
	return neg_v;
}

export function add(v, w) {
	// TODO implement
	if (v.length != w.length) return;
	const n = v.length || w.length;
<<<<<<< HEAD
	var added_v = [];
=======
	let added_v = [];
>>>>>>> b67cc0623e37f64e5a0cf96156d87212273cf4cc
	for (let i = 0; i < n; i++) {
		added_v.push(v[i] + w[i]);
	}
	return added_v;
}

export function subtract(v, w) {
	// TODO implement
	if (v.length != w.length) return;
	const n = v.length || w.length;
<<<<<<< HEAD
	var sub_v = [];
	for (let i = 0; i < n; i++) {
		sub_v.push(Math.abs(v[i] - w[i]));
=======
	let sub_v = [];
	for (let i = 0; i < n; i++) {
		sub_v.push(v[i] - w[i]);
>>>>>>> b67cc0623e37f64e5a0cf96156d87212273cf4cc
	}
	return sub_v;
}

export function multiply(v, w) {
	// TODO implement
	if (v.length != w.length) return;
	const n = v.length || w.length;
<<<<<<< HEAD
	var mul_v = [];
=======
	let mul_v = [];
>>>>>>> b67cc0623e37f64e5a0cf96156d87212273cf4cc
	for (let i = 0; i < n; i++) {
		mul_v.push(v[i] * w[i]);
	}
	return mul_v;
}

export function divide(v, w) {
	// TODO implement
	if (v.length != w.length) return;
	const n = v.length || w.length;
<<<<<<< HEAD
	var div_v = [];
=======
	let div_v = [];
>>>>>>> b67cc0623e37f64e5a0cf96156d87212273cf4cc
	for (let i = 0; i < n; i++) {
		div_v.push(v[i] / w[i]);
	}
	return div_v;
}

export function dot(v, w) {
	// TODO implement
	if (v.length != w.length) return;
<<<<<<< HEAD
	var phi = (angle(v, w) * Math.PI) / 180;
	var abs_v = length(v);
	var abs_w = length(w);
=======
	const phi = angle(v, w);
	const abs_v = length(v);
	const abs_w = length(w);
>>>>>>> b67cc0623e37f64e5a0cf96156d87212273cf4cc
	return abs_v * abs_w * Math.cos(phi);
}

export function cross(v, w) {
	// TODO implement
<<<<<<< HEAD
=======
	let a = v[1] * w[2] - v[2] * w[1];
	let b = v[0] * w[2] - v[2] * w[0];
	let c = v[0] * w[1] - v[1] * w[0];
	return [a, b, c];
>>>>>>> b67cc0623e37f64e5a0cf96156d87212273cf4cc
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
<<<<<<< HEAD
	var abs_v = length(v);
	var norm_v = [];
=======
	const abs_v = length(v);
	let norm_v = [];
>>>>>>> b67cc0623e37f64e5a0cf96156d87212273cf4cc
	for (let i = 0; i < n; i++) {
		norm_v.push(v[i] / abs_v);
	}
	return norm_v;
}

export function project(v, w) {
	// TODO implement
<<<<<<< HEAD
=======
	const scale = dot(v, w) / Math.pow(length(w), 2);
	let proj_v = [];
	for (let i = 0; i < w.length; i++) {
		proj_v.push(w[i] * scale);
	}
	return proj_v;
>>>>>>> b67cc0623e37f64e5a0cf96156d87212273cf4cc
}

export function reflect(v, w) {
	// TODO implement
<<<<<<< HEAD
=======
	let k = 2 * dot(v, w);
	let dot_w = [];
	for (let i = 0; i < w.length; i++) {
		dot_w.push(w[i] * k);
	}
	let reflected_v = subtract(v, dot_w);
	return reflected_v;
>>>>>>> b67cc0623e37f64e5a0cf96156d87212273cf4cc
}

export function angle(v, w) {
	// TODO implement
<<<<<<< HEAD
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
=======
	return Math.acos(
		multiply(v, w).reduce((acc, val) => {
			return acc + val;
		}, 0) /
			(length(v) * length(w))
	);
	// 	180) /
	// Math.PI
>>>>>>> b67cc0623e37f64e5a0cf96156d87212273cf4cc
}
