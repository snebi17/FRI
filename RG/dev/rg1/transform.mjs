<<<<<<< HEAD
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
=======
import {
	translation,
	rotationX,
	rotationY,
	rotationZ,
	scaling,
	transform as transformacija,
} from "./Matrix.mjs";

export function transform(points) {
	// TODO implement
	let transformed_matrix = [];
	for (let i = 0; i < points.length; i++) {
		let v = points[i];
		v.push(1);
		v = transformacija(translation([2.8, 0, 0]), v);
		v = transformacija(rotationY(Math.PI / 4), v);
		v = transformacija(translation([0, 0, 7.15]), v);
		v = transformacija(translation([0, 2.45, 0]), v);
		v = transformacija(scaling([1.8, 1.8, 1]), v);
		v = transformacija(rotationX((5 * Math.PI) / 11), v);
		v = transformacija(rotationZ((9 * Math.PI) / 11), v);
		v.pop();
		transformed_matrix.push(v);
	}
	return transformed_matrix;
}

// Vhod: [
// 	[1, 2, 3],
// 	[2, 3, 4],
// 	[3, 4, 5],
// 	[4, 5, 6],
// ];
// Izhod: [
// 	[-4.3738, 9.203, 8.8655],
// 	[-6.6538, 10.3637, 10.6472],
// 	[-8.9338, 11.5244, 12.4289],
// 	[-11.2137, 12.6852, 14.2106],
// ];
>>>>>>> b67cc0623e37f64e5a0cf96156d87212273cf4cc
