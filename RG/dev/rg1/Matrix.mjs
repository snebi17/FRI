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
		[0, 0, 0, 1]
	];
}

export function translation(t) {
	// TODO implement
}

export function scaling(s) {
	// TODO implement
}

export function rotationX(angle) {
	// TODO implement
	return [
		[1, 0, 0],
		[0, Math.cos(angle), -Math.sin(angle)],
		[0, Math.sin(angle), Math.cos(angle)],
	];
}

export function rotationY(angle) {
	// TODO implement
	return [
		[Math.cos(angle), 0, Math.sin(angle)],
		[0, 1, 0],
		[-Math.sin(angle), 0, Math.cos(angle)],
	];
}

export function rotationZ(angle) {
	// TODO implement
	return [
		[Math.cos(angle), -Math.sin(0), 0],
		[Math.sin(angle), Math.cos(angle), 0],
		[0, 0, 1],
	];
}

export function negate(m) {
	// TODO implement
}

export function add(m, n) {
	// TODO implement
}

export function subtract(m) {
	// TODO implement
}

export function transpose(m) {
	// TODO implement
	var transposed_m = [];
	var n_rows = m.length;
	var n_cols = m[0].length;
	for (let i = 0; i < n_cols; i++) {
		var row = [];
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
}

export function transform(m, v) {
	// TODO implement
}
