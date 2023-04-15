<<<<<<< HEAD
import { transpose } from "./Matrix.mjs";
import { transform } from "./transform.mjs";
import {
	add,
	divide,
	negate,
	dot,
	angle,
	length,
	normalize,
} from "./Vector.mjs";
=======
import { transform } from "./transform.mjs";
import "./Vector.mjs";
import { project } from "./Vector.mjs";
>>>>>>> b67cc0623e37f64e5a0cf96156d87212273cf4cc

const input = document.getElementById("input");
const output = document.getElementById("output");

input.addEventListener("change", (e) => {
	const points = JSON.parse(input.value);
<<<<<<< HEAD
	console.log(points);
	// const transformedPoints = transform(points);
	// const transformedPoints = negate(points);
	// const transformedPoints = divide(points[0], points[1]);
	// const transformedPoints = dot(points[0], points[1]);
	// const transformedPoints = angle(points[0], points[1]);
	// console.log(length(points));
	// console.log(angle(points[0], points[1]));
	// console.log(normalize(points));
	const transformedPoints = transpose(points);
=======
	const transformedPoints = transform(points);
	// const transformedPoints = project(points[0], points[1]);
>>>>>>> b67cc0623e37f64e5a0cf96156d87212273cf4cc
	output.value = JSON.stringify(transformedPoints);
});
