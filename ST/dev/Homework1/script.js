<<<<<<< HEAD
let isCategory = false;
let currentCategory = [];
let currentCategoryId;
let entries;

// Add card modal
const openModal = document.getElementById("add-fab");
openModal.addEventListener("click", () => {
	let modal = document.createElement("div");
	modal.classList.add("add-modal");
	showModal(modal);
});

const sortCategories = document.getElementById("sort-fab");
sortCategories.addEventListener("click", () => {
	let inventory = JSON.parse(localStorage.getItem("inventory"));

	const sorted = inventory.sort(function (a, b) {
		let first = Object.keys(a)[0];
		let second = Object.keys(b)[0];
		return first < second ? -1 : first > second ? 1 : 0;
	});

	localStorage.setItem("inventory", JSON.stringify(sorted));

	let categories = document.getElementById("categories");
	removeChildrenFromDOM(categories);
	getEntries();
	renderCategories();
=======
// let inventory = [];
// let displayInventory = {};
// let isMobile;

// addEventListener("resize", (e) => {
// 	isMobile = e.target.innerWidth < 400;
// });

// let exitModal = document.getElementById("exit-modal");
// exitModal.addEventListener("click", () => {
// 	let modal = document.getElementById("add-modal");
// 	hideModal(modal);
// });

// let backModal = document.getElementById("back-modal");
// backModal.addEventListener("click", () => {
// 	let modal = document.getElementById("cards-modal");
// 	hideModal(modal);
// 	// const childNodes = Array.from(modal.childNodes);
// 	const filteredNodes = childNodes.filter(
// 		(node) => node.nodeName == "BUTTON"
// 	);
// 	modal.replaceChildren(...filteredNodes);
// 	displayInventory = {};
// });

// window.onload = () => {
// 	setup();
// };

// function setup() {
// 	if (localStorage.getItem("inventory") == null) {
// 		fetch("./card.json")
// 			.then((response) => response.json())
// 			.then((data) => (inventory = data))
// 			.catch((err) => console.log(err))
// 			.finally(() => {
// 				if (!isMobile) {
// 					generateCards(12);
// 				} else {
// 					generateCards(8);
// 				}
// 			});
// 	} else {
// 		inventory = JSON.parse(localStorage.getItem("inventory"));
// 		generateCards();
// 	}

// 	let addCategoryForm = document.getElementById("add-form");
// 	addCategoryForm.addEventListener("submit", (e) => {
// 		let category = document.getElementById("category-name").value;
// 		if (category == "") {
// 			e.preventDefault();
// 			return;
// 		}
// 		inventory.push({ [category]: [] });
// 		localStorage.setItem("inventory", JSON.stringify(inventory));
// 		hideModal();
// 	});

// 	let editCardForm = document.getElementById("");
// }

// function generateCards() {
// 	let cards = document.getElementById("cards");
// 	if (inventory.length > 0) {
// 		inventory.forEach((card, i) => {
// 			let text = Object.keys(card).toString();
// 			let length = Object.values(card)[0].length;
// 			let div = generateCard(text, length, i);
// 			cards.appendChild(div);
// 		});
// 	}
// 	let div = generateAddCard();
// 	cards.appendChild(div);
// }

// function generateCard(txt, len, i) {
// 	let text = document.createTextNode(txt);
// 	let small = document.createElement("small");
// 	small.appendChild(document.createTextNode(len + " cards"));
// 	let div = document.createElement("div");
// 	div.classList.add("grid-item");
// 	div.appendChild(text);
// 	div.appendChild(small);
// 	div.addEventListener("click", (e) => {
// 		let cardsModal = document.getElementById("cards-modal");
// 		showModal(cardsModal);
// 		generateCategoryCards(i, cardsModal);
// 	});
// 	return div;
// }

// function generateAddCard() {
// 	let div = document.createElement("div");
// 	div.classList.add("grid-item");
// 	let li = document.createElement("li");
// 	li.classList.add("fa-solid", "fa-plus");
// 	div.appendChild(li);
// 	div.addEventListener("click", () => {
// 		let modal = document.getElementById("add-modal");
// 		showModal(modal);
// 	});
// 	return div;
// }

// function generateCategoryCards(i, modal) {
// 	Object.values(inventory[i]).forEach((card) => {
// 		for (let i = 0; i < card.length; i++) {
// 			let div = document.createElement("div");
// 			let text = Object.keys(card[i]).toString();
// 			let q = document.createTextNode(text);
// 			div.appendChild(q);
// 			displayInventory = { [text]: [...Object.values(card[i])] };
// 			let length = Object.values(card[i])[0].length;
// 			let answers = Object.values(card[i])[0];
// 			for (let j = 0; j < length; j++) {
// 				let a = Object.keys(answers[j]).toString();
// 				let small = document.createElement("small");
// 				small.appendChild(document.createTextNode(a));
// 				div.classList.add("grid-item");
// 				div.id = i;
// 				div.appendChild(small);
// 				div.addEventListener("click", editCard);
// 			}
// 			modal.appendChild(div);
// 		}
// 		let div = generateAddCard();
// 		modal.appendChild(div);
// 	});
// }

// function editCard(e) {
// 	let answers = [...[...Object.values(displayInventory)[0]]][0];
// 	answers.forEach((answer) => {
// 		e.currentTarget.innerHTML = "";
// 		let modal = editCardModal();
// 		e.currentTarget.appendChild(modal);
// 		let input = document.createElement("input");
// 		input.value = Object.values(answer).toString();
// 		e.currentTarget.appendChild(input);
// 	});
// }

// function editCardModal() {
// 	let modal = document.createElement("div");
// 	modal.classList.add("edit-card-modal");
// 	return modal;
// }

// function saveCard() {}

// function deleteCard(e) {
// 	console.log(displayInventory[e.target.id]);
// }

// function showModal(modal) {
// 	modal.classList.remove("display-none");
// }

// function hideModal(modal) {
// 	modal.classList.add("display-none");
// }

let isCategory = false;
let currentCategory = [];
let entries;

// Add card modal
const openModal = document.getElementById("add-fab");
openModal.addEventListener("click", () => {
	let modal = document.createElement("div");
	let form = document.createElement("form");
	form.classList.add("add-form");
	modal.classList.add("add-modal");
	showModal(modal, form);
>>>>>>> eeb2c414ac6d424a7684d2f2af251a4efd5801fc
});

// Exit modal button
const exitModal = document.getElementById("exit-modal");

window.onload = () => {
<<<<<<< HEAD
	currentCategory = JSON.parse(localStorage.getItem("category") || "[]");
=======
	currentCategory = JSON.parse(localStorage.getItem("category") || "{}");
>>>>>>> eeb2c414ac6d424a7684d2f2af251a4efd5801fc
	setup();
};

function setup() {
	getEntries();
	if (isCategory) {
		renderCategory();
	} else {
		renderCategories();
	}
}

function getEntries() {
<<<<<<< HEAD
	entries = Object.entries(
		JSON.parse(localStorage.getItem("inventory") || "{}")
	);
}

function showModal(modal) {
	let exit = document.createElement("div");
	exit.classList.add("exit-modal");
	let li = document.createElement("li");
	li.style = "cursor: pointer; color: white;";
	li.addEventListener("click", removeModal);
	li.classList.add("fa-solid", "fa-x");
	exit.appendChild(li);

	let form = document.createElement("form");
	form.classList.add("add-form");

	if (!isCategory) {
		let input = document.createElement("input");
		input.classList.add("form-control");
		input.id = "add-form-input";
		let label = document.createElement("label");
		label.htmlFor = input.id;
		label.innerText = "Category: ";
		label.appendChild(input);
		let addBtn = document.createElement("button");
		addBtn.textContent = "Add";
		addBtn.classList.add("btn");
		addBtn.addEventListener("click", addCardToInventory);
		form.appendChild(label);
		form.appendChild(addBtn);
	} else {
		let input = document.createElement("input");
		input.classList.add("form-control");
		input.id = "question";
		let label = document.createElement("label");
		label.htmlFor = input.id;
		label.innerText = "Question: ";
		label.appendChild(input);
		form.appendChild(label);
		for (let i = 0; i < 3; i++) {
			let inputGroup = document.createElement("div");
			inputGroup.classList.add("input-group");
			let answer = document.createElement("input");
			answer.classList.add("form-control");
			answer.id = i;
			let label = document.createElement("label");
			label.htmlFor = answer.id;
			label.innerText = "Answer #" + (i + 1);
			let _true = document.createElement("input");
			let _false = document.createElement("input");
			_true.type = _false.type = "radio";
			_true.name = _false.name = i;
			inputGroup.appendChild(answer);
			inputGroup.appendChild(_true);
			inputGroup.appendChild(_false);
			label.appendChild(inputGroup);
			form.appendChild(label);
		}
		let addBtn = document.createElement("button");
		addBtn.textContent = "Add";
		addBtn.classList.add("btn");
		addBtn.addEventListener("click", addCardToCategory);
		form.appendChild(addBtn);
		console.log(form);
	}

	modal.appendChild(exit);
	modal.appendChild(form);
	document.body.appendChild(modal);
}

function removeModal() {
	let modal = document.getElementsByClassName("add-modal")[0];
	document.body.removeChild(modal);
}

function addCardToInventory(e) {
	let formInput = document.getElementById("add-form-input").value;
	if (formInput != "") {
		let inventory = JSON.parse(localStorage.getItem("inventory") || "[]");
		inventory.push({ [formInput]: [] });
		localStorage.setItem("inventory", JSON.stringify(inventory));
	} else {
		e.preventDefault();
		if (e.target.parentNode.getElementsByTagName("small")[0]) {
			return;
		}
		let error = document.createElement("small");
		error.classList.add("error");
		error.textContent = "Card category should not be empty!";
		e.target.parentNode.appendChild(error);
	}
	renderCards();
}

function addCardToCategory(e) {
	e.preventDefault();
	let question = document.getElementById("question").value;
	if (question == "") {
		if (e.target.parentNode.getElementsByTagName("small")[0]) {
			return;
		}
		let error = document.createElement("small");
		error.classList.add("error");
		error.textContent = "Question should not be an empty string!";
		e.target.parentNode.appendChild(error);
		return;
	}
	let el = e.target.parentNode.getElementsByTagName("small")[0];
	if (el) {
		e.target.parentNode.removeChild(el);
	}
	let answers = [];
	let inputs = document.getElementsByClassName("form-control");
	for (let i = 0; i < 3; i++) {
		let answer = inputs[i + 1].value;
		let trueFalse = document.getElementsByName(i);
		let validity;
		if (!trueFalse[0].checked && !trueFalse[1].checked) {
			let error = document.createElement("small");
			error.classList.add("error");
			error.textContent = "Answer is whether true or false!";
			e.target.parentNode.appendChild(error);
			return;
		} else if (trueFalse[0].checked) {
			validity = "true";
		} else {
			validity = "false";
		}
		answers.push({ [answer]: validity });
	}

	Object.values(currentCategory)[0].push({ [question]: answers });
	localStorage.setItem("category", JSON.stringify(currentCategory));
	let inventory = JSON.parse(localStorage.getItem("inventory"));
	inventory[currentCategoryId] = currentCategory;
	localStorage.setItem("inventory", JSON.stringify(inventory));
	renderCategory();
}

function renderCategory() {
	isCategory = true;
	let cards = document.getElementById("cards");

	if (cards.childElementCount > 0) {
		removeChildrenFromDOM(cards);
	}

	if (cards.classList.contains("display-none")) {
		cards.classList.remove("display-none");
	}

	createBackButton();

	let noOfQuestions = Object.values(currentCategory)[0].length;
	if (
		!noOfQuestions &&
		!document.body.getElementsByClassName("zero-cards")[0]
	) {
		let div = document.createElement("div");
		div.classList.add("zero-cards");
		let textNode = document.createTextNode(
			"There are zero cards in this category!"
		);
		div.appendChild(textNode);
		cards.appendChild(div);
	} else {
		let zero = document.getElementsByClassName("zero-cards")[0];
		if (zero) {
			cards.removeChild(zero);
		}
		Object.values(currentCategory).forEach((card) => {
			Object.values(card).forEach((obj, i) => {
				const [question, answers] = Object.entries(obj)[0];
				let card = document.createElement("div");
				card.id = i;
				card.classList.add("grid-item");
				card.addEventListener("click", (e) => {
					let id = e.currentTarget.id;
					console.log(currentCategory);
					Object.values(currentCategory)[0].splice(id, 1);
					localStorage.setItem(
						"category",
						JSON.stringify(currentCategory)
					);
					let inventory = JSON.parse(
						localStorage.getItem("inventory")
					);
					inventory[currentCategoryId] = currentCategory;
					localStorage.setItem(
						"inventory",
						JSON.stringify(inventory)
					);
					renderCategory();
				});
				let p = document.createElement("p");
				p.textContent = question;
				p.style = "text-align: center;";
				card.appendChild(p);

				console.log(Object.values(answers));
				answers.forEach((answer) => {
					let [a, v] = Object.entries(answer)[0];
					let small = document.createElement("small");
					small.textContent = a;
					if (v == "true") {
						small.style = "color: green";
					} else {
						small.style = "color: red";
					}
					card.appendChild(small);
				});
				cards.appendChild(card);
			});
		});
	}
}

function createBackButton() {
	let backBtn = document.createElement("button");
	backBtn.classList.add("back-btn");
	let li = document.createElement("li");
	li.classList.add("fa-solid", "fa-arrow-left");
	backBtn.append(li);
	backBtn.addEventListener("click", () => {
		document.body.removeChild(backBtn);
		let cards = document.getElementById("cards");
		renderCategories();
		removeChildrenFromDOM(cards);
	});
	document.body.appendChild(backBtn);
}

function renderCategories() {
	isCategory = false;
	let categories = document.getElementById("categories");

	if (categories.classList.contains("display-none")) {
		categories.classList.remove("display-none");
	}

	entries.forEach((entry) => {
		let card = document.createElement("div");
		card.classList.add("grid-item");
		card.addEventListener("click", () => {
			currentCategoryId = entry[0];
			currentCategory = Object.values(entry)[1];
			localStorage.setItem("category", JSON.stringify(currentCategory));
			categories.classList.add("display-none");
			renderCategory();
			removeChildrenFromDOM(categories);
		});
		let p = document.createElement("h2");
		p.textContent = Object.keys(entry[1]).toString();
		let small = document.createElement("small");
		small.textContent =
			"No. of cards: " + Object.values(entry[1])[0].length;
		card.appendChild(p);
		card.appendChild(small);
		categories.appendChild(card);
	});
}

function removeChildrenFromDOM(parent) {
	while (parent.firstChild) {
		parent.removeChild(parent.firstChild);
	}
=======
	entries = Object.entries(JSON.parse(localStorage.getItem("inventory") || "{}"));
}

function showModal(modal, form, isCategory) {
	let exit = document.createElement("div");
	exit.classList.add("exit-modal");
	let li = document.createElement("li");
	li.style = "cursor: pointer; color: white;";
	li.addEventListener("click", removeModal);
	li.classList.add("fa-solid", "fa-x");
	exit.appendChild(li);

	if (!isCategory) {
		let input = document.createElement("input");
		input.classList.add("form-control");
		input.id = "add-form-input";
		let addBtn = document.createElement("button");
		addBtn.textContent = "Add";
		addBtn.classList.add("btn");
		addBtn.addEventListener("click", addCardToInventory);
		form.appendChild(input);
		form.appendChild(addBtn);
	} else {
		let inputGroup = document.createElement("div");
		inputGroup.classList.add("input-group");
		for (let i = 0; i < 3; i++) {
			let answer = document.createElement("input");
			answer.classList.add("form-control");
			answer.id = i;
			let _true = document.createElement("radio");
			let _false = document.createElement("radio");
			_true.id = _false.id = answer;
			inputGroup.appendChild(answer);
			inputGroup.appendChild( _true);
			inputGroup.appendChild(_false);
			form.appendChild(inputGroup);
		}
		let addBtn = document.createElement("button");
		addBtn.textContent = "Add";
		addBtn.classList.add("btn");
		addBtn.addEventListener("click", addCardToCategory);
	}

	modal.appendChild(exit);
	modal.appendChild(form);
	document.body.appendChild(modal);
}

function removeModal() {
	let modal = document.getElementsByClassName("add-modal")[0];
	document.body.removeChild(modal);
}

function addCardToInventory(e) {
	let formInput = document.getElementById("add-form-input").value;
	if (formInput != "") {
		let inventory = JSON.parse(localStorage.getItem("inventory") || "[]");
		inventory.push({ [formInput]: [] });
		localStorage.setItem("inventory", JSON.stringify(inventory));
	} else {
		e.preventDefault();
		if (e.target.parentNode.getElementsByTagName("small")[0]) {
			return;
		}
		let error = document.createElement("small");
		error.classList.add("error");
		error.textContent = "Card category should not be empty!";
		e.target.parentNode.appendChild(error);
	}
	renderCards();
}

function addCardToCategory(e) {
	let id;
	let category = JSON.parse(localStorage.getItem("inventory" || "[]"));
	e.preventDefault();
	let formInput = document.getElementById("add-form-input").value;
	if (formInput != "") {
		let storage = JSON.parse(localStorage.getItem("inventory") || "[]");
		storage.push({ [formInput]: [] });
		localStorage.setItem("inventory", JSON.stringify(storage));
	} else {
		e.preventDefault();
		if (e.target.parentNode.getElementsByTagName("small")[0]) {
			return;
		}
		let error = document.createElement("small");
		error.classList.add("error");
		error.textContent = "Card category should not be empty!";
		e.target.parentNode.appendChild(error);
	}
}

function renderCategory() {
	isCategory = true;
	let cards = document.getElementById("cards");

	if (cards.classList.contains("display-none")) {
		cards.classList.remove("display-none");
	}

	createBackButton();

	let noOfQuestions = Object.values(currentCategory)[0].length;
	if (!noOfQuestions && !document.body.getElementsByClassName("zero-cards")[0]) {
		let div = document.createElement("div");
		div.classList.add("zero-cards");
		let textNode = document.createTextNode("There are zero cards in this category!");
		div.appendChild(textNode);
		cards.appendChild(div);
	}
	Object.values(currentCategory).forEach((q) => { console.log(q) });
}

function createBackButton() {
	let backBtn = document.createElement("button");
	backBtn.classList.add("back-btn");
	let li = document.createElement("li");
	li.classList.add("fa-solid", "fa-arrow-left");
	backBtn.append(li);
	backBtn.addEventListener("click", () => { 
		document.body.removeChild(backBtn);
		let cards = document.getElementById("cards");
		cards.getChildern
		renderCategories();
	 });
	document.body.appendChild(backBtn);
>>>>>>> eeb2c414ac6d424a7684d2f2af251a4efd5801fc
}

function renderCategories() {
	isCategory = false;
	let categories = document.getElementById("categories");
	
	if (categories.classList.contains("display-none")) {
		categories.classList.remove("display-none");
	}

	entries.forEach(entry => {
		let card = document.createElement("div");
		card.classList.add("grid-item");
		card.addEventListener("click", () => {
			currentCategory = Object.values(entry)[1];
			localStorage.setItem("category", JSON.stringify(currentCategory));
			categories.classList.add("display-none");
			renderCategory(currentCategory);
		})
		let p = document.createElement("h2");
		p.textContent = Object.keys(entry[1]).toString();
		let small = document.createElement("small");
		small.textContent = "No. of cards: " + Object.values(entry[1])[0].length;
		card.appendChild(p);
		card.appendChild(small);
		categories.appendChild(card);
	});
}