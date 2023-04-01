import { Rectangle } from "./rectangle.mjs";

let leafs = [];
let leafsToDraw = [];

export class Quadtree {
	constructor(bounds, capacity, ctx, parent) {
		this.bounds = bounds;
		this.capacity = capacity;
		this.ctx = ctx;

		this.children = [];
		this.parent = parent || null;
		this.balls = [];
	}

	update(node, ball, flag) {
		this.insert(node, ball);
		this.removeUnusedLeafs();
		this.resolveCollision();
		if (flag) {
			this.draw(leafsToDraw);
		}
	}

	draw() {
		leafs.forEach((quad) => {
			const { boundX, boundY, boundW, boundH } = quad.bounds;
			this.ctx.beginPath();
			this.ctx.strokeStyle = "white";
			this.ctx.strokeRect(
				boundX - boundW,
				boundY - boundH,
				boundW * 2,
				boundH * 2
			);
		});
	}

	divide(node) {
		let { boundX, boundY, boundW, boundH } = node.bounds;
		let northWest = new Rectangle(
			boundX - boundW / 2,
			boundY - boundH / 2,
			boundW / 2,
			boundH / 2
		);
		let topLeft = new Quadtree(northWest, node.capacity, node.ctx, node);

		let northEast = new Rectangle(
			boundX + boundW / 2,
			boundY - boundH / 2,
			boundW / 2,
			boundH / 2
		);
		let topRight = new Quadtree(northEast, node.capacity, node.ctx, node);

		let southWest = new Rectangle(
			boundX - boundW / 2,
			boundY + boundH / 2,
			boundW / 2,
			boundH / 2
		);
		let bottomLeft = new Quadtree(southWest, node.capacity, node.ctx, node);

		let southEast = new Rectangle(
			boundX + boundW / 2,
			boundY + boundH / 2,
			boundW / 2,
			boundH / 2
		);
		let bottomRight = new Quadtree(southEast, node.capacity, node.ctx, node);

		node.children = [topLeft, topRight, bottomLeft, bottomRight];

		let i = leafs.indexOf(node);
		leafs.splice(i, 1);
		leafs.push(...node.children);
	}

	insert(node, ball) {
		if (node.children.length == 0) {
			if (!this.contains(node, ball)) {
				return;
			}
			this.removeFromNeighbors(node, ball);
			this.handleDuplicate(node, ball);
			if (node.balls.length >= node.capacity) {
				this.divide(node);
				this.insert(node, ball);
			}
		} else {
			const quadIndex = this.getQuadrantIndex(node, ball);
			const child = node.children[quadIndex];
			if (this.contains(child, ball)) {
				this.insert(child, ball);
			}
		}
	}

	removeFromNeighbors(node, ball) {
		if (node.parent == null) {
			return;
		}
		let neighbors = node.parent.children.filter((child) => child != node);
		neighbors.forEach((quad) => {
			let foundBall = quad.balls.find((b) => b.id == ball.id);
			if (foundBall != undefined) {
				let i = quad.balls.indexOf(foundBall);
				quad.balls.splice(i, 1);
			}
		});
	}

	handleDuplicate(node, ball) {
		let foundBall = node.balls.find((b) => b.id == ball.id);
		// if ball wasn't in the quadrant before add it
		if (foundBall == undefined) {
			node.balls.push(ball);
			return;
		}
		// if it were, update it
		let i = node.balls.indexOf(foundBall);
		node.balls.splice(i, 1);
		node.balls.push(ball);
	}

	resolveCollision() {
		leafs.forEach((quad) => {
			if (quad.balls.length != 0) {
				quad.balls.forEach((bx) => {
					quad.balls.forEach((by) => {
						if (bx != by && this.intersects(bx, by)) {
							bx.draw("green");
							by.draw("green");
						}
					});
				});
			}
		});
	}

	removeUnusedLeafs() {
		// leafs = leafs.filter((quad) => quad.balls.length != 0);
		// v update() -> this.draw(leafs);
		leafsToDraw = leafs.filter((quad) => quad.balls.length != 0);
	}

	contains(node, ball) {
		const { x, y, r } = ball;
		const { boundX, boundY, boundW, boundH } = node.bounds;
		const leftBound = boundX - boundW;
		const rightBound = boundX + boundW;
		const topBound = boundY - boundH;
		const bottomBound = boundY + boundH;

		return (
			x - r >= leftBound &&
			x + r <= rightBound &&
			y - r >= topBound &&
			y + r <= bottomBound
		);
	}

	getQuadrantIndex(node, ball) {
		const { x, y } = ball;
		const { boundX, boundY } = node.bounds;

		if (x < boundX && y < boundY) {
			return 0;
		} else if (x >= boundX && y < boundY) {
			return 1;
		} else if (x < boundX && y >= boundY) {
			return 2;
		} else {
			return 3;
		}
	}

	intersects(bx, by) {
		let diameter = bx.r + by.r;
		return (
			diameter >= Math.sqrt(Math.pow(bx.x - by.x, 2) + Math.pow(bx.y - by.y, 2))
		);
	}
}
