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
	addCategoryForm.addEventListener("submit", () => {
		let category = document.getElementById("category-name").value;
		inventory.push({ [category]: [] });
		localStorage.setItem("inventory", JSON.stringify(inventory));
		hideModal();
	});
}

function generateCards(n) {
	let cards = document.getElementById("cards");
	if (inventory.length > 0) {
		if (inventory.length < n) {
			inventory.forEach((card) => {
				let text = Object.keys(card).toString();
				let length = Object.values(card)[0].length;
				let div = generateCard(text, length);
				cards.appendChild(div);
			});
		} else {
			for (let i = 0; i < n - 1; i++) {
				let text = Object.keys(inventory[i]).toString();
				let length = Object.values(inventory[i])[0].length;
				let div = generateCard(text, length);
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

function generateCard(txt, len) {
	let text = document.createTextNode(txt);
	let small = document.createElement("small");
	small.appendChild(document.createTextNode(len + " cards"));
	let div = document.createElement("div");
	div.classList.add("grid-item");
	div.appendChild(text);
	div.appendChild(small);
	div.addEventListener("click", (e) => {
		let cardModal = document.getElementById("cards-modal");
		cards = showModal(cardModal);
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

function showModal(modal) {
	modal.classList.remove("display-none");
}

function hideModal(modal) {
	modal.classList.add("display-none");
}
