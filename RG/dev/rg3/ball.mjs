export class Ball {
	ctx;
	constructor(e, id, ctx) {
		this.id = id;
		this.x = e.x;
		this.y = e.y;
		this.vx = Math.floor(Math.random() * 51 + 50) / 60;
		this.vy = Math.floor(Math.random() * 51 + 50) / 60;
		this.r = Math.floor(Math.random() * 5 + 3);
		this.ctx = ctx;
	}

	move() {
		this.handleCollision();
		this.x += this.vx;
		this.y += this.vy;
	}

	draw(color) {
		this.ctx.fillStyle = color || "white";
		this.ctx.beginPath();
		this.ctx.arc(this.x, this.y, this.r, 0, 2 * Math.PI, false);
		this.ctx.stroke();
		this.ctx.fill();
	}

	hasCollidedWith(b) {
		let diameter = this.r + b.r;
		return (
			diameter >=
			Math.sqrt(Math.pow(this.x - b.x, 2) + Math.pow(this.y - b.y, 2))
		);
	}

	highlight(b) {
		let x = this;
		let y = b;
		x.draw("green");
		y.draw("green");
	}

	handleCollision() {
		if (this.x - this.r / 2 < 0 && this.vx < 0) {
			this.vx *= -1;
		}
		if (this.x + this.r / 2 > 600 && this.vx > 0) {
			this.vx *= -1;
		}
		if (this.y - this.r / 2 < 0 && this.vy < 0) {
			this.vy *= -1;
		}
		if (this.y + this.r / 2 > 600 && this.vy > 0) {
			this.vy *= -1;
		}
	}
}
