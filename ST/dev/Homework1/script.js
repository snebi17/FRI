let inventory = [];
let isMobile;

let categoryMap = {};

let exitModal = document.getElementById("exit-modal");
exitModal.addEventListener("click", () => {
	let modal = document.getElementById("add-modal");
	hideModal(modal);
});

let backModal = document.getElementById("back-modal");
backModal.addEventListener("click", () => {
	let modal = document.getElementById("cards-modal");
	hideModal(modal);
	modal.childNodes.forEach((node) => {
		if (node.nodeName == "DIV") {
			node.remove();
		}
	});
});

window.onload = () => {
	isMobile = window.outerWidth <= 768;
	setup();
};

function setup() {
	if (localStorage.getItem("inventory") == null) {
		fetch("./card.json")
			.then((response) => response.json())
			.then((data) => (inventory = data))
			.catch((err) => console.log(err))
			.finally(() => {
				if (!isMobile) {
					generateCards(12);
				} else {
					generateCards(8);
				}
			});
	} else {
		inventory = JSON.parse(localStorage.getItem("inventory"));
		if (!isMobile) {
			generateCards(12);
		} else {
			generateCards(8);
		}
	}

	let addCategoryForm = document.getElementById("add-form");
	addCategoryForm.addEventListener("submit", (e) => {
		let category = document.getElementById("category-name").value;
		if (category == "") {
			e.preventDefault();
			return;
		}
		inventory.push({ [category]: [] });
		localStorage.setItem("inventory", JSON.stringify(inventory));
		hideModal();
	});
}

function generateCards(n) {
	let cards = document.getElementById("cards");
	if (inventory.length > 0) {
		if (inventory.length < n) {
			inventory.forEach((card, i) => {
				let text = Object.keys(card).toString();
				let length = Object.values(card)[0].length;
				let div = generateCard(text, length, i);
				cards.appendChild(div);
			});
		} else {
			for (let i = 0; i < n - 1; i++) {
				let text = Object.keys(inventory[i]).toString();
				let length = Object.values(inventory[i])[0].length;
				let div = generateCard(text, length, i);
				cards.appendChild(div);
			}
			let div = generateMoreCard();
			cards.appendChild(div);
			return;
		}
	}
	let div = generateAddCard();
	cards.appendChild(div);
}

function generateCard(txt, len, i) {
	let text = document.createTextNode(txt);
	let small = document.createElement("small");
	small.appendChild(document.createTextNode(len + " cards"));
	let div = document.createElement("div");
	div.classList.add("grid-item");
	div.appendChild(text);
	div.appendChild(small);
	div.addEventListener("click", (e) => {
		let cardsModal = document.getElementById("cards-modal");
		showModal(cardsModal);
		// Object.values(inventory[i]).forEach((card) => {
		// 	card.forEach((question) => {
		// 		let q = Object.keys(question).toString();
		// 		Object.values(question)[0].forEach((answer) => {
		// 			let a = Object.keys(answer).toString();
		// 		});
		// 	});
		// });
		generateCategoryCards(i, cardsModal);
	});
	return div;
}

function generateMoreCard() {
	let div = document.createElement("div");
	div.classList.add("grid-item");
	let li = document.createElement("li");
	li.classList.add("fa-solid", "fa-ellipsis");
	div.appendChild(li);
	div.addEventListener("click", (e) => {
		// show more category cards
	});
	return div;
}

function generateAddCard() {
	let div = document.createElement("div");
	div.classList.add("grid-item");
	let li = document.createElement("li");
	li.classList.add("fa-solid", "fa-plus");
	div.appendChild(li);
	div.addEventListener("click", () => {
		let modal = document.getElementById("add-modal");
		showModal(modal);
	});
	return div;
}

function generateCategoryCards(i, modal) {
	Object.values(inventory[i]).forEach((card) => {
		card.forEach((question) => {
			let div = document.createElement("div");
			let text = Object.keys(question).toString();
			let q = document.createTextNode(text);
			div.appendChild(q);
			Object.values(question)[0].forEach((answer) => {
				let a = Object.keys(answer).toString();
				let small = document.createElement("small");
				small.appendChild(document.createTextNode(a));
				div.classList.add("grid-item");
				div.appendChild(small);
			});
			modal.appendChild(div);
		});
	});
}

function showModal(modal) {
	modal.classList.remove("display-none");
}

function hideModal(modal) {
	modal.classList.add("display-none");
}
