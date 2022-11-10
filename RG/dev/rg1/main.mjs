import { transform } from "./transform.mjs";
import "./Vector.mjs";
import { project } from "./Vector.mjs";

const input = document.getElementById("input");
const output = document.getElementById("output");

input.addEventListener("change", (e) => {
	const points = JSON.parse(input.value);
	const transformedPoints = transform(points);
	// const transformedPoints = project(points[0], points[1]);
	output.value = JSON.stringify(transformedPoints);
});
