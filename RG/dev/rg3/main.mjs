import { Quadtree } from "./quadtree.mjs";
import { Ball } from "./ball.mjs";
import { Rectangle } from "./rectangle.mjs";

const canvas = document.querySelector("canvas");
const ctx = canvas.getContext("2d");
const width = 600;
const height = 600;

let quadTree;
let ballId = 0;
let balls = [];

let startStop = document.getElementById("startStop");
let stopped = false;

startStop.addEventListener("click", () => {
	stopped = !stopped;
});

let showBounds = document.getElementById("showBounds");
showBounds.addEventListener("click", () => {
	showBounds = !showBounds;
});

window.onload = init;

function init() {
	initCanvas();
	initQuadtree();
	loop();
}

function loop() {
	requestAnimationFrame(loop);
	if (!stopped) {
		updateAndRender();
	}
}

function updateAndRender() {
	drawCanvas();
	balls.forEach((ball) => {
		ball.move();
		quadTree.update(quadTree, ball, showBounds);
		ball.draw();
	});
}

function initCanvas() {
	canvas.width = width;
	canvas.height = height;
	drawCanvas();
	canvas.addEventListener("mousedown", (e) => {
		const rect = canvas.getBoundingClientRect();
		let ball = new Ball(
			{
				x: e.clientX - rect.left,
				y: e.clientY - rect.top,
			},
			ballId++,
			ctx
		);
		balls.push(ball);
		quadTree.insert(quadTree, ball);
	});
}

function drawCanvas() {
	ctx.clearRect(0, 0, width, height);
	ctx.fillStyle = "black";
	ctx.fillRect(0, 0, width, height);
	ctx.globalAlpha = 1;
}

function initQuadtree() {
	const bounds = new Rectangle(width / 2, height / 2, width / 2, height / 2);
	quadTree = new Quadtree(bounds, 5, ctx, null);
}
