import { Bezier } from "./Bezier.mjs";
import { Spline } from "./Spline.mjs";

const width = 550;
const height = 400;

let canvas;
let ctx;
let spline;

const btnContinuous = document.getElementById("continuous");
const btnSmooth = document.getElementById("smooth");

function draw() {
	clearCanvas();

	const ratio = (width / height) * 10;
	let curves = spline.getCurves();
	ctx.fillStyle = "white";
	for (let i = 0; i < curves.length; i++) {
		let p0 = curves[i].p[0];
		let p1 = curves[i].p[3];
		if (i == 0) {
			drawArc(p0, ratio);
		} else if (i == curves.length - 1) {
			drawArc(p1, ratio);
		} else {
			drawRect(p0, ratio);
			drawRect(p1, ratio);
		}
	}
}

function drawArc(p, ratio) {
	ctx.fillStyle = "black";
	ctx.beginPath();
	ctx.arc(p[0] * ratio, p[1] * ratio, 3, 0, 2 * Math.PI);
	ctx.stroke();
	ctx.fill();
}

function drawRect(p, ratio) {
	ctx.fillStyle = "black";
	ctx.beginPath();
	ctx.rect(p[0] * ratio - 2.5, p[1] * ratio - 2.5, 10, 10);
	ctx.stroke();
}

function drawLine(p0, p1) {}

function clearCanvas() {
	ctx.clearRect(0, 0, canvas.width, canvas.height);
	ctx.fillStyle = "black";
	ctx.fillRect(0, 0, canvas.width, canvas.height);
	ctx.fillStyle = "white";
	ctx.fillRect(5, 5, canvas.width - 10, canvas.height - 10);
}

function initCanvas() {
	canvas = document.querySelector("canvas");
	canvas.width = width;
	canvas.height = height;
	ctx = canvas.getContext("2d");
	ctx.fillStyle = "black";
	ctx.fillRect(0, 0, canvas.width, canvas.height);
	ctx.fillStyle = "white";
	ctx.fillRect(5, 5, canvas.width - 10, canvas.height - 10);
}

function init() {
	initCanvas();
	let b0 = new Bezier([
		[1, 2],
		[3, 4],
		[5, 6],
		[9, 9],
	]);
	let b1 = new Bezier([
		[7, 8],
		[9, 10],
		[11, 12],
		[15, 15],
	]);
	let b2 = new Bezier([
		[13, 14],
		[15, 16],
		[17, 18],
		[20, 20],
	]);
	let b3 = new Bezier([
		[10, 20],
		[30, 40],
		[50, 60],
		[50, 60],
	]);
	spline = new Spline([b0, b1, b2, b3]);
	draw();
}

function makeContinuous() {
	spline.makeContinuous();
}

function makeSmooth() {
	spline.makeSmooth();
}

btnContinuous.addEventListener("click", () => {
	makeContinuous();
	draw();
});

btnSmooth.addEventListener("click", () => {
	makeSmooth();
	draw();
});

document.onload = init();
