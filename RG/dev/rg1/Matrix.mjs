// Return matrices as 2D arrays in row-major order, e.g.:
// return [
//     [ 1, 2, 3, 4 ],
//     [ 5, 6, 7, 8 ],
//     [ 7, 6, 5, 4 ],
//     [ 3, 2, 1, 0 ],
// ];

export function identity() {
	// TODO implement
	return [
		[1, 0, 0, 0],
		[0, 1, 0, 0],
		[0, 0, 1, 0],
		[0, 0, 0, 1],
	];
}

export function translation(t) {
	// TODO implement
	return [
		[1, 0, 0, t[0]],
		[0, 1, 0, t[1]],
		[0, 0, 1, t[2]],
		[0, 0, 0, 1],
	];
}

export function scaling(s) {
	// TODO implement
	return [
		[s[0], 0, 0, 0],
		[0, s[1], 0, 0],
		[0, 0, s[2], 0],
		[0, 0, 0, 1],
	];
}

export function rotationX(angle) {
	// TODO implement
	return [
		[1, 0, 0, 0],
		[0, Math.cos(angle), -Math.sin(angle), 0],
		[0, Math.sin(angle), Math.cos(angle), 0],
		[0, 0, 0, 1],
	];
}

export function rotationY(angle) {
	// TODO implement
	return [
		[Math.cos(angle), 0, Math.sin(angle), 0],
		[0, 1, 0, 0],
		[-Math.sin(angle), 0, Math.cos(angle), 0],
		[0, 0, 0, 1],
	];
}

export function rotationZ(angle) {
	// TODO implement
	return [
		[Math.cos(angle), -Math.sin(angle), 0, 0],
		[Math.sin(angle), Math.cos(angle), 0, 0],
		[0, 0, 1, 0],
		[0, 0, 0, 1],
	];
}

export function negate(m) {
	// TODO implement
	let neg_m = [];
	const n_rows = m.length;
	const n_cols = m[0].length;
	for (let i = 0; i < n_rows; i++) {
		for (let j = 0; j < n_cols; j++) {
			neg_m[i][j].push(-m[i][j]);
		}
	}
	return neg_m;
}

export function add(m, n) {
	// TODO implement
	let added_m = [];
	const n_rows = m.length;
	const n_cols = m[0].length;
	for (let i = 0; i < n_rows; i++) {
		for (let j = 0; j < n_cols; j++) {
			added_m[i][j].push(m[i][j] + n[i][j]);
		}
	}
	return added_m;
}

export function subtract(m, n) {
	// TODO implement
	let sub_m = [];
	const n_rows = m.length;
	const n_cols = m[0].length;
	for (let i = 0; i < n_rows; i++) {
		for (let j = 0; j < n_cols; j++) {
			sub_m[i][j].push(m[i][j] - n[i][j]);
		}
	}
	return sub_m;
}

export function transpose(m) {
	// TODO implement
	let transposed_m = [];
	const n_rows = m.length;
	const n_cols = m[0].length;
	for (let i = 0; i < n_cols; i++) {
		let row = [];
		for (let j = 0; j < n_rows; j++) {
			row.push(m[j][i]);
		}
		transposed_m.push(row);
	}
	return transposed_m;
}

export function multiply(m, n) {
	// TODO implement
	if (m[0].length != n.length) return;
	let multiplied_m = [];
	const mn_rows = m.length;
	const mn_cols = m[0].length;
	const nn_cols = n[0].length;
	for (let i = 0; i < mn_rows; i++) {
		multiplied_m[i] = [];
		for (let j = 0; j < nn_cols; j++) {
			multiplied_m[i][j] = 0;
			for (let k = 0; k < mn_cols; k++) {
				multiplied_m[i][j] += m[i][k] * n[k][j];
			}
		}
	}
	return multiplied_m;
}

export function transform(m, v) {
	// TODO implement
	if (m[0].length != v.length) return;
	let multiplied_v = [];
	const mn_rows = m.length;
	const vn_rows = v.length;
	for (let i = 0; i < mn_rows; i++) {
		multiplied_v[i] = 0;
		for (let j = 0; j < vn_rows; j++) {
			multiplied_v[i] += m[i][j] * v[j];
		}
		multiplied_v[i] = Number.parseFloat(multiplied_v[i].toFixed(4));
	}
	return multiplied_v;
}
